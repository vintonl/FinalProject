package com.skilldistillery.gearsilo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	
}