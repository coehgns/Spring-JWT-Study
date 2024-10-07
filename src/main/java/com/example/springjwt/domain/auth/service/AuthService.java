package com.example.springjwt.domain.auth.service;

import com.example.springjwt.domain.auth.exception.PasswordMismatchException;
import com.example.springjwt.domain.auth.presentation.dto.request.LoginRequest;
import com.example.springjwt.domain.auth.presentation.dto.request.SignupRequest;
import com.example.springjwt.domain.auth.presentation.dto.response.TokenResponse;
import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.domain.user.domain.repository.UserRepository;
import com.example.springjwt.domain.user.exception.UserNotFoundException;
import com.example.springjwt.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signup(SignupRequest request) {
        userRepository.save(
                User.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .build()
        );
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.createAccessToken(request.getUsername()))
                .refreshToken(jwtTokenProvider.createRefreshToken(request.getUsername()))
                .build();
    }
}
