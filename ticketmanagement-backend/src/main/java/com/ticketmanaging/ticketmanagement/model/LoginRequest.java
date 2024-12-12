package com.ticketmanaging.ticketmanagement.model;

public class LoginRequest {

    private String email;
    private String password;
    private String role; // "customer" or "vendor"
    private boolean accType;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getAccType() {
        return accType;
    }
    public void setAccType(boolean accType) {
        this.accType = accType;
    }
}
