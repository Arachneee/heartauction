package com.heartauction.auction.domain.repository;

import com.heartauction.auction.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
