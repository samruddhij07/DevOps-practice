import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainTicketManager {

    // A simple inner class to represent a Ticket
    static class Ticket {
        String passengerName;
        int seatNumber;
        String destination;

        public Ticket(String passengerName, int seatNumber, String destination) {
            this.passengerName = passengerName;
            this.seatNumber = seatNumber;
            this.destination = destination;
        }

        @Override
        public String toString() {
            return "Seat " + seatNumber + " | Passenger: " + passengerName + " | To: " + destination;
        }
    }

    // System constraints and state
    private static final int TOTAL_SEATS = 10;
    private static boolean[] seatAvailability = new boolean[TOTAL_SEATS]; // false means available
    private static List<Ticket> bookedTickets = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to the Java Express Terminal ===");

        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. View Available Seats");
            System.out.println("2. Book a Ticket");
            System.out.println("3. View Booked Tickets");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAvailableSeats();
                    break;
                case "2":
                    bookTicket(scanner);
                    break;
                case "3":
                    viewBookedTickets();
                    break;
                case "4":
                    System.out.println("Thank you for using Java Express. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
        
        scanner.close();
    }

    private static void viewAvailableSeats() {
        System.out.println("\n--- Seat Availability ---");
        int availableCount = 0;
        for (int i = 0; i < TOTAL_SEATS; i++) {
            if (!seatAvailability[i]) {
                System.out.print("[Seat " + (i + 1) + "] ");
                availableCount++;
            } else {
                System.out.print("[ BOOKED ] ");
            }
            // Line break every 5 seats for neatness
            if ((i + 1) % 5 == 0) System.out.println();
        }
        System.out.println("Total available seats: " + availableCount);
    }

    private static void bookTicket(Scanner scanner) {
        System.out.println("\n--- Book a Ticket ---");
        
        System.out.print("Enter Seat Number (1-" + TOTAL_SEATS + "): ");
        int seatNumber;
        try {
            seatNumber = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
            return;
        }

        // Validate seat number
        if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
            System.out.println("Invalid seat number. Please choose between 1 and " + TOTAL_SEATS + ".");
            return;
        }

        // Check if already booked
        if (seatAvailability[seatNumber - 1]) {
            System.out.println("Sorry, Seat " + seatNumber + " is already booked.");
            return;
        }

        // Get passenger details
        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        // Process booking
        seatAvailability[seatNumber - 1] = true;
        bookedTickets.add(new Ticket(name, seatNumber, destination));
        
        System.out.println("Success! Ticket booked for " + name + " on Seat " + seatNumber + ".");
    }

    private static void viewBookedTickets() {
        System.out.println("\n--- Booked Tickets Manifest ---");
        if (bookedTickets.isEmpty()) {
            System.out.println("No tickets have been booked yet.");
        } else {
            for (Ticket ticket : bookedTickets) {
                System.out.println(ticket.toString());
            }
        }
    }
}