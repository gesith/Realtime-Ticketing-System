public class TicketPool {
    private int maxTicketCapacity;
    private int totalNumberTickets;//This refers to the current number of tickets available for purchase at any given time
    // private Vendor vendor;
    public TicketPool(int maxTicketCapacity, int totalNumberTickets){
        this.maxTicketCapacity=maxTicketCapacity;
        this.totalNumberTickets=totalNumberTickets;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public int getTotalNumberTickets() {
        return totalNumberTickets;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public void setTotalNumberTickets(int totalNumberTickets) {
        this.totalNumberTickets = totalNumberTickets;
    }

    public synchronized void addTickets(int numOfTicketAdd, Vendor vendor){
        if(numOfTicketAdd<=(maxTicketCapacity-totalNumberTickets)){
            this.totalNumberTickets=totalNumberTickets+numOfTicketAdd;
            System.out.println(numOfTicketAdd+" tickets add by vendor "+vendor.getVendorID()+
                    "\nTotal number of tickets available is: "+getTotalNumberTickets());
            //System.out.println("Total number of tickets available is: "+getTotalNumberTickets());
            System.out.println(" ");
        }
        else {
            System.out.println("Cannot add tickets. Exceeds max capacity.");
        }
    }
    public synchronized void removeTickets(int numOfTicketBuy, Customer customer,boolean type){
        if(numOfTicketBuy<=totalNumberTickets){
            this.totalNumberTickets=totalNumberTickets-numOfTicketBuy;
            if(type) {
                System.out.println(numOfTicketBuy + " tickets buy by VIP Customer " + customer.getCustomerID() +
                        "\nTotal number of tickets available is:" + getTotalNumberTickets());
                System.out.println();
            }
            else{
                System.out.println(numOfTicketBuy + " tickets buy by customer " + customer.getCustomerID() +
                        "\nTotal number of tickets available is:" + getTotalNumberTickets());
                System.out.println();
            }
        }
        else {
            System.out.println("Cannot Buy tickets. Exceeds available Tickets!." +
                    "\nTotal number of tickets available is:"+getTotalNumberTickets());
            //System.out.println("Total number of tickets available is: "+getTotalNumberTickets());
        }
    }
}
