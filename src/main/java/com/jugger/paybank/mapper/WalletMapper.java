package com.jugger.paybank.mapper;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jugger.paybank.dto.walletdto.WalletRequestDTO;
import com.jugger.paybank.dto.walletdto.WalletResponseDTO;
import com.jugger.paybank.model.Wallet;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", expression = "java(java.math.BigDecimal.ZERO)")
    @Mapping(target = "user", ignore = true) // Set manually in service
    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Wallet toEntity(WalletRequestDTO dto);

    @Mapping(source = "id", target = "walletId")
    @Mapping(source = "user.id", target = "userId")
    WalletResponseDTO toDto(Wallet wallet);
}
