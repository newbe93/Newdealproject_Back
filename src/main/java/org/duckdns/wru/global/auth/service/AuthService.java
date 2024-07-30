package org.duckdns.wru.global.auth.service;

import lombok.RequiredArgsConstructor;
import org.duckdns.wru.app.user.domain.User;
import org.duckdns.wru.app.user.repository.UserRepository;
import org.duckdns.wru.global.auth.dto.JoinRequest;
import org.duckdns.wru.global.auth.dto.LoginResponse;
import org.duckdns.wru.global.auth.exception.DuplicateDataException;
import org.duckdns.wru.global.common.dto.ApiResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinRequest joinRequest) {
        String username = joinRequest.getUsername();
        String password = joinRequest.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            throw new DuplicateDataException("이미 존재하는 아이디입니다.");
        }

        User user = new User(username, bCryptPasswordEncoder.encode(password), "ROLE_ADMIN");
        userRepository.save(user);


    }
}
