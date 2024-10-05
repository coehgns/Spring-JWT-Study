package com.example.springjwt.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class SignupRequest {

    private String email;

    private String password;
}
