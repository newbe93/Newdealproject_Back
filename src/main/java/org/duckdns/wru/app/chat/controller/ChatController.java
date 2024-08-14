package org.duckdns.wru.app.chat.controller;

import org.duckdns.wru.app.chat.domain.ChatRoom;
import org.duckdns.wru.app.chat.dto.ChatRequest;
import org.duckdns.wru.app.chat.dto.ChatRoomResponse;
import org.duckdns.wru.app.chat.service.ChatService;
import org.duckdns.wru.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {


    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> createChatRooms(@RequestBody ChatRequest chatRequest) {
        System.out.println(chatRequest);
        chatService.createChat(chatRequest);
        return ResponseEntity.ok(ApiResponse.success(chatRequest));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ChatRoomResponse>>> getChatRooms() {
        List<ChatRoomResponse> chatRooms =  chatService.getChatRoom();
        return ResponseEntity.ok(ApiResponse.success(chatRooms));
    }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<ApiResponse<ChatRoomResponse>> getChatRoom(@PathVariable(value = "chatRoomId") Long chatRoomId) {
        ChatRoomResponse chatRoom =  chatService.getChatRoomDetail(chatRoomId);
        return ResponseEntity.ok(ApiResponse.success(chatRoom));
    }
}
