package com.example.springjwt.global.error;

import com.example.springjwt.global.error.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
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
}
