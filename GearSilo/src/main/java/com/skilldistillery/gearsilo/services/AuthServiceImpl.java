package com.skilldistillery.gearsilo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Address;
import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.repositories.AddressRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user) {
		
		if(user.getFirstName().length() < 1 || user.getLastName().length() < 1 || user.getPhone().length() < 10) {
			return null;
		}
		
		if(user.getAddress().getPostalCode() < 501 || user.getAddress().getAddress().length() < 3 || user.getAddress().getState().length() < 2) {
			return null;
		}
		
		if(user.getAddress().getCity().length() < 1) {
			return null;
		}
		
		if (user.getAddress() != null) {
			Address newAdd = user.getAddress();
			newAdd = addressRepo.saveAndFlush(newAdd);
			user.setAddress(newAdd);
		}
		
		String encrypted = encoder.encode(user.getPassword());
		user.setPassword(encrypted);
		user.setEnabled(true);
		user.setRole("stardard");
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public boolean isUserUnique(User user) {
		User userFound = userRepo.findUserByUsername(user.getUsername());

		if (userFound != null) {
			return false;
		}
		
		return true;
	}

}
