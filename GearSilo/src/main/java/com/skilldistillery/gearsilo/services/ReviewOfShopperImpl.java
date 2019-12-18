package com.skilldistillery.gearsilo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.repositories.ReviewOfShopperRepository;

@Service
public class ReviewOfShopperImpl implements ReviewOfShopperService {

	@Autowired
	private ReviewOfShopperRepository reviewShopperRepo;
}
