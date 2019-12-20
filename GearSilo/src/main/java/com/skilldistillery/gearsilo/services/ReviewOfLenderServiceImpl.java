package com.skilldistillery.gearsilo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.entities.ReviewOfLender;
import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.repositories.ReservationRepository;
import com.skilldistillery.gearsilo.repositories.ReviewOfLenderRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class ReviewOfLenderServiceImpl implements ReviewOfLenderService {

	@Autowired
	private ReviewOfLenderRepository reviewLenderRepo;

	@Autowired
	ReservationRepository resRepo;

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

	@Override
	public ReviewOfLender create(String username, ReviewOfLender lenderReview, int resId) {

		User user = userRepo.findUserByUsername(username);
		// Might need more in the if to check if resId is correct or this could be set in angular code?
		if (user != null && reviewLenderRepo.findReservationById(username, resId) != null) {
			reviewLenderRepo.saveAndFlush(lenderReview);

		} else {
			lenderReview = null;
		}
		return lenderReview;
	}


	@Override
	public ReviewOfLender update(String username, ReviewOfLender lenderReview, int resId, int reviewOfLenderId) {
		ReviewOfLender existing = null;
		Optional<ReviewOfLender> optRev = reviewLenderRepo.findById(reviewOfLenderId);
		
		if(optRev.isPresent()) {
			existing = optRev.get();
			existing.setRating(lenderReview.getRating());
			existing.setReview(lenderReview.getReview());
			existing.setReservation(reviewLenderRepo.findReservationById(username, resId));
			reviewLenderRepo.saveAndFlush(existing);
		} else {
			return null;
		}
		return existing;
	}

}
		// Admin feature for create?
//		if (user.getId() == id || user.getRole().equals("admin")) {
//
//			List<Reservation> reservation = user.getReservations();
//
//			for (Reservation reservation2 : reservation) {
//
//				if (reservation2.getId() == rid) {
//
//					lenderReview.setReservation(reservation2);
//
//				}
//
//			}


