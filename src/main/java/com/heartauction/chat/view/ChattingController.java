package com.heartauction.chat.view;

import com.heartauction.chat.application.ChatService;
import com.heartauction.chat.application.request.ChatRequest;
import com.heartauction.chat.application.response.ChatResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChattingController {

    private final ChatService chatService;

    @MessageMapping("/chat/auctions/{auctionId}")
    @SendTo("/sub/chat/auctions/{auctionId}")
    public ChatResponse createChat(@DestinationVariable Long auctionId, @Payload String message, StompHeaderAccessor headerAccessor) {
        Long memberId = (Long) headerAccessor.getSessionAttributes().get("memberId");

        return chatService.createChat(auctionId, new ChatRequest(memberId, message));
    }

    // TODO : 페이징
    @ResponseBody
    @GetMapping("/chats")
    public ResponseEntity<List<ChatResponse>> findAll(@RequestParam("auctionId") Long auctionId) {
        log.info("GET /chats 호출됨, donationId: {}", auctionId); // 로그 추가
        List<ChatResponse> chatResponses = chatService.findAllByAuctionId(auctionId);
        return ResponseEntity.ok(chatResponses);
    }
}
