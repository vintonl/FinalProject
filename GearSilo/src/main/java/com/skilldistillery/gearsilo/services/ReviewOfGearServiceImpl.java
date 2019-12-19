package com.skilldistillery.gearsilo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.ReviewOfGear;
import com.skilldistillery.gearsilo.repositories.ReviewOfGearRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class ReviewOfGearServiceImpl implements ReviewOfGearService {

	@Autowired
	private ReviewOfGearRepository reviewOfGearRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public List<ReviewOfGear> showMyReviewOfGear(String username) {
		return reviewOfGearRepo.findByUser_Username(username);
	}

	@Override
	public List<ReviewOfGear> findAll(String name, int uid) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
