package com.heartauction.auction.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AuctionRequest(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        Long startingPrice,
        @NotNull
        LocalDateTime startingDateTime
) {
}
