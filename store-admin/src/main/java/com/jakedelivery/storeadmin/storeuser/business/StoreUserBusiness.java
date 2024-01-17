package com.jakedelivery.storeadmin.storeuser.business;

import com.jakedelivery.db._common.constant.StoreStatus;
import com.jakedelivery.db.store.StoreRepository;
import com.jakedelivery.storeadmin._core.common.annotation.Business;
import com.jakedelivery.storeadmin.storeuser.converter.StoreUserConverter;
import com.jakedelivery.storeadmin.storeuser.dto.StoreUserRegisterRequest;
import com.jakedelivery.storeadmin.storeuser.dto.StoreUserResponse;
import com.jakedelivery.storeadmin.storeuser.service.StoreUserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class StoreUserBusiness {
    private final StoreUserConverter storeUserConverter;
    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository; // TODO: Service 로 변경하기

    public StoreUserResponse register(
            StoreUserRegisterRequest request
    ) {
        var storeEntity = Optional.ofNullable(storeRepository.findFirstByNameAndStatusOrderByIdDesc(request.getStoreName(), StoreStatus.REGISTERED));
        var entity = storeUserConverter.toEntity(request, storeEntity.get()); // TODO: Null point error check
        var newEntity = storeUserService.register(entity);

        return storeUserConverter.toResponse(newEntity, storeEntity.get());
    }
}
