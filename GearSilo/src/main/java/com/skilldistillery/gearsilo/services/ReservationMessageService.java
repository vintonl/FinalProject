package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.ReservationMessage;
import com.skilldistillery.gearsilo.entities.ReviewOfLender;

public interface ReservationMessageService {
	
	List<ReservationMessage> findAll(String username, int id);
	
	public ReservationMessage findReservationMessageById(String username, int resMsgId);

	public ReservationMessage createReservationMessage(String username, ReservationMessage resMsg, int id, int resId);
		
	public ReservationMessage updateReservationMessage(String username, ReservationMessage resMsg, int id, int resId, int rmid);
	
	public boolean deleteReservationMessage(String username, int rmid);

	
}
