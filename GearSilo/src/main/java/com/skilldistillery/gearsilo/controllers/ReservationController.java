package com.skilldistillery.gearsilo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.services.ReservationService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class ReservationController {
		
	@Autowired
	private ReservationService resSvc;
	
	@GetMapping("reservations")
	@ResponseBody
	private List<Reservation> index(HttpServletRequest req, HttpServletResponse res) {
		return resSvc.findAll();
		
	}
	
}
