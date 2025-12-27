package screens;

import app.Main;
import model.Category;
import model.Ticket;
import model.TicketStatus;
import model.User;

public class UserScreen {

    public static void show(User user) {
        while (true) {
            Screen.separator();
            System.out.println("USER DASH (" + user.getUsername() + "):");
            System.out.println("1. Raise Ticket");
            System.out.println("2. View My Tickets ");
            System.out.println("q. Logout");

            String choice = Screen.sc.nextLine();

            switch (choice) {
                case "1" -> raiseTicket(user);
                case "2" -> viewMyTickets(user);
                case "q" -> {
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void raiseTicket(User user) {
        Screen.separator();
        System.out.println("RAISE TICKET");

        Ticket t = new Ticket();
        t.setId(System.currentTimeMillis());
        t.setReporterId(user.getId());

        System.out.println("Title:  ");
        t.setTitle(Screen.sc.nextLine());

        System.out.println("Description: ");
        t.setDescription(Screen.sc.nextLine());

        System.out.println("Select Category");
        for (Category c : Category.values()) {
            System.out.println("- " + c);
        }

        t.setCategory(Category.values()[Integer.parseInt(Screen.sc.nextLine().toUpperCase())]);
        t.setStatus(TicketStatus.OPEN);

        AgentScreen.assignAgentToTicket(t);

        Main.store.tickets.add(t);
        System.out.println("Ticket Raised with ID:  " + t.getId());


    }

    private static void viewMyTickets(User user) {
        Screen.separator();
        System.out.println("MY TICKETS");

        Main.store.tickets.stream()
                .filter(t -> t.getReporterId().equals(user.getId()))
                .forEach(t -> {
                    System.out.println("ID: " + t.getId() + " | " +
                            "Title: " + t.getTitle() + " | " +
                            "Status: " + t.getStatus());
                });
        System.out.println();
    }
}
