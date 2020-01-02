package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.User;

public interface UserService {

	List<User> findAll(String username);

	User updateUser(User user, String username);
	
	User updateUserProfile( String username, User user);

	User findUserByUsername(String username);

}
