package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.Gear;

public interface GearService {

	List<Gear> listAllGears();

	Gear findGear(int id);

	Gear addGear(Gear gear);

	Gear updateGear(int id, Gear gear);

}
