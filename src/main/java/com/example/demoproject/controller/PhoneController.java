package com.example.demoproject.controller;

import com.example.demoproject.dto.PhoneDTO;
import com.example.demoproject.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/phones")
public class PhoneController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PhoneDTO phoneDTO) {
        phoneService.create(phoneDTO);
        return null;
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PhoneDTO phoneDTO) {
        phoneService.update(phoneDTO);
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody PhoneDTO phoneDTO) {
        phoneService.delete(phoneDTO);
        return null;
    }
}
