package com.skilldistillery.gearsilo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.ReviewOfLender;
import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.repositories.ReviewOfLenderRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class ReviewOfLenderServiceImpl implements ReviewOfLenderService {

	@Autowired
	private ReviewOfLenderRepository reviewLenderRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<ReviewOfLender> findAll(String username, int id) {

		User user = userRepo.findUserByUsername(username);
		List<ReviewOfLender> results = new ArrayList<>();
		List<ReviewOfLender> lenderReviews = new ArrayList<>();

		if (user.getId() == id || user.getRole().equals("admin")) {

			lenderReviews = reviewLenderRepo.findAll();

			for (ReviewOfLender reviewOfLender : lenderReviews) {

				if (reviewOfLender.getReservation().getGearId().getId() == id) {

					results.add(reviewOfLender);

				}

			}
			return results;

		}
		return null;

	}

//	@Override
//	public List<ReviewOfLender> findByUser_Id(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
