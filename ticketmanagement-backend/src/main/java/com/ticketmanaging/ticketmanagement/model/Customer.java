package com.ticketmanaging.ticketmanagement.model;
public class Customer implements Runnable {
    protected Customer customer;
    protected TicketPool ticketPool;
    protected int ticketsToBuy;
    protected int customerRetrievalRate;

    public Customer(int ticketsToRemove, int customerRetrievalRate, TicketPool ticketPool) {
        this.ticketsToBuy = ticketsToRemove;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
    }
    @Override
    public void run(){
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting to buy tickets...");
            Thread.sleep(customerRetrievalRate); // Wait for the retrieval rate
            ticketPool.removeTickets(ticketsToBuy, customer);
            System.out.println(Thread.currentThread().getName() + " has completed its task.");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }
}
