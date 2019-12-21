package com.skilldistillery.gearsilo.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.gearsilo.entities.Gear;
import com.skilldistillery.gearsilo.services.GearService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class GearController {

	@Autowired
	private GearService gearSvc;

	@GetMapping("gears")
	public List<Gear> index(HttpServletRequest req, HttpServletResponse res) {
		System.err.println("checking on controller routes");
		return gearSvc.listAllGears();
	}

	@GetMapping("gearslist")
	public List<Gear> listAllGears(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		List<Gear> gearList = gearSvc.listAllGears();
		if (gearList == null) {
			resp.setStatus(404);
		}
		return gearList;
	}

	@GetMapping("gears/{gearId}")
	public Gear show(@PathVariable("gearId") Integer gid, HttpServletRequest req, HttpServletResponse resp) {

		Gear gear = gearSvc.findGear(gid);
		if (gear == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return gear;
	}

	@PostMapping("gears/users")
	public Gear create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Gear gear) {
		
		System.out.println("inside of add gears in gear controller");
		System.out.println(gear);
		try {
			gear = gearSvc.addGear(principal.getName(), gear);
			if (gear == null) {
				res.setStatus(401);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(gear.getId());
				res.addHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(500);
			gear = null;
		}

		return gear;
	}

	@PutMapping("gears/users/{gid}")
	public Gear updateGear(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int gid,
			@RequestBody Gear gear) {
		
		System.out.println("inside pu update gear - printing gear " + gear);
		try {
			gear = gearSvc.updateGear(principal.getName(), gid, gear);
			if (gear == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			gear = null;
		}
		System.out.println("inside pu update gear - printing return of gear" + gear);
		return gear;
	}

	@DeleteMapping("/gears/{gid}")
	public Gear deactivateGear(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable int gid) {
		Gear gear = null;

		try {
			gear = gearSvc.deactivateGear(principal.getName(), gid);
			if (gear == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			gear = null;
		}
		return gear;
	}

	@GetMapping("gears/users/username")
	public List<Gear> getUsersGear(@PathVariable("username") String username, HttpServletResponse resp,
			Principal principal) {

		System.out.println("inside gear controller get gear by user");
		List<Gear> gear = gearSvc.findByUserUsername(username);
		resp.setStatus(200);

		System.out.println(gear);
		return gear;
	}

}
