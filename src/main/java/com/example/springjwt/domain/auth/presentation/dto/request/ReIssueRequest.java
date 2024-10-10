package com.example.springjwt.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class ReIssueRequest {
    private String refreshToken;
}
