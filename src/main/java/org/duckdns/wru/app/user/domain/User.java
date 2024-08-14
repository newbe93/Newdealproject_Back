package org.duckdns.wru.app.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.duckdns.wru.app.userLocation.domain.UserLocation;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLocation> locations = new ArrayList<>();

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username ,String role, Long id) {
        this.username = username;
        this.role = role;
        this.id = id;
    }

    public void addLocation(UserLocation location) {
        locations.add(location);
        location.setUser(this);
    }

    public void removeLocation(UserLocation location) {
        locations.remove(location);
        location.setUser(null);
    }
}
