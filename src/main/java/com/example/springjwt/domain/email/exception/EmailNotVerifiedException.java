package com.example.springjwt.domain.email.exception;

import com.example.springjwt.global.error.exception.BusinessException;
import com.example.springjwt.global.error.exception.ErrorCode;

public class EmailNotVerifiedException extends BusinessException {
    public static BusinessException EXCEPTION = new EmailNotVerifiedException();

    private EmailNotVerifiedException() {
        super(ErrorCode.EMAIL_NOT_VERIFIED);
    }
}
