package com.heartauction.chat.application.request;

import com.heartauction.chat.domain.Chat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChatRequest(
        @NotNull
        Long senderId,
        @NotBlank
        String message
) {
    public Chat toChat(Long auctionId) {
        return new Chat(auctionId, senderId, message);
    }
}
