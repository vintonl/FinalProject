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

import com.skilldistillery.gearsilo.entities.ReviewOfLender;
import com.skilldistillery.gearsilo.entities.User;
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

//	@GetMapping("user/{id}/reviews/lenderreview/{lid}")
//	public List<ReviewOfLender> findLenderReviewById(HttpServletRequest req, HttpServletResponse resp,
//			Principal principal) {
//		ReviewOfLender lenderReview = lenderReviewSvc.show(principal.getName(), tid);
//
//		if (lenderReview == null) {
//			resp.setStatus(404);
//		}
//		if (lenderReview.size() == 0) {x
//			resp.setStatus(204);
//		}
//
//		return lenderReview;
//	}

//	@PutMapping("users/{id}")
//	public User replaceExistingUser(@RequestBody User user, @PathVariable int id, HttpServletRequest req,
//			HttpServletResponse resp) {
//		try {
//			user = reviewSvc.updateUser(id, user);
//			if (user == null) {
//				resp.setStatus(404);
//				return null;
//			}
//			resp.setStatus(202);
//			StringBuffer url = req.getRequestURL();
//			url.append("/").append(user.getId());
//			resp.addHeader("Location", url.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//			resp.setStatus(400);
//			return null;
//		}
//		return user;
//	}
}
