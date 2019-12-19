package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.User;

public interface UserService {

	List<User> findAll();

	User updateUser(int id, User user);

}
