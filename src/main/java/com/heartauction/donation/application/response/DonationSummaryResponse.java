package com.heartauction.donation.application.response;

import com.heartauction.donation.domain.Donation;

public record DonationSummaryResponse(
        Long id,
        String title,
        Long presentPrice
) {
    public static DonationSummaryResponse from(Donation donation) {
        return new DonationSummaryResponse(donation.getId(), donation.getTitle(), donation.getStartAmount());
    }
}
