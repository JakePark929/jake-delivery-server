package com.jakedelivery.api._core.common.exception;

import com.jakedelivery.api._core.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {
    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();
}
