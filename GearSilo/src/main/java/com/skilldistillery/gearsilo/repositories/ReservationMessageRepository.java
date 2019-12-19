package com.skilldistillery.gearsilo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.entities.ReservationMessage;

public interface ReservationMessageRepository extends JpaRepository<ReservationMessage, Integer> {
		
	
	
}