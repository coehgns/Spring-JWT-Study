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

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtResolver jwtResolver;

    private final ObjectMapper objectMapper;

    @Bean
    public void configure(HttpSecurity httpSecurity) {
        JwtFilter jwtTokenFilter = new JwtFilter(jwtTokenProvider, jwtResolver);
        GlobalExceptionFilter globalExceptionFilter = new GlobalExceptionFilter(objectMapper);
        httpSecurity.addFilter(jwtTokenFilter).addFilter(globalExceptionFilter);
    }
}
