package com.example.springjwt.domain.auth.domain;

import lombok.Getter;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash
public class RefreshToken {

    @Id
    private final String accountId;

    @Indexed
    private String token;

    @TimeToLive
    private Long ttl;
}