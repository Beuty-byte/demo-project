package com.example.demoproject.service.impl;


import com.example.demoproject.dao.EmailDAO;
import com.example.demoproject.dao.PhoneDAO;
import com.example.demoproject.dao.UserDAO;
import com.example.demoproject.dto.UserDTO;
import com.example.demoproject.exception.EmailNotFoundException;
import com.example.demoproject.exception.UserNotFoundException;
import com.example.demoproject.exception.WrongDataForSearchException;
import com.example.demoproject.mapper.UserMapper;
import com.example.demoproject.model.Account;
import com.example.demoproject.model.Email;
import com.example.demoproject.model.Phone;
import com.example.demoproject.model.User;
import com.example.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserDAO userDAO;

    private final PhoneDAO phoneDAO;

    private final EmailDAO emailDAO;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserDAO userDAO, PhoneDAO phoneDAO, EmailDAO emailDAO) {
        this.userMapper = userMapper;
        this.userDAO = userDAO;
        this.phoneDAO = phoneDAO;
        this.emailDAO = emailDAO;
    }

    @Transactional
    @Override
    public long save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setAccount(new Account());
        return userDAO.save(user);
    }


    @Override
    public List<UserDTO> find(Integer amountUsers, Integer page, String findBy, String param) {
        if (param.equals("dateOfBirth")) {
            List<User> users = userDAO.findByBirthday(amountUsers, page, param);
            return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
        } else if (param.equals("name")) {
            List<User> users = userDAO.findByName(amountUsers, page, param);
            return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
        } else if (param.equals("phone")) {
            User user = phoneDAO.findByNumber(param)
                    .map(Phone::getUser).orElseThrow(() -> new UserNotFoundException("User by phone not found"));
            return List.of(userMapper.toDTO(user));
        } else if (param.equals("email")) {
            User user = emailDAO.findByEmail(param)
                    .map(Email::getUser).orElseThrow(() -> new EmailNotFoundException("User by email not found"));
            return List.of(userMapper.toDTO(user));
        }
        throw new WrongDataForSearchException("Wrong data");
    }
}
