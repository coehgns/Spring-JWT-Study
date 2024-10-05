package com.example.springjwt.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
