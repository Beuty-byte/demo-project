package com.example.demoproject.mapper.impl;

import com.example.demoproject.dto.PhoneDTO;
import com.example.demoproject.mapper.PhoneMapper;
import com.example.demoproject.model.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapperImpl implements PhoneMapper {
    @Override
    public Phone toEntity(PhoneDTO phoneDTO) {
        Phone phone = new Phone();
        phone.setPhoneNumber(phoneDTO.getPhone());
        return phone;
    }
}
