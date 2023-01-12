package com.example.demoproject.mapper.impl;

import com.example.demoproject.dto.EmailDTO;
import com.example.demoproject.mapper.EmailMapper;
import com.example.demoproject.model.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailMapperImpl implements EmailMapper {
    @Override
    public Email toEntity(EmailDTO emailDTO) {
        Email email = new Email();
        email.setEmail(email.getEmail());
        return email;
    }
}
