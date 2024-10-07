package com.example.springjwt.global.config;

import com.example.springjwt.global.error.GlobalExceptionFilter;
import com.example.springjwt.global.security.jwt.JwtFilter;
import com.example.springjwt.global.security.jwt.JwtResolver;
import com.example.springjwt.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
// SecurityConfigurerAdapter를 상속하여 필터 설정 관리
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtResolver jwtResolver;

    private final ObjectMapper objectMapper;

    @Bean
    public void configure(HttpSecurity httpSecurity) {
        // JWT 토큰을 기반으로 인증 절차를 수행하는 JwtFilter 객체 생성
        JwtFilter jwtTokenFilter = new JwtFilter(jwtTokenProvider, jwtResolver);

        // 글로벌 예외 처리 필터 생성
        GlobalExceptionFilter globalExceptionFilter = new GlobalExceptionFilter(objectMapper);

        // Spring Security 설정에 JWT 인증 필터와 글로버 예외 처리 필터 추가
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(globalExceptionFilter, JwtFilter.class);
    }
}
