package dao;


import model.User;


public interface UserDAO {
	public User getUserByEmailAndPassword(String email, String password);
}
