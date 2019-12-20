package com.skilldistillery.gearsilo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.Gear;

public interface GearRepository extends JpaRepository<Gear, Integer> {

	Gear findByUser_UsernameAndId(String username, int gid);

	List<Gear> findByUser_Username(String username);

	List<Gear> findByUserUsername(String username);
}