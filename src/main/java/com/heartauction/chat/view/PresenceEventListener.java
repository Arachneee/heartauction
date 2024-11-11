package com.heartauction.chat.view;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Slf4j
@RequiredArgsConstructor
@Component
public class PresenceEventListener implements ApplicationListener<AbstractSubProtocolEvent> {

    private static final String SUB_CHAT_DONATIONS_PREFIX = "/sub/chat/donations/";
    private static final String SUB_CHAT_DONATIONS_SUFFIX = "/subscribers";

    private final SimpMessageSendingOperations messagingTemplate;
    private final Map<String, List<MemberSession>> donationSubscribers = new ConcurrentHashMap<>();

    @Override
    public void onApplicationEvent(AbstractSubProtocolEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        MemberSession memberSession = MemberSession.from(sha);

        if (event instanceof SessionSubscribeEvent) {
            String destination = sha.getDestination();
            String donationId = getDonationIdFromDestination(destination);

            if (donationId != null) {
                donationSubscribers.computeIfAbsent(donationId, k -> new CopyOnWriteArrayList<>()).add(memberSession);
                broadcastSubscribers(donationId);
            }
            return;
        }
        if (event instanceof SessionUnsubscribeEvent || event instanceof SessionDisconnectEvent) {
            removeUserFromDonations(memberSession);
        }
    }

    private String getDonationIdFromDestination(String destination) {
        log.info("destination : {}", destination);
        if (destination != null && destination.startsWith(SUB_CHAT_DONATIONS_PREFIX)) {
            return destination.substring(SUB_CHAT_DONATIONS_PREFIX.length());
        }
        return null;
    }

    private void broadcastSubscribers(String donationId) {
        List<String> subscribers = donationSubscribers.get(donationId).stream()
                .map(MemberSession::name)
                .toList();

        messagingTemplate.convertAndSend(SUB_CHAT_DONATIONS_PREFIX + donationId + SUB_CHAT_DONATIONS_SUFFIX, subscribers);
    }

    private void removeUserFromDonations(MemberSession memberSession) {
        donationSubscribers.forEach((donationId, subscribers) -> {
            if (subscribers.remove(memberSession)) {
                broadcastSubscribers(donationId);
            }
        });
    }
}
