package com.heartauction.chat.application.request;

import com.heartauction.auth.LoginMember;
import com.heartauction.chat.domain.Chat;

public record ChatRequest(
        LoginMember sender,
        String message
) {
    public Chat toChat(Long auctionId) {
        return new Chat(auctionId, sender.id(), message);
    }
}
