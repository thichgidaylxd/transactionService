package com.example.transactionService;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fund_transactions")
@Getter
@Setter
public class FundTransaction {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "sepay_id")
    private Long sepayId;

    private String gateway;

    private LocalDateTime transactionDate;

    private String accountNumber;

    private String code;

    private String content;

    private String transferType;

    private BigDecimal transferAmount;

    private BigDecimal accumulated;

    private String subAccount;

    private String referenceCode;

    @Column(columnDefinition = "TEXT")
    private String description;
}