package com.ticketmanaging.ticketmanagement.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Configuration {

    private int maxTicketCapacity;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int totalNumberTickets;

    public Configuration() {
    }

    public Configuration(int maxTicketCapacity, int ticketReleaseRate, int customerRetrievalRate, int totalNumberTickets) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.totalNumberTickets = totalNumberTickets;
    }

    // Getters and Setters
    public int getTotalNumberTickets() {
        return totalNumberTickets;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }


    public void setTotalNumberTickets(int totalNumberTickets) {
        this.totalNumberTickets = totalNumberTickets;
    }

    // Save Configuration to JSON File
    public void saveConfiguration(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), this);
            System.out.println("Configuration saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }

    public static Configuration loadConfiguration(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), Configuration.class);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            return null;
        }
    }
}