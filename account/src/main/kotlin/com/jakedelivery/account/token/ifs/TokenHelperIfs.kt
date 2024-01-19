package com.jakedelivery.account.token.ifs

import com.jakedelivery.account.token.model.Token

interface TokenHelperIfs {
    fun issueAccessToken(data: Map<String, Any>?): Token?
    fun issueRefreshToken(data: Map<String, Any>?): Token?
    fun validationTokenWithThrow(token: String?): Map<String, Any>?
}