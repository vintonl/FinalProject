package com.skilldistillery.gearsilo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Category;
import com.skilldistillery.gearsilo.entities.Gear;
import com.skilldistillery.gearsilo.repositories.CategoryRepository;
import com.skilldistillery.gearsilo.repositories.GearRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private GearRepository gearRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Category> listAllCategories() {
		return catRepo.findAll();
	}

}
