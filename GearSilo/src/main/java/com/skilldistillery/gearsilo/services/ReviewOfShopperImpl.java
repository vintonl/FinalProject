package com.skilldistillery.gearsilo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.ReviewOfLender;
import com.skilldistillery.gearsilo.entities.ReviewOfShopper;
import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.repositories.ReviewOfShopperRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class ReviewOfShopperImpl implements ReviewOfShopperService {

	@Autowired
	private ReviewOfShopperRepository reviewShopperRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<ReviewOfShopper> findAll(String username, int id) {

		User user = userRepo.findUserByUsername(username);
		List<ReviewOfShopper> results = new ArrayList<>();
		List<ReviewOfShopper> shopperReviews = new ArrayList<>();

		if (user.getId() == id || user.getRole().equals("admin")) {

			shopperReviews = reviewShopperRepo.findAll();
			

			for (ReviewOfShopper reviewOfShopper : shopperReviews) {

				if (reviewOfShopper.getReservation().getGearId().getId()  == id) {

					System.out.println(reviewOfShopper);

					results.add(reviewOfShopper);

				}

			}
			return results;

		}
		return null;

	}
}
