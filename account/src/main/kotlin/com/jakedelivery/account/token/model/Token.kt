package com.jakedelivery.account.token.model

import java.time.LocalDateTime

data class Token(
    var token: String? = null,
    var expiredAt: LocalDateTime?= null
)