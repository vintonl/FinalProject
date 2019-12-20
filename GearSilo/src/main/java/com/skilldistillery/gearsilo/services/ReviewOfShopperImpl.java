package com.skilldistillery.gearsilo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.entities.ReviewOfGear;
import com.skilldistillery.gearsilo.entities.ReviewOfLender;
import com.skilldistillery.gearsilo.entities.ReviewOfShopper;
import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.repositories.ReservationRepository;
import com.skilldistillery.gearsilo.repositories.ReviewOfShopperRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class ReviewOfShopperImpl implements ReviewOfShopperService {

	@Autowired
	private ReviewOfShopperRepository reviewOfShopperRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;

	public List<ReviewOfShopper> findById(String username, int id) {
		User user = userRepo.findUserByUsername(username);
		List<ReviewOfShopper> results = new ArrayList<>();
		List<ReviewOfShopper> shopperReviews = new ArrayList<>();
		if (user.getId() == id || user.getRole().equals("admin")) {
			shopperReviews = reviewOfShopperRepo.findAll();
			for (ReviewOfShopper reviewOfShopper : shopperReviews) {
				if (reviewOfShopper.getReservation().getUserShopper().getId() == id) {
					results.add(reviewOfShopper);
				}
			}
			return results;
		}
		return null;
	}
	
	@Override
	public ReviewOfShopper createReviewOfShopper(String username, ReviewOfShopper shopperReview, int id, int rid) {
		User user = userRepo.findUserByUsername(username);
		Optional<Reservation> reviewReservationOpt = reservationRepo.findById(rid);
		Reservation reservation;
		if (reviewReservationOpt.isPresent()) {
			reservation = reviewReservationOpt.get();
			if (user.getId() == id || user.getRole().equals("admin")) {
				List<Reservation> reservations = user.getReservations();
				shopperReview.setReservation(reservation);
			}
			reviewOfShopperRepo.saveAndFlush(shopperReview);
		}
		return shopperReview;
	}
	
	@Override
	public ReviewOfShopper updateReviewOfShopper(String username, ReviewOfShopper shopperReview, int id, int rid, int sid) {
		User user = userRepo.findUserByUsername(username);
		Optional<Reservation> reviewReservationOpt = reservationRepo.findById(rid);
		Reservation reservation;
		ReviewOfShopper existing = null;
		if (reviewReservationOpt.isPresent()) {
			reservation = reviewReservationOpt.get();
			if (user.getId() == id || user.getRole().equals("admin")) {
				List<Reservation> reservations = user.getReservations();
				shopperReview.setReservation(reservation);
				Optional<ReviewOfShopper> updateReviewOfShopperOpt = reviewOfShopperRepo.findById(sid);
				if (updateReviewOfShopperOpt.isPresent()) {
					existing = updateReviewOfShopperOpt.get();
					existing.setRating(shopperReview.getRating());
					existing.setReview(shopperReview.getReview());
					reviewOfShopperRepo.saveAndFlush(existing);
				} else {
					return null;
				}
			}
		}
		return existing;
	}
}
