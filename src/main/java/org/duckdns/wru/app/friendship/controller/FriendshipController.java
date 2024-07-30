package org.duckdns.wru.app.friendship.controller;

import org.duckdns.wru.app.friendship.domain.Friendship;
import org.duckdns.wru.app.friendship.service.FriendService;
import org.duckdns.wru.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/friendship")
public class FriendshipController {

    private final FriendService friendService;

    public FriendshipController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/{username}")
    public ResponseEntity<ApiResponse<?>> createFriendship(@PathVariable(value = "username") String username) {
        Friendship friendship = friendService.createFriendship(username);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
