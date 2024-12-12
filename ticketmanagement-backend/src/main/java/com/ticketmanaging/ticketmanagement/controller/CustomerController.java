package com.ticketmanaging.ticketmanagement.controller;

import com.ticketmanaging.ticketmanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // Add tickets to the TicketPool
    @PostMapping("/start-customer-task")
    public String startVendorTask(@RequestParam int ticketsToRemove) {
        return customerService.startCustomerTask(ticketsToRemove);
    }
    // fetch total number of available tickets
    @GetMapping("/available-tickets")
    public int getAvailableTickets() {
        return customerService.getAvailableTickets();
    }
}
