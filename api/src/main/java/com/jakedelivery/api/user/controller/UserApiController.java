package com.jakedelivery.api.user.controller;

import com.jakedelivery.api.user.business.UserBusiness;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserApiController {
    private final UserBusiness userBusiness;
}
