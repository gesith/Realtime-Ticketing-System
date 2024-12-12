package com.ticketmanaging.ticketmanagement.service;

import com.ticketmanaging.ticketmanagement.model.Configuration;
import com.ticketmanaging.ticketmanagement.model.TicketPool;
import com.ticketmanaging.ticketmanagement.model.Vendor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketService {

    private TicketPool ticket;

    @Autowired
    private ConfigurationService configurationService;

    public TicketService() {
        // No initialization here because we need to wait for the configuration to load
    }

    @PostConstruct //@PostConstruct is a lifecycle annotation that tells Spring to call the method after the bean has been created and all dependencies have been injected.
    public void initializeTicket() {
        // Load configuration to initialize TicketPool
        Configuration config = configurationService.loadConfiguration();
        if (config != null) {
            ticket = new TicketPool(config.getMaxTicketCapacity(), config.getTotalNumberTickets(),config);
        } else {
            System.err.println("Failed to initialize Ticket: Configuration is null");
        }
    }

    public TicketPool getTicket() {
        return ticket;
    }
}


