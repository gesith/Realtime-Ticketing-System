package com.ticketmanaging.ticketmanagement.service;

import com.ticketmanaging.ticketmanagement.database.DatabaseConnection;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    public boolean registerUser(String name, String email, String password, String role,boolean accType) {
        return DatabaseConnection.registerUser(name, email, password, role,accType);
    }
}
