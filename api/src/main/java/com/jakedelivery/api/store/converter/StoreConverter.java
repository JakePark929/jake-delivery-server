package com.jakedelivery.api.store.converter;

import com.jakedelivery.api._core.common.annotation.Converter;
import com.jakedelivery.api._core.common.error.ErrorCode;
import com.jakedelivery.api._core.common.exception.ApiException;
import com.jakedelivery.api.store.dto.StoreRegisterRequest;
import com.jakedelivery.api.store.dto.StoreResponse;
import com.jakedelivery.db.store.StoreEntity;

import java.util.Optional;

@Converter
public class StoreConverter {
    public StoreEntity toEntity(StoreRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> StoreEntity.builder()
                        .name(request.getName())
                        .address(request.getAddress())
                        .category(request.getCategory())
                        .thumbnailUrl(request.getThumbnailUrl())
                        .minimumAmount(request.getMinimumAmount())
                        .minimumDeliveryAmount(request.getMinimumDeliveryAmount())
                        .phoneNumber(request.getPhoneNumber())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreResponse toResponse(StoreEntity entity) {
        return Optional.ofNullable(entity)
                .map(it -> StoreResponse.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .address(entity.getAddress())
                        .status(entity.getStatus())
                        .category(entity.getCategory())
                        .star(entity.getStar())
                        .thumbnailUrl(entity.getThumbnailUrl())
                        .minimumAmount(entity.getMinimumAmount())
                        .minimumDeliveryAmount(entity.getMinimumDeliveryAmount())
                        .phoneNumber(entity.getPhoneNumber())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
