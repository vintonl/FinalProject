package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.ReviewOfGear;

public interface ReviewOfGearService {
	
	List<ReviewOfGear> showMyReviewOfGear(String username);

}
