package com.heartauction.donation.domain;

import com.heartauction.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Donation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long startAmount;

    public Donation(String title, String description, Long memberId, Long startAmount) {
        this.title = title;
        this.description = description;
        this.memberId = memberId;
        this.startAmount = startAmount;
    }
}
