package com.jakedelivery.storeadmin.userorder.converter;

import com.jakedelivery.db.userorder.UserOrderEntity;
import com.jakedelivery.storeadmin._core.common.annotation.Converter;
import com.jakedelivery.storeadmin.userorder.dto.UserOrderResponse;

@Converter
public class UserOrderConverter {
    public UserOrderResponse toResponse(UserOrderEntity userOrderEntity) {
        return UserOrderResponse.builder()
                .id(userOrderEntity.getId())
                .userId(userOrderEntity.getUserId())
                .storeId(userOrderEntity.getStoreId())
                .status(userOrderEntity.getStatus())
                .amount(userOrderEntity.getAmount())
                .orderedAt(userOrderEntity.getOrderedAt())
                .acceptedAt(userOrderEntity.getAcceptedAt())
                .cookingStartedAt(userOrderEntity.getCookingStartedAt())
                .deliveryStartedAt(userOrderEntity.getDeliveryStartedAt())
                .receivedAt(userOrderEntity.getReceivedAt())
                .build();
    }
}
