package org.duckdns.wru.app.friendship.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FriendResponse {
    private Long id;
    private String username;

    public FriendResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}