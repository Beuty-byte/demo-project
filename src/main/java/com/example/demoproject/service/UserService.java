package com.example.demoproject.service;

import com.example.demoproject.dto.UserDTO;

import java.util.List;

public interface UserService {
    long save(UserDTO userDTO);

    List<UserDTO> find(Integer amountUsers, Integer page, String findBy, String param);
}