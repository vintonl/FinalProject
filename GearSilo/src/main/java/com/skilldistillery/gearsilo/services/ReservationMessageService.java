package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.ReservationMessage;

public interface ReservationMessageService {
	
	public List<ReservationMessage> findAll(String username);
	
	public ReservationMessage findReservationMessageById(String username, int resMsgId);

	public ReservationMessage createReservationMessage(String username, ReservationMessage resMsg, int id, int resId);
		
	public ReservationMessage updateReservationMessage(String username, ReservationMessage resMsg, int resMsgId);
	
	public ReservationMessage deleteReservationMessage(String username, int resMsg);


}
