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
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gearsilo.entities.Gear;
import com.skilldistillery.gearsilo.entities.ReviewOfGear;
import com.skilldistillery.gearsilo.services.ReviewOfGearService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class ReviewOfGearController {
	
	@Autowired
	private ReviewOfGearService reviewGearSvc;
	
	@GetMapping("users/{uid}/reviews/gearreviews")
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
	
	@PostMapping("user/{uid}/reservation/{rid}/reviews/gearreviews")
	public ReviewOfGear createGearReview(@RequestBody ReviewOfGear gearReview, @PathVariable int uid,
			@PathVariable int rid, HttpServletRequest req, HttpServletResponse res, Principal principal) {
	
		try {
			reviewGearSvc.createReviewOfGear(principal.getName(), gearReview, uid, rid);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			res.addHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return gearReview;
	}
	
//	@PostMapping("users/{uid}/gears")
//	public Gear create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Gear gear,
//			@PathVariable("uid") int uid) {
//		try {
//			gear = gearSvc.addGear(principal.getName(), gear);
//			if (gear == null) {
//				res.setStatus(401);
//			} else {
//				res.setStatus(201);
//				StringBuffer url = req.getRequestURL();
//				url.append("/").append(gear.getId());
//				res.addHeader("Location", url.toString());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			res.setStatus(500);
//			gear = null;
//		}
//
//		return gear;
//	}
}
