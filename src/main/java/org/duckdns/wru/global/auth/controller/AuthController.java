package org.duckdns.wru.global.auth.controller;

import lombok.RequiredArgsConstructor;
import org.duckdns.wru.global.auth.dto.JoinRequest;
import org.duckdns.wru.global.auth.service.AuthService;
import org.duckdns.wru.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<?> joinProcess(@RequestBody JoinRequest joinRequest) {
        authService.joinProcess(joinRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/isExist")
    public ResponseEntity<?> isExistProcess(@RequestBody JoinRequest joinRequest) {
        boolean isExist =  authService.isExist(joinRequest);
        return ResponseEntity.ok(ApiResponse.success(isExist));
    }
}
