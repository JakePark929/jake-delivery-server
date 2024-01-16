package com.jakedelivery.api.user.controller;

import com.jakedelivery.api.user.business.UserBusiness;
import com.jakedelivery.api.user.dto.UserResponse;
import com.jakedelivery.api.user.model.User;
import com.jakedelivery.common.Api;
import com.jakedelivery.common.annotation.UserSession;
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
    public Api<UserResponse> me(@UserSession User user) {
        var response = userBusiness.me(user);
        return Api.OK(response);
    }
}
