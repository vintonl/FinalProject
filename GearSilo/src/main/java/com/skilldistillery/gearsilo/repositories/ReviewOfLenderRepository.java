package com.skilldistillery.gearsilo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.ReviewOfLender;

public interface ReviewOfLenderRepository extends JpaRepository<ReviewOfLender, Integer> {

	List<ReviewOfLender> findByActiveTrue();



}