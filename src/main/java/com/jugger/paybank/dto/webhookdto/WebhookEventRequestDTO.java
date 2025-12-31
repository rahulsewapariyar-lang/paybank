package com.jugger.paybank.dto.webhookdto;

import java.util.UUID;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class WebhookEventRequestDTO {
    
    @NotNull(message = "Transaction ID is required")
    private UUID transactionId;
    
    @NotBlank(message = "Event type is required")
    @Pattern(regexp = "^(payment_success|payment_failed|refund_success|refund_failed)$", 
             message = "Event type must be payment_success, payment_failed, refund_success, or refund_failed")
    private String eventType;
}