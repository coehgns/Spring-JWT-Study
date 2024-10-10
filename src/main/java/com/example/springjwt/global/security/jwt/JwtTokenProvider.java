package com.example.springjwt.global.security.jwt;

import com.example.springjwt.domain.auth.domain.RefreshToken;
import com.example.springjwt.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.springjwt.domain.auth.exception.ExpiredTokenException;
import com.example.springjwt.domain.auth.exception.InvalidTokenException;
import com.example.springjwt.domain.auth.presentation.dto.response.TokenResponse;
import com.example.springjwt.domain.user.domain.repository.UserRepository;
import com.example.springjwt.global.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;

    public TokenResponse createToken(String username) {

        Date now = new Date();

        String accessToken = createAccessToken(username);
        String refreshToken = createRefreshToken(username);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .username(username)
                        .token(refreshToken)
                        .build()
        );

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpiredAt(new Date(now.getTime() + jwtProperties.getAccessExpiration()))
                .refreshExpiredAt(new Date(now.getTime() + jwtProperties.getRefreshExpiration()))
                .build();
    }

    public String createAccessToken(String username) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(username)
                .claim("type", "access")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getAccessExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public String createRefreshToken(String username) {
        Date now = new Date();

        String rfToken = Jwts.builder()
                .setSubject(username)
                .claim("type", "refresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getRefreshExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();

        return rfToken;
    }

    // 토큰에 담겨 있는 userId로 SpringSecurity Authentication 정보를 반환하는 메소드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public TokenResponse receiveToken(String username) {

        Date now = new Date();

         return createToken(username);
    }

    // HTTP 요청 헤더에서 토큰을 가져오는 메소드
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix()) && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // refreshToken을 통해 accessToken 재발급받는 메소드
    public TokenResponse reissue(String rfToken) {

        RefreshToken token = refreshTokenRepository.findByToken(rfToken)
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);

        String accountId = userRepository.findByUsername(token.getUsername())
                .orElseThrow(() -> InvalidTokenException.EXCEPTION).getUsername();

        refreshTokenRepository.delete(token);

        Date now = new Date();

        return TokenResponse.builder()
                .accessToken(createAccessToken(accountId))
                .refreshToken(createRefreshToken(accountId))
                .accessExpiredAt(new Date(now.getTime() + jwtProperties.getAccessExpiration()))
                .refreshExpiredAt(new Date(now.getTime() + jwtProperties.getRefreshExpiration()))
                .build();
    }

}
