package com.heartauction.chat.application.request;

import com.heartauction.chat.domain.Chat;

public record ChatRequest(
        Long senderId,
        String message
) {
    public Chat toChat(Long donationId) {
        return new Chat(donationId, senderId, message);
    }
}
