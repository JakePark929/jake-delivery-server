package com.jakedelivery.api.userordermenu.converter;

import com.jakedelivery.common.annotation.Converter;
import com.jakedelivery.db.storemenu.StoreMenuEntity;
import com.jakedelivery.db.userorder.UserOrderEntity;
import com.jakedelivery.db.userordermenu.UserOrderMenuEntity;

@Converter
public class UserOrderMenuConverter {
    public UserOrderMenuEntity toEntity(UserOrderEntity userOrderEntity, StoreMenuEntity storeMenuEntity) {
        return UserOrderMenuEntity.builder()
                .userOrderId(userOrderEntity.getId())
                .storeMenuId(storeMenuEntity.getId())
                .build();
    }
}
