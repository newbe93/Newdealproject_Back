package org.duckdns.wru.app.user.service;

import lombok.RequiredArgsConstructor;
import org.duckdns.wru.app.user.domain.User;
import org.duckdns.wru.global.auth.dto.JoinRequest;
import org.duckdns.wru.app.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


}
