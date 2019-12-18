package com.skilldistillery.gearsilo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	
}