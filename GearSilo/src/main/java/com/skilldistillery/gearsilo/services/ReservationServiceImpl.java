package com.skilldistillery.gearsilo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.repositories.ReservationRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository resRepo;
	
	@Autowired
	private UserRepository uRepo;

	@Override
	public List<Reservation> findAll(String username) {
		
		return resRepo.findAll();
		
	}

	@Override
	public Reservation findReservationById(String username, int id) {
		
		Reservation res = null;
		Optional<Reservation> opt = resRepo.findById(id);
		if (opt.isPresent()) {
			res = opt.get();
			
			return res;
		} else {
			return null;
		}

	}

	@Override
	public Reservation createReservation(String username, Reservation reservation) {

		User user = uRepo.findUserByUsername(username);

		if (user != null) {
			reservation.setUserShopper(user);
			return resRepo.saveAndFlush(reservation);
		} else {
			return null;
		}
	}

	@Override
	public Reservation updateReservation(String username, Reservation reservation, int id) {
		
		Reservation oldRes = resRepo.findByUserShopper_UsernameAndId(username, id);
		
		if (oldRes != null) {
			
			oldRes.setApproved(reservation.getApproved());
			oldRes.setCloseDate(reservation.getCloseDate());
			oldRes.setCompleted(reservation.getCompleted());
			oldRes.setCreatedAt(reservation.getCreatedAt());
			oldRes.setGearId(reservation.getGearId());
			oldRes.setGearReview(reservation.getGearReview());
			oldRes.setLenderReview(reservation.getLenderReview());
			oldRes.setMessages(reservation.getMessages());
			oldRes.setOpenDate(reservation.getOpenDate());
			oldRes.setShopperReview(reservation.getShopperReview());
			return resRepo.saveAndFlush(oldRes);
			
		} else {
			return null;
		}
		
		
		
		
	}
	
	// disable vs delete ... 
	@Override
	public boolean deleteReservation(String username, int id) {
		return false; 

	}
}
