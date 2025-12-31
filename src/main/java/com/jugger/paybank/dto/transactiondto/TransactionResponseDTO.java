package com.jugger.paybank.dto.transactiondto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import lombok.Data;

@Data
public class TransactionResponseDTO {

    private UUID transactionId;
    private UUID walletId;
    private String type;
    private String status;
    private BigDecimal amount;
    private String currency;
    private Map<String, Object> metadata;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and setters
    
}
