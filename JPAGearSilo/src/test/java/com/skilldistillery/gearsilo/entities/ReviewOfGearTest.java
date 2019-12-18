package com.skilldistillery.gearsilo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReviewOfGearTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private ReviewOfGear reviewGear;

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
		reviewGear = em.find(ReviewOfGear.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		reviewGear = null;
	}

	@Test
	@DisplayName("ReviewOfGear entity mapping to Id")
	void test() {
		assertEquals(1, reviewGear.getId());
	}

	@Test
	@DisplayName("ReviewOfGear entity mapping to Rating")
	void test1() {
		assertEquals(4, reviewGear.getRating());
	}

	@Test
	@DisplayName("ReviewOfGear entity mapping to Review")
	void test2() {
		assertEquals("Mountain Bike road well!", reviewGear.getReview());
	}

}
