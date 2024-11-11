package com.heartauction.donation.application;

import com.heartauction.donation.application.response.DonationResponse;
import com.heartauction.donation.application.response.DonationSummaryResponse;
import com.heartauction.donation.domain.Donation;
import com.heartauction.donation.domain.DonationRepository;
import com.heartauction.member.application.MemberService;
import com.heartauction.member.application.response.MemberResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public List<DonationSummaryResponse> findAll() {
        return donationRepository.findAll().stream()
                .map(DonationSummaryResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public DonationResponse findById(Long donationId) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new IllegalArgumentException("donation not found"));
        MemberResponse member = memberService.findById(donation.getMemberId());

        return DonationResponse.of(donation, member);
    }
}
