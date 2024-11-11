package com.heartauction.donation.view;

import com.heartauction.donation.application.DonationService;
import com.heartauction.donation.application.response.DonationResponse;
import com.heartauction.donation.application.response.DonationSummaryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DonationController {

    private final DonationService donationService;

    // TODO : 페이징
    @GetMapping("/donations")
    public ResponseEntity<List<DonationSummaryResponse>> findAll() {
        return ResponseEntity.ok(donationService.findAll());
    }

    @GetMapping("/donations/{donationId}")
    public ResponseEntity<DonationResponse> findById(@PathVariable Long donationId) {
        return ResponseEntity.ok(donationService.findById(donationId));
    }
}
