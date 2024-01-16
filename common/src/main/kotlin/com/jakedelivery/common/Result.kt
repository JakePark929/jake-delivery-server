package com.jakedelivery.common

import com.jakedelivery.common.error.ErrorCode
import com.jakedelivery.common.error.ErrorCodeIfs

data class Result(
    val resultCode: Int? = null,
    val resultMessage: String? = null,
    val resultDescription: String? = null
) {
    companion object {
        fun OK(): Result {
            return Result(
                resultCode = ErrorCode.OK.getErrorCode(),
                resultMessage = ErrorCode.OK.getDescription(),
                resultDescription = "SUCCESS"
            )
        }

        fun ERROR(errorCodeIfs: ErrorCodeIfs): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = "FAILED"
            )
        }

        fun ERROR(errorCodeIfs: ErrorCodeIfs, throwable: Throwable): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = throwable.localizedMessage
            )
        }

        fun ERROR(errorCodeIfs: ErrorCodeIfs, description: String): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = description
            )
        }
    }
}