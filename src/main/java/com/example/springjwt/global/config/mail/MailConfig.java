package com.example.springjwt.global.config.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("spring.mail.host")
    private String host;

    @Value("spring.mail.port")
    private int port;

    @Value("spring.mail.username")
    private String username;

    @Value("spring.mail.password")
    private String password;

    @Value("spring.mail.properties.mail.smtp.auth")
    private String smtpAuth;

    @Value("spring.mail.properties.mail.smtp.starttls.enable")
    private String starttlsEnable;

    @Value("spring.mail.properties.mail.debug")
    private String mailDebug;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.starttls.enable", starttlsEnable);
        properties.put("mail.debug", mailDebug);

        return mailSender;
    }
}
