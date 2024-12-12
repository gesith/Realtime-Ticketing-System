package com.ticketmanaging.ticketmanagement.service;

import com.ticketmanaging.ticketmanagement.model.TicketPool;
import com.ticketmanaging.ticketmanagement.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    @Autowired
    private TicketService ticketService;

    public String startVendorTask(int ticketsToAdd) {
        try {
            // Retrieve the ticket release rate from the TicketService configuration
            int ticketReleaseRate = ticketService.getTicket().getConfig().getTicketReleaseRate();
            TicketPool ticketPool = ticketService.getTicket();
            int totalNumTicket = ticketPool.getTotalNumberTickets();
            // Create a Vendor object
            Vendor vendor = new Vendor(ticketsToAdd, ticketReleaseRate,ticketPool);

            // Pass the Vendor object to a Thread
            Thread vendorThread = new Thread(vendor);

            // Start the thread
            vendorThread.start();

            // Wait for the thread to finish
            vendorThread.join();

            if (totalNumTicket+ ticketsToAdd > ticketPool.getMaxTicketCapacity()) {
                return "Error : Exceeds max capacity "+ticketPool.getMaxTicketCapacity()+"! Already "+ticketPool.getTotalNumberTickets()+" tickets contains";
            }
            System.out.println("Vendor successfully add " + ticketsToAdd + " tickets. "+"Total number of tickets is:"+ticketPool.getTotalNumberTickets());
            return "Vendor successfully add " + ticketsToAdd + " tickets. "+"Total number of tickets is:"+ticketPool.getTotalNumberTickets();
        } catch (Exception e) {
            return "Failed to start Vendor thread: " + e.getMessage();
        }
    }
    public int getAvailableTickets() {
        return ticketService.getTicket().getTotalNumberTickets();
    }
    public int getMaxTicketCapacity(){return ticketService.getTicket().getMaxTicketCapacity();}
}
