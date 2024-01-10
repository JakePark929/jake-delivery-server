package com.jakedelivery.storeadmin.storeuser.controller;

import com.jakedelivery.storeadmin.storeuser.business.StoreUserBusiness;
import com.jakedelivery.storeadmin.storeuser.dto.StoreUserRegisterRequest;
import com.jakedelivery.storeadmin.storeuser.dto.StoreUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/open-api/store-user")
@RestController
public class StoreUserOpenApiController {
    private final StoreUserBusiness storeUserBusiness;

    @PostMapping()
    public StoreUserResponse register(@Valid @RequestBody StoreUserRegisterRequest request) {
        return storeUserBusiness.register(request);
    }
}
