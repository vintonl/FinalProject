package com.skilldistillery.gearsilo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.entities.ReservationMessage;
import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.repositories.ReservationMessageRepository;
import com.skilldistillery.gearsilo.repositories.ReservationRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class ReservationMessageServiceImpl implements ReservationMessageService {

	@Autowired
	private ReservationMessageRepository resMsgRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ReservationRepository resRepo;

	@Override
	public List<ReservationMessage> findAll(String username) {

		return resMsgRepo.findAll();
	}

	@Override
	public ReservationMessage findReservationMessageById(String username, int resMsgId) {

		ReservationMessage resMsg = null;
		Optional<ReservationMessage> opt = resMsgRepo.findById(resMsgId);
		if (opt.isPresent()) {
			resMsg = opt.get();
			return resMsg;
		} else {
			return null;
		}

	}

	@Override
	public ReservationMessage createReservationMessage(String username, ReservationMessage resMsg, int id, int resId) {
		User user = userRepo.findUserByUsername(username);

		Optional<Reservation> resOpt = resRepo.findById(resId);
		Reservation reservation;

		if (resOpt.isPresent()) {
			reservation = resOpt.get();

			System.out.println(reservation);

			if (user.getId() == id || user.getRole().equals("admin")) {
				List<Reservation> reservations = user.getReservations();
				System.err.println("number of reservations in user: " + reservations.size());
				resMsg.setReservation(reservation);
			}
			resMsgRepo.saveAndFlush(resMsg);
		}

		return resMsg;
	}

	@Override
	public ReservationMessage updateReservationMessage(String username, ReservationMessage resMsg, int resMsgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservationMessage deleteReservationMessage(String username, int resMsg) {
		// TODO Auto-generated method stub
		return null;
	}
}
