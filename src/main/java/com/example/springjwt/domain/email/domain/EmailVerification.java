package com.example.springjwt.domain.email.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@RequiredArgsConstructor
public class EmailVerification {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String authCode;

    @Column(nullable = false)
    private Boolean isVerified;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public void verify() {
        this.isVerified = true;
    }

    public EmailVerification(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
        this.isVerified = false;
        this.createdAt = LocalDateTime.now();
    }
}