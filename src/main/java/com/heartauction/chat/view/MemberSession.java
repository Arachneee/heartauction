package com.heartauction.chat.view;

import com.heartauction.auth.LoginMember;
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
        LoginMember member = (LoginMember) sessionAttributes.get(LoginMember.key);
        String sessionId = sha.getSessionId();

        log.info("Listener : sessionId, memberName = {}, {}, {}", sessionId, member.name());
        return new MemberSession(sessionId, member.name());
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
