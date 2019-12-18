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

class AddressTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Address address;

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
		address = em.find(Address.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		address = null;
	}

	@Test
	@DisplayName("Address entity mapping to Id")
	void test() {
		assertEquals(1, address.getId());
	}
	@Test
	@DisplayName("Address entity mapping to address")
	void test1() {
		assertEquals("7400 E Orchard Rd #1450n", address.getAddress());
	}

	@Test
	@DisplayName("Address entity mapping to address2")
	void test2() {
		assertEquals(null, address.getAddress2());
	}

	@Test
	@DisplayName("Address entity mapping to city")
	void test3() {
		assertEquals("Greenwood Village", address.getCity());
	}

	@Test
	@DisplayName("Address entity mapping to state")
	void test4() {
		assertEquals("Colorado", address.getState());
	}

	@Test
	@DisplayName("Address entity mapping to postal code")
	void test5() {
		assertEquals(80111, address.getPostalCode());
	}

	@Test
	@DisplayName("Address entity mapping to country")
	void test6() {
		assertEquals("USA", address.getCountry());
	}

}
