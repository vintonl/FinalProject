package com.skilldistillery.gearsilo.controllers;

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

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class ReservationController {

	@Autowired
	private ReservationService resSvc;

	@GetMapping("reservations")
	@ResponseBody
	public List<Reservation> index() {
		return resSvc.findAll();

	}

	@GetMapping("reservations/{id}")
	@ResponseBody
	public Reservation findReservationById(@PathVariable int id, HttpServletResponse resp) {
		System.err.println("In Controller" + id);
		Reservation res = resSvc.findReservationById(id);
		if (res == null) {
			resp.setStatus(404);
		}
		return res;
	}

	@PostMapping("reservations")
	@ResponseBody
	public Reservation createReservation(@RequestBody Reservation reservation, HttpServletRequest req,
			HttpServletResponse resp) {

		try {
			resSvc.createReservation(reservation);
			resp.setStatus(201);
			return reservation;
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

	}

}
