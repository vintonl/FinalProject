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
	public User updateUser(int id, User user) {

		Optional<User> userOpt = userRepo.findById(id);
		if (userOpt.isPresent()) {
			User managedUser = userOpt.get();
			managedUser.setFirstName(user.getFirstName());
			managedUser.setLastName(user.getLastName());
			managedUser.setEmail(user.getEmail());
			managedUser.setPassword(user.getPassword());
			managedUser.setCreatedAt(user.getCreatedAt());
			managedUser.setUpdatedAt(user.getUpdatedAt());
			managedUser.setRole(user.getRole());

			// TODO check to make sure security works

			userRepo.saveAndFlush(managedUser);
			return managedUser;
		}

		return null;
	}

	@Override
	public User findUserByUsername(String username) {

		return userRepo.findUserByUsername(username);
	}
}
