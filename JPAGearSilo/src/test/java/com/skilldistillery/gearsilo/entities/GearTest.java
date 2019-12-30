package com.skilldistillery.gearsilo.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GearTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Gear gear;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("GearSiloPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		gear = em.find(Gear.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		gear = null;
		em.close();
	}

	@Test
	@DisplayName("test primary fields")
	void test_Post_entity__mappings() {
		assertNotNull(gear);
		assertEquals(1, gear.getId());
		assertEquals("Mountain Bike", gear.getName());
		assertEquals("New", gear.getGearCondition());
		assertEquals(50.0, gear.getPrice());
		assertEquals("Black,10 gear", gear.getDescription());
		assertEquals("https://i.imgur.com/vPbnSXC.jpg",gear.getImageUrl());
		assertTrue(gear.getAvailable());
		assertTrue(gear.getActive());
	}
	@Test
	@DisplayName("test gear to category")
	void test_Post_entity__mappings_gear_to_category() {
		assertNotNull(gear);
		assertEquals(3, gear.getCategories().size());	
		assertEquals("Mountain Biking", gear.getCategories().get(0).getName());	
	}

}
