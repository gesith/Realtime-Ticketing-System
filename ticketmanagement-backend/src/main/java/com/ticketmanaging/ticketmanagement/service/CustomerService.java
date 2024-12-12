package com.ticketmanaging.ticketmanagement.service;

import com.ticketmanaging.ticketmanagement.model.TicketPool;
import com.ticketmanaging.ticketmanagement.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CustomerService {
    @Autowired
    private TicketService ticketService;
    public String startCustomerTask(int ticketsToRemove) {
        try {
            // Retrieve the ticket release rate from the TicketService configuration
            int customerRetrievalRate = ticketService.getTicket().getConfig().getCustomerRetrievalRate();
            TicketPool ticketPool = ticketService.getTicket();
            int totalNumTicket = ticketPool.getTotalNumberTickets();
            Customer customer = new Customer(ticketsToRemove,customerRetrievalRate,ticketPool);
            Thread customerThread = new Thread(customer);
            customerThread.start();
            customerThread.join();
            if (ticketsToRemove > totalNumTicket) {
                return "Error : Cannot buy tickets. Exceeds total number of tickets";
            }
            return "Customer successfully buy " + ticketsToRemove + " tickets. ";
        } catch (Exception e) {
            return "Failed to start Vendor thread: " + e.getMessage();
        }
    }
    public int getAvailableTickets() {
        return ticketService.getTicket().getTotalNumberTickets();
    }
}
