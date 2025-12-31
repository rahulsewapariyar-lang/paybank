package com.jugger.paybank.mapper;

import com.jugger.paybank.dto.userdto.UserRequestDTO;
import com.jugger.paybank.dto.userdto.UserResponseDTO;
import com.jugger.paybank.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apiKey", ignore = true) // Generated separately in service
    @Mapping(target = "wallets", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserRequestDTO dto);
    
    UserResponseDTO toDto(User user);
}
