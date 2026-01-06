package service;

import app.Main;
import model.User;

public class LoginService {

	public static User login(String username, String password) {
		for (User user : Main.store.users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

}
