package com.lgposse.user.database;

import java.util.ArrayList;

import com.lgposse.thirdparty.MySQLDatabase;
import com.lgposse.user.models.User;

public class UserDatabase extends MySQLDatabase {

	public UserDatabase(String hostname, int port, String database,
			String username, String password) {
		super(hostname, port, database, username, password);
	}
	
	public User getUser(String username) {
		return new User(username);
	}
	
	public User getUser(int id) {
		return null;
	}
	
	public ArrayList<User> getAllUsers() {
		return null;
	}
	
	public int registerUser(User user, String password) {
		return 0;
	}
	
	public User loginUser(String username, String password) {
		return null;
	}

}
