package com.example.springjwt.domain.email.service;

import com.example.springjwt.domain.email.domain.EmailVerification;
import com.example.springjwt.domain.email.domain.repository.EmailVerificationRepository;
import com.example.springjwt.domain.email.exception.EmailNotVerifiedException;
import com.example.springjwt.domain.email.facade.EmailVerificationFacade;
import com.example.springjwt.domain.email.presentation.dto.request.SendEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SendAuthCodeService {

    private final EmailService emailService;
    private final EmailVerificationFacade emailVerificationFacade;
    private final EmailVerificationRepository emailVerificationRepository;

    public void execute(SendEmailRequest request) {
        String email = request.getEmail();
        String authCode = makeAuthCode();

        EmailVerification emailVerification = emailVerificationFacade.getEmailVerificationByEmail(request.getEmail());
        emailVerificationRepository.save(new EmailVerification(email, authCode));

        emailService.sendEmail(email, authCode);
    }

    private String makeAuthCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += (int) (random.nextDouble() * 10);
        }
        return code;
    }
}
