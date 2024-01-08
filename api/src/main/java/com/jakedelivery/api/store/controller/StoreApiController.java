package com.jakedelivery.api.store.controller;

import com.jakedelivery.api._core.common.Api;
import com.jakedelivery.api.store.business.StoreBusiness;
import com.jakedelivery.api.store.dto.StoreResponse;
import com.jakedelivery.db._common.constant.StoreCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/store")
@RestController
public class StoreApiController {
    private final StoreBusiness storeBusiness;

    @GetMapping("/search")
    public Api<List<StoreResponse>> search(@RequestParam(required = false) StoreCategory storeCategory) {
        var response = storeBusiness.searchCategory(storeCategory);

        return Api.OK(response);
    }
}
