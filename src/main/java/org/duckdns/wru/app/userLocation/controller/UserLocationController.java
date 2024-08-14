package org.duckdns.wru.app.userLocation.controller;

import org.duckdns.wru.app.userLocation.domain.UserLocation;
import org.duckdns.wru.app.userLocation.dto.UserLocationRequest;
import org.duckdns.wru.app.userLocation.service.UserLocationService;
import org.duckdns.wru.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/userLocation")
public class UserLocationController {

    private final UserLocationService userLocationService;

    public UserLocationController(UserLocationService userLocationService) {
        this.userLocationService = userLocationService;
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> addUserLocation(@RequestBody UserLocationRequest userLocationRequest) {
        ApiResponse<?> response =  userLocationService.updateUserLocation(userLocationRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<?>> getFriendsLocation() {
        ApiResponse<?> response = userLocationService.getFriendsLocation();
        return ResponseEntity.ok(response);
    }
}
