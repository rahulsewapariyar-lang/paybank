package com.jugger.paybank.service;

import org.springframework.stereotype.Service;

import com.jugger.paybank.dto.userdto.UserRequestDTO;
import com.jugger.paybank.dto.userdto.UserResponseDTO;
import com.jugger.paybank.mapper.UserMapper;
import com.jugger.paybank.model.User;
import com.jugger.paybank.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    // Implementation for user registration
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
      User user = userMapper.toEntity(userRequestDTO);
      userRepository.save(user);
      return userMapper.toDto(user);
    }
    public User findByApiKey(String apiKey){
                return userRepository.findByApiKey(apiKey)
        .orElseThrow(() -> new RuntimeException("User not found with API key: " + apiKey));
    }
    
}
