package com.jakedelivery.api.user.controller;

import com.jakedelivery.api._core.common.Api;
import com.jakedelivery.api.user.business.UserBusiness;
import com.jakedelivery.api.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserApiController {
    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me() {

        var response = userBusiness.me();

        return Api.OK(response);
    }
}
