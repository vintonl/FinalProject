package com.skilldistillery.gearsilo.services;

import java.util.List;

import com.skilldistillery.gearsilo.entities.Address;

public interface AddressService {
	
	public List<Address> allAddresses();
	
	public Address showAddress(Integer addressId);
	
	public Address createAddress(Address address);
	
	public Address update(Address address, Integer addressId);
	
	public boolean delete(Integer id);
	
}
