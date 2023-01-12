package com.example.demoproject.dao;

import com.example.demoproject.model.Phone;

import java.util.Optional;

public interface PhoneDAO {
    Optional<Phone> find(String phone);

    long save(Phone phone);

    long update(Phone phone);

    void delete(Phone phone);

    Optional<Phone> findByNumber(String param);
}
