package screens;

import app.Main;
import model.Agent;
import model.Category;
import model.Role;
import model.User;

public class RegisterScreen {

    public static void show() {
        Screen.separator();

        System.out.println("REGISTER NEW UER");

        System.out.println("Enter Name:  ");
        String name = Screen.sc.nextLine();
        System.out.println("Enter Username");
        String username = Screen.sc.nextLine();
        System.out.println("Enter email");
        String email = Screen.sc.nextLine();
        System.out.println("Enter Password");
        String password = Screen.sc.nextLine();

        System.out.println("Select Role:  USER / AGENT / ADMIN");
        Role role = Role.valueOf(Screen.sc.nextLine().toUpperCase());

        long id = System.currentTimeMillis();

        User newUser;

        if (role == Role.AGENT) {
            System.out.println("Choose Category");
            for (Category c : Category.values()) {
                System.out.println("- " + c);
            }

            Category category = Category.valueOf(Screen.sc.nextLine().toUpperCase());
            newUser = new Agent(id, name, username, email, password, category);
        } else {
            newUser = new User(id, name, username, email, password, role);

        }
        Main.store.users.add(newUser);
        System.out.println("User Registered Successfully");
        System.out.println("User ID:  " + id + "| Role:  " + role);
    }

    public static void selfRegister() {
        Screen.separator();

        System.out.println("REGISTER NEW UER");

        System.out.println("Enter Name:  ");
        String name = Screen.sc.nextLine();
        System.out.println("Enter Username");
        String username = Screen.sc.nextLine();
        System.out.println("Enter email");
        String email = Screen.sc.nextLine();
        System.out.println("Enter Password");
        String password = Screen.sc.nextLine();

//        System.out.println("Select Role:  USER / AGENT / ADMIN");
        Role role = Role.USER;

        long id = System.currentTimeMillis();

        User newUser;
        newUser = new User(id, name, username, email, password, role);

        Main.store.users.add(newUser);
        System.out.println("User Registered Successfully");
        System.out.println("User ID:  " + id + "| Role:  " + role);
    }
}
