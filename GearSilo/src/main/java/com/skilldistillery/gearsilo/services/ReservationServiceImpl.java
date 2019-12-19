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
	public Reservation findReservationById(int id) {
		System.err.println("In Impl " + id);
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
	public Reservation createReservation(Reservation reservation) {
		return resRepo.saveAndFlush(reservation);
	}

	@Override
	public Reservation updateReservation(Reservation reservation, int id) {
		return null;
	}

	@Override
	public boolean deleteReservation(int id) { // disable vs delete ...
		return false;
	}

}
