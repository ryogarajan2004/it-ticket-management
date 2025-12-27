package model;

public class Agent extends User {
    private Category category;

    public Agent(Long id, String name, String username, String email,
                 String password, Category category) {

        super(id, name, username, email, password, Role.AGENT);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
