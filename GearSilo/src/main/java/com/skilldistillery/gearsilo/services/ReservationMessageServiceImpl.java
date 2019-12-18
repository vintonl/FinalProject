package com.skilldistillery.gearsilo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.repositories.ReservationMessageRepository;

@Service
public class ReservationMessageServiceImpl implements ReservationMessageService {

	@Autowired
	private ReservationMessageRepository resMessRepo;
}
