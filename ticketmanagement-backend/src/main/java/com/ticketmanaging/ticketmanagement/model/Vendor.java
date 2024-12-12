package com.ticketmanaging.ticketmanagement.model;
public class Vendor implements Runnable{
    private int vendorID;
    private Vendor vendor;
    private TicketPool ticketPool;
    private int ticketsToAdd;
    private int ticketReleaseRate;

    public Vendor(Vendor vendor, TicketPool ticketPool, int ticketsToAdd,int ticketReleaseRate) {
        this.vendor = vendor;
        this.ticketPool = ticketPool;
        this.ticketsToAdd = ticketsToAdd;
        this.ticketReleaseRate=ticketReleaseRate;
    }
    public Vendor(int ticketsToAdd,int ticketReleaseRate,TicketPool ticketPool){
        this.ticketsToAdd = ticketsToAdd;
        this.ticketReleaseRate=ticketReleaseRate;
        this.ticketPool = ticketPool;
    }
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting to release tickets...");
            Thread.sleep(ticketReleaseRate); // Wait for the ticket release rate
            ticketPool.addTickets(ticketsToAdd, vendor);
            System.out.println(Thread.currentThread().getName() + " has completed its task.");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }
}