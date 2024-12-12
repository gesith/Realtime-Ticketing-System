package com.ticketmanaging.ticketmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {

    // Replace with your own database details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/TicketingSystem";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password"; // Use your database password here

    // Method to establish a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to get the password for a specific user
    public static String getPasswordForUser(String email, String role,boolean accType) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        String query = "";
        if (role.equalsIgnoreCase("customer")) {
            query = "SELECT password FROM Customer WHERE email = ? AND is_vip = ?";
        } else if (role.equalsIgnoreCase("vendor")) {
            query = "SELECT password FROM Vendor WHERE email = ?";
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            if(role.equalsIgnoreCase("customer")){
                if(accType){
                    stmt.setInt(2, 1);
                }
                else{
                    stmt.setInt(2, 0);
                }}
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("password");
            } else {
                // Handle case where no record was found for the provided email
                System.out.println("No user found with the given email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // You could log this or throw a custom exception if needed
        }

        return null;  // Return null if no password found or error occurred
    }

    public static boolean registerUser(String name, String email, String password, String role, boolean isVIP) {
        String query = "";
        if (role.equalsIgnoreCase("customer")) {
            query = "INSERT INTO Customer (name, email, password, is_vip) VALUES (?, ?, ?, ?)";
        } else if (role.equalsIgnoreCase("vendor")) {
            query = "INSERT INTO Vendor (name, email, password) VALUES (?, ?, ?)";
        } else {
            return false; // Invalid role
        }
        System.out.println(isVIP);
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            if(role.equalsIgnoreCase("customer")){
                if (isVIP) {
                    stmt.setInt(4, 1); // Set is_vip to 0 for normal customers, 1 for VIP customers
                }
                else {
                    stmt.setInt(4, 0);
                }
            }
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

