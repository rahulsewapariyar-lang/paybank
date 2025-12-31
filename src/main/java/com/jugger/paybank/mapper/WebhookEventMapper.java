package com.jugger.paybank.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jugger.paybank.dto.webhookdto.WebhookEventRequestDTO;
import com.jugger.paybank.dto.webhookdto.WebhookEventResponseDTO;
import com.jugger.paybank.model.WebhookEvent;

@Mapper(componentModel = "spring")
public interface WebhookEventMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "transaction", ignore = true) // Set manually in service
    @Mapping(target = "delivered", constant = "false")
    @Mapping(target = "attemptCount", constant = "0")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    WebhookEvent toEntity(WebhookEventRequestDTO dto);
    
    @Mapping(source = "transaction.id", target = "transactionId")
    WebhookEventResponseDTO toDto(WebhookEvent webhookEvent);
}
