package com.jakedelivery.api.store.business;

import com.jakedelivery.api._core.common.annotation.Business;
import com.jakedelivery.api.store.converter.StoreConverter;
import com.jakedelivery.api.store.dto.StoreRegisterRequest;
import com.jakedelivery.api.store.dto.StoreResponse;
import com.jakedelivery.api.store.service.StoreService;
import com.jakedelivery.db._common.constant.StoreCategory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class StoreBusiness {
    private final StoreService storeService;
    private final StoreConverter storeConverter;

    public StoreResponse register(StoreRegisterRequest storeRegisterRequest) {
        var entity = storeConverter.toEntity(storeRegisterRequest);
        var newEntity = storeService.register(entity);

        return storeConverter.toResponse(newEntity);
    }

    public List<StoreResponse> searchCategory(StoreCategory storeCategory) {
        var storeList =  storeService.searchByCategory(storeCategory);

        return storeList.stream()
                .map(storeConverter::toResponse)
                .collect(Collectors.toList());
    }
}
