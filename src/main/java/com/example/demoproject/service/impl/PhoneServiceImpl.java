package com.example.demoproject.service.impl;

import com.example.demoproject.dao.PhoneDAO;
import com.example.demoproject.dto.PhoneDTO;
import com.example.demoproject.exception.EmailModifyException;
import com.example.demoproject.exception.PhoneModifyException;
import com.example.demoproject.mapper.PhoneMapper;
import com.example.demoproject.model.Phone;
import com.example.demoproject.model.User;
import com.example.demoproject.security.CustomUserDetails;
import com.example.demoproject.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneMapper phoneMapper;
    private final PhoneDAO phoneDAO;

    @Autowired
    public PhoneServiceImpl(PhoneMapper phoneMapper, PhoneDAO phoneDAO) {
        this.phoneMapper = phoneMapper;
        this.phoneDAO = phoneDAO;
    }

    @Override
    public long create(PhoneDTO phoneDTO) {
        checkPhoneForExistence(phoneDTO);
        Phone phone = phoneMapper.toEntity(phoneDTO);
        phone.setUser(getUserFromSecurityContext());
        return phoneDAO.save(phone);
    }

    @Override
    public long update(PhoneDTO phoneDTO) {
        if (checkPhoneForRelationshipBetweenHolderAndCurrentUser(phoneDTO.getPhone())) {
            Phone phone = phoneMapper.toEntity(phoneDTO);
            phone.setUser(getUserFromSecurityContext());
            return phoneDAO.update(phone);
        }
        throw new PhoneModifyException("You can't modify this phone");
    }

    @Override
    public void delete(PhoneDTO phoneDTO) {
        if (checkPhoneForRelationshipBetweenHolderAndCurrentUser(phoneDTO.getPhone())) {
            Phone phone = phoneMapper.toEntity(phoneDTO);
            phone.setUser(getUserFromSecurityContext());
            phoneDAO.delete(phone);
        }
        throw new EmailModifyException("You can't modify this email");
    }

    private boolean checkPhoneForRelationshipBetweenHolderAndCurrentUser(String phone) {
        Phone foundPhone = phoneDAO.find(phone)
                .orElseThrow(() -> new PhoneModifyException("Phone not found"));
        return foundPhone.getUser().equals(getUserFromSecurityContext());
    }

    private void checkPhoneForExistence(PhoneDTO phoneDTO) {
        if (phoneDAO.find(phoneDTO.getPhone()).isPresent()) {
            throw new PhoneModifyException("Phone already exist");
        }
    }

    private User getUserFromSecurityContext() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return customUserDetails.getUser();
    }
}
