package com.example.springjwt.global.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtResolver {

    private final JwtProperties jwtProperties;

    public String resolverToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(jwtProperties.getHeader());

        // 토큰 정보 존재 여부 및 Bearer 토큰인지 확인
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
