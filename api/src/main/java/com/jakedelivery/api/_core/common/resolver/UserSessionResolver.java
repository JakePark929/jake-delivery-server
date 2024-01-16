package com.jakedelivery.api._core.common.resolver;

import com.jakedelivery.api.user.model.User;
import com.jakedelivery.api.user.service.UserService;
import com.jakedelivery.common.annotation.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class UserSessionResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터, 어노테이션 체크

        // 1. 어노테이션이 있는지 체크
        var annotation = parameter.hasParameterAnnotation(UserSession.class);
        // 2. 파라미터 타입 체크
        var parameterType = parameter.getParameterType().equals(User.class);

        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        // supportsParameter 에서 true 반환 시 실행

        // request context holder 에서 찾아오기
        var requestContext = RequestContextHolder.getRequestAttributes();
        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
        var userEntity = userService.getUserWithThrow(Long.parseLong(userId.toString()));

        // 사용자 정보 세팅
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .address(userEntity.getAddress())
                .status(userEntity.getStatus())
                .registeredAt(userEntity.getRegisteredAt())
                .unRegisteredAt(userEntity.getUnRegisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .build();
    }
}
