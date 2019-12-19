package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.ReviewOfLender;
import com.skilldistillery.gearsilo.entities.ReviewOfShopper;

public interface ReviewOfShopperService {

	List<ReviewOfShopper> findAll(String name, int uid);

}