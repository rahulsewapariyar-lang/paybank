package com.jugger.paybank.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jugger.paybank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
     List<Transaction> findByWalletId(UUID walletId);
}
