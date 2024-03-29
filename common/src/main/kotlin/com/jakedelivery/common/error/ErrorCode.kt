package com.jakedelivery.common.error

enum class ErrorCode(
    private val httpStatusCode: Int,
    private val errorCode: Int,
    private val description: String
) : ErrorCodeIfs {
    OK(200, 200, "성공"),
    BAD_REQUEST(400, 400, "잘못된 요청"),
    INTERNAL_SERVER_ERROR(500, 500, "서버 에러"),
    NULL_POINT(500, 512, "Null Point"),
    ;

    override fun getHttpStatusCode(): Int {
        return this.httpStatusCode
    }

    override fun getErrorCode(): Int {
        return this.errorCode
    }

    override fun getDescription(): String {
        return this.description
    }
}