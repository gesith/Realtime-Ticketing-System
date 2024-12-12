package com.ticketmanaging.ticketmanagement.controller;

import com.ticketmanaging.ticketmanagement.service.LoginService;
import com.ticketmanaging.ticketmanagement.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        boolean isValid = loginService.authenticateUser(
                loginRequest.getEmail(),
                loginRequest.getPassword(),
                loginRequest.getRole(),
                loginRequest.getAccType()
        );
        if (isValid) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }
}
