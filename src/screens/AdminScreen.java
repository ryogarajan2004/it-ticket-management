package screens;

import app.Main;
import model.Category;
import model.Role;
import model.Ticket;
import model.User;
import service.AdminService;

public class AdminScreen {

    public static void show(User admin) {
        while (true) {
            Screen.separator();
            System.out.println("ADMIN DASHBOARD (" + admin.getName() + ")");
            System.out.println("1.  View All Users");
            System.out.println("2.  View All Tickets");
            System.out.println("3.  Change User Role");
            System.out.println("4. Register New User");
            System.out.println("5. Escalate Ticket");
            System.out.println("6. Resolve Ticket");
            System.out.println("7. Reassign Ticket");
            System.out.println("8. View Ticket History");

            System.out.println("q.  Logout");

            String c = Screen.sc.nextLine();

            switch (c) {
                case "1" -> viewUsers();
                case "2" -> viewTickets();
                case "3" -> changeRole();
                case "4" -> RegisterScreen.show();
                case "5" -> escalateTicket();
                case "6" -> resolveTicket();
                case "7" -> reassignTicket();
                case "8" -> viewTicketHistory();
                case "q" -> {
                    Main.currentUser=null;
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void viewUsers() {
        Screen.separator();

        Main.store.users.forEach(u -> {
            System.out.println("USER ID: " + u.getId()
                    + "| NAME: " + u.getName(

            ) +
                    "| ROLE: " + u.getRole());
        });
    }

    private static void viewTickets() {
        Screen.separator();
        System.out.println("ALL TICKETS");

        Main.store.tickets.forEach(t -> {
            System.out.println(
                    "ID: " + t.getId() +
                            " | Title: " + t.getTitle() +
                            " | Status: " + t.getStatus() +
                            " | Priority: " + t.getPriority() +
                            " | Escalated: " + t.getEscalated() +
                            " | Agent: " + t.getAssigneeId()
            );
        });
    }


    private static void changeRole() {
        System.out.println("Enter USER ID: ");
        long id = Long.parseLong(Screen.sc.nextLine());


        System.out.println("New ROLE:  USER / AGENT / ADMIN");
        Role role = Role.valueOf(Screen.sc.nextLine().toUpperCase());
        Category category=null;

        if(role.equals(Role.AGENT)) {
            for (Category c : Category.values()) {
                System.out.println("- " + c);
            }
            category= Category.valueOf(Screen.sc.nextLine().toUpperCase());
        }

        AdminService.changeUserRole(id, role,category);

        System.out.println("Role Updated");
    }

    private static void escalateTicket() {
        System.out.println("Enter Ticket ID");
        long id = Long.parseLong(Screen.sc.nextLine());


        System.out.println("Enter Reason");
        String reason = Screen.sc.nextLine();


        AdminService.escalateTicket(id, reason);

        System.out.println("Ticket Escalated");
    }

    private static void resolveTicket() {
        System.out.println("Enter Ticket ID:  ");
        long id = Long.parseLong(Screen.sc.nextLine());


        AdminService.resolveTicket(id);
        System.out.println("Ticket Resolved");
    }

    private static void reassignTicket() {
        System.out.print("Ticket ID: ");
        long ticketId = Long.parseLong(Screen.sc.nextLine());

        System.out.print("New Agent ID: ");
        long agentId = Long.parseLong(Screen.sc.nextLine());


        AdminService.reassignTicket(ticketId, agentId);
        System.out.println("Ticket reassigned");
    }

    private static void viewTicketHistory() {
        System.out.print("Enter Ticket ID: ");
        long id = Long.parseLong(Screen.sc.nextLine());

        Ticket t = AdminService.getTicket(id
        );



        Screen.separator();
        t.getChangeHistory().forEach(System.out::println);
    }

}
