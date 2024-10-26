package com.example.springjwt.domain.email.facade;

import com.example.springjwt.domain.email.domain.EmailVerification;
import com.example.springjwt.domain.email.domain.repository.EmailVerificationRepository;
import com.example.springjwt.domain.email.exception.EmailNotVerifiedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailVerificationFacade {
    private final EmailVerificationRepository emailVerificationRepository;

    private EmailVerification getEmailVerificationByEmail(String email) {
        return emailVerificationRepository.findByEmail(email)
                .orElseThrow(() -> EmailNotVerifiedException.EXCEPTION);
    }
}
