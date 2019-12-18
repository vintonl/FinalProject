package com.skilldistillery.gearsilo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.Address;
import com.skilldistillery.gearsilo.entities.ReservationMessage;

public interface ReservationMessageRepository extends JpaRepository<ReservationMessage, Integer> {

	
}