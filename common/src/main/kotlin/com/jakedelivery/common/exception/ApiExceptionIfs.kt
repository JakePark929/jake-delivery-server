package com.jakedelivery.common.exception

import com.jakedelivery.common.error.ErrorCodeIfs

interface ApiExceptionIfs {
    val errorCodeIfs: ErrorCodeIfs?
    val errorDescription: String?
}