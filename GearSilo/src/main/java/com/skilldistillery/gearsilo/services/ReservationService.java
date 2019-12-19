package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.Reservation;

public interface ReservationService {

	public List<Reservation> findAll(String username);

	public Reservation findReservationById(int id);
	
	public Reservation createReservation(Reservation reservation);
	
	public Reservation updateReservation(Reservation reservation, int id);
	
	public boolean deleteReservation(int id);

}
