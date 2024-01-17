package com.jakedelivery.api.token.converter;

import com.jakedelivery.api.token.dto.TokenResponse;
import com.jakedelivery.api.token.model.Token;
import com.jakedelivery.common.annotation.Converter;
import com.jakedelivery.common.error.ErrorCode;
import com.jakedelivery.common.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {
    public TokenResponse toResponse(Token accessToken, Token refreshToken) {
        Objects.requireNonNull(accessToken, () -> {throw new ApiException(ErrorCode.NULL_POINT);});
        Objects.requireNonNull(refreshToken, () -> {throw new ApiException(ErrorCode.NULL_POINT);});

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
