package com.heartauction.chat.domain;

import java.util.HashSet;
import java.util.Set;
import org.springframework.web.socket.WebSocketSession;

public class ChatRoom {

    private final Long donationId;

    private final Set<WebSocketSession> sessions = new HashSet<>();

    public ChatRoom(Long donationId) {
        this.donationId = donationId;
    }
}
