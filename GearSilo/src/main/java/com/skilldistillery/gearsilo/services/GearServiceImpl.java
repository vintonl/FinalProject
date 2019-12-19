package com.skilldistillery.gearsilo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Gear;
import com.skilldistillery.gearsilo.entities.User;
import com.skilldistillery.gearsilo.repositories.GearRepository;
import com.skilldistillery.gearsilo.repositories.UserRepository;

@Service
public class GearServiceImpl implements GearService {

	@Autowired
	private GearRepository gearRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Gear> showMyGear(String username) {
		return gearRepo.findByUser_Username(username);
	}
		
	@Override
	public List<Gear> listAllGears() {
		return gearRepo.findAll();
	}

	@Override
	public Gear findGear(int id) {
		Gear gear = null;
		Optional<Gear> opt = gearRepo.findById(id);
		if (opt.isPresent()) {
			gear = opt.get();
		}
		return gear;
	}
	
	@Override
	public Gear addGear(String username, Gear gear) {
		User user = userRepo.findUserByUsername(username);
		if (user != null) {
			gear.setUser(user);
			gearRepo.saveAndFlush(gear);
		} else {
			gear = null;
		}
		return gear;
	}

	@Override
	public Gear updateGear(String username, int gid, Gear gear) {
		Gear updateGear = gearRepo.findByUser_UsernameAndId(username, gid);
		if (updateGear != null) {
		updateGear.setName(gear.getName());
		updateGear.setGearCondition(gear.getGearCondition());
		updateGear.setPrice(gear.getPrice());
		updateGear.setDescription(gear.getDescription());
		updateGear.setImageUrl(gear.getImageUrl());
		updateGear.setImageUrl2(gear.getImageUrl2());
		updateGear.setImageUrl3(gear.getImageUrl3());
		updateGear.setAvailable(gear.getAvailable());
		updateGear.setActive(gear.getActive());
		gearRepo.saveAndFlush(updateGear);
		}
		return updateGear;
	}
	
}
