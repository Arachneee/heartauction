package com.heartauction.auction.application.response;

import com.heartauction.auction.domain.Auction;
import com.heartauction.auction.domain.Donation;
import java.time.LocalDateTime;

public record AuctionSummaryResponse(
        Long id,
        String title,
        Long startPrice,
        LocalDateTime startDateTime
) {
    public static AuctionSummaryResponse from(Auction auction) {
        Donation donation = auction.getDonation();

        return new AuctionSummaryResponse(auction.getId(), donation.getTitle(), auction.getStartPrice().getValue(), auction.getStartDateTime());
    }
}
