package com.ticketmanaging.ticketmanagement.service;
import com.ticketmanaging.ticketmanagement.model.Configuration;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
    private static final String CONFIG_FILE_PATH = "config.json";
    // Load configuration
    public Configuration loadConfiguration() {
        Configuration config = Configuration.loadConfiguration(CONFIG_FILE_PATH);
        if (config == null) {
            System.out.println("No configuration file found. Creating a new one.");
            // Default configuration or prompt user for new configuration
            config = new Configuration(100, 10, 5, 50); // Default values
            config.saveConfiguration(CONFIG_FILE_PATH);
        } else {
            System.out.println("Configuration loaded from: " + CONFIG_FILE_PATH);
        }
        return config;
    }
    // Save updated configuration
    public String saveConfiguration(Configuration configuration) {
        if (configuration.getTotalNumberTickets() > configuration.getMaxTicketCapacity()) {
            System.out.println(
                    "Total number of tickets (" + configuration.getTotalNumberTickets() +
                            ") cannot exceed max ticket capacity (" + configuration.getMaxTicketCapacity() + ")."
            );
            return "Error : Total number of tickets (" + configuration.getTotalNumberTickets() +
                    ") cannot exceed max ticket capacity (" + configuration.getMaxTicketCapacity() + ").";
        }
        else{
            configuration.saveConfiguration(CONFIG_FILE_PATH);
            return "Configuration saved successfully.";
        }
    }
}
