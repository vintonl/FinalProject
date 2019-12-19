package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.ReviewOfGear;

public interface ReviewOfGearService {

	List<ReviewOfGear> findAll(String username, int uid);
	
}
