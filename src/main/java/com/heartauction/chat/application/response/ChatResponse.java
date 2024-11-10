package com.heartauction.chat.application.response;

import com.heartauction.chat.domain.Chat;
import com.heartauction.member.application.response.MemberResponse;

public record ChatResponse(
        Long id,
        String senderName,
        String message
) {
    public static ChatResponse of(Chat savedChat, MemberResponse member) {
        return new ChatResponse(savedChat.getId(), member.name(), savedChat.getMessage());
    }
}
