package com.example.springjwt.domain.auth.presentation;

import com.example.springjwt.domain.auth.presentation.dto.request.LoginRequest;
import com.example.springjwt.domain.auth.presentation.dto.request.ReIssueRequest;
import com.example.springjwt.domain.auth.presentation.dto.request.SignupRequest;
import com.example.springjwt.domain.auth.presentation.dto.response.TokenResponse;
import com.example.springjwt.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequest request) {
        authService.signup(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/re-issue")
    public TokenResponse reissue(@RequestBody ReIssueRequest request) {
        return authService.reissue(request.getRefreshToken());
    }
}
