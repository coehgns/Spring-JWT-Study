package com.example.springjwt.global.security.auth;


import com.example.springjwt.domain.user.domain.User;
import com.example.springjwt.domain.user.domain.repository.UserRepository;
import com.example.springjwt.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
// 사용자 정보를 불러오는 클래스임.
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return new CustomUserDetails(user);
    }
}
