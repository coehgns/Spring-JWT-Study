package com.example.springjwt.domain.user.exception;

import com.example.springjwt.global.error.exception.ErrorCode;
import com.example.springjwt.global.error.exception.BusinessException;


public class UserNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserNotFoundException();
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
