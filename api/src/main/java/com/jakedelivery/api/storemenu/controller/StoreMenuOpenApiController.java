package com.jakedelivery.api.storemenu.controller;

import com.jakedelivery.api.storemenu.business.StoreMenuBusiness;
import com.jakedelivery.api.storemenu.dto.StoreMenuRegisterRequest;
import com.jakedelivery.api.storemenu.dto.StoreMenuResponse;
import com.jakedelivery.common.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/open-api/store-menu")
@RestController
public class StoreMenuOpenApiController {
    private final StoreMenuBusiness storeMenuBusiness;

    @PostMapping("/register")
    public Api<StoreMenuResponse> register(@Valid @RequestBody Api<StoreMenuRegisterRequest> request) {
        var response = storeMenuBusiness.register(request.getBody());

        return Api.OK(response);
    }
}
