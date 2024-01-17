package com.jakedelivery.storeadmin.userorder.business;

import com.jakedelivery.message.model.UserOrderMessage;
import com.jakedelivery.storeadmin._core.common.annotation.Business;
import com.jakedelivery.storeadmin.sse.connection.model.SseConnectionPool;
import com.jakedelivery.storeadmin.storemenu.converter.StoreMenuConverter;
import com.jakedelivery.storeadmin.storemenu.service.StoreMenuService;
import com.jakedelivery.storeadmin.userorder.converter.UserOrderConverter;
import com.jakedelivery.storeadmin.userorder.dto.UserOrderDetailResponse;
import com.jakedelivery.storeadmin.userorder.service.UserOrderService;
import com.jakedelivery.storeadmin.userordermenu.service.UserOrderMenuService;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class UserOrderBusiness {
    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final SseConnectionPool sseConnectionPool;

    public void pushUserOrder(UserOrderMessage userOrderMessage) {
        // 1. 주문
        // 2. 주문 내역찾기
        var userOrderEntity = userOrderService.getUserOrder(userOrderMessage.getUserOrderId())
                .orElseThrow(() -> new RuntimeException("사용자 주문내역 없음"));

        // 3. 스토어 찾기
        // 4. 연결된 세션 찾아서
        var userConnection = sseConnectionPool.getSession(userOrderEntity.getStore().getId().toString());

        // user order entity
        // user order menu
        var userOrderMenuList = userOrderMenuService.getUserOrderMenuList(userOrderEntity.getId());

        // user order menu -> store menu
        var storeMenuResponses = userOrderMenuList.stream()
                .map(it -> storeMenuService.getStoreMenuWithThrow(it.getStoreMenu().getId()))
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());

        var userOrderResponse = userOrderConverter.toResponse(userOrderEntity);

        // response
        var push = UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderResponse)
                .storeMenuResponses(storeMenuResponses)
                .build();

        // 주문 메뉴, 가격, 상태
        // 5. 스토어 관리자에게 push
        userConnection.sendMessage(push);
    }
}
