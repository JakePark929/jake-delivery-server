package com.jakedelivery.api.user.converter;

import com.jakedelivery.api._core.common.annotation.Converter;
import com.jakedelivery.api._core.common.error.ErrorCode;
import com.jakedelivery.api._core.common.exception.ApiException;
import com.jakedelivery.api.user.dto.UserResponse;
import com.jakedelivery.api.user.dto.request.UserRegisterRequest;
import com.jakedelivery.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {
    public UserEntity toEntity(UserRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> {
                   // request to entity
                   return UserEntity.builder()
                           .name(request.getName())
                           .email(request.getEmail())
                           .password(request.getPassword())
                           .address(request.getAddress())
                           .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest is null"));
    }

    public UserResponse toResponse(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    // entity to response
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .email(userEntity.getEmail())
                            .address(userEntity.getAddress())
                            .status(userEntity.getStatus())
                            .registeredAt(userEntity.getRegisteredAt())
                            .unRegisteredAt(userEntity.getUnRegisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity is null"));
    }
}
