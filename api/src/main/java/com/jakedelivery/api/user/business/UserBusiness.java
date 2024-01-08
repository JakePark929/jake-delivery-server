package com.jakedelivery.api.user.business;

import com.jakedelivery.api._core.common.annotation.Business;
import com.jakedelivery.api.user.converter.UserConverter;
import com.jakedelivery.api.user.dto.UserResponse;
import com.jakedelivery.api.user.dto.request.UserLoginRequest;
import com.jakedelivery.api.user.dto.request.UserRegisterRequest;
import com.jakedelivery.api.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;

    /**
     * [ 회원 가입 ]
     * @param request
     * @return UserResponse
     * @apiNote 사용자에 대한 가입처리 로직
     */
    public UserResponse register(UserRegisterRequest request) {
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        return userConverter.toResponse(newEntity);
    }

    public UserResponse login(UserLoginRequest request) {
        // 1. email, user 를 가지고 사용자 체크
        // 2. user entity 로그인 확인
        var userEntity = userService.login(request.getEmail(), request.getPassword());
        // 3. TODO: token 생성
        // 4. token response
        return userConverter.toResponse(userEntity);
    }
}
