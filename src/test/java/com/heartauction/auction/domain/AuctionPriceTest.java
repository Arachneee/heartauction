package com.heartauction.auction.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AuctionPriceTest {

    @DisplayName("경매 물품의 가격 단위는 1000원이다. - True")
    @ParameterizedTest
    @ValueSource(longs = {1000L, 10000L})
    void unitTrue(Long value) {
        AuctionPrice auctionPrice = new AuctionPrice(value);

        assertThat(auctionPrice.getValue()).isEqualTo(value);
    }

    @DisplayName("경매 물품의 가격 단위는 1000원이다. - False")
    @ParameterizedTest
    @ValueSource(longs = {100L, 10100L, -1L, 0L, 999L})
    void unitFalse(Long value) {
        assertThatThrownBy(() -> new AuctionPrice(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
