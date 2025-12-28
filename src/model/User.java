package model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Role role;

    // Agent-only field (nullable)
    private Category category;

    public User(Long id, String name, String username, String email,
                String password, Role role) {

        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.category=null;
    }
    public User(Long id, String name, String username, String email,
                String password, Role role,Category category) {

        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.category=category;
    }
    // ---------- getters/setters ----------

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public Category getCategory() { return category; }

    public void setRole(Role role) {
        this.role = role;

        // auto-clear category if not agent
        if (role != Role.AGENT) {
            this.category = null;
        }
    }

    public void setCategory(Category category) {
        if (this.role != Role.AGENT) {
            throw new IllegalStateException("Only AGENT can have category");
        }
        this.category = category;
    }
}
