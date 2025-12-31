package com.jugger.paybank.dto.walletdto;

import java.util.UUID;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class WalletRequestDTO {
    
    @NotNull(message = "User ID is required")
    private UUID userId;
    
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid 3-letter currency code")
    private String currency = "NPR"; // default currency
}
