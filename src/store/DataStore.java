package store;

import model.Ticket;
import model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataStore implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<User> users = new ArrayList<>();
    public List<Ticket> tickets = new ArrayList<>();
}
