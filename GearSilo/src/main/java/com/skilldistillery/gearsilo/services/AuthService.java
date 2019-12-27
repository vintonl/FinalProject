package com.skilldistillery.gearsilo.services;

import com.skilldistillery.gearsilo.entities.User;

public interface AuthService {

	User register(User user);

	boolean isUserUnique(User user);

}
