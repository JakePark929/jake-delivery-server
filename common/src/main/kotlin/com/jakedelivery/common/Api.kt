package com.jakedelivery.common

import com.jakedelivery.common.error.ErrorCodeIfs
import javax.validation.Valid

data class Api<T>(
    var result: Result? = null,

    @field:Valid
    var body: T? = null
) {
    companion object {
        @JvmStatic
        fun <T> OK(data: T?): Api<T> {
            return Api(
                result = Result.OK(),
                body = data
            )
        }

        @JvmStatic
        fun <T> ERROR(result: Result?): Api<T> {
            return Api(
                result = result,
            )
        }

        @JvmStatic
        fun <T> ERROR(errorCodeIfs: ErrorCodeIfs): Api<T> {
            return Api(
                result = Result.ERROR(errorCodeIfs)
            )
        }

        @JvmStatic
        fun <T> ERROR(errorCodeIfs: ErrorCodeIfs, throwable: Throwable): Api<T> {
            return Api(
                result = Result.ERROR(errorCodeIfs, throwable)
            )
        }

        @JvmStatic
        fun <T> ERROR(errorCodeIfs: ErrorCodeIfs, description: String): Api<T> {
            return Api(
                result = Result.ERROR(errorCodeIfs, description)
            )
        }
    }
}