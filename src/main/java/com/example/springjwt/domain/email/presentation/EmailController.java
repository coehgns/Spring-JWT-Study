package com.example.springjwt.domain.email.presentation;

import com.example.springjwt.domain.email.presentation.dto.request.CheckAuthCodeRequest;
import com.example.springjwt.domain.email.presentation.dto.request.SendEmailRequest;
import com.example.springjwt.domain.email.presentation.dto.response.CheckAuthCodeResponse;
import com.example.springjwt.domain.email.service.CheckAuthCodeService;
import com.example.springjwt.domain.email.service.SendAuthCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final CheckAuthCodeService checkAuthCodeService;
    private final SendAuthCodeService sendAuthCodeService;

    @PostMapping("/send-authCode")
    public void sendAuthCode(@RequestBody SendEmailRequest request) {
        sendAuthCodeService.execute(request);
    }

    @PostMapping("/check-authCode")
    public CheckAuthCodeResponse checkAuthCode(@RequestBody CheckAuthCodeRequest request) {
        return checkAuthCodeService.execute(request);
    }
}
