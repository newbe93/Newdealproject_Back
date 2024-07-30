package org.duckdns.wru.app.friendship.repository;

import org.duckdns.wru.app.friendship.domain.Friendship;
import org.duckdns.wru.app.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friendship, Long> {
    boolean existsByUserAndFriend(User user, User friend);
}
