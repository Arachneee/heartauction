package com.heartauction.auction.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuctionTest {

    @DisplayName("경매는 생성되면 초기 상태가 NOT_STARTED 이다.")
    @Test
    void create() {
        Donation donation = new Donation("제목", "내용",1L);
        Auction auction = Auction.create(donation, 1000L, LocalDateTime.of(2024, Month.DECEMBER, 31, 0, 0));

        assertThat(auction.getStatus()).isEqualTo(AuctionStatus.NOT_STARTED);
    }
}
