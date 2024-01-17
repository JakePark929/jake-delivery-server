package com.jakedelivery.api.token.business;

import com.jakedelivery.api.token.converter.TokenConverter;
import com.jakedelivery.api.token.dto.TokenResponse;
import com.jakedelivery.api.token.service.TokenService;
import com.jakedelivery.common.annotation.Business;
import com.jakedelivery.common.error.ErrorCode;
import com.jakedelivery.common.exception.ApiException;
import com.jakedelivery.db.BaseEntity;
import com.jakedelivery.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {
    private final TokenService tokenService;
    private final TokenConverter tokenConverter;
    
    public TokenResponse issueToken(UserEntity userEntity) {
        // 1. user entity user id 추출
        // 2. access, refresh token 발행
        // 3. converter -> token response 로 변경
        return Optional.ofNullable(userEntity)
                .map(BaseEntity::getId)
                .map(userId -> {
                    var accessToken = tokenService.issueAccessToken(userId);
                    var refreshToken = tokenService.issueRefreshToken(userId);

                    return tokenConverter.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public Long validationAccessToken(String accessToken) {
        var userId = tokenService.validationToken(accessToken);

        return userId;
    }
}
