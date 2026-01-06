package screens;

import app.Main;

import model.User;
import service.LoginService;

import java.util.Scanner;

public class LoginScreen {

	static Scanner sc = new Scanner(System.in);

	public static void show() {
		Screen.separator();
		System.out.println("WELCOME");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("q. Exit");
		String choice = sc.nextLine();
		switch (choice) {
		case "1" -> LoginScreen.loginScreen();
		case "2" -> RegisterScreen.selfRegister();
		case "q" -> System.exit(0);
		default -> {
			System.out.println("Wrong choice");
			LoginScreen.show();
		}
		}
	}

	public static void loginScreen() {
		Screen.separator();
		System.out.println("LOGIN");

		System.out.println("Username:");
		String username = sc.nextLine();
		System.out.println("Password:");
		String password = sc.nextLine();

		User u = LoginService.login(username, password);

		if (u == null) {
			System.out.println("Invalid username or password");
			return;
		}
		Main.currentUser = u;

		switch (u.getRole()) {
		case USER -> UserScreen.show(u);
		case ADMIN -> AdminScreen.show(u);
		case AGENT -> AgentScreen.show(u);
		}
	}
}
