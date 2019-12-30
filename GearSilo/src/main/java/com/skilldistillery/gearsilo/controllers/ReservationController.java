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
		System.out.println("IN RES CONTROLLER");

		if (res != null && res.size() == 0) {
			resp.setStatus(204);
		}

		if (res == null) {
			resp.setStatus(404);
		}
		System.out.println("IN RES CONTROLLER RETURN");
		System.out.println(res);
		return res;
	}

	@GetMapping("reservations/users")
	public List<Reservation> indexByUser(Principal p, HttpServletRequest req, HttpServletResponse resp) {
		List<Reservation> res = resSvc.findAllReservationsByUserUsername(p.getName());
		System.out.println("IN RES CONTROLLER");

		if (res != null && res.size() == 0) {
			resp.setStatus(204);
		}

		if (res == null) {
			resp.setStatus(404);
		}
		System.out.println("IN RES CONTROLLER RETURN");
		System.out.println(res);
		return res;
	}

//	@GetMapping("user{id}reservations/{id}")
//	public Reservation findReservationById(@PathVariable int id, HttpServletResponse resp, Principal p) {
//
//		Reservation res = resSvc.findReservationById(p.getName(), id);
//		if (res == null) {
//			resp.setStatus(404);
//		}
//		return res;
//	}

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

//	@PutMapping("reservations/users/{id}")
//	public Reservation updateReservation(@RequestBody Reservation reservation, @PathVariable int id,
//			HttpServletRequest req, HttpServletResponse resp, Principal p) {
//		System.out.println("inside res controller update - " + reservation.getApproved());
//
//		try {
//			resp.setStatus(201);
//			StringBuffer url = req.getRequestURL();
//			resp.addHeader("Location", url.toString());
//			return resSvc.updateHostReservation(p.getName(), reservation, id);
//
//		} catch (Exception e) {
//			resp.setStatus(400);
//			return null;
//		}
//	}

}
