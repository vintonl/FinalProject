package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.ReviewOfLender;

public interface ReviewOfLenderService {

	List<ReviewOfLender> findAll(String username, int id);

	ReviewOfLender createReviewOfLender(String username, ReviewOfLender lenderReview, int id, int resId);

	public ReviewOfLender updateReviewOfLender(String username, ReviewOfLender lenderReview, int id, int resId, int reviewOfLenderId);

}
