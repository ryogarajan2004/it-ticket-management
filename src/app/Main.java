package app;

import database.Connect;
import model.Role;
import model.User;
import screens.LoginScreen;
import store.DataStore;
import store.DataStoreManager;

public class Main {
	public static DataStore store;
	public static User currentUser;

	public static void main(String[] args) throws ClassNotFoundException
{

		store = DataStoreManager.loadData();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> DataStoreManager.saveData(store))

		);
		Class.forName("org.sqlite.JDBC");


		if (store.users.isEmpty() || (store.users.stream().noneMatch(x -> x.getRole().equals(Role.ADMIN)))) {
			store.users.add(new User(1L, "Admin", "admin", "admin@gmail.com", "1234", Role.ADMIN, null));
			System.out.println("Default admin created:  admin/ 1234");
		}
		Connect.defaultSchema();
		while (true) {
			try {
				LoginScreen.show();
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
		}
	}
}
