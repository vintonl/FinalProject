package com.skilldistillery.gearsilo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.entities.ReviewOfGear;
import com.skilldistillery.gearsilo.entities.ReviewOfLender;
import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.repositories.ReviewOfGearRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class ReviewOfGearServiceImpl implements ReviewOfGearService {

	@Autowired
	private ReviewOfGearRepository reviewOfGearRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public List<ReviewOfGear> findAll(String username, int id) {

		User user = userRepo.findUserByUsername(username);
		List<ReviewOfGear> results = new ArrayList<>();
		List<ReviewOfGear> gearReviews = new ArrayList<>();
		if (user.getId() == id || user.getRole().equals("admin")) {
			gearReviews = reviewOfGearRepo.findAll();
			for (ReviewOfGear reviewOfGear : gearReviews) {
				if (reviewOfGear.getReservation().getGearId().getId() == id) {
					results.add(reviewOfGear);
				}
			}
			return results;
		}
		return null;
	}
	
	@Override
	public ReviewOfGear createReviewOfGear(String username, ReviewOfGear gearReview, int id, int rid) {

		User user = userRepo.findUserByUsername(username);
		if (user.getId() == id || user.getRole().equals("admin")) {
			List<Reservation> reservation = user.getReservations();
			for (Reservation reservation2 : reservation) {
				if (reservation2.getId() == rid) {
					gearReview.setReservation(reservation2);
				}
			}
			reviewOfGearRepo.saveAndFlush(gearReview);
		}
		return gearReview;
	}
}
