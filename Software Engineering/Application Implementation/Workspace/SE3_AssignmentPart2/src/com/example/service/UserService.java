package com.example.service;

import com.example.business.User;
import com.example.dao.UserDao;
import com.example.exceptions.DaoException;

public class UserService {

	UserDao dao = new UserDao();
	
	public User login(String username, String password){
		
		User u = null;
		try {			
			u = dao.findUserByUsernamePassword(username, password);
		} 
		catch (DaoException e) {
			e.printStackTrace();
		}
		return u;
		
	}
	
	public User changePassword(String password, String username){
		
		User u = null;
		try {			
			u = dao.changePassword(password, username);
		} 
		catch (DaoException e) {
			e.printStackTrace();
		}
		return u;
		
	}
}
