package com.example.springjwt.global.error;

import com.example.springjwt.global.error.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private String message;
    private Integer status;
    private String description;

    public static ErrorResponse of(ErrorCode errorCode, String description) {
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .status(errorCode.getStatusCode())
                .description(errorCode.getMessage())
                .build();
    }

    public static ErrorResponse of(int errorCode, String description) {
        return ErrorResponse.builder()
                .message(description)
                .status(errorCode)
                .description(description)
                .build();
    }

}
