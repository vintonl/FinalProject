package com.skilldistillery.gearsilo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gearsilo.entities.Gear;
import com.skilldistillery.gearsilo.services.ReviewOfGearService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class ReviewOfGearController {
	
	@Autowired
	private ReviewOfGearService reviewGearSvc;
	
//	@GetMapping("users/{uid}/reviews")
//	public List<Gear> index (HttpServletRequest req, HttpServletResponse res, Principal principal) {
//		return reviewGearSvc.showMyReviewOfGear(principal.getName());
//	}
}
