package com.heartauction.auction.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AuctionPrice {

    private static final long PRICE_UNIT = 1000L;

    private Long value;

    public AuctionPrice(Long value) {
        validatePrice(value);
        this.value = value;
    }

    private void validatePrice(Long value) {
        if (value == null || value < PRICE_UNIT || (value % PRICE_UNIT) != 0L) {
            throw new IllegalArgumentException("잘못된 가격입니다.");
        }
    }

    public Long getValue() {
        return value;
    }
}
