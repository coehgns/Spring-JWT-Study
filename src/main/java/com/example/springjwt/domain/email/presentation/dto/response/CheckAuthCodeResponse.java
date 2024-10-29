package com.example.springjwt.domain.email.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckAuthCodeResponse {
    private boolean isVerified;
}
