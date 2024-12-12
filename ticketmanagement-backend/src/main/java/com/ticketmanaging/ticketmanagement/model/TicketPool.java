package com.ticketmanaging.ticketmanagement.model;
public class TicketPool {
    private int maxTicketCapacity;
    private int totalNumberTickets;//This refers to the current number of tickets available for purchase at any given time
    // private Vendor vendor;
    private Configuration config;
    public TicketPool(int maxTicketCapacity, int totalNumberTickets,Configuration config){
        this.maxTicketCapacity=maxTicketCapacity;
        this.totalNumberTickets=totalNumberTickets;
        this.config = config;
    }
    public Configuration getConfig() {
        return config; // Provide access to the configuration
    }
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public int getTotalNumberTickets() {
        return totalNumberTickets;
    }

    public void setTotalNumberTickets(int totalNumberTickets) {
        this.totalNumberTickets = totalNumberTickets;
    }

    public synchronized boolean addTickets(int numOfTicketAdd, Vendor vendor){
        if(numOfTicketAdd<=(maxTicketCapacity-totalNumberTickets)){
            setTotalNumberTickets(totalNumberTickets+numOfTicketAdd);
            config.setTotalNumberTickets(totalNumberTickets+numOfTicketAdd);
            //this.totalNumberTickets=totalNumberTickets+numOfTicketAdd;
            System.out.println(numOfTicketAdd+" tickets add by vendor ");
            System.out.println("Total number of tickets available is: "+getTotalNumberTickets());
            System.out.println(" ");
            return true;
        }
        else {
            System.out.println("Cannot add tickets. Exceeds max capacity.");
        }
        return false;
    }
    public synchronized void removeTickets(int numOfTicketBuy, Customer customer){
        if(numOfTicketBuy<=totalNumberTickets){
            this.totalNumberTickets=totalNumberTickets-numOfTicketBuy;
            System.out.println("Customer buy : "+numOfTicketBuy);
            System.out.println("Total number of tickets available is: "+getTotalNumberTickets());
            System.out.println(" ");
        }
        else {
            System.out.println("Cannot Buy tickets. Exceeds available Tickets!.");
            System.out.println("Total number of tickets available is: "+getTotalNumberTickets());
        }
    }
}