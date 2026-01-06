package dao;

import java.util.List;

import model.User;

public interface UserDao {
	public User createUser(User user);
	
	
	public User getUserById(Long id);
	
	public List<User> getAllUsers();
	
	public User editUserById(Long id, User user);
	
	
	public void deleteUserById(Long id);
	
	
}
