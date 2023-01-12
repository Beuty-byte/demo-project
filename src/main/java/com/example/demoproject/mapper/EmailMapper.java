package com.example.demoproject.mapper;

import com.example.demoproject.dto.EmailDTO;
import com.example.demoproject.model.Email;

public interface EmailMapper {
    Email toEntity(EmailDTO emailDTO);
}
