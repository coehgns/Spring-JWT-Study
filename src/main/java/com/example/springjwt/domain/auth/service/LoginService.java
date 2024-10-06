package com.example.springjwt.domain.auth.service;

import com.example.springjwt.domain.auth.exception.PasswordMismatchException;
import com.example.springjwt.domain.auth.presentation.dto.request.LoginRequest;
import com.example.springjwt.domain.auth.presentation.dto.response.TokenResponse;
import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.domain.user.facade.UserFacade;
import com.example.springjwt.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse login(LoginRequest request) {
        User user = userFacade.findByUserEmail(request.getEmail());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(request.getEmail()))
                .refreshToken(jwtTokenProvider.createRefreshToken(request.getEmail()))
                .build();
    }
}
