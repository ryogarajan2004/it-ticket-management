package app;

import model.Role;
import model.User;
import screens.LoginScreen;
import store.DataStore;
import store.DataStoreManager;

public class Main {
    public static DataStore store;

    public static void main(String[] args) {

        store = DataStoreManager.loadData();

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> DataStoreManager.saveData(store))
        );

        if (store.users.isEmpty()) {
            store.users.add(new User(1L, "Admin", "admin", "admin@gmail.com", "1234", Role.ADMIN));
            System.out.println("Default admin created:  admin/ 1234");
        }
        while (true) {
            LoginScreen.show();
        }
    }
}
