package com.jakedelivery.api.store.controller;

import com.jakedelivery.api._core.common.Api;
import com.jakedelivery.api.store.business.StoreBusiness;
import com.jakedelivery.api.store.dto.StoreRegisterRequest;
import com.jakedelivery.api.store.dto.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/open-api/store")
@RestController
public class StoreOpenApiController {
    private final StoreBusiness storeBusiness;

    @PostMapping("/register")
    public Api<StoreResponse> register(@Valid @RequestBody Api<StoreRegisterRequest> request) {
        var response = storeBusiness.register(request.getBody());

        return Api.OK(response);
    }
}
