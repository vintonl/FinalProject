package com.skilldistillery.gearsilo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		user = null;
		em.close();
	}

	@Test
	@DisplayName("test primary fields")
	void test_Post_entity__mappings() {
		assertNotNull(user);
		assertEquals(1, user.getId());
		assertEquals("gear", user.getFirstName());
		assertEquals("silo", user.getLastName());
		assertEquals("gearsilo@gmail.com", user.getEmail());
		assertEquals("gear", user.getPassword());
		assertTrue(user.getCreatedAt().toString().contains("2019-12-17"));
		assertTrue(user.getUpdatedAt().toString().contains("2019-12-17"));
		assertEquals("admin", user.getRole());
		assertEquals("sdafasd", user.getImageUrl());
		assertEquals("afdsadf", user.getAbout());
		assertEquals("(555)555-5555", user.getPhone());

	}

	@Test
	@DisplayName("testing user-address relationship mappings")
	void test2() {
		// SELECT adr.posal_code FROM address adr JOIN user usr ON usr.address_id = adr.id
		// WHERE usr.id = 1;
		assertNotNull(user);
		assertEquals(80111, user.getAddress().getPostalCode());
	}

}
