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

import com.skilldistillery.gearsilo.entities.Reservation;
import com.skilldistillery.gearsilo.entities.ReviewOfLender;
import com.skilldistillery.gearsilo.services.ReviewOfLenderService;
import com.skilldistillery.gearsilo.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class ReviewOfLenderController {

	@Autowired
	private ReviewOfLenderService lenderReviewSvc;

	@Autowired
	private UserService userSvc;

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

//	@PostMapping("user/{uid}/reservation/{rid}/reviews/lenderreviews")
//	public ReviewOfLender createLenderReview(@RequestBody ReviewOfLender lenderReview, @PathVariable int uid,
//			@PathVariable int rid, HttpServletRequest req, HttpServletResponse res, Principal principal) {
//
////		List<ReviewOfLender> lenderReview = lenderReviewSvc.findAll(principal.getName(), lenderReview);
//
//		System.out.println("inside constroller add review");
//
//		try {
//			lenderReviewSvc.create(principal.getName(), lenderReview, uid, rid);
//			res.setStatus(201);
////			resp.addHeader("Location", "http://localhost:8082/api/addresses/" + address.getId());
//			StringBuffer url = req.getRequestURL();
////			url.append("/").append(todo.getId());
//			res.addHeader("Location", url.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			res.setStatus(400);
//			e.printStackTrace();
//		}
//		return lenderReview;
//	}

}
