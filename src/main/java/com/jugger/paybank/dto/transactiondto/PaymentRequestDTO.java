package com.jugger.paybank.dto.transactiondto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PaymentRequestDTO {

    @NotNull(message = "Wallet ID is required")
    private UUID walletId;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Amount must have at most 10 integer digits and 2 decimal places")
    private BigDecimal amount;
    
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid 3-letter currency code")
    private String currency = "NPR";
    
    @NotBlank(message = "Transaction type is required")
    @Pattern(regexp = "^(DEBIT|CREDIT|TRANSFER)$", message = "Type must be DEBIT, CREDIT, or TRANSFER")
    private String type;
    
    @NotBlank(message = "Merchant is required")
    @Size(max = 100, message = "Merchant name must not exceed 100 characters")
    private String merchant;
    
    private Map<String, Object> metadata;

    // Getters and setters
    
}
