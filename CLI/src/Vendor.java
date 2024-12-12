public class Vendor implements Runnable{
    private int vendorID;
    private TicketPool ticketPool;
    private int ticketsToAdd;
    private int ticketReleaseRate;
    public Vendor(int vendorID, TicketPool ticketPool, int ticketsToAdd,int ticketReleaseRate) {
        this.vendorID=vendorID;
        this.ticketPool = ticketPool;
        this.ticketsToAdd = ticketsToAdd;
        this.ticketReleaseRate=ticketReleaseRate;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public int getTicketsToAdd() {
        return ticketsToAdd;
    }

    public void setTicketsToAdd(int ticketsToAdd) {
        this.ticketsToAdd = ticketsToAdd;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getVendorID() {
        return vendorID;
    }
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting to release tickets...");
            Thread.sleep(ticketReleaseRate); // Wait for the ticket release rate
            ticketPool.addTickets(ticketsToAdd, this);
            System.out.println();
            System.out.println(Thread.currentThread().getName() + " has completed its task.");
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }
}
