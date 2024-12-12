public class VIPCustomer extends Customer{
    public VIPCustomer(int customerID, TicketPool ticketPool, int ticketsToBuy,int customerRetrievalRate) {
        super(customerID, ticketPool, ticketsToBuy,customerRetrievalRate); // No wait for VIP customers
    }

    @Override
    public void run() {
        try {
            TicketPool ticketPool=getTicketPool();
            System.out.println(Thread.currentThread().getName() + " (VIP) is buying tickets immediately...");
            System.out.println();
            ticketPool.removeTickets(super.getCustomerID(), this,true);
            System.out.println(Thread.currentThread().getName() + " (VIP) has completed its task.");
            System.out.println();
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " (VIP) encountered an error.");
        }
    }
}
