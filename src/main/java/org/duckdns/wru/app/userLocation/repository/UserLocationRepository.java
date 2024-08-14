package org.duckdns.wru.app.userLocation.repository;

import org.duckdns.wru.app.userLocation.domain.UserLocation;
import org.duckdns.wru.app.userLocation.dto.UserLocationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    @Query("SELECT DISTINCT NEW org.duckdns.wru.app.userLocation.dto.UserLocationResponse(" +
            "CASE WHEN f.user.id = :userId THEN f.friend.id ELSE f.user.id END, " +
            "CASE WHEN f.user.id = :userId THEN f.friend.username ELSE f.user.username END, " +
            "ul.latitude, ul.longitude, ul.timestamp) " +
            "FROM Friendship f " +
            "JOIN UserLocation ul ON (ul.user.id = CASE WHEN f.user.id = :userId THEN f.friend.id ELSE f.user.id END) " +
            "WHERE (f.user.id = :userId OR f.friend.id = :userId) " +
            "AND ul.timestamp = (" +
            "    SELECT MAX(ul2.timestamp) " +
            "    FROM UserLocation ul2 " +
            "    WHERE ul2.user.id = CASE WHEN f.user.id = :userId THEN f.friend.id ELSE f.user.id END" +
            ")")
    List<UserLocationResponse> findLatestFriendsLocations(@Param("userId") Long userId);

    @Query("SELECT NEW org.duckdns.wru.app.userLocation.dto.UserLocationResponse(" +
            "ul.user.id, ul.user.username, ul.latitude, ul.longitude, ul.timestamp) " +
            "FROM UserLocation ul " +
            "WHERE ul.user.id = :userId " +
            "AND ul.timestamp = (" +
            "    SELECT MAX(ul2.timestamp) " +
            "    FROM UserLocation ul2 " +
            "    WHERE ul2.user.id = :userId" +
            ")")
    UserLocationResponse findLatestUserLocation(@Param("userId") Long userId);
}
