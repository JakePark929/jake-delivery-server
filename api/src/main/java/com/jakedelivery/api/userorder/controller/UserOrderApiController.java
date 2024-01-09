package com.jakedelivery.api.userorder.controller;

import com.jakedelivery.api._core.common.Api;
import com.jakedelivery.api._core.common.annotation.UserSession;
import com.jakedelivery.api.user.model.User;
import com.jakedelivery.api.userorder.business.UserOrderBusiness;
import com.jakedelivery.api.userorder.dto.UserOrderRequest;
import com.jakedelivery.api.userorder.dto.UserOrderResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/user-order")
@RestController
public class UserOrderApiController {
    private final UserOrderBusiness userOrderBusiness;

    // 사용자 주문
    @PostMapping("")
    public Api<UserOrderResponse> userOrder(
            @Valid @RequestBody Api<UserOrderRequest> userOrderRequest,
            @Parameter(hidden = true) @UserSession User user
    ) {
        var response = userOrderBusiness.userOrder(user, userOrderRequest.getBody());

        return Api.OK(response);
    }
}
