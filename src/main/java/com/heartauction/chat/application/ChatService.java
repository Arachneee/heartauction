package com.heartauction.chat.application;

import com.heartauction.chat.application.request.ChatRequest;
import com.heartauction.chat.application.response.ChatResponse;
import com.heartauction.chat.domain.Chat;
import com.heartauction.chat.domain.ChatRepository;
import com.heartauction.member.application.MemberService;
import com.heartauction.member.application.response.MemberResponse;
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
    public ChatResponse createChat(Long donationId, ChatRequest request) {
        MemberResponse memberResponse = memberService.findById(request.senderId());

        Chat chat = request.toChat(donationId);
        Chat savedChat = chatRepository.save(chat);

        return ChatResponse.of(savedChat, memberResponse);
    }

    @Transactional(readOnly = true)
    public List<ChatResponse> findAllByDonationId(Long donationId) {
        List<Chat> chats = chatRepository.findAllByDonationId(donationId);

        return chats.stream()
                .map(chat -> ChatResponse.of(chat, memberService.findById(chat.getSenderId())))
                .toList();
    }
}
