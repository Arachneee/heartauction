package com.heartauction.chat.application.response;

import com.heartauction.chat.domain.Chat;
import com.heartauction.auth.LoginMember;

public record ChatResponse(
        Long id,
        Long memberId,
        String senderName,
        String message
) {
    public static ChatResponse of(Chat savedChat, LoginMember member) {
        return new ChatResponse(savedChat.getId(), member.id(), member.name(), savedChat.getMessage());
    }
}
