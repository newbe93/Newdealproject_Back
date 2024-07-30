package org.duckdns.wru.app.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String username;

    public UserDTO(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
