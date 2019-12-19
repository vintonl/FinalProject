package com.skilldistillery.gearsilo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Gear;
import com.skilldistillery.gearsilo.repositories.GearRepository;

@Service
public class GearServiceImpl implements GearService {

	@Autowired
	private GearRepository gearRepo;

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
	public Gear addGear(Gear gear) {
		gearRepo.saveAndFlush(gear);
		return gear;
	}

	@Override
	public Gear updateGear(int id, Gear gear) {
		Optional<Gear> opt = gearRepo.findById(id);
		Gear updateGear = opt.get();
		updateGear.setName(gear.getName());
		updateGear.setCondition(gear.getCondition());
		updateGear.setPrice(gear.getPrice());
		updateGear.setDescription(gear.getDescription());
		updateGear.setImageUrl(gear.getImageUrl());
		updateGear.setImageUrl2(gear.getImageUrl2());
		updateGear.setImageUrl3(gear.getImageUrl3());
		updateGear.setAvailable(gear.getAvailable());
		updateGear.setActive(gear.getActive());
		gearRepo.saveAndFlush(updateGear);
		return updateGear;
	}
}
