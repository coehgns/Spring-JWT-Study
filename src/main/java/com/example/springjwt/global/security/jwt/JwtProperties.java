package com.example.springjwt.global.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String header;

    private String prefix;

    private String secretKey;

    private Long accessExpiration;

    private Long refreshExpiration;
}
