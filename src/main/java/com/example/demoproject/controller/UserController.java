package com.example.demoproject.controller;

import com.example.demoproject.dto.UserDTO;
import com.example.demoproject.model.User;
import com.example.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final String URI_FOR_RESPONSE = "/api/v1/users/%s";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        long userId = userService.save(userDTO);
        return ResponseEntity.created(URI.create(String.format(URI_FOR_RESPONSE, userId))).build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> find(@RequestParam(name = "amount", required = false, defaultValue = "5") Integer amountUsersAtPage,
                                  @RequestParam(name = "page",required = false, defaultValue = "0") Integer page,
                                  @RequestParam(name = "find-by", required = false, defaultValue = "null") String findBy,
                                  @RequestParam(name = "param" , required = false, defaultValue = "name") String param) {
        List<UserDTO> users = userService.find(amountUsersAtPage, page, findBy, param);
        return ResponseEntity.ok(users);
    }
}