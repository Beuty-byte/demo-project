package com.example.demoproject.mapper.impl;


import com.example.demoproject.dto.UserDTO;
import com.example.demoproject.mapper.UserMapper;
import com.example.demoproject.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setDateOfBirthday(userDTO.getDateOfBirthday());
        return user;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setDateOfBirthday(user.getDateOfBirthday());
        return userDTO;
    }
}
