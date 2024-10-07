package com.example.springjwt.global.config;

import com.example.springjwt.global.security.jwt.JwtResolver;
import com.example.springjwt.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtResolver jwtResolver;

    private final ObjectMapper objectMapper;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // csrf disable
        httpSecurity.csrf(AbstractHttpConfigurer::disable)

                // Form 로그인 방식 disable
                .formLogin(AbstractHttpConfigurer::disable)

                // http basic 인증 방식 disable
                .httpBasic(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())

                // Session을 STATELESS 상태로 설정
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .with(new FilterConfig(jwtTokenProvider, jwtResolver ,objectMapper), Customizer.withDefaults());

        return httpSecurity.build();
    }

    // cors 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(false);  // 쿠키 요청 여부 false
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // HTTP 메서드 허용
        config.setAllowedOrigins(List.of("*"));  // 모든 출처 Origin 허용
        config.addAllowedHeader("*");  // 모든 헤더에 응답 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
