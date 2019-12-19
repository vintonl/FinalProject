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

import com.skilldistillery.gearsilo.entities.ReviewOfShopper;
import com.skilldistillery.gearsilo.services.ReviewOfShopperService;
import com.skilldistillery.gearsilo.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class ReviewOfShopperController {

	@Autowired
	private ReviewOfShopperService shopperReviewSvc;

	@Autowired
	private UserService userSvc;

	@GetMapping("user/{uid}/reviews/shopperreviews")
	public List<ReviewOfShopper> findShopperReviewsListByUser(@PathVariable int uid, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {

		List<ReviewOfShopper> shopperReview = shopperReviewSvc.findAll(principal.getName(), uid);

		if (shopperReview != null && shopperReview.size() == 0) {
			resp.setStatus(204);
		}

		if (shopperReview == null) {
			resp.setStatus(404);
		}

		return shopperReview;
	}

}
