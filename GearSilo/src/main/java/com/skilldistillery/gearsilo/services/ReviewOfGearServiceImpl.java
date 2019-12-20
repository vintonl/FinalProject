package com.skilldistillery.gearsilo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.entities.ReviewOfGear;
import com.skilldistillery.gearsilo.entities.ReviewOfLender;
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
		// use an optional to make sure that a reservation actually exists
		Optional<Reservation> reviewReservationOpt = reservationRepo.findById(rid);
		Reservation reservation;
		if (reviewReservationOpt.isPresent()) {
			// if the optional exists (is not null), set the optional to the Reservation object
			reservation = reviewReservationOpt.get();
			System.err.println(reservation);
			// if the user is the proper user or an admin, attach this reservation object before save and flush of Gear Review
			if (user.getId() == id || user.getRole().equals("admin")) {
				List<Reservation> reservations = user.getReservations();
				System.err.println("number of reservations in user: " + reservations.size());
				gearReview.setReservation(reservation);
//				for (Reservation reservation2 : reservations) {
//					if (reservation2.equals(reservation)) {
//						System.err.println("in the foreach loop setting reservation to review");
//					}
//				}
			}
			reviewOfGearRepo.saveAndFlush(gearReview);
		}
		return gearReview;
	}
}
