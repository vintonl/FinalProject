package com.skilldistillery.gearsilo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReservationMessageTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private ReservationMessage res;

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
		res = em.find(ReservationMessage.class, 1);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		res = null;
		em.close();
	}

//	@Test
//	void test_Reservation_Message_entity__mappings() {
//		assertNotNull(res);
//		assertEquals("gearsilo@gmail.com", res.getMessage());
//		
//		
//	}



}
