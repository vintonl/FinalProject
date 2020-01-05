package com.skilldistillery.gearsilo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.ReviewOfShopper;

public interface ReviewOfShopperRepository extends JpaRepository<ReviewOfShopper, Integer> {

	List<ReviewOfShopper> findByActiveTrue();

	
}