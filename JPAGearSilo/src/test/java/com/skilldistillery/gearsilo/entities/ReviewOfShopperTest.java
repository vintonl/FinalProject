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

class ReviewOfShopperTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private ReviewOfShopper reviewShopper;

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
		reviewShopper = em.find(ReviewOfShopper.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		reviewShopper = null;
	}

	@Test
	@DisplayName("ReviewOfGear entity mapping to Id")
	void test() {
		assertEquals(1, reviewShopper.getId());
	}

	@Test
	@DisplayName("ReviewOfGear entity mapping to Rating")
	void test1() {
		assertEquals(5, reviewShopper.getRating());
	}

	@Test
	@DisplayName("ReviewOfGear entity mapping to Review")
	void test2() {
		assertEquals("Larry showed up on time and took great care of the bike!", reviewShopper.getReview());
		assertEquals(1, reviewShopper.getReservation().getId());
	}

}
