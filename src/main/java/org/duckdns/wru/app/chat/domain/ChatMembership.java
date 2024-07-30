package org.duckdns.wru.app.chat.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.duckdns.wru.app.user.domain.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_membership")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @Column(nullable = false)
    private LocalDateTime joinedAt;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(nullable = false)
    private boolean isActive;

    // 생성자, 기타 메서드 등

    public enum MemberRole {
        ADMIN, MODERATOR, MEMBER
    }
}