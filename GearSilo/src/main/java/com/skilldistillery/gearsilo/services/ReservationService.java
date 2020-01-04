package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.Reservation;

public interface ReservationService {

	public List<Reservation> findAll(String username);

	public Reservation findReservationById(String username, int id);
	
	public Reservation createReservation(String username, Reservation reservation);
	
	public Reservation updateShopperReservation(String username, Reservation reservation, int id);
	
	public boolean deleteReservation(String username, int id);

	List<Reservation> findAllReservationsByUserUsername(String username);

	Reservation updateHostReservation(String username, Reservation reservation, int gid);

	List<Reservation> findAllReservationsByUserShopper(String username);

}
