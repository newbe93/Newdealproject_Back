package org.duckdns.wru.global.auth.controller;

import lombok.RequiredArgsConstructor;
import org.duckdns.wru.global.auth.dto.JoinRequest;
import org.duckdns.wru.global.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<?> joinProcess(JoinRequest joinRequest) {
        authService.joinProcess(joinRequest);
        return ResponseEntity.ok().build();
    }
}
