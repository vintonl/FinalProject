package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.entities.ReviewOfGear;
import com.skilldistillery.gearsilo.entities.ReviewOfLender;

public interface ReviewOfLenderService {

	List<ReviewOfLender> findAll(String username, int uid);

	ReviewOfLender createReviewOfLender(String username, ReviewOfLender lenderReview, int id, int resId);

	public ReviewOfLender updateReviewOfLender(String username, ReviewOfLender lenderReview, int id, int resId, int reviewOfLenderId);


}
