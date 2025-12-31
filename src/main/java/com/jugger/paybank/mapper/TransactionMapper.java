package com.jugger.paybank.mapper;

import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jugger.paybank.dto.transactiondto.PaymentRequestDTO;
import com.jugger.paybank.dto.transactiondto.TransactionResponseDTO;
import com.jugger.paybank.model.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "wallet", ignore = true) // Set manually in service
    @Mapping(target = "metadata", expression = "java(convertMapToJson(dto.getMetadata()))")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "webhookEvents", ignore = true)
    Transaction toEntity(PaymentRequestDTO dto);

    @Mapping(source = "id", target = "transactionId")
    @Mapping(source = "wallet.id", target = "walletId")
    @Mapping(target = "metadata", expression = "java(parseMetadata(transaction.getMetadata()))")
    TransactionResponseDTO toDto(Transaction transaction);

    // Helper method to convert Map to JSON string
    default String convertMapToJson(Map<String, Object> metadata) {
        if (metadata == null || metadata.isEmpty()) {
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(metadata);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    // Helper method to convert JSON string to Map
    default Map<String, Object> parseMetadata(String metadata) {
        if (metadata == null || metadata.trim().isEmpty()) {
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(metadata, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            return null;
        }
    }
}
