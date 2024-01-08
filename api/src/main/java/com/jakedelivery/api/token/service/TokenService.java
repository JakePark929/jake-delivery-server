package com.jakedelivery.api.token.service;

import com.jakedelivery.api._core.common.error.ErrorCode;
import com.jakedelivery.api._core.common.exception.ApiException;
import com.jakedelivery.api.token.ifs.TokenHelperIfs;
import com.jakedelivery.api.token.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenHelperIfs tokenHelperIfs;

    public Token issueAccessToken(Long userId) {
        var data = new HashMap<String, Object>();
        data.put("userId", userId);

        return tokenHelperIfs.issueAccessToken(data);
    }

    public Token issueRefreshToken(Long userId) {
        var data = new HashMap<String, Object>();
        data.put("userId", userId);

        return tokenHelperIfs.issueRefreshToken(data);
    }

    public Long validationToken(String token) {
        var map = tokenHelperIfs.validationTokenWithThrow(token);
        var userId = map.get("userId");

        Objects.requireNonNull(userId, () -> {throw new ApiException(ErrorCode.NULL_POINT);});

        return Long.parseLong(userId.toString());
    }
}
