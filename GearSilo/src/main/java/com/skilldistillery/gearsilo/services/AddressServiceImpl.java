package com.skilldistillery.gearsilo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gearsilo.entities.Address;
import com.skilldistillery.gearsilo.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addRepo;

	@Override
	public List<Address> allAddresses() {
		
		return addRepo.findAll();
	}

	@Override
	public Address showAddress(Integer addressId) {
		Optional<Address> op = addRepo.findById(addressId);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public Address createAddress(Address address) {
		addRepo.saveAndFlush(address);
		return address;
	}

	@Override
	public Address update(Address address, Integer addressId) {
		Address existing = null;
		Optional<Address> op = addRepo.findById(addressId);
		if(op.isPresent()) {
			existing = op.get();
			existing.setAddress(address.getAddress());
			existing.setAddress2(address.getAddress2());
			existing.setCity(address.getCity());
			existing.setState(address.getState());
			existing.setPostalCode(address.getPostalCode());
			existing.setCountry(address.getCountry());
			addRepo.saveAndFlush(existing);
		} else {
			return null;
		}
		return existing;
	}

	@Override
	public boolean delete(Integer addressId) {
		boolean successful = true;
		if (!addRepo.existsById(addressId)){
			return false;
		} else {
			addRepo.deleteById(addressId);
			return successful;
		}
	}
}
