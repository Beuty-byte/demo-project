package com.example.demoproject.dao;

import com.example.demoproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    long save(User user);

    Optional<User> findByEmail(String email);

    List<User> findByName(Integer amountUsers, Integer page, String param);

    List<User> findByBirthday(Integer amountUsers, Integer page, String param);

}
