
package ui;

import dao.ScenarioDao;
import model.Ticket;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);
    private final ScenarioDao scenarioDao = new ScenarioDao();

    public void start() {
        int choice;

        do {
            System.out.println("\n======================================");
            System.out.println("     EVENT TICKET SALES SYSTEM");
            System.out.println("======================================");
            System.out.println("[1] Add New Ticket");
            System.out.println("[2] View Sold Tickets");
            System.out.println("[3] Exit System");
            System.out.print("Choose an option: ");

            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                System.out.print("Choose an option: ");
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addTicket();
                    break;
                case 2:
                    viewTickets();
                    break;
                case 3:
                    System.out.println("Exiting Event Ticket Sales System...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 3);
    }

    private void addTicket() {
        try {
            System.out.println("\n--- ADD NEW TICKET ---");

            System.out.print("Enter attendee name: ");
            String attendeeName = sc.nextLine().trim();

            if (attendeeName.isEmpty()) {
                System.out.println("Attendee name cannot be empty.");
                return;
            }

            System.out.print("Enter ticket type (VIP/REGULAR/GENERAL): ");
            String ticketType = sc.nextLine().trim().toUpperCase();

            if (!ticketType.equals("VIP")
                    && !ticketType.equals("REGULAR")
                    && !ticketType.equals("GENERAL")) {
                System.out.println("Invalid ticket type.");
                return;
            }

            System.out.print("Enter ticket price: ");

            while (!sc.hasNextDouble()) {
                System.out.println("Please enter a valid price.");
                sc.nextLine();
                System.out.print("Enter ticket price: ");
            }

            double ticketPrice = sc.nextDouble();
            sc.nextLine();

            if (ticketPrice < 0) {
                System.out.println("Ticket price cannot be negative.");
                return;
            }

            System.out.print("Enter payment method (CARD/GCASH/CASH): ");
            String paymentStatus = sc.nextLine().trim().toUpperCase();

            if (!paymentStatus.equals("CARD")
                    && !paymentStatus.equals("GCASH")
                    && !paymentStatus.equals("CASH")) {
                System.out.println("Invalid payment method.");
                return;
            }

            Ticket ticket = new Ticket(
                    attendeeName,
                    ticketType,
                    ticketPrice,
                    paymentStatus
            );

            boolean added = scenarioDao.addTicket(ticket);

            if (added) {
                System.out.println("Ticket added successfully!");
            } else {
                System.out.println("Failed to add ticket.");
            }

        } catch (Exception e) {
            System.out.println("Error adding ticket: " + e.getMessage());
        }
    }

    private void viewTickets() {
        try {
            List<Ticket> tickets = scenarioDao.getAllTickets();

            System.out.println();
            System.out.println(
                    "+----+----------------+-------------+----------+----------+"
            );

            System.out.printf(
                    "| %-2s | %-14s | %-11s | %-8s | %-8s |%n",
                    "ID", "ATTENDEE NAME", "TICKET TYPE",
                    "PRICE", "PAYMENT"
            );

            System.out.println(
                    "+----+----------------+-------------+----------+----------+"
            );

            for (Ticket ticket : tickets) {
                System.out.printf(
                        "| %-2d | %-14s | %-11s | %8.2f | %-8s |%n",
                        ticket.getId(),
                        ticket.getAttendeeName(),
                        ticket.getTicketType(),
                        ticket.getTicketPrice(),
                        ticket.getPaymentStatus()
                );
            }

            System.out.println(
                    "+----+----------------+-------------+----------+----------+"
            );

            System.out.println(
                    "Total Records Displayed: " + tickets.size()
            );

        } catch (Exception e) {
            System.out.println(
                    "Error displaying tickets: " + e.getMessage()
            );
        }
    }
}
