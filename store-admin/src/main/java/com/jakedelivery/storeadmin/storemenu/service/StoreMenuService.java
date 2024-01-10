package com.jakedelivery.storeadmin.storemenu.service;

import com.jakedelivery.db._common.constant.StoreMenuStatus;
import com.jakedelivery.db.storemenu.StoreMenuEntity;
import com.jakedelivery.db.storemenu.StoreMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreMenuService {
    private final StoreMenuRepository storeMenuRepository;

    public StoreMenuEntity getStoreMenuWithThrow(Long id) {
        return storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED)
                .orElseThrow(() -> new RuntimeException("Store menu not found"));
    }
}
