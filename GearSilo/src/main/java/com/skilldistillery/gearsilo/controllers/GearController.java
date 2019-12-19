package com.skilldistillery.gearsilo.controllers;

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
import com.skilldistillery.gearsilo.services.GearService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4207" })
public class GearController {

	@Autowired
	private GearService gearSvc;

	@GetMapping("gears")
	public List<Gear> listAllGears(HttpServletRequest req, HttpServletResponse resp) {
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
	
//	@PostMapping("gears")
//	public Gear create(@RequestBody Gear gear, HttpServletRequest req, HttpServletResponse resp) {
//		try {
//			gear = gearSvc.addGear(gear);
//			resp.setStatus(201);
//			StringBuffer url = req.getRequestURL();
//			url.append("/").append(gear.getId());
//			resp.addHeader("Location", url.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//			resp.setStatus(400);
//			gear = null;
//		}
//		return gear;
//}

}
