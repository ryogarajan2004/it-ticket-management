package service;
import model.User;
import store.DataStore;

import java.util.*;
public class LoginServices {
    public static User login(Long id,String password)
    {
        for(User user: DataStore.users)
        {
            if(Objects.equals(user.getId(), id) && user.getPassword().equals(password))
            {
                return user;
            }
        }
        return null;
    }
}
