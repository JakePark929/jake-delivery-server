package com.jakedelivery.account.token.controller

import com.jakedelivery.account._core.common.Log
import com.jakedelivery.account.token.business.TokenBusiness
import com.jakedelivery.account.token.dto.TokenValidationRequest
import com.jakedelivery.account.token.dto.TokenValidationResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/internal-api/token")
@RestController
class TokenInternalApiController(
    private val tokenBusiness: TokenBusiness
) {
    companion object: Log

    @PostMapping("/validation")
    fun tokenValidation(
        @RequestBody tokenValidationRequest: TokenValidationRequest?
    ): TokenValidationResponse {
        log.info("token validation init: {}", tokenValidationRequest)
        return tokenBusiness.tokenValidation(tokenValidationRequest)
    }
}