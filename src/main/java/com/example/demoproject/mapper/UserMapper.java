package com.example.demoproject.mapper;

import com.example.demoproject.dto.UserDTO;
import com.example.demoproject.model.User;

public interface UserMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
}
