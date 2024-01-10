package com.jakedelivery.storeadmin.storeuser.controller;

import com.jakedelivery.storeadmin._core.authorization.UserSession;
import com.jakedelivery.storeadmin.storeuser.converter.StoreUserConverter;
import com.jakedelivery.storeadmin.storeuser.dto.StoreUserResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/store-user")
@RestController
public class StoreUserApiController {
    private final StoreUserConverter storeUserConverter;

    @GetMapping("/me")
    public StoreUserResponse me(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
        return storeUserConverter.toResponse(userSession);
    }
}
