package com.example.springjwt.domain.board.exception;

import com.example.springjwt.global.error.exception.BusinessException;
import com.example.springjwt.global.error.exception.ErrorCode;

public class BoardAuthorMismatchException extends BusinessException {
    public static final BusinessException EXCEPTION = new BoardAuthorMismatchException();
    BoardAuthorMismatchException() {
        super(ErrorCode.BOARD_AUTHOR_MISMATCH);
    }
}
