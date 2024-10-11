package com.example.springjwt.domain.auth.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Builder
@RedisHash(timeToLive = 60 * 60 * 120)
public class RefreshToken {

    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "token")
    private String token;
}
