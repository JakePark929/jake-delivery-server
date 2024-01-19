package com.jakedelivery.apigateway.dto

import com.jakedelivery.apigateway.model.Token

data class TokenValidationRequest(
    var token: Token? = null
)
