/**
 * 
 */
package iuh.fit.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iuh.fit.dao.FoodDAO;
import iuh.fit.entity.Food;
import iuh.fit.entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 21 May 2024 - 2:38:12 am
 */
public class FoodImpl implements FoodDAO {
	private EntityManager em;

	public FoodImpl() {
		em = Persistence.createEntityManagerFactory("food-management-server").createEntityManager();
	}

	@Override
	public boolean addFood(Food food) {
		try {
			if (food == null || food.getId().isBlank() || !food.getId().matches("F\\d{3}")) {
				return false;
			}

			em.getTransaction().begin();
			em.persist(food);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Item> listItems(String supplierName) {
		try {
			// Create query to get all items of a supplier name (only match part of the
			// name, case insensitive, with supplier name in Beverage class which is child
			// of Item)
			return em.createQuery("SELECT b FROM beverages b WHERE b.supplierName LIKE :name and b.onSpecial = true",
					Item.class).setParameter("name", "%" + supplierName + "%").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Food, Double> listFoodAndCost() {
		try {
			// Create query to get all food and their cost
			// Price = SUM(quantity * price) of the ingredients (from the joined table) +
			// (preparationTime + servingTime) * 0.2
			// Group by food.id, food.preparationTime, food.servingTime
			// Order by cost descending
			String query = "SELECT f.id, SUM(i.quantity * i.price) + (f.preparationTime + f.servingTime) * 0.2 AS cost "
					+ "FROM foods f JOIN f.ingredients i " + "GROUP BY f.id, f.preparationTime, f.servingTime "
					+ "ORDER BY cost DESC";
			List<?> result = em.createQuery(query).getResultList();
			Map<Food, Double> map = new HashMap<>();
			for (Object obj : result) {
				Object[] arr = (Object[]) obj;
				map.put(em.find(Food.class, arr[0]), (Double) arr[1]);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}
}
