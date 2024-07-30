package org.duckdns.wru.app.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRequest {
    private Long[] id;
    private String name;
}
