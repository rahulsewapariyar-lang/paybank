package com.jugger.paybank.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
// import lombok.Data;
import jakarta.persistence.CascadeType;

@Data
@Entity
@Table(name = "users")
public class User {

    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String password;
    private String apiKey;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Wallet> wallets;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    
}
