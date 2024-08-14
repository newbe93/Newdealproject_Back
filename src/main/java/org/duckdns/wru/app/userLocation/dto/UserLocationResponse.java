package org.duckdns.wru.app.userLocation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLocationResponse {
    private Long userId;
    private String username;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
}
