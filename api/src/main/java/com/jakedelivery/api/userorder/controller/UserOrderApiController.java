package com.jakedelivery.api.userorder.controller;

import com.jakedelivery.api.user.model.User;
import com.jakedelivery.api.userorder.business.UserOrderBusiness;
import com.jakedelivery.api.userorder.dto.UserOrderDetailResponse;
import com.jakedelivery.api.userorder.dto.UserOrderRequest;
import com.jakedelivery.api.userorder.dto.UserOrderResponse;
import com.jakedelivery.common.Api;
import com.jakedelivery.common.annotation.UserSession;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    
    // 현재 진행 중인 주문 건
    @GetMapping("/current")
    public Api<List<UserOrderDetailResponse>> current(@Parameter(hidden = true) @UserSession User user) {
        var response = userOrderBusiness.current(user);

        return Api.OK(response);
    }
    
    // 과거 주문 내역
    @GetMapping("/history")
    public Api<List<UserOrderDetailResponse>> history(@Parameter(hidden = true) @UserSession User user) {
        var response = userOrderBusiness.history(user);

        return Api.OK(response);
    }
    
    // 주문 1건에 대한 내역
    @GetMapping("/id/{orderId}")
    public Api<UserOrderDetailResponse> read(
            @Parameter(hidden = true) @UserSession User user,
            @PathVariable Long orderId
    ) {
        var response = userOrderBusiness.read(user, orderId);

        return Api.OK(response);
    }
}
