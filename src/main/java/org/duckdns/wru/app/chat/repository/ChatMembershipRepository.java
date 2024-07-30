package org.duckdns.wru.app.chat.repository;

import org.duckdns.wru.app.chat.domain.ChatMembership;
import org.duckdns.wru.app.chat.domain.ChatRoom;
import org.duckdns.wru.app.chat.dto.ChatRoomMemberDTO;
import org.duckdns.wru.app.chat.dto.ChatRoomResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMembershipRepository extends CrudRepository<ChatMembership, Long> {

//    @Query("SELECT cm FROM ChatMembership cm JOIN FETCH cm.chatRoom WHERE cm.user.id = :userId")
//    List<ChatMembership> findAllWithChatRoomsByUserId(@Param("userId") Long userId);
//    @Query("SELECT DISTINCT cr FROM ChatRoom cr " +
//            "JOIN cr.membership cm " +
//            "JOIN cm.user u " +
//            "WHERE cr.id IN (SELECT cm2.chatRoom.id FROM ChatMembership cm2 WHERE cm2.user.id = :userId)")
//    List<ChatRoom> findAllWithMembersByUserId(@Param("userId") Long userId);
    @Query("SELECT new org.duckdns.wru.app.chat.dto.ChatRoomMemberDTO(cm.chatRoom.id, cm.chatRoom.name, cm.user.id, cm.user.username) " +
            "FROM ChatMembership cm " +
            "WHERE cm.chatRoom.id IN (SELECT cm2.chatRoom.id FROM ChatMembership cm2 WHERE cm2.user.id = :userId)")
    List<ChatRoomMemberDTO> findChatRoomsAndMembersByUserId(@Param("userId") Long userId);
}
