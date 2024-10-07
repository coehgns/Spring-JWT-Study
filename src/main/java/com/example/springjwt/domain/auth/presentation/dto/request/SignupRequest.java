package com.example.springjwt.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class SignupRequest {

    private String username;

    private String password;
}
