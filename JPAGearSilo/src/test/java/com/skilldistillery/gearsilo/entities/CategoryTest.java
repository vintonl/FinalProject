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

class CategoryTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Category category;

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
		category = em.find(Category.class, 1);
	}
	

	@AfterEach
	void tearDown() throws Exception {
		category = null;
		em.close();
	}

	@Test
	void test_User_entity__mappings() {
		assertNotNull(category);
		assertEquals(1, category.getId());
		assertEquals("Mountain biking", category.getName());		
	}
	@Test
	@DisplayName("category to gears")
	void test_User_entity__mappings_to_gears() {
		assertNotNull(category);
		assertEquals(1, category.getGearList().size());		
	}
	
//	@Test
//	@DisplayName("testing user-address relationship mappings")
//	void test2() {
//		// SELECT adr.street FROM address adr JOIN user usr ON usr.address_id = adr.id
//		// WHERE usr.id = 1;
//		assertEquals("silo", user.getLastName());
//	}


}
