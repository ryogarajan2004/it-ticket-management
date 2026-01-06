package service;

import app.Main;
import model.*;

import java.time.LocalDateTime;

public class AgentService {

	private static User requireAgent() {
		User u = Main.currentUser;
		if (!u.getRole().equals(Role.AGENT))
			throw new SecurityException("Agent access required");
		return u;
	}

	public static void autoAssign(Ticket ticket) {
		for (User u : Main.store.users) {
			if (u.getRole().equals(Role.AGENT)) {
				if (u.getCategory() == (ticket.getCategory())) {
					ticket.reassign(u.getId());
					ticket.setStatus(TicketStatus.ASSIGNED);
					return;
				}
			}
		}
	}

	public static void markInProgress(Long ticketId) {
		User agent = requireAgent();
		Ticket t = findAssignedTicket(ticketId, agent);

		if (t.getStatus() == TicketStatus.RESOLVED) {
			throw new IllegalStateException("Ticket Already Resolved");
		}

		t.setStatus(TicketStatus.IN_PROGRESS);
		t.getChangeHistory().add(LocalDateTime.now() + " - Marked IN_PROGRESS by Agent" + agent.getId());
	}

	public static void resolve(Long ticketId) {
		User agent = requireAgent();
		Ticket t = findAssignedTicket(ticketId, agent);

		if (t.getStatus() != TicketStatus.IN_PROGRESS) {
			throw new IllegalStateException("Ticket must be IN_PROGRESS before resolve");
		}

		t.resolve();
	}

	public static void addRemark(Long ticketId, String remark) {
		User agent = requireAgent();

		Ticket t = findAssignedTicket(ticketId, agent);

		t.setRemark(remark);

		t.getChangeHistory().add(LocalDateTime.now() + " - Remark Added by Agent" + agent.getId());
	}

	private static Ticket findAssignedTicket(Long id, User agent) {
		return Main.store.tickets.stream().filter(t -> t.getId().equals(id))
				.filter(t -> agent.getId().equals(t.getAssigneeId())).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Ticket Not assigned to you"));
	}

}