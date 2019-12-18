package com.skilldistillery.gearsilo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.repositories.ReviewOfLenderRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class ReviewOfLenderServiceImpl implements ReviewOfLenderService {

	@Autowired
	private ReviewOfLenderRepository reviewLenderRepo;
}
