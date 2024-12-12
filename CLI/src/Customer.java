public class Customer implements Runnable {
    private int customerID;
    private TicketPool ticketPool;
    private int ticketsToBuy;
    private int customerRetrievalRate;

    public Customer(int customerID, TicketPool ticketPool, int ticketsToBuy,int customerRetrievalRate) {
        this.customerID = customerID;
        this.ticketPool = ticketPool;
        this.ticketsToBuy = ticketsToBuy;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public int getTicketsToBuy() {
        return ticketsToBuy;
    }

    public void setTicketsToBuy(int ticketsToBuy) {
        this.ticketsToBuy = ticketsToBuy;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run(){
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting to buy tickets...");
            Thread.sleep(customerRetrievalRate); // Wait for the retrieval rate
            ticketPool.removeTickets(ticketsToBuy, this,false);
            System.out.println();
            System.out.println(Thread.currentThread().getName() + " has completed its task.");
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }
}
