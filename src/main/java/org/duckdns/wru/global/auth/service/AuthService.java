package org.duckdns.wru.global.auth.service;

import lombok.RequiredArgsConstructor;
import org.duckdns.wru.app.user.domain.User;
import org.duckdns.wru.app.user.repository.UserRepository;
import org.duckdns.wru.app.userLocation.domain.UserLocation;
import org.duckdns.wru.app.userLocation.repository.UserLocationRepository;
import org.duckdns.wru.global.auth.dto.JoinRequest;
import org.duckdns.wru.global.auth.exception.DuplicateDataException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserLocationRepository userLocationRepository;

    public void joinProcess(JoinRequest joinRequest) {
        String username = joinRequest.getUsername();
        System.out.println("username = " + username);
        String password = joinRequest.getPassword();
        System.out.println("password = " + password);

        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            throw new DuplicateDataException("이미 존재하는 아이디입니다.");
        }

        User user = new User(username, bCryptPasswordEncoder.encode(password), "ROLE_ADMIN");
        userRepository.save(user);

        // 서울시청의 위치를 기본 위치로 설정
        double defaultLatitude = 37.5665;
        double defaultLongitude = 126.9780;

        UserLocation initialLocation = new UserLocation(user, defaultLatitude, defaultLongitude);
        userLocationRepository.save(initialLocation);
    }

    public boolean isExist(JoinRequest joinRequest) {
        String username = joinRequest.getUsername();
        System.out.println("username = " + username);
        if(username == null) { return true;}
        return userRepository.existsByUsername(username);
    }
}
