package com.example.springjwt.domain.board.exception;

import com.example.springjwt.global.error.exception.BusinessException;
import com.example.springjwt.global.error.exception.ErrorCode;

public class BoardCommentAuthorMissmatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new BoardNotFoundException();

    public BoardCommentAuthorMissmatchException() {
        super(ErrorCode.BOARD_COMMENT_NOT_FOUND);
    }
}
