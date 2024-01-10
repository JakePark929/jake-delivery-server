package com.jakedelivery.storeadmin.storeuser.converter;

import com.jakedelivery.db.store.StoreEntity;
import com.jakedelivery.db.storeuser.StoreUserEntity;
import com.jakedelivery.storeadmin._core.authorization.UserSession;
import com.jakedelivery.storeadmin._core.common.annotation.Converter;
import com.jakedelivery.storeadmin.storeuser.dto.StoreUserRegisterRequest;
import com.jakedelivery.storeadmin.storeuser.dto.StoreUserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter
public class StoreUserConverter {
    public StoreUserEntity toEntity(
            StoreUserRegisterRequest request,
            StoreEntity storeEntity
    ) {
        return StoreUserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .storeId(storeEntity.getId())
                .build();
    }

    public StoreUserResponse toResponse(StoreUserEntity storeUserEntity, StoreEntity storeEntity) {
        return StoreUserResponse.builder()
                .user(
                        StoreUserResponse.UserResponse.builder()
                                .id(storeUserEntity.getId())
                                .email(storeUserEntity.getEmail())
                                .status(storeUserEntity.getStatus())
                                .role(storeUserEntity.getRole())
                                .registeredAt(storeUserEntity.getRegisteredAt())
                                .unRegisteredAt(storeUserEntity.getUnRegisteredAt())
                                .lastLoginAt(storeUserEntity.getLastLoginAt())
                                .build()
                )
                .store(
                        StoreUserResponse.StoreResponse.builder()
                                .id(storeEntity.getId())
                                .name(storeEntity.getName())
                                .build()
                )
                .build();
    }

    public StoreUserResponse toResponse(UserSession userSession) {
        return StoreUserResponse.builder()
                .user(
                        StoreUserResponse.UserResponse.builder()
                                .id(userSession.getUserId())
                                .email(userSession.getEmail())
                                .status(userSession.getStatus())
                                .role(userSession.getRole())
                                .registeredAt(userSession.getRegisteredAt())
                                .unRegisteredAt(userSession.getUnRegisteredAt())
                                .lastLoginAt(userSession.getLastLoginAt())
                                .build()
                )
                .store(
                        StoreUserResponse.StoreResponse.builder()
                                .id(userSession.getStoreId())
                                .name(userSession.getStoreName())
                                .build()
                )
                .build();
    }
}
