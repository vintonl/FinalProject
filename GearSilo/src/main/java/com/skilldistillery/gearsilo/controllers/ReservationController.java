package com.skilldistillery.gearsilo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public List<Reservation> index(Principal p, HttpServletRequest req, HttpServletResponse resp) {
		List<Reservation> res = resSvc.findAll(p.getName());

		if (res != null && res.size() == 0) {
			resp.setStatus(204);
		}

		if (res == null) {
			resp.setStatus(404);
		}

		return res;
	}

	@GetMapping("reservations/users")
	public List<Reservation> indexByUser(Principal p, HttpServletRequest req, HttpServletResponse resp) {
		List<Reservation> res = resSvc.findAllReservationsByUserUsername(p.getName());

		if (res != null && res.size() == 0) {
			resp.setStatus(204);
		}

		if (res == null) {
			resp.setStatus(404);
		}

		return res;
	}

	@GetMapping("reservations/users/shoppers")
	public List<Reservation> indexByUserShopper(Principal p, HttpServletRequest req, HttpServletResponse resp) {
		
		List<Reservation> res = resSvc.findAllReservationsByUserShopper(p.getName());

		if (res != null && res.size() == 0) {
			resp.setStatus(204);
		}

		if (res == null) {
			resp.setStatus(404);
		}
		
		return res;
	}

	@PostMapping("reservations")
	public Reservation createReservation(@RequestBody Reservation reservation, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {

		Reservation newRes = resSvc.createReservation(principal.getName(), reservation);

		if (newRes != null) {
			StringBuffer url = req.getRequestURL();
			resp.addHeader("Location", url.toString());
			resp.setStatus(201);
			return newRes;
		} else {
			resp.setStatus(401);
			return null;
		}

	}

	@PutMapping("reservations/users")
	public Reservation updateReservation(HttpServletRequest req, HttpServletResponse resp, Principal principal,
			@RequestBody Reservation reservation) {

		try {
			reservation = resSvc.updateHostReservation(principal.getName(), reservation, reservation.getId());
			if (reservation == null) {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			reservation = null;
		}
		
		return reservation;
	}

}
