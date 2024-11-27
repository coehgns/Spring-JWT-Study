package com.example.springjwt.domain.user.facade;

import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.domain.user.domain.repository.UserRepository;
import com.example.springjwt.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User currentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
