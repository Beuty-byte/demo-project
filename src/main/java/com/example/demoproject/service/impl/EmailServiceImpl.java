package com.example.demoproject.service.impl;

import com.example.demoproject.dao.EmailDAO;
import com.example.demoproject.dto.EmailDTO;
import com.example.demoproject.exception.EmailModifyException;
import com.example.demoproject.mapper.EmailMapper;
import com.example.demoproject.model.Email;
import com.example.demoproject.model.User;
import com.example.demoproject.security.CustomUserDetails;
import com.example.demoproject.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailMapper emailMapper;
    private final EmailDAO emailDAO;

    @Autowired
    public EmailServiceImpl(EmailMapper emailMapper, EmailDAO emailDAO) {
        this.emailMapper = emailMapper;
        this.emailDAO = emailDAO;
    }

    @Override
    public long create(EmailDTO emailDTO) {
        checkEmailForExistence(emailDTO);
        Email email = emailMapper.toEntity(emailDTO);
        email.setUser(getUserFromSecurityContext());
        return emailDAO.save(email);
    }

    @Override
    public long update(EmailDTO emailDTO) {
        if (checkEmailForRelationshipBetweenHolderAndCurrentUser(emailDTO.getEmail())) {
            Email email = emailMapper.toEntity(emailDTO);
            email.setUser(getUserFromSecurityContext());
            return emailDAO.update(email);
        }
        throw new EmailModifyException("You can't modify this email");
    }

    @Override
    public void delete(EmailDTO emailDTO) {
        if (checkEmailForRelationshipBetweenHolderAndCurrentUser(emailDTO.getEmail())) {
            Email email = emailMapper.toEntity(emailDTO);
            email.setUser(getUserFromSecurityContext());
            emailDAO.delete(email);
        }
        throw new EmailModifyException("You can't modify this email");
    }

    private boolean checkEmailForRelationshipBetweenHolderAndCurrentUser(String email) {
        Email foundEmail = emailDAO.find(email)
                .orElseThrow(() -> new EmailModifyException("Email not found"));
        return foundEmail.getUser().equals(getUserFromSecurityContext());
    }

    private User getUserFromSecurityContext() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return customUserDetails.getUser();
    }

    private void checkEmailForExistence(EmailDTO emailDTO) {
        if (emailDAO.find(emailDTO.getEmail()).isPresent()) {
            throw new EmailModifyException("Email already exist");
        }
    }

}
