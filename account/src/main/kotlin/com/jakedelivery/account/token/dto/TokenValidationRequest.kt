package com.jakedelivery.account.token.dto

import com.jakedelivery.account.token.model.Token

data class TokenValidationRequest(
    var token: Token? = null
)
