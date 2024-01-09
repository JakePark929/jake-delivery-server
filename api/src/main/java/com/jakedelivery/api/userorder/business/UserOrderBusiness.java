package com.jakedelivery.api.userorder.business;

import com.jakedelivery.api._core.common.annotation.Business;
import com.jakedelivery.api.storemenu.service.StoreMenuService;
import com.jakedelivery.api.user.model.User;
import com.jakedelivery.api.userorder.converter.UserOrderConverter;
import com.jakedelivery.api.userorder.dto.UserOrderRequest;
import com.jakedelivery.api.userorder.dto.UserOrderResponse;
import com.jakedelivery.api.userorder.service.UserOrderService;
import com.jakedelivery.api.userordermenu.converter.UserOrderMenuConverter;
import com.jakedelivery.api.userordermenu.service.UserOrderMenuService;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class UserOrderBusiness {
    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;
    private final StoreMenuService storeMenuService;
    private final UserOrderMenuConverter userOrderMenuConverter;
    private final UserOrderMenuService userOrderMenuService;

    // 1. 사용자, 메뉴 id
    // 2. userOrder 생성
    // 3. userOrderMenu 생성
    // 4. 응답 생성
    public UserOrderResponse userOrder(User user, UserOrderRequest request) {
        var storeMenuEntities = request.getStoreMenuIds().stream()
                .map(storeMenuService::getStoreMenuWithThrow)
                .collect(Collectors.toList());

        var userOrderEntity = userOrderConverter.toEntity(user, storeMenuEntities);

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

        return userOrderConverter.toResponse(newUserOrderEntity);
    }
}
