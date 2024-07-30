package org.duckdns.wru.app.chat.service;

import org.duckdns.wru.app.chat.domain.ChatMembership;
import org.duckdns.wru.app.chat.domain.ChatRoom;
import org.duckdns.wru.app.chat.dto.ChatRequest;
import org.duckdns.wru.app.chat.dto.ChatRoomMemberDTO;
import org.duckdns.wru.app.chat.dto.ChatRoomResponse;
import org.duckdns.wru.app.chat.repository.ChatMembershipRepository;
import org.duckdns.wru.app.chat.repository.ChatRoomRepository;
import org.duckdns.wru.app.user.domain.User;
import org.duckdns.wru.app.user.dto.UserDTO;
import org.duckdns.wru.app.user.exception.UserNotFoundException;
import org.duckdns.wru.app.user.repository.UserRepository;
import org.duckdns.wru.global.auth.dto.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMembershipRepository chatMembershipRepository;
    private final UserRepository userRepository;

    public ChatService(ChatRoomRepository chatRoomRepository, ChatMembershipRepository chatMembershipRepository, UserRepository userRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatMembershipRepository = chatMembershipRepository;
        this.userRepository = userRepository;
    }

    public void createChat(ChatRequest chatRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Long id = userDetails.getUserId();
        Long[] memberIds = chatRequest.getId();
        String roomname = chatRequest.getName();

        // 채팅방 생성
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(roomname);
        chatRoom.setCreatedAt(LocalDateTime.now());

        chatRoomRepository.save(chatRoom);

        // 관리자와 멤버 ID를 포함한 전체 참가자 목록 생성
        Set<Long> allParticipantIds = new HashSet<>(Arrays.asList(memberIds));
        allParticipantIds.add(id);

        // 모든 참가자에 대한 ChatMembership 생성 및 저장
        List<ChatMembership> memberships = allParticipantIds.stream()
                .map(userId -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new UserNotFoundException(userId));

                    ChatMembership membership = new ChatMembership();
                    membership.setUser(user);
                    membership.setChatRoom(chatRoom);
                    membership.setJoinedAt(LocalDateTime.now());
                    membership.setActive(true);
                    membership.setRole(userId.equals(id) ?
                            ChatMembership.MemberRole.ADMIN : ChatMembership.MemberRole.MEMBER);

                    return membership;
                })
                .collect(Collectors.toList());

        chatMembershipRepository.saveAll(memberships);

    }

    public List<ChatRoomResponse> getChatRoom() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Long userId = userDetails.getUserId();

        List<ChatRoomMemberDTO> chatRoomMembers = chatMembershipRepository.findChatRoomsAndMembersByUserId(userId);

        Map<Long, ChatRoomResponse> chatRoomMap = new HashMap<>();

        for (ChatRoomMemberDTO dto : chatRoomMembers) {
            chatRoomMap.computeIfAbsent(dto.getChatRoomId(), id ->
                    new ChatRoomResponse(dto.getChatRoomId(), dto.getChatRoomName(), new ArrayList<>())
            ).addMember(new UserDTO(dto.getUserId(), dto.getUsername()));
        }

        return new ArrayList<>(chatRoomMap.values());
    }
}
