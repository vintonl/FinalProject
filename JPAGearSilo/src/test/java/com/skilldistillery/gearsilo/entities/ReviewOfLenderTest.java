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

class ReviewOfLenderTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private ReviewOfLender reviewLender;

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
		reviewLender = em.find(ReviewOfLender.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		reviewLender = null;
	}

//	@Test
//	@DisplayName("ReviewOfLender entity mapping to Id")
//	void test() {
//		assertEquals(1, reviewLender.getId());
//	}
//
//	@Test
//	@DisplayName("ReviewOfLender entity mapping to Rating")
//	void test1() {
//		assertEquals(5, reviewLender.getRating());
//	}
//
	@Test
	@DisplayName("ReviewOfLender entity mapping to Review")
	void test2() {
		assertEquals("Lender was timely and had great tips!", reviewLender.getReview());
	}
//	@Test
//	@DisplayName("ReviewOfLender to Reservation")
//	void test3() {
//		assertEquals("2019-12-19 00:00:00.0", reviewLender.getReservation().getOpenDate().toString());
//	}

}
