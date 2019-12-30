package com.skilldistillery.gearsilo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	private ReservationRepository resRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<ReviewOfLender> findAll(String username, int id) {

		User user = userRepo.findUserByUsername(username);
		List<ReviewOfLender> results = new ArrayList<>();
		List<ReviewOfLender> lenderReviews = new ArrayList<>();

		if (user != null) {
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
	public ReviewOfLender createReviewOfLender(String username, ReviewOfLender lenderReview, int id, int resId) {

		User user = userRepo.findUserByUsername(username);

		Reservation reservation;
		Optional<Reservation> resOpt = resRepo.findById(resId);

		if (resOpt.isPresent()) {
			reservation = resOpt.get();

			System.out.println(reservation);
			if (user.getId() == id || user.getRole().equals("admin")) {
				lenderReview.setReservation(reservation);
			}
			reviewLenderRepo.saveAndFlush(lenderReview);
		}

		return lenderReview;
	}

	@Override
	public ReviewOfLender updateReviewOfLender(String username, ReviewOfLender lenderReview, int id, int resId,
			int reviewOfLenderId) {
		User user = userRepo.findUserByUsername(username);
		Optional<Reservation> resOpt = resRepo.findById(resId);
		Reservation reservation;
		ReviewOfLender existing = null;

		if (resOpt.isPresent()) {
			reservation = resOpt.get();
			if (user.getId() == id || user.getRole().equals("admin")) {
				Optional<ReviewOfLender> optRev = reviewLenderRepo.findById(reviewOfLenderId);
				lenderReview.setReservation(reservation);

				if (optRev.isPresent()) {
					existing = optRev.get();
					existing.setRating(lenderReview.getRating());
					existing.setReview(lenderReview.getReview());
					existing.setReservation(lenderReview.getReservation());
					reviewLenderRepo.saveAndFlush(existing);
				} else {
					return null;
				}
			}
		}
		return existing;
	}
}
