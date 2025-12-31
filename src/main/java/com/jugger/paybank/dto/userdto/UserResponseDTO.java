package com.jugger.paybank.dto.userdto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String apiKey;
    private LocalDateTime createdAt;

    
}
