package com.heartauction.chat.view;

import com.heartauction.member.domain.Member;
import com.heartauction.member.domain.MemberRepository;
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

    private final MemberRepository memberRepository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(message);

        if (accessor.getMessageType() != null && accessor.getMessageType().equals(SimpMessageType.CONNECT)) {
            String memberIdValue = accessor.getFirstNativeHeader("memberId");
            if (memberIdValue != null) {
                Long memberId = Long.parseLong(memberIdValue);
                Member member = memberRepository.findById(memberId)
                        .orElseThrow(() -> new IllegalArgumentException("Member not found"));

                log.info("Interceptor : memberId, memberName = {}, {}", memberId, member.getName());
                accessor.getSessionAttributes().put("memberId", memberId);
                accessor.getSessionAttributes().put("memberName", member.getName());
            }
        }
        return message;
    }
}
