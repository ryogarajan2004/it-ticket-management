package screens;

import app.Main;
import model.Agent;
import model.Ticket;
import model.TicketStatus;
import model.User;

public class AgentScreen {

    public static void show(User user) {
        Agent agent = (Agent) user;

        while (true) {
            Screen.separator();
            System.out.println("AGENT DASHBOARD  (" + agent.getId() + ")");
            System.out.println("Category:  " + agent.getCategory());
            System.out.println("1.  View Assigned Tickets");
            System.out.println("2.  Work on Ticket");
            System.out.println("q.  Logout");

            String c = Screen.sc.nextLine();

            switch (c) {
                case "1" -> viewAssigned(agent);
                case "2" -> workOnTicket(agent);
                case "q" -> {
                    return;
                }
                default -> System.out.println("Invalid Option");
            }
        }
    }

    public static void assignAgentToTicket(Ticket t) {
        for (User u : Main.store.users) {
            if (u instanceof Agent agent) {
                if (agent.getCategory() == t.getCategory()) {
                    t.setAssigneeId(agent.getId());
                    t.setStatus(TicketStatus.ASSIGNED);
                    return;
                }
            }
        }
    }

    private static void viewAssigned(Agent agent) {
        Screen.separator();
        System.out.println("TICKETSS ASSIGNED TO YOU:  ");

        Main.store.tickets.stream().filter(t -> agent.getId().equals(t.getAssigneeId())).forEach(t -> System.out.println("ID: " + t.getId() +
                " | Title:  " + t.getTitle() +
                " | Status:  " + t.getStatus()));
    }

    private static void workOnTicket(Agent agent) {
        System.out.println("Enter Ticket ID:  ");

        long id = Long.parseLong(Screen.sc.nextLine());

        Ticket t = Main.store.tickets.stream().filter(x -> x.getId().equals(id) && agent.getId().equals(x.getAssigneeId())).findFirst().orElse(null);

        if (t == null) {
            System.out.println("Ticket NOT FOUND");
            return;
        }
        System.out.println("1.  Mark In Progress");
        System.out.println("2.  Resolve Ticket");

        String choice = Screen.sc.nextLine();

        switch (choice) {
            case "1" -> t.setStatus(TicketStatus.IN_PROGRESS);
            case "2" -> t.setStatus(TicketStatus.RESOLVED);
        }

        System.out.println("Updated Ticket");
    }
}
