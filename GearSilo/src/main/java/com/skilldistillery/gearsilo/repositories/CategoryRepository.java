package com.skilldistillery.gearsilo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.Category;
import com.skilldistillery.gearsilo.entities.Gear;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	
}