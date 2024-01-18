package com.jakedelivery.account.token.service

import com.jakedelivery.account.token.ifs.TokenHelperIfs
import com.jakedelivery.account.token.model.Token
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val tokenHelperIfs: TokenHelperIfs
) {
    fun issueAccessToken(userId: Long?): Token? {
        return userId?.let {
            val data = mapOf("userId" to it)

            tokenHelperIfs.issueAccessToken(data)
        }
    }

    fun issueRefreshToken(userId: Long?): Token? {
        requireNotNull(userId)
        val data = mapOf("userId" to userId)

        return tokenHelperIfs.issueRefreshToken(data)
    }

    fun validationToken(token: String?): Long? {
        return token?.let { token ->
            tokenHelperIfs.validationTokenWithThrow(token)
        }?.let { map ->
            map["userId"]
        }?.let { userId ->
            userId.toString().toLong()
        }
    }
}