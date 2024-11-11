package com.heartauction.donation.application.response;

import com.heartauction.donation.domain.Donation;
import com.heartauction.member.application.response.MemberResponse;

public record DonationResponse(
        Long id,
        String title,
        String description,
        String memberName,
        Long presentPrice
) {
    public static DonationResponse of(Donation donation, MemberResponse member) {
        return new DonationResponse(
                donation.getId(), donation.getTitle(), donation.getDescription(), member.name(),
                donation.getStartAmount()
        );
    }
}
