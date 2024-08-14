package org.duckdns.wru.app.userLocation.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLocationRequest {
    private Double latitude;
    private Double longitude;
}
