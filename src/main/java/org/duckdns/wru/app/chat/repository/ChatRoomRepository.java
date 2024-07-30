package org.duckdns.wru.app.chat.repository;

import org.duckdns.wru.app.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
