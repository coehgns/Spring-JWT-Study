package com.example.springjwt.domain.email.service;

import com.example.springjwt.domain.email.domain.EmailVerification;
import com.example.springjwt.domain.email.domain.repository.EmailVerificationRepository;
import com.example.springjwt.domain.email.presentation.dto.request.SendEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SendAuthCodeService {

    private final EmailService emailService;
    private final EmailVerificationRepository emailVerificationRepository;

    public void execute(SendEmailRequest request) {
        String email = request.getEmail();
        String authCode = makeAuthCode();

        emailVerificationRepository.save(new EmailVerification(email, authCode));

        emailService.sendEmail(email, authCode);
    }

    private String makeAuthCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append((int) (random.nextDouble() * 10));
        }
        return code.toString();
    }
}
