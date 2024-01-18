package com.jakedelivery.account.token.helper

import com.jakedelivery.account.token.ifs.TokenHelperIfs
import com.jakedelivery.account.token.model.Token
import com.jakedelivery.common.error.TokenErrorCode
import com.jakedelivery.common.exception.ApiException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
class TokenHelper: TokenHelperIfs {
    @Value("\${token.secret.key}")
    private val secretKey: String? = null

    @Value("\${token.access-token.plus-hour}")
    private val accessTokenPlusHour: Long = 1

    @Value("\${token.refresh-token.plus-hour}")
    private val refreshTokenPlusHour: Long = 12

    override fun issueAccessToken(data: Map<String, Any>?): Token? {
        val expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour)
        val expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant())

        val key = Keys.hmacShaKeyFor(secretKey?.toByteArray())

        val jwtToken = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setClaims(data)
            .setExpiration(expiredAt)
            .compact()

        return Token(
            token = jwtToken,
            expiredAt = expiredLocalDateTime
        )
    }

    override fun issueRefreshToken(data: Map<String, Any>?): Token? {
        val expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour)
        val expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant())

        val key = Keys.hmacShaKeyFor(secretKey?.toByteArray())

        val jwtToken = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setClaims(data)
            .setExpiration(expiredAt)
            .compact()

        return Token(
            token = jwtToken,
            expiredAt = expiredLocalDateTime
        )
    }

    override fun validationTokenWithThrow(token: String?): Map<String, Any>? {
        val key = Keys.hmacShaKeyFor(secretKey?.toByteArray())
        val parser = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()

        return try {
            val result = token?.let { parser.parseClaimsJws(token) }

            HashMap(result?.body)
        } catch(e: Exception) {
            when (e) {
                is SignatureException -> {
                    throw ApiException(TokenErrorCode.INVALID_TOKEN, e)
                } is ExpiredJwtException -> {
                    throw ApiException(TokenErrorCode.EXPIRED_TOKEN, e)
                } else -> {
                    throw ApiException(TokenErrorCode.TOKEN_EXCEPTION, e)
                }
            }
        }
    }
}