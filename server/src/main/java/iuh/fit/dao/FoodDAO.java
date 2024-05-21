/**
 * 
 */
package iuh.fit.dao;

import java.util.List;
import java.util.Map;

import iuh.fit.entity.Food;
import iuh.fit.entity.Item;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 21 May 2024 - 2:37:53 am
 */
public interface FoodDAO {
	public boolean addFood(Food food);

	public List<Item> listItems(String supplierName);

	public Map<Food, Double> listFoodAndCost();
}
