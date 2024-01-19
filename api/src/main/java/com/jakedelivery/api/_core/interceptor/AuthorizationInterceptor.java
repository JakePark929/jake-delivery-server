package com.jakedelivery.api._core.interceptor;

import com.jakedelivery.common.error.ErrorCode;
import com.jakedelivery.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Authorization Interceptor url : {}", request.getRequestURI());

        // WEB, 특히 Chrome 의 경우 GET, POST 요청 전 메소드 지원하는지 OPTIONS = pass
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        // js. html. png resource 를 요청하는 경우 = pass
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // header 검증 로직
        var userId = request.getHeader("x-user-id");
        if (userId == null) {
            throw new ApiException(ErrorCode.BAD_REQUEST, "x-user-id header 없음");
        }

        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);

        return true;
    }
}
