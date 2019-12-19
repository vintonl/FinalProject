package com.skilldistillery.gearsilo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.services.ReservationService;
import com.skilldistillery.gearsilo.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class ReservationController {

	@Autowired
	private ReservationService resSvc;
	
	

	@GetMapping("reservations")
	@ResponseBody
	public List<Reservation> index(Principal p, HttpServletRequest req,
			HttpServletResponse resp) {
		
		List<Reservation> res = resSvc.findAll(p.getName());
		
		if (res != null && res.size() == 0) {
			resp.setStatus(204);
		}
		
		if (res == null) {
			resp.setStatus(404);
		}
		
		return res;

	}

	@GetMapping("reservations/{id}")
	@ResponseBody
	public Reservation findReservationById(@PathVariable int id, HttpServletResponse resp, Principal p) {
		
		Reservation res = resSvc.findReservationById(p.getName(), id);
		if (res == null) {
			resp.setStatus(404);
		}
		return res;
	}

	@PostMapping("reservations")
	@ResponseBody
	public Reservation createReservation(@RequestBody Reservation reservation, HttpServletRequest req,
			HttpServletResponse resp, Principal p) {

		try {
			resSvc.createReservation(p.getName(), reservation);
			resp.setStatus(201);
			return reservation;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

	}

}
