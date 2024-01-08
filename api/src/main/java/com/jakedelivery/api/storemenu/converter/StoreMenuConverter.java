package com.jakedelivery.api.storemenu.converter;

import com.jakedelivery.api._core.common.annotation.Converter;
import com.jakedelivery.api._core.common.error.ErrorCode;
import com.jakedelivery.api._core.common.exception.ApiException;
import com.jakedelivery.api.storemenu.dto.StoreMenuRegisterRequest;
import com.jakedelivery.api.storemenu.dto.StoreMenuResponse;
import com.jakedelivery.db.storemenu.StoreMenuEntity;

import java.util.Optional;

@Converter
public class StoreMenuConverter {
    public StoreMenuEntity toEntity(StoreMenuRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> StoreMenuEntity.builder()
                        .storeId(request.getStoreId())
                        .name(request.getName())
                        .amount(request.getAmount())
                        .thumbnailUrl(request.getThumbnailUrl())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreMenuResponse toResponse(StoreMenuEntity storeMenuEntity) {
        return Optional.ofNullable(storeMenuEntity)
                .map(it -> StoreMenuResponse.builder()
                        .id(storeMenuEntity.getId())
                        .storeId(storeMenuEntity.getStoreId())
                        .name(storeMenuEntity.getName())
                        .amount(storeMenuEntity.getAmount())
                        .status(storeMenuEntity.getStatus())
                        .thumbnailUrl(storeMenuEntity.getThumbnailUrl())
                        .likeCount(storeMenuEntity.getLikeCount())
                        .sequence(storeMenuEntity.getSequence())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
