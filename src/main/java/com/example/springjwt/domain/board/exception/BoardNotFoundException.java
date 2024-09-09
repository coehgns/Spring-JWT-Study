package com.example.springjwt.domain.board.exception;

import com.example.springjwt.global.error.exception.BusinessException;
import com.example.springjwt.global.error.exception.ErrorCode;

public class BoardNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new BoardNotFoundException();

    public BoardNotFoundException() {
        super(ErrorCode.BOARD_NOT_FOUND);
    }
}