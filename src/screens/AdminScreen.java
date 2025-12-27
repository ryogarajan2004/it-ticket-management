package screens;

import app.Main;
import model.Role;
import model.User;

public class AdminScreen {

    public static void show(User admin) {
        while (true) {
            Screen.separator();
            System.out.println("ADMIN DASHBOARD (" + admin.getName() + ")");
            System.out.println("1.  View All Users");
            System.out.println("2.  View All Tickets");
            System.out.println("3.  Change User Role");
            System.out.println("4. Register New User");
            System.out.println("q.  Logout");

            String c = Screen.sc.nextLine();

            switch (c) {
                case "1" -> viewUsers();
                case "2" -> viewTickets();
                case "3" -> changeRole();
                case "4" -> RegisterScreen.show();
                case "q" -> {
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
        Main.store.tickets.forEach(t -> {
            System.out.println("TICKET ID: " + t.getId() + "Title:  " + t.getTitle() + "Description:  " + t.getDescription() + "Agent:  " + t.getAssigneeId() + "Category:  " + t.getCategory());
        });


    }

    private static void changeRole() {
        System.out.println("Enter USER ID: ");
        long id = Long.parseLong(Screen.sc.nextLine());
        User u = Main.store.users.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);

        if (u == null) {
            System.out.println("User Not found");
            return;

        }

        System.out.println("New ROLE:  USER / AGENT / ADMIN");
        Role r = Role.valueOf(Screen.sc.nextLine().toUpperCase());
        u.setRole(r);

        System.out.println("Role Updated");
    }
}
