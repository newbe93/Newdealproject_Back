package org.duckdns.wru.app.friendship.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.duckdns.wru.app.user.domain.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "friendship")
@Setter
@Getter
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private User friend;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    public enum FriendshipStatus {
        PENDING, ACCEPTED, REJECTED
    }
}
