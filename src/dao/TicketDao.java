package dao;

import java.util.List;

import model.Ticket;

public interface TicketDao {
	public Ticket createTicket(Ticket ticket);

	public Ticket getTicketById(Long id);

	public List<Ticket> getAllTickets();

	public Ticket editTicketById(Long id, Ticket ticket);

	 void deleteTicketById(Long id);
}
