package com.heartauction.auction.domain.repository;

import com.heartauction.auction.domain.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
