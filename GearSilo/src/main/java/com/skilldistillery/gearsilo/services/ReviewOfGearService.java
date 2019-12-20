package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.Gear;
import com.skilldistillery.gearsilo.entities.ReviewOfGear;

public interface ReviewOfGearService {

	List<ReviewOfGear> findAll(String username, int uid);

	ReviewOfGear createReviewOfGear(String username, ReviewOfGear gearReview, int id, int rid);

	ReviewOfGear updateReviewOfGear(String username, ReviewOfGear gearReview, int id, int rid, int grid);
	
}
