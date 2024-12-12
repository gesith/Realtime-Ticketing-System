import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Configuration {
    private int maxTicketCapacity; //This parameter defines the total number of tickets available for a particular event
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int totalNumberTickets;

    public Configuration() {
    }
    public Configuration(int maxTicketCapacity,int ticketReleaseRate,int customerRetrievalRate,int totalNumberTickets){
        this.maxTicketCapacity=maxTicketCapacity;
        this.ticketReleaseRate=ticketReleaseRate;
        this.customerRetrievalRate=customerRetrievalRate;
        this.totalNumberTickets=totalNumberTickets;
    }

    public int getTotalNumberTickets() {
        return totalNumberTickets;
    }
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }
    public void setTotalNumberTickets(int totalNumberTickets) {
        this.totalNumberTickets = totalNumberTickets;
    }

    // Save Configuration to JSON File
    public void saveConfiguration(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), this);
            System.out.println("Configuration saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }

    // Load Configuration from JSON File
    public Configuration loadConfiguration(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), Configuration.class);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            return null;
        }
    }
    // Create new Configuration
    public Configuration createNewConfiguration(Scanner scanner, String configFilePath) {
        System.out.print("Enter Maximum Ticket Capacity: ");
        int maxTicketCapacity = scanner.nextInt();
        System.out.print("Enter Total Number of Tickets: ");
        int totalTickets = scanner.nextInt();
        System.out.print("Enter Ticket Release Rate (ms): ");
        int ticketReleaseRate = scanner.nextInt();
        System.out.print("Enter Customer Retrieval Rate (ms): ");
        int customerRetrievalRate = scanner.nextInt();

        Configuration configuration = new Configuration(maxTicketCapacity,ticketReleaseRate,customerRetrievalRate,totalTickets);
        configuration.saveConfiguration(configFilePath);
        System.out.println("New configuration saved to: " + configFilePath);
        return configuration;
    }
}
