package com.skilldistillery.gearsilo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.gearsilo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByUsername(String username);
}