package com.jakedelivery.api.token.helper;

import com.jakedelivery.api._core.common.error.TokenErrorCode;
import com.jakedelivery.api._core.common.exception.ApiException;
import com.jakedelivery.api.token.ifs.TokenHelperIfs;
import com.jakedelivery.api.token.model.Token;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenHelper implements TokenHelperIfs {
    @Value("${token.secret.key}")
    private String secretKey;
    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;
    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public Token issueAccessToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);
        return getTokenDto(data, expiredLocalDateTime);
    }

    @Override
    public Token issueRefreshToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour);
        return getTokenDto(data, expiredLocalDateTime);
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var parser = Jwts.parserBuilder().setSigningKey(key).build();

        try {
            var result = parser.parseClaimsJws(token);

            return new HashMap<>(result.getBody());
        } catch (Exception e) {
            if (e instanceof SignatureException) {
                // 토큰이 유효하지 않을 때
                throw new ApiException(TokenErrorCode.INVALID_TOKEN, e);
            } else if (e instanceof ExpiredJwtException) {
                // 만료된 토큰
                throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, e);
            } else {
                // 그 외
                throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION, e);
            }
        }
    }

    private Token getTokenDto(Map<String, Object> data, LocalDateTime expiredLocalDateTime) {
        var expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return Token.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }
}
