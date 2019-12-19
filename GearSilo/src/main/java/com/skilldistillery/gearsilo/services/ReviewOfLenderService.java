package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.entities.ReviewOfLender;

public interface ReviewOfLenderService {

	List<ReviewOfLender> findAll(String username, int uid);

	public ReviewOfLender create(String name, ReviewOfLender lenderReview, int id, int rid);

//	List<ReviewOfLender> findByUser_Id( int uid);

//	ReviewOfLender findById();

}
