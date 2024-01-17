package com.jakedelivery.api.userorder.business;

import com.jakedelivery.api.store.converter.StoreConverter;
import com.jakedelivery.api.store.service.StoreService;
import com.jakedelivery.api.storemenu.converter.StoreMenuConverter;
import com.jakedelivery.api.storemenu.service.StoreMenuService;
import com.jakedelivery.api.user.model.User;
import com.jakedelivery.api.userorder.converter.UserOrderConverter;
import com.jakedelivery.api.userorder.dto.UserOrderRequest;
import com.jakedelivery.api.userorder.dto.UserOrderResponse;
import com.jakedelivery.api.userorder.dto.response.UserOrderDetailResponse;
import com.jakedelivery.api.userorder.producer.UserOrderProducer;
import com.jakedelivery.api.userorder.service.UserOrderService;
import com.jakedelivery.api.userordermenu.converter.UserOrderMenuConverter;
import com.jakedelivery.api.userordermenu.service.UserOrderMenuService;
import com.jakedelivery.common.annotation.Business;
import com.jakedelivery.db._common.constant.UserOrderMenuStatus;
import com.jakedelivery.db.userordermenu.UserOrderMenuEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Business
public class UserOrderBusiness {
    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;
    private final StoreService storeService;
    private final StoreConverter storeConverter;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final UserOrderMenuConverter userOrderMenuConverter;
    private final UserOrderMenuService userOrderMenuService;
    private final UserOrderProducer userOrderProducer;

    // 1. 사용자, 메뉴 id
    // 2. userOrder 생성
    // 3. userOrderMenu 생성
    // 4. 응답 생성
    public UserOrderResponse userOrder(User user, UserOrderRequest request) {
        var storeEntity = storeService.getStoreWithThrow(request.getStoreId());

        var storeMenuEntities = request.getStoreMenuIds().stream()
                .map(storeMenuService::getStoreMenuWithThrow)
                .collect(Collectors.toList());

        var userOrderEntity = userOrderConverter.toEntity(user, storeEntity, storeMenuEntities);

        // 주문
        var newUserOrderEntity = userOrderService.order(userOrderEntity);

        // 맵핑
        var userOrderMenuEntities = storeMenuEntities.stream()
                .map(it -> {
                    // menu + user order
                    return userOrderMenuConverter.toEntity(newUserOrderEntity, it);
                })
                .collect(Collectors.toList());

        userOrderMenuEntities.forEach(userOrderMenuService::order);

        // 비동기로 가맹점에 주문 날리기!
        userOrderProducer.sendOrder(newUserOrderEntity);

        return userOrderConverter.toResponse(newUserOrderEntity);
    }

    public List<UserOrderDetailResponse> current(User user) {
        var userOrderEntities = userOrderService.current(user.getId());

        // 주문 1건씩 처리

        return userOrderEntities.stream().map(userOrderEntity -> {
            // 사용자가 주문한 메뉴
            var userOrderMenuEntities = userOrderEntity.getUserOrderMenuEntities().stream()
                    .filter(it -> it.getStatus().equals(UserOrderMenuStatus.REGISTERED))
                    .collect(Collectors.toList());

            var storeMenuEntities = userOrderMenuEntities.stream()
                    .map(UserOrderMenuEntity::getStoreMenu)
                    .collect(Collectors.toList());

            // 사용자가 주문한 스토어
            var storeEntity = userOrderEntity.getStore();

            return UserOrderDetailResponse.builder()
                    .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                    .storeMenuResponses(storeMenuConverter.toResponse(storeMenuEntities))
                    .storeResponse(storeConverter.toResponse(storeEntity))
                    .build();
        }).collect(Collectors.toList());
    }

    public List<UserOrderDetailResponse> history(User user) {
        var userOrderEntities = userOrderService.history(user.getId());

        // 주문 1건씩 처리
        return userOrderEntities.stream().map(userOrderEntity -> {
            // 사용자가 주문한 메뉴
            var userOrderMenuEntities = userOrderEntity.getUserOrderMenuEntities().stream()
                    .filter(it -> it.getStatus().equals(UserOrderMenuStatus.REGISTERED))
                    .collect(Collectors.toList());

            var storeMenuEntities = userOrderMenuEntities.stream()
                    .map(UserOrderMenuEntity::getStoreMenu)
                    .collect(Collectors.toList());

            // 사용자가 주문한 스토어
            var storeEntity = userOrderEntity.getStore();

            return UserOrderDetailResponse.builder()
                    .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                    .storeMenuResponses(storeMenuConverter.toResponse(storeMenuEntities))
                    .storeResponse(storeConverter.toResponse(storeEntity))
                    .build();
        }).collect(Collectors.toList());
    }

    public UserOrderDetailResponse read(User user, Long orderId) {
        var userOrderEntity = userOrderService.getUserOrderWithOutStatusWithThrow(orderId, user.getId());

        // 사용자가 주문한 메뉴
        var userOrderMenuEntities = userOrderEntity.getUserOrderMenuEntities().stream()
                .filter(it -> it.getStatus().equals(UserOrderMenuStatus.REGISTERED))
                .collect(Collectors.toList());

        var storeMenuEntities = userOrderMenuEntities.stream()
                .map(UserOrderMenuEntity::getStoreMenu)
                .collect(Collectors.toList());

        // 사용자가 주문한 스토어
        var storeEntity = userOrderEntity.getStore();

        return UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                .storeMenuResponses(storeMenuConverter.toResponse(storeMenuEntities))
                .storeResponse(storeConverter.toResponse(storeEntity))
                .build();
    }
}
