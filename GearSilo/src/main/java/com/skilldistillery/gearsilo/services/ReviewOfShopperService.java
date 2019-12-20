package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.ReviewOfLender;
import com.skilldistillery.gearsilo.entities.ReviewOfShopper;

public interface ReviewOfShopperService {

	List<ReviewOfShopper> findById(String username, int uid);

	ReviewOfShopper createReviewOfShopper(String username, ReviewOfShopper shopperReview, int id, int rid);

	ReviewOfShopper updateReviewOfShopper(String username, ReviewOfShopper shopperReview, int id, int rid, int sid);

}