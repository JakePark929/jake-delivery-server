package com.jakedelivery.api.userorder.converter;

import com.jakedelivery.api._core.common.annotation.Converter;
import com.jakedelivery.api.user.model.User;
import com.jakedelivery.api.userorder.dto.response.UserOrderResponse;
import com.jakedelivery.db.storemenu.StoreMenuEntity;
import com.jakedelivery.db.userorder.UserOrderEntity;

import java.math.BigDecimal;
import java.util.List;

@Converter
public class UserOrderConverter {
    public UserOrderEntity toEntity(User user, Long storeId, List<StoreMenuEntity> storeMenuEntities) {
        var totalAmount = storeMenuEntities.stream()
                .map(StoreMenuEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return UserOrderEntity.builder()
                .userId(user.getId())
                .storeId(storeId)
                .amount(totalAmount)
                .build();
    }

    public UserOrderResponse toResponse(UserOrderEntity userOrderEntity) {
        return UserOrderResponse.builder()
                .id(userOrderEntity.getId())
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
