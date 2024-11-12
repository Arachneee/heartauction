package com.heartauction.auction.domain;

import com.heartauction.common.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Auction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_id", nullable = false)
    private Donation donation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "start_price"))
    })
    private AuctionPrice startPrice;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuctionStatus status;

    private Auction(Donation donation, AuctionPrice startPrice, LocalDateTime startDateTime, AuctionStatus status) {
        this.donation = donation;
        this.startPrice = startPrice;
        this.startDateTime = startDateTime;
        this.status = status;
    }

    public static Auction create(Donation donation, Long startingPrice, LocalDateTime startDateTime) {
        AuctionPrice auctionPrice = new AuctionPrice(startingPrice);
        return new Auction(donation, auctionPrice, startDateTime, AuctionStatus.NOT_STARTED);
    }
}
