package model;

public enum TicketStatus {
	OPEN, // user raised
	ASSIGNED, // agent assigned
	IN_PROGRESS, // agent working
	RESOLVED, // agent marked resolved
	WAITING_USER, // waiting for user confirmation
	CLOSED, // user confirmed
	ESCALATED
}
