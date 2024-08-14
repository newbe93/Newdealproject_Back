package org.duckdns.wru.app.friendship.repository;

import org.duckdns.wru.app.friendship.domain.Friendship;
import org.duckdns.wru.app.friendship.dto.FriendResponse;
import org.duckdns.wru.app.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friendship, Long> {
    boolean existsByUserAndFriend(User user, User friend);

    @Query("SELECT NEW org.duckdns.wru.app.friendship.dto.FriendResponse(" +
            "CASE WHEN f.user.id = :userId THEN f.friend.id ELSE f.user.id END, " +
            "CASE WHEN f.user.id = :userId THEN f.friend.username ELSE f.user.username END) " +
            "FROM Friendship f " +
            "WHERE f.user.id = :userId OR f.friend.id = :userId")
    List<FriendResponse> findFriendsByUserId(@Param("userId") Long userId);


}
