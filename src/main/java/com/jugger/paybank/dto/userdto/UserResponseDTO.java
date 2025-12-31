package com.jugger.paybank.dto.userdto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class UserResponseDTO {

    private UUID id;
    private String username;
    private String email;
    private String apiKey;
    private LocalDateTime createdAt;

    
}
