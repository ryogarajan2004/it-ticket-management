package service;

import app.Main;
import model.*;

public class UserService {

    private static User requireUser() {
        User u = Main.currentUser;
        if (u == null) {
            throw new SecurityException("Login required");
        }
        return u;
    }

    public static Ticket raiseTicket(
            String title,
            String description,
            Category category
    ) {
        User user = requireUser();

        Ticket t = new Ticket();
        t.setId(System.currentTimeMillis());
        t.setTitle(title);
        t.setDescription(description);
        t.setCategory(category);
        t.setReporterId(user.getId());
        t.setStatus(TicketStatus.OPEN);

        Main.store.tickets.add(t);

        // auto assignment hook
        AgentService.autoAssign(t);

        return t;
    }
}
