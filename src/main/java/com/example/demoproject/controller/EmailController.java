package com.example.demoproject.controller;

import com.example.demoproject.dto.EmailDTO;
import com.example.demoproject.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmailDTO emailDTO) {
        emailService.create(emailDTO);
        return null;
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody EmailDTO emailDTO) {
        emailService.update(emailDTO);
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody EmailDTO emailDTO) {
        emailService.delete(emailDTO);
        return null;
    }
}
