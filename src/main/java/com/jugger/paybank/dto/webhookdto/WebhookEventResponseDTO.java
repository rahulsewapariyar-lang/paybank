package com.jugger.paybank.dto.webhookdto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class WebhookEventResponseDTO {
    
    private UUID id;
    private UUID transactionId;
    private String eventType;
    private Boolean delivered;
    private Integer attemptCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}