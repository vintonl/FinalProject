package com.skilldistillery.gearsilo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
