package com.jugger.paybank.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.jugger.paybank.dto.walletdto.WalletRequestDTO;
import com.jugger.paybank.dto.walletdto.WalletResponseDTO;
import com.jugger.paybank.mapper.WalletMapper;
import com.jugger.paybank.model.User;
import com.jugger.paybank.model.Wallet;
import com.jugger.paybank.repository.UserRepository;
import com.jugger.paybank.repository.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final WalletMapper walletMapper;
     public WalletService(WalletRepository walletRepository, UserRepository userRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.walletMapper = walletMapper;
    }
     public WalletResponseDTO createWallet(WalletRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletMapper.toEntity(dto);
        wallet.setUser(user);
        walletRepository.save(wallet);
        return walletMapper.toDto(wallet);
    }
        public WalletResponseDTO addFunds(String walletId, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(UUID.fromString(walletId))
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
        return walletMapper.toDto(wallet);
    }
    public Wallet findById(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

}
