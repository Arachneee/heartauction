package com.heartauction.chat.view;

import com.heartauction.member.application.MemberService;
import com.heartauction.member.application.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandshakeInterceptor implements ChannelInterceptor {

    private final MemberService memberService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(message);

        if (accessor.getMessageType() != null && accessor.getMessageType().equals(SimpMessageType.CONNECT)) {
            String memberIdValue = accessor.getFirstNativeHeader("memberId");
            if (memberIdValue != null) {
                Long memberId = Long.parseLong(memberIdValue);
                MemberResponse member = memberService.findById(memberId);

                log.info("Interceptor : memberId, memberName = {}, {}", memberId, member.name());
                accessor.getSessionAttributes().put("memberId", memberId);
                accessor.getSessionAttributes().put("memberName", member.name());
            }
        }
        return message;
    }
}
