package com.skilldistillery.gearsilo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.entities.ReviewOfGear;
import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.repositories.ReservationRepository;
import com.skilldistillery.gearsilo.repositories.ReviewOfGearRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class ReviewOfGearServiceImpl implements ReviewOfGearService {

	@Autowired
	private ReviewOfGearRepository reviewOfGearRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ReservationRepository reservationRepo;

	public List<ReviewOfGear> findAll(String username, int gearId) {
		User user = userRepo.findUserByUsername(username);
		List<ReviewOfGear> results = new ArrayList<>();
		List<ReviewOfGear> gearReviews = new ArrayList<>();

		if (user != null) {
			
//			gearReviews = reviewOfGearRepo.findAll();

			gearReviews = reviewOfGearRepo.findByActiveTrue();

			for (ReviewOfGear reviewOfGear : gearReviews) {
				if (reviewOfGear.getReservation().getGearId().getId() == gearId) {
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
		// use an optional to make sure that a reservation actually exists
		Optional<Reservation> reviewReservationOpt = reservationRepo.findById(rid);
		Reservation reservation;
		if (reviewReservationOpt.isPresent()) {
			// if the optional exists (is not null), set the optional to the Reservation
			// object
			reservation = reviewReservationOpt.get();
			// if the user is the proper user or an admin, attach this reservation object
			// before save and flush of Gear Review
			if (user.getId() == id || user.getRole().equals("admin")) {
				List<Reservation> reservations = user.getReservations();
				gearReview.setReservation(reservation);
			}
			reviewOfGearRepo.saveAndFlush(gearReview);
		}
		return gearReview;
	}

	@Override
	public ReviewOfGear updateReviewOfGear(String username, ReviewOfGear gearReview, int id, int rid, int grid) {
		User user = userRepo.findUserByUsername(username);
		Optional<Reservation> reviewReservationOpt = reservationRepo.findById(rid);
		Reservation reservation;
		ReviewOfGear existing = null;
		if (reviewReservationOpt.isPresent()) {
			reservation = reviewReservationOpt.get();
			if (user.getId() == id || user.getRole().equals("admin")) {
				List<Reservation> reservations = user.getReservations();
				gearReview.setReservation(reservation);
				Optional<ReviewOfGear> updateReviewOfGearOpt = reviewOfGearRepo.findById(grid);
				if (updateReviewOfGearOpt.isPresent()) {
					existing = updateReviewOfGearOpt.get();
					existing.setRating(gearReview.getRating());
					existing.setReview(gearReview.getReview());
					reviewOfGearRepo.saveAndFlush(existing);
				} else {
					return null;
				}
			}
		}
		return existing;
	}
}
