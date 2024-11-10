package com.heartauction.chat.view;

import com.heartauction.chat.application.ChatService;
import com.heartauction.chat.application.request.ChatRequest;
import com.heartauction.chat.application.response.ChatResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class ChattingController {

    private final ChatService chatService;

    @MessageMapping("/donations/{donationId}")
    @SendTo("/sub/donations/{donationId}")
    public ChatResponse createChat(@DestinationVariable Long donationId, @Payload ChatRequest request) {
        return chatService.createChat(donationId, request);
    }

    // TODO : 페이징
    @ResponseBody
    @GetMapping("/donations/{donationId}/chatting")
    public ResponseEntity<List<ChatResponse>> findAll(@PathVariable Long donationId) {
        return ResponseEntity.ok(chatService.findAllByDonationId(donationId));
    }
}
