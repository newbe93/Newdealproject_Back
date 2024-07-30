package org.duckdns.wru.app.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatRoomMemberDTO {
    private Long chatRoomId;
    private String chatRoomName;
    private Long userId;
    private String username;
}
