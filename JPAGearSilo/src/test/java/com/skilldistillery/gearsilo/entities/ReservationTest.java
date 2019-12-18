package com.skilldistillery.gearsilo.entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReservationTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Reservation resv;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("GearSiloPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		resv = em.find(Reservation.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		resv = null;
		em = null;
	}

	@Test
	void test() {
//		fail("Not yet implemented");
		assertNotNull(resv);
	}

	@Test
	@DisplayName("Mappings Test")
	void test2() {
		assertEquals("Mountain Bike", resv.getGearId().getName());
		assertEquals("Larry", resv.getUserShopper().getFirstName());

	}

	@Test
	void test3() {
		assertEquals(1, resv.getId());
		assertEquals(false, resv.isCompleted());
		assertEquals(true, resv.isApproved());
		assertEquals(4, resv.getGearReview().getRating());
		assertEquals(5, resv.getLenderReview().getRating());
	}

}
