package com.ticketmanaging.ticketmanagement.service;

import com.ticketmanaging.ticketmanagement.database.DatabaseConnection;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean authenticateUser(String email, String password, String role,boolean accType) {
        // Fetch the stored password for the user based on role (customer/vendor)
        String storedPassword = DatabaseConnection.getPasswordForUser(email, role,accType);

        // Check if the entered password matches the stored password
        return storedPassword != null && storedPassword.equals(password);
    }
}