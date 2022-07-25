package edu.multicamps.dao;


import edu.multicamps.model.User;


public interface UserDAO {
	public User getUserByEmailAndPassword(String email, String password);
}
