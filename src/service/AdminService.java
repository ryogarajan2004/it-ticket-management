package service;

import app.Main;
import model.*;

public class AdminService {

    public static void changeUserRole(Long userId, Role newRole, Category category) {

        User existing = Main.store.users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Remove old object
        Main.store.users.remove(existing);

        User updated;

        if (newRole == Role.AGENT) {

            if (category == null) {
                throw new IllegalArgumentException("Agent category required");
            }

            updated = new Agent(
                    existing.getId(),
                    existing.getName(),
                    existing.getUsername(),
                    existing.getEmail(),
                    existing.getPassword(),
                    category
            );

        } else {
            updated = new User(
                    existing.getId(),
                    existing.getName(),
                    existing.getUsername(),
                    existing.getEmail(),
                    existing.getPassword(),
                    newRole
            );
        }

        if(Main.currentUser!=null&&Main.currentUser.getId().equals(userId))
            Main.currentUser=updated;
        Main.store.users.add(updated);
    }


    public static void escalateTicket(Long ticketId, String reason) {
        Ticket t = findTicket(ticketId);
        t.escalate(reason);
    }

    public static void resolveTicket(Long ticketId) {
        Ticket t = findTicket(ticketId);
        t.resolve();
    }

    public static void reassignTicket(Long ticketId, Long agentId) {
        Ticket t = findTicket(ticketId);

        boolean agentExists = Main.store.users.stream()
                .anyMatch(u -> u.getId().equals(agentId) && u.getRole() == Role.AGENT);

        if (!agentExists) {
            throw new IllegalArgumentException("Invalid agent");
        }

        t.reassign(agentId);
    }

    public static Ticket getTicket(long id) {
        return findTicket(id);
    }


    private static Ticket findTicket(Long id) {
        return Main.store.tickets.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
    }
}
