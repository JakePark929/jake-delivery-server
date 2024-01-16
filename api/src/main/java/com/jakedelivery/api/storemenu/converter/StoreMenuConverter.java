package com.jakedelivery.api.storemenu.converter;

import com.jakedelivery.api.storemenu.dto.StoreMenuRegisterRequest;
import com.jakedelivery.api.storemenu.dto.StoreMenuResponse;
import com.jakedelivery.common.annotation.Converter;
import com.jakedelivery.common.error.ErrorCode;
import com.jakedelivery.common.exception.ApiException;
import com.jakedelivery.db.storemenu.StoreMenuEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<StoreMenuResponse> toResponse(List<StoreMenuEntity> entities) {
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
