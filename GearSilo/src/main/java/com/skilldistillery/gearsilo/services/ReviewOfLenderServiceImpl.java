package com.skilldistillery.gearsilo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Reservation;
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

	@Override
	public ReviewOfLender create(String username, ReviewOfLender lenderReview, int id, int rid) {
		System.out.println("in service impl" + lenderReview);

		User user = userRepo.findUserByUsername(username);

		System.out.println(user.getEmail());

		if (user.getId() == id || user.getRole().equals("admin")) {

			List<Reservation> reservation = user.getReservations();

			for (Reservation reservation2 : reservation) {

				if (reservation2.getId() == rid) {

					lenderReview.setReservation(reservation2);

				}

			}

			System.out.println("IN REVIEW OF LENDER " + lenderReview);
			System.out.println("IN REVIEW OF RES ID" + lenderReview.getReservation());

//			lenderReview.getReservation().getGearId().getUser();
//			lenderReview.getReservation().getGearId().setUser(user);
			System.out.println(reviewLenderRepo);
			System.out.println("1");
			reviewLenderRepo.saveAndFlush(lenderReview);
//			reviewLenderRepo.save(lenderReview);
//			System.out.println("2");
//			reviewLenderRepo.flush();
			System.out.println("after flush" + lenderReview);

		}

		return lenderReview;
	}

}
