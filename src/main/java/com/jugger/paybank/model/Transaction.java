package com.jugger.paybank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

    private UUID id;
    private Wallet wallet;
    private String type;
    private String status;
    private BigDecimal amount;
    private String currency = "Rs";
    @Column(columnDefinition = "jsonb")
    private String metadata; // store JSON as string

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Getters and setters
    
}
