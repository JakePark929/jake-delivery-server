package com.jakedelivery.storeadmin.storemenu.converter;

import com.jakedelivery.db.storemenu.StoreMenuEntity;
import com.jakedelivery.storeadmin._core.common.annotation.Converter;
import com.jakedelivery.storeadmin.storemenu.dto.StoreMenuResponse;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {
    public StoreMenuResponse toResponse(StoreMenuEntity storeMenuEntity) {
        return StoreMenuResponse.builder()
                .id(storeMenuEntity.getId())
                .name(storeMenuEntity.getName())
                .amount(storeMenuEntity.getAmount())
                .status(storeMenuEntity.getStatus())
                .thumbnailUrl(storeMenuEntity.getThumbnailUrl())
                .likeCount(storeMenuEntity.getLikeCount())
                .sequence(storeMenuEntity.getSequence())
                .build();
    }

    public List<StoreMenuResponse> toResponse(List<StoreMenuEntity> storeMenuEntities) {
        return storeMenuEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
