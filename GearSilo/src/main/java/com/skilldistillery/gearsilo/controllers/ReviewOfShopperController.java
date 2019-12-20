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

import com.skilldistillery.gearsilo.entities.ReviewOfGear;
import com.skilldistillery.gearsilo.entities.ReviewOfShopper;
import com.skilldistillery.gearsilo.services.ReviewOfShopperService;
import com.skilldistillery.gearsilo.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class ReviewOfShopperController {

	@Autowired
	private ReviewOfShopperService shopperReviewSvc;

	@GetMapping("users/{uid}/reviews/shopperreviews")
	public List<ReviewOfShopper> index(@PathVariable int uid, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {
		List<ReviewOfShopper> shopperReview = shopperReviewSvc.findById(principal.getName(), uid);

		if (shopperReview != null && shopperReview.size() == 0) {
			resp.setStatus(204);
		}

		if (shopperReview == null) {
			resp.setStatus(404);
		}
		return shopperReview;
	}
	
	@PostMapping("users/{uid}/reservation/{rid}/reviews/shopperreviews")
	public ReviewOfShopper createShopperReview(@RequestBody ReviewOfShopper shopperReview, @PathVariable int uid,
			@PathVariable int rid, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			shopperReviewSvc.createReviewOfShopper(principal.getName(), shopperReview, uid, rid);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			res.addHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return shopperReview;
	}
	
	@PutMapping("users/{uid}/reservation/{rid}/reviews/shopperreviews/{sid}")
	public ReviewOfShopper updateShopperReview(@RequestBody ReviewOfShopper shopperReview, @PathVariable int uid, 
			@PathVariable int rid, @PathVariable int sid, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			shopperReview = shopperReviewSvc.updateReviewOfShopper(principal.getName(), shopperReview, uid, rid, sid);
			if (shopperReview == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			shopperReview = null;
		}
		return shopperReview;
	}

}
