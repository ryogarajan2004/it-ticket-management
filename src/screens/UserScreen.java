package screens;

import app.Main;
import model.Category;
import model.Ticket;
import model.TicketStatus;
import model.User;
import service.UserService;

public class UserScreen {

	public static void show(User user) {
		while (true) {
			Screen.separator();
			System.out.println("USER DASH (" + user.getUsername() + ")");
			System.out.println("1. Raise Ticket");
			System.out.println("2. View My Tickets");
			System.out.println("3. Close my resolved tickets");
			System.out.println("q. Logout");

			String choice = Screen.sc.nextLine();

			switch (choice) {
			case "1" -> raiseTicket();
			case "2" -> viewMyTickets(user);
			case "3" -> closeTicket();
			case "q" -> {
				Main.currentUser = null;
				return;
			}
			default -> System.out.println("Invalid choice");
			}
		}
	}

	private static void closeTicket() {
		Screen.separator();
		System.out.print("Close Ticket");
		Main.store.tickets.stream().filter(x -> (x.getReporterId().equals(Main.currentUser.getId())
				&& x.getStatus().equals(TicketStatus.RESOLVED))).forEach(System.out::println);
		System.out.print("Enter ticket id: ");
		long ticketId = Long.parseLong(Screen.sc.nextLine());
		System.out.print(ticketId);
		UserService.closeTicket(ticketId);
	}

	private static void raiseTicket() {
		try {
			Screen.separator();
			System.out.println("RAISE TICKET");

			System.out.print("Title: ");
			String title = Screen.sc.nextLine();

			System.out.print("Description: ");
			String description = Screen.sc.nextLine();

			System.out.println("Select Category:");
			Category[] categories = Category.values();
			for (int i = 0; i < categories.length; i++) {
				System.out.println(i + ". " + categories[i]);
			}

			int index = Integer.parseInt(Screen.sc.nextLine());
			Category category = categories[index];

			Ticket t = UserService.raiseTicket(title, description, category);

			System.out.println("Ticket raised successfully");
			System.out.println("Ticket ID: " + t.getId());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void viewMyTickets(User user) {
		Screen.separator();
		System.out.println("MY TICKETS");

		Main.store.tickets.stream().filter(t -> user.getId().equals(t.getReporterId())).forEach(t -> System.out
				.println("ID: " + t.getId() + " | Title: " + t.getTitle() + " | Status: " + t.getStatus()));
	}
}
