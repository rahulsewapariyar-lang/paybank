package com.jugger.paybank.dto.walletdto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class WalletRequestDTO {
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid 3-letter currency code")
    private String currency = "NPR"; // default currency
}
