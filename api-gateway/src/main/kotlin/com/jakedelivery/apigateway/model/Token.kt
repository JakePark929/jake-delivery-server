package com.jakedelivery.apigateway.model

import java.time.LocalDateTime

data class Token(
    var token: String? = null,
    var expiredAt: LocalDateTime?= null
)