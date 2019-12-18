package com.skilldistillery.gearsilo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.Gear;

public interface GearRepository extends JpaRepository<Gear, Integer> {

	
}