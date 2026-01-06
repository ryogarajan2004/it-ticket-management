package screens;

import java.util.List;

import app.Main;
import model.Ticket;
import model.User;
import service.AgentService;

public class AgentScreen {

	public static void show(User agent) {

		while (true) {
			Screen.separator();
			System.out.println("AGENT DASHBOARD (" + agent.getId() + ")");
			System.out.println("Category: " + agent.getCategory());

			System.out.println("1. View Assigned Tickets");
			System.out.println("2. Work on Ticket");
			System.out.println("3. Add Remark");
			System.out.println("q. Logout");

			String c = Screen.sc.nextLine();

			switch (c) {
			case "1" -> viewAssigned(agent);
			case "2" -> workOnTicket();
			case "3" -> addRemark();
			case "q" -> {
				return;
			}
			default -> System.out.println("Invalid option");
			}
		}
	}

	private static void viewAssigned(User agent) {
		Screen.separator();
		System.out.println("TICKETS ASSIGNED TO YOU");

		Main.store.tickets.stream().filter(t -> agent.getId().equals(t.getAssigneeId())).forEach(t -> System.out
				.println("ID: " + t.getId() + " | Title: " + t.getTitle() + " | Status: " + t.getStatus()));
	}

	private static void workOnTicket() {
		try {
		List<Ticket> myTickets=	Main.store.tickets.stream().filter(x->x.getAssigneeId().equals(Main.currentUser.getId())).toList();
		for(Ticket t:myTickets)
		{
			System.out.println("Ticket id: "+ t.getId()+"\n Title: "+t.getTitle()+" \nStatus: "+t.getStatus());
			
		}
			System.out.print("Enter Ticket ID: ");
			
			long id = Long.parseLong(Screen.sc.nextLine());
			if(myTickets.stream().filter(x->x.getId().equals(id)).count()==0) {
				System.out.println("Ticket Not found");
				return;
			}
			System.out.println("1. Mark IN_PROGRESS");
			System.out.println("2. Resolve Ticket");

			String choice = Screen.sc.nextLine();

			if ("1".equals(choice)) {
				AgentService.markInProgress(id);
				System.out.println("Ticket marked IN_PROGRESS");
			} else if ("2".equals(choice)) {
				AgentService.resolve(id);
				System.out.println("Ticket resolved");
			} else {
				System.out.println("Invalid choice");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void addRemark() {
		try {
			System.out.print("Enter Ticket ID: ");
			long id = Long.parseLong(Screen.sc.nextLine());

			System.out.print("Enter remark: ");
			String remark = Screen.sc.nextLine();

			AgentService.addRemark(id, remark);
			System.out.println("Remark added");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
