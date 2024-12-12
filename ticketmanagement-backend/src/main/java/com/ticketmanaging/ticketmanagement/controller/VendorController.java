package com.ticketmanaging.ticketmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ticketmanaging.ticketmanagement.service.VendorService;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // Add tickets to the TicketPool
    @PostMapping("/start-vendor-task")
    public String startVendorTask(@RequestParam int ticketsToAdd) {
        return vendorService.startVendorTask(ticketsToAdd);
    }
    @GetMapping("/available-tickets")
    public int getAvailableTickets() {
        return vendorService.getAvailableTickets();
    }
    @GetMapping("/max-tickets")
    public int getMaxTicketCapacity() {
        return vendorService.getMaxTicketCapacity();
    }
}
