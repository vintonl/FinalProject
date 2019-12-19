package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.ReviewOfLender;

public interface ReviewOfLenderService {

	List<ReviewOfLender> findAll(String username, int uid);

//	List<ReviewOfLender> findByUser_Id( int uid);

//	ReviewOfLender findById();

}
