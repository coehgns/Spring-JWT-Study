package com.example.springjwt.domain.email.presentation.dto.request;

import lombok.Getter;

@Getter
public class CheckAuthCodeRequest {
    private String email;
    private String authCode;
}
