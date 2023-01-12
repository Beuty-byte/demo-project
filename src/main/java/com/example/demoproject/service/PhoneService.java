package com.example.demoproject.service;

import com.example.demoproject.dto.PhoneDTO;

public interface PhoneService {
    long create(PhoneDTO phoneDTO);

    long update(PhoneDTO phoneDTO);

    void delete(PhoneDTO phoneDTO);
}
