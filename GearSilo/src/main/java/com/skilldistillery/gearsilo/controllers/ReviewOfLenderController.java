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

import com.skilldistillery.gearsilo.entities.ReviewOfLender;
import com.skilldistillery.gearsilo.services.ReviewOfLenderService;
import com.skilldistillery.gearsilo.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class ReviewOfLenderController {

	@Autowired
	private ReviewOfLenderService lenderReviewSvc;

	@GetMapping("user/{uid}/reviews/lenderreviews")
	public List<ReviewOfLender> findLenderReviewsListByUser(@PathVariable int uid, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {

		List<ReviewOfLender> lenderReview = lenderReviewSvc.findAll(principal.getName(), uid);

		if (lenderReview != null && lenderReview.size() == 0) {
			resp.setStatus(204);
		}

		if (lenderReview == null) {
			resp.setStatus(404);
		}

		return lenderReview;
	}

	@PostMapping("user/{uid}/reservation/{rid}/reviews/lenderreviews")
	public ReviewOfLender createLenderReview(@RequestBody ReviewOfLender lenderReview, @PathVariable int uid,
			@PathVariable int rid, HttpServletRequest req, HttpServletResponse res, Principal principal) {

		System.out.println("inside constroller add review");

		try {
			lenderReviewSvc.createReviewOfLender(principal.getName(), lenderReview, uid, rid);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			res.addHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return lenderReview;
	}
	
	@PutMapping("user/{uid}/reservation/{rid}/reviews/lenderreviews/{lrid}")
	public ReviewOfLender updateReviewOfLender(@RequestBody ReviewOfLender lenderReview, @PathVariable int uid,
			@PathVariable int rid,@PathVariable int lrid, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			lenderReview = lenderReviewSvc.updateReviewOfLender(principal.getName(), lenderReview, uid, rid, lrid);
			if (lenderReview == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			lenderReview = null;
		}
		return lenderReview;
	}

}
