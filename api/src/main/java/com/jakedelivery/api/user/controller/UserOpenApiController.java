package com.jakedelivery.api.user.controller;

import com.jakedelivery.api._core.common.Api;
import com.jakedelivery.api.user.business.UserBusiness;
import com.jakedelivery.api.user.dto.UserResponse;
import com.jakedelivery.api.user.dto.request.UserLoginRequest;
import com.jakedelivery.api.user.dto.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/open-api/user")
@RestController
public class UserOpenApiController {
    private final UserBusiness userBusiness;

    // 사용자 가입 요청
    @PostMapping("/register")
    public Api<UserResponse> register(
            @Valid
            @RequestBody Api<UserRegisterRequest> request
    ) {
        var response = userBusiness.register(request.getBody());
        return Api.OK(response);
    }

    // 로그인
    @PostMapping("/login")
    public Api<UserResponse> login(
            @Valid
            @RequestBody Api<UserLoginRequest> request
    ) {
        var response = userBusiness.login(request.getBody());
        return Api.OK(response);
    }
}
