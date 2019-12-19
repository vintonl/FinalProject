package com.skilldistillery.gearsilo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gearsilo.entities.ReviewOfGear;
import com.skilldistillery.gearsilo.services.ReviewOfGearService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class ReviewOfGearController {
	
	@Autowired
	private ReviewOfGearService reviewGearSvc;
	
	@GetMapping("users/{uid}/reviews")
	public List<ReviewOfGear> index (@PathVariable int uid, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {
		List<ReviewOfGear> gearReview = reviewGearSvc.findAll(principal.getName(), uid);

		if (gearReview != null && gearReview.size() == 0) {
			resp.setStatus(204);
		}

		if (gearReview == null) {
			resp.setStatus(404);
		}
		return gearReview;
	}	
}
