package com.heartauction.chat.application;

import com.heartauction.chat.application.request.ChatRequest;
import com.heartauction.chat.application.response.ChatResponse;
import com.heartauction.chat.domain.Chat;
import com.heartauction.chat.domain.ChatRepository;
import com.heartauction.member.application.MemberService;
import com.heartauction.auth.LoginMember;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final MemberService memberService;
    private final ChatRepository chatRepository;

    @Transactional
    public ChatResponse createChat(Long auctionId, ChatRequest request) {
        LoginMember loginMember = request.sender();

        Chat chat = request.toChat(auctionId);
        Chat savedChat = chatRepository.save(chat);

        return ChatResponse.of(savedChat, loginMember);
    }

    @Transactional(readOnly = true)
    public List<ChatResponse> findAllByAuctionId(Long auctionId) {
        List<Chat> chats = chatRepository.findAllByAuctionId(auctionId);

        return chats.stream()
                .map(chat -> ChatResponse.of(chat, memberService.login(chat.getSenderId())))
                .toList();
    }
}
