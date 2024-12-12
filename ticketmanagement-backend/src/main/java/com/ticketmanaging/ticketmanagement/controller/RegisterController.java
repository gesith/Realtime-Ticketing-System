package com.ticketmanaging.ticketmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ticketmanaging.ticketmanagement.model.RegisterRequest;
import com.ticketmanaging.ticketmanagement.service.RegisterService;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public String register(@RequestBody RegisterRequest registerRequest) {
        boolean isRegistered = registerService.registerUser(
                registerRequest.getName(),
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getRole(),
                registerRequest.getAccType()
        );
        if (isRegistered) {
            return "Registration successful!";
        } else {
            return "Registration failed!";
        }
    }
}
