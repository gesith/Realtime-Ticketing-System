package com.ticketmanaging.ticketmanagement.service;

import com.ticketmanaging.ticketmanagement.model.TicketPool;
import com.ticketmanaging.ticketmanagement.model.CustomerVIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VIPCustomerService {

    @Autowired
    private TicketService ticketService;

    public String startVIPCustomerTask(int ticketsToRemove) {
        try {
            TicketPool ticketPool = ticketService.getTicket();
            int totalNumTicket = ticketPool.getTotalNumberTickets();

            // Create and start the VIP customer thread
            CustomerVIP vipCustomer = new CustomerVIP(ticketsToRemove, ticketPool);
            Thread vipCustomerThread = new Thread(vipCustomer);
            vipCustomerThread.start();
            vipCustomerThread.join();

            if (ticketsToRemove > totalNumTicket) {
                return "Error: Cannot buy tickets. Exceeds total number of tickets";
            }

            return "VIP Customer successfully bought " + ticketsToRemove ;
        } catch (Exception e) {
            return "Failed to start VIP Customer thread: " + e.getMessage();
        }
    }

    public int getAvailableTickets() {
        return ticketService.getTicket().getTotalNumberTickets();
    }
}

