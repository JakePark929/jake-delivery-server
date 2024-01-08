package com.jakedelivery.api.storemenu.controller;

import com.jakedelivery.api._core.common.Api;
import com.jakedelivery.api.storemenu.business.StoreMenuBusiness;
import com.jakedelivery.api.storemenu.dto.StoreMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/store-menu")
@RestController
public class StoreMenuApiController {
    private final StoreMenuBusiness storeMenuBusiness;

    @GetMapping("/search")
    public Api<List<StoreMenuResponse>> search(@RequestParam Long storeId) {
        var response = storeMenuBusiness.search(storeId);

        return Api.OK(response);
    }
}
