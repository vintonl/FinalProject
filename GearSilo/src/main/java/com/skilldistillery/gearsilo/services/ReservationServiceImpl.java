package com.skilldistillery.gearsilo.services;

import java.util.Date;
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
		User user = uRepo.findUserByUsername(username);

		if (user.getRole().equalsIgnoreCase("admin")) {
			return resRepo.findAll();
		}

		return null;
	}

	@Override
	public List<Reservation> findAllReservationsByUserUsername(String username) {
		User user = uRepo.findUserByUsername(username);

		List<Reservation> reservations = resRepo.findByGearId_User_Id(user.getId());

		return reservations;
	}

	@Override
	public List<Reservation> findAllReservationsByUserShopper(String username) {
		User user = uRepo.findUserByUsername(username);

		List<Reservation> reservations = resRepo.findByUserShopper(user);

		return reservations;
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

		if (reservation.getOpenDate() == null || reservation.getCloseDate() == null) {
			return null;
		}

		if (reservation.getOpenDate().after(reservation.getCloseDate())) {
			return null;
		}

		Date today = new Date();

		if (today.after(reservation.getOpenDate())) {
			return null;
		}

		if (user != null) {
			reservation.setUserShopper(user);
			return resRepo.saveAndFlush(reservation);
		} else {
			return null;
		}
	}

	@Override
	public Reservation updateShopperReservation(String username, Reservation reservation, int id) {

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

	@Override
	public Reservation updateHostReservation(String username, Reservation reservation, int id) {
		Reservation updateReservation = resRepo.findByGearId_User_UsernameAndId(username, id);
		if (reservation != null) {
			updateReservation.setApproved(reservation.getApproved());
			updateReservation.setCompleted(reservation.getCompleted());
			resRepo.saveAndFlush(updateReservation);
		}
		return updateReservation;
	}

	// disable vs delete ...
	@Override
	public boolean deleteReservation(String username, int id) {
		return false;

	}
}
