package edu.multicampus.dao;

import edu.multicampus.model.User;

public interface UserDAO {
	public User getUserByEmailAndPassword(String email, String password);
}
