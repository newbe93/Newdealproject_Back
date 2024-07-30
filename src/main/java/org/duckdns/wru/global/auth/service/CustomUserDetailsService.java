package org.duckdns.wru.global.auth.service;

import org.duckdns.wru.app.user.domain.User;
import org.duckdns.wru.app.user.repository.UserRepository;
import org.duckdns.wru.global.auth.dto.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userData = userRepository.findByUsername(username);

        // 검증
        if (userData != null) {
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
