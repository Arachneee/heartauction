package com.heartauction.chat.view;

import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

@Slf4j
public record MemberSession(
        String sessionId,
        String name
) {

    public static MemberSession from(StompHeaderAccessor sha) {
        Map<String, Object> sessionAttributes = sha.getSessionAttributes();
        Long memberId = (Long) sessionAttributes.get("memberId");
        String memberName = (String) sessionAttributes.get("memberName");
        String sessionId = sha.getSessionId();

        log.info("Listener : sessionId, memberId, memberName = {}, {}, {}", sessionId, memberId, memberName);
        return new MemberSession(sessionId, memberName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberSession that = (MemberSession) o;
        return Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sessionId);
    }
}
