package org.duckdns.wru.app.friendship.controller;

import org.duckdns.wru.app.friendship.domain.Friendship;
import org.duckdns.wru.app.friendship.dto.FriendResponse;
import org.duckdns.wru.app.friendship.dto.FriendshipRequest;
import org.duckdns.wru.app.friendship.service.FriendService;
import org.duckdns.wru.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friendship")
public class FriendshipController {

    private final FriendService friendService;

    public FriendshipController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<?>> getFriendships() {
        List<FriendResponse> friendsList = friendService.getFriends();
        System.out.println(friendsList);
        return ResponseEntity.ok(ApiResponse.success(friendsList));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> createFriendship(@RequestBody FriendshipRequest friendshipRequest) {
        System.out.println(friendshipRequest.getUsername());
        Friendship friendship = friendService.createFriendship(friendshipRequest.getUsername());
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
