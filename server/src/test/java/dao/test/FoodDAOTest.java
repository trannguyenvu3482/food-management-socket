/**
 * 
 */
package dao.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.FoodDAO;
import iuh.fit.dao.impl.FoodImpl;
import iuh.fit.entity.Food;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 22 May 2024 - 1:15:49 am
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FoodDAOTest {
	FoodDAO foodDAO;

	@BeforeAll
	void setUp() {
		foodDAO = new FoodImpl();
	}

	@Test
	void testAddFoodCorrect() {
		Food food = new Food("F999", "Bún bò", 30000, "Bún bò Huế", false, Food.Type.MAIN_COURSE, 10, 20);
		boolean result = foodDAO.addFood(food);
		assertTrue(result);
	}

	@Test
	void testAddFoodDuplicateID() {
		Food food = new Food("F101", "Bún bò", 30000, "Bún bò Huế", false, Food.Type.MAIN_COURSE, 10, 20);
		boolean result = foodDAO.addFood(food);
		assertFalse(result);
	}

	@Test
	void testAddFoodInvalidID() {
		Food food = new Food("F99", "Bún bò", 30000, "Bún bò Huế", false, Food.Type.MAIN_COURSE, 10, 20);
		boolean result = foodDAO.addFood(food);
		assertFalse(result);
	}
}
