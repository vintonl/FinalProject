package com.skilldistillery.gearsilo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> findAll(String username) {

		User user = userRepo.findUserByUsername(username);
		if (user.getRole().equals("admin")) {
			return userRepo.findAll();

		}

		return null;
	}

	@Override
	public User updateUser(User user, String username) {

		User userAdmin = userRepo.findUserByUsername(username);
		
		Optional<User> userOpt = userRepo.findById(user.getId());
		
		if (userAdmin.getRole().equals("admin")) {
			if (userOpt.isPresent()) {
				
			user.setEnabled(user.getEnabled());

			userRepo.saveAndFlush(user);
			return user;
			}
		}

		return null;
	}

	@Override
	public User findUserByUsername(String username) {

		return userRepo.findUserByUsername(username);
	}
	
	public User updateUserProfile(String username, User user) {

		User userPrincipal = userRepo.findUserByUsername(username);

		if (userPrincipal.getId() == user.getId()) {

			Optional<User> userOpt = userRepo.findById(user.getId());
			if (userOpt.isPresent()) {
				User managedUser = userOpt.get();
				managedUser.setFirstName(user.getFirstName());
				managedUser.setLastName(user.getLastName());
				managedUser.setPhone(user.getPhone());
				managedUser.setImageUrl(user.getImageUrl());


				userRepo.saveAndFlush(managedUser);
				return managedUser;
			}
		}
		return null;
	}
}
