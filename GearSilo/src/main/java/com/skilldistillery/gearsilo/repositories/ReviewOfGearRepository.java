package com.skilldistillery.gearsilo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.ReviewOfGear;

public interface ReviewOfGearRepository extends JpaRepository<ReviewOfGear, Integer> {
	
}