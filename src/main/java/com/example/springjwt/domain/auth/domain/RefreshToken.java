package com.example.springjwt.domain.auth.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(timeToLive = 60 * 60 * 120)
public class RefreshToken {

    @Id
    private final String accountId;

    @Column(name = "token")
    private String token;
}
