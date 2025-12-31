package com.jugger.paybank.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "webhook_events")
public class WebhookEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotNull(message = "Transaction is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @NotBlank(message = "Event type is required")
    @Pattern(regexp = "^(payment_success|payment_failed|refund_success|refund_failed)$", 
             message = "Event type must be payment_success, payment_failed, refund_success, or refund_failed")
    @Column(nullable = false, length = 50)
    private String eventType;
    
    @NotNull(message = "Delivered status is required")
    @Column(nullable = false)
    private Boolean delivered = false;
    
    @NotNull(message = "Attempt count is required")
    @Min(value = 0, message = "Attempt count cannot be negative")
    @Max(value = 10, message = "Maximum 10 attempts allowed")
    @Column(nullable = false)
    private Integer attemptCount = 0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Getters and setters   
}
