package com.jakedelivery.api.storemenu.business;

import com.jakedelivery.api.store.service.StoreService;
import com.jakedelivery.api.storemenu.converter.StoreMenuConverter;
import com.jakedelivery.api.storemenu.dto.StoreMenuRegisterRequest;
import com.jakedelivery.api.storemenu.dto.StoreMenuResponse;
import com.jakedelivery.api.storemenu.service.StoreMenuService;
import com.jakedelivery.common.annotation.Business;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class StoreMenuBusiness {
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;
    private final StoreService storeService;

    public List<StoreMenuResponse> search(Long storeId) {
        var list = storeMenuService.getStoreMenuByStoreId(storeId);

        return list.stream()
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());
    }

    public StoreMenuResponse register(StoreMenuRegisterRequest request) {
        var storeEntity = storeService.getStoreWithThrow(request.getStoreId());

        var entity = storeMenuConverter.toEntity(storeEntity, request);
        var newEntity = storeMenuService.register(entity);

        return storeMenuConverter.toResponse(newEntity);
    }
}
