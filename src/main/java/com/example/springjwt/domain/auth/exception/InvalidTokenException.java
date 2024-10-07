package com.example.springjwt.domain.auth.exception;

import com.example.springjwt.global.error.exception.BusinessException;
import com.example.springjwt.global.error.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
