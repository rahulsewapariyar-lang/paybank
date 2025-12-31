package com.jugger.paybank.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jugger.paybank.dto.transactiondto.PaymentRequestDTO;
import com.jugger.paybank.dto.transactiondto.TransactionResponseDTO;
import com.jugger.paybank.mapper.TransactionMapper;
import com.jugger.paybank.model.Transaction;
import com.jugger.paybank.model.Wallet;
import com.jugger.paybank.repository.TransactionRepository;
import com.jugger.paybank.repository.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final TransactionMapper transactionMapper;

      public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.transactionMapper = transactionMapper;
    }
    @Transactional
    public TransactionResponseDTO processPayment(UUID walletId, PaymentRequestDTO dto) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet.getBalance().compareTo(dto.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        // Create transaction (pending)
        Transaction transaction = transactionMapper.toEntity(dto);
        transaction.setWallet(wallet);
        transactionRepository.save(transaction);

        // Simulate payment
        boolean success = simulatePayment();

        if (success) {
            wallet.setBalance(wallet.getBalance().subtract(dto.getAmount()));
            transaction.setStatus("success");
        } else {
            transaction.setStatus("failed");
        }

        walletRepository.save(wallet);
        transactionRepository.save(transaction);

        return transactionMapper.toDto(transaction);
    }
    private boolean simulatePayment() {
        Random random = new Random();
        return random.nextDouble() < 0.85; // 85% success rate
    }
      @Transactional
    public TransactionResponseDTO processRefund(UUID transactionId) {
        Transaction original = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if (!"success".equals(original.getStatus())) {
            throw new RuntimeException("Only successful transactions can be refunded");
        }

        Wallet wallet = original.getWallet();
        wallet.setBalance(wallet.getBalance().add(original.getAmount()));

        Transaction refund = new Transaction();
        refund.setWallet(wallet);
        refund.setAmount(original.getAmount());
        refund.setType("refund");
        refund.setStatus("success");
        refund.setCurrency("NPR");

        walletRepository.save(wallet);
        transactionRepository.save(refund);

        return transactionMapper.toDto(refund);
    }
    
}
