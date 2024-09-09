package com.example.springjwt.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    USER_NOT_FOUND(404, "User not found."),

    BOARD_NOT_FOUND(404, "Board not found."),

    BOARD_AUTHOR_MISMATCH(403, "Board author mismatch");

    private final int statusCode;
    private final String message;
}
