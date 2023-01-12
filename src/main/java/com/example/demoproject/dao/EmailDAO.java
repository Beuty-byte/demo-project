package com.example.demoproject.dao;

import com.example.demoproject.model.Email;

import java.util.Optional;

public interface EmailDAO {
    Optional<Email> find(String email);

    long save(Email email);

    long update(Email email);

    void delete(Email email);

    Optional<Email> findByEmail(String param);
}
