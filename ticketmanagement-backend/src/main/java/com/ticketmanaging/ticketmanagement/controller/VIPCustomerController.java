package com.ticketmanaging.ticketmanagement.controller;

import com.ticketmanaging.ticketmanagement.service.VIPCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vip-customer")
public class VIPCustomerController {

    @Autowired
    private VIPCustomerService vipCustomerService;

    // Endpoint for VIP customers to buy tickets
    @PostMapping("/start-vip-customer-task")
    public String startVIPCustomerTask(@RequestParam int ticketsToRemove) {
        return vipCustomerService.startVIPCustomerTask(ticketsToRemove);
    }

    // New endpoint to fetch total number of available tickets for VIP customers
    @GetMapping("/available-tickets")
    public int getAvailableTickets() {
        return vipCustomerService.getAvailableTickets();
    }
}
