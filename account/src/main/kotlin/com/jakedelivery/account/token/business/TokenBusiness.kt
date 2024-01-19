package com.jakedelivery.account.token.business

import com.jakedelivery.account.token.dto.TokenValidationRequest
import com.jakedelivery.account.token.dto.TokenValidationResponse
import com.jakedelivery.account.token.service.TokenService
import com.jakedelivery.common.annotation.Business

@Business
class TokenBusiness(
    private val tokenService: TokenService
) {
    fun tokenValidation(tokenValidationRequest: TokenValidationRequest?): TokenValidationResponse {
        val result = tokenService.validationToken(tokenValidationRequest?.token?.token)

        return TokenValidationResponse(userId = result)
    }
}