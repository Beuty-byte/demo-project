package com.example.demoproject.mapper;

import com.example.demoproject.dto.PhoneDTO;
import com.example.demoproject.model.Phone;

public interface PhoneMapper {
    Phone toEntity(PhoneDTO phoneDTO);
}
