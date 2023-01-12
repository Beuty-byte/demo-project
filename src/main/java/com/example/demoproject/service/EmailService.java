package com.example.demoproject.service;

import com.example.demoproject.dto.EmailDTO;

public interface EmailService {
    long create(EmailDTO emailDTO);
    long update(EmailDTO emailDTO);
    void delete(EmailDTO emailDTO);
}
