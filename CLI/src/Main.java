import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        String configFilePath = "config.json";
        Configuration configuration =new Configuration();
        configuration = configuration.loadConfiguration(configFilePath);

        if (configuration == null) {
            System.out.println("No configuration file found. Creating a new one.");
            configuration = configuration.createNewConfiguration(scanner, configFilePath);
        } else {
            System.out.println("Configuration loaded from: " + configFilePath);
            System.out.print("Do you want to update the configuration?(yes/no) : ");
            String response = scanner.next().trim().toLowerCase();
            System.out.println();

            while (!response.equals("yes") && !response.equals("no")) {
                System.out.println("Enter a valid input (yes/no): ");
                response = scanner.next().trim().toLowerCase();
            }
            if (response.equals("yes")) {
                configuration = configuration.createNewConfiguration(scanner, configFilePath);
            }
        }
        int numberOfVendors = 0;
        while (numberOfVendors<=0) {
            try {
                System.out.print("Enter number of vendors: ");
                numberOfVendors = scanner.nextInt();
                // Exit loop if input is valid
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }

        int numberOfVIPCustomers = 0;
        while (numberOfVIPCustomers<=0) {
            try {
                System.out.print("Enter number of VIP customers: ");
                numberOfVIPCustomers = scanner.nextInt();
                break; // Exit loop if input is valid
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }

        int numberOfCustomers = 0;
        while (numberOfCustomers <= 0) {
            try {
                System.out.print("Enter the number of customers: ");
                numberOfCustomers = scanner.nextInt();
                 // Exit loop if input is valid
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
        System.out.println();

        TicketPool ticket = new TicketPool(configuration.getMaxTicketCapacity(),configuration.getTotalNumberTickets());
        List<Thread> threadsCustomer=new ArrayList<>();
        List<Thread> threadsVIPCustomer=new ArrayList<>();
        List<Thread> threadsVendor=new ArrayList<>();
        Random random = new Random();

        for(int i=1;i<=numberOfVIPCustomers;i++){
            int ticketsToBuy = random.nextInt(50)+1;
            Thread thread = new Thread(new VIPCustomer(i,ticket,ticketsToBuy,configuration.getCustomerRetrievalRate()));
            threadsVIPCustomer.add(thread);
        }

        for(int i=1;i<=numberOfVendors;i++){
            int ticketsToAdd = random.nextInt(50)+1;
            Thread thread = new Thread(new Vendor(i, ticket, ticketsToAdd,configuration.getTicketReleaseRate()));
            threadsVendor.add(thread);
        }

        for(int i=1;i<=numberOfCustomers;i++){
            int ticketsToBuy = random.nextInt(20)+1;
            Thread thread = new Thread(new Customer(i,ticket,ticketsToBuy,configuration.getCustomerRetrievalRate()));
            threadsCustomer.add(thread);
        }

        for (Thread thread : threadsVIPCustomer) {
            thread.start();
        }
        for (Thread thread : threadsVendor) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (Thread thread : threadsCustomer) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}