package com.ticketmanaging.ticketmanagement.model;

public class CustomerVIP extends Customer{
    public CustomerVIP(int ticketsToRemove, TicketPool ticketPool) {
        super(ticketsToRemove, 0, ticketPool);
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is waiting to buy tickets...");
        //Thread.sleep(customerRetrievalRate); // Wait for the retrieval rate
        ticketPool.removeTickets(ticketsToBuy, customer);
        System.out.println(Thread.currentThread().getName() + " has completed its task.");
    }
}