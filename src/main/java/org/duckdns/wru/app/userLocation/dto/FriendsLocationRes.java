package org.duckdns.wru.app.userLocation.dto;

import lombok.Getter;
import lombok.Setter;
import org.duckdns.wru.app.user.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FriendsLocationRes {
    private Long id;
    private String username;
    private Double latitude;
    private Double longitude;
    private List<UserLocationResponse> friendsLocations = new ArrayList<>();

    public void addMember(UserLocationResponse friendsLocation) {
        this.friendsLocations.add(friendsLocation);
    }
}
