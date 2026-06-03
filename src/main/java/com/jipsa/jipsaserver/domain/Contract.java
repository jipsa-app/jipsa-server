package com.jipsa.jipsaserver.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String type; // MONTHLY, JEONSE, SALE

    @Column(nullable = false)
    private String address;

    @Column(name = "contract_date")
    private LocalDate contractDate;

    @Column(name = "balance_date")
    private LocalDate balanceDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    private String memo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public void update(String address, String type, LocalDate contractDate,
                       LocalDate balanceDate, LocalDate expiryDate, String memo) {
        this.address = address;
        this.type = type;
        this.contractDate = contractDate;
        this.balanceDate = balanceDate;
        this.expiryDate = expiryDate;
        this.memo = memo;
    }
}
