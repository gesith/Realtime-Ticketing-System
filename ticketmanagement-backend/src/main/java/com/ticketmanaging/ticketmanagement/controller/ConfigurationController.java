package com.ticketmanaging.ticketmanagement.controller;

import com.ticketmanaging.ticketmanagement.model.Configuration;
import com.ticketmanaging.ticketmanagement.service.ConfigurationService;
import com.ticketmanaging.ticketmanagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TicketService ticketService;

    // Get current configuration
    @GetMapping
    public Configuration getConfiguration() {
        return configurationService.loadConfiguration();
    }

    // Update configuration
    @PostMapping
    public String updateConfiguration(@RequestBody Configuration newConfig) {
        String message =configurationService.saveConfiguration(newConfig);
        ticketService.initializeTicket();
        return message;
    }
}

