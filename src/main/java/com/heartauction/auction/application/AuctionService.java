package com.heartauction.auction.application;

import com.heartauction.auction.application.request.AuctionRequest;
import com.heartauction.auction.application.response.AuctionResponse;
import com.heartauction.auction.application.response.AuctionSummaryResponse;
import com.heartauction.auction.domain.Auction;
import com.heartauction.auction.domain.Donation;
import com.heartauction.auction.domain.repository.DonationRepository;
import com.heartauction.auction.domain.repository.AuctionRepository;
import com.heartauction.member.application.MemberService;
import com.heartauction.auth.LoginMember;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final DonationRepository donationRepository;
    private final MemberService memberService;

    @Transactional
    public void create(LoginMember member, AuctionRequest request) {
        log.info("auction request : {}", request);
        Donation donation = new Donation(request.title(), request.description(), member.id());
        donationRepository.save(donation);

        Auction auction = Auction.create(donation, request.startingPrice(), request.startingDateTime());
        auctionRepository.save(auction);
    }

    @Transactional(readOnly = true)
    public List<AuctionSummaryResponse> findAll() {
        return auctionRepository.findAll().stream()
                .map(AuctionSummaryResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public AuctionResponse findById(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new IllegalArgumentException("auction not found"));

        Donation donation = auction.getDonation();
        LoginMember member = memberService.login(donation.getMemberId());

        return AuctionResponse.of(auction, member);
    }
}
