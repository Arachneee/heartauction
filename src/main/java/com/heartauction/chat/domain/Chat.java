package com.heartauction.chat.domain;

import com.heartauction.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Chat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long donationId;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false)
    private String message;

    public Chat(Long donationId, Long senderId, String message) {
        this.donationId = donationId;
        this.senderId = senderId;
        this.message = message;
    }
}
