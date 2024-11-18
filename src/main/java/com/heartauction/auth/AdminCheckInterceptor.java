package com.heartauction.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
@Component
public class AdminCheckInterceptor implements ChannelInterceptor, HandlerInterceptor {

    private static final String AUTH_KEY = "memberId";

    private final AuthService authService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(message);

        if (accessor.getMessageType() != null && accessor.getMessageType().equals(SimpMessageType.CONNECT)) {
            String memberIdValue = accessor.getFirstNativeHeader(AUTH_KEY);
            if (memberIdValue != null) {
                Long memberId = Long.parseLong(memberIdValue);
                LoginMember member = authService.login(memberId);

                log.info("Interceptor : memberId, memberName = {}, {}", memberId, member.name());
                accessor.getSessionAttributes().put(LoginMember.key, member);
            }
        }
        return message;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        if (HttpMethod.OPTIONS.equals(method)) {
            return true;
        }
        long memberId = Long.parseLong(request.getHeader(AUTH_KEY));
        LoginMember member = authService.login(memberId);
        request.setAttribute(LoginMember.key, member);
        return true;
    }
}
