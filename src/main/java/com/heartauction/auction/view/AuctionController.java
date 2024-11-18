package com.heartauction.auction.view;

import com.heartauction.auction.application.AuctionService;
import com.heartauction.auction.application.request.AuctionRequest;
import com.heartauction.auction.application.response.AuctionResponse;
import com.heartauction.auction.application.response.AuctionSummaryResponse;
import com.heartauction.auth.Login;
import com.heartauction.auth.LoginMember;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuctionController {

    private final AuctionService auctionService;

    // TODO : 페이징
    @GetMapping("/auctions")
    public ResponseEntity<List<AuctionSummaryResponse>> findAll() {
        return ResponseEntity.ok(auctionService.findAll());
    }

    @GetMapping("/auctions/{auctionId}")
    public ResponseEntity<AuctionResponse> findById(@PathVariable Long auctionId) {
        return ResponseEntity.ok(auctionService.findById(auctionId));
    }

    @PostMapping("/auctions")
    public ResponseEntity<Void> create(@Login LoginMember member, @RequestBody AuctionRequest request) {
        auctionService.create(member, request);
        return ResponseEntity.ok().build();
    }
}
