package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.Gear;

public interface GearService {

	List<Gear> listAllGears();

	Gear findGear(int id);

	Gear addGear(String username, Gear gear);

	Gear updateGear(String username, int id, Gear gear);

	List<Gear> showMyGear(String name);

	List<Gear> findByUserUsername(String username);

}
