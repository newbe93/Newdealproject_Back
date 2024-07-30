package org.duckdns.wru.app.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.duckdns.wru.app.user.dto.UserDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ChatRoomResponse {
    private Long chatRoomId;
    private String chatRoomName;
    private List<UserDTO> members = new ArrayList<>();

    public void addMember(UserDTO member) {
        this.members.add(member);
    }

}
