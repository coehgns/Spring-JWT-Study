package com.example.springjwt.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    EXPIRED_TOKEN(401, "expired token"),

    INVALID_TOKEN(401, "invalid token"),

    PASSWORD_MISMATCH(401, "password mismatch"),

    BOARD_AUTHOR_MISMATCH(403, "Board author mismatch"),

    BOARD_COMMENT_AUTHOR_MISMATCH(403, "Board comment author mismatch"),

    USER_NOT_FOUND(404, "User not found."),

    BOARD_COMMENT_NOT_FOUND(404, "Board Comment not found."),

    BOARD_NOT_FOUND(404, "Board not found."),

    USER_ALREADY_EXISTS(409, "User Already Exists"),

    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int statusCode;
    private final String message;
}
