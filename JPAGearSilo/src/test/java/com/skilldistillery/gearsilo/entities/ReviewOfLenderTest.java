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

	@Test
	@DisplayName("Address entity mapping to address")
	void test() {
		assertEquals(1, reviewLender.getId());
	}

	@Test
	@DisplayName("Address entity mapping to address2")
	void test1() {
		assertEquals(2, reviewLender.getRating());
	}

	@Test
	@DisplayName("Address entity mapping to city")
	void test2() {
		assertEquals("Perfect", reviewLender.getReview());
	}

}
