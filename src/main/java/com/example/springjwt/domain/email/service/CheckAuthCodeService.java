package com.example.springjwt.domain.email.service;

import com.example.springjwt.domain.email.domain.EmailVerification;
import com.example.springjwt.domain.email.facade.EmailVerificationFacade;
import com.example.springjwt.domain.email.presentation.dto.request.CheckAuthCodeRequest;
import com.example.springjwt.domain.email.presentation.dto.response.CheckAuthCodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckAuthCodeService {

    private final EmailVerificationFacade emailVerificationFacade;

    public CheckAuthCodeResponse checkAuthCode(CheckAuthCodeRequest request) {
        EmailVerification emailVerification = emailVerificationFacade.getEmailVerificationByEmail(request.getEmail());

        if(emailVerification.getAuthCode().equals(request.getAuthCode())) {
            emailVerification.verify();
            return CheckAuthCodeResponse.builder().isVerified(true).build();
        }

        return CheckAuthCodeResponse.builder().isVerified(false).build();
    }
}
