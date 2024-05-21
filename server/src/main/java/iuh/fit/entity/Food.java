/**
 * 
 */
package iuh.fit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 21 May 2024 - 1:39:14 am
 */

@Entity(name = "foods")
public class Food extends Item {
	public enum Type {
		APPETIZER, MAIN_COURSE, DESSERT
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4270088523026513661L;
	@Enumerated(EnumType.STRING)
	private Type type;
	@Column(name = "preparation_time", nullable = false)
	private int preparationTime;
	@Column(name = "serving_time", nullable = false)
	private int servingTime;

	public Food() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param description
	 * @param onSpecial
	 * @param type
	 * @param preparationTime
	 * @param servingTime
	 */

	public Food(String name, double price, String description, boolean onSpecial, Type type, int preparationTime,
			int servingTime) {
		super(name, price, description, onSpecial);
		this.type = type;
		this.preparationTime = preparationTime;
		this.servingTime = servingTime;
	}

	public Food(String id, String name, double price, String description, boolean onSpecial, Type type,
			int preparationTime, int servingTime) {
		super(id, name, price, description, onSpecial);
		this.type = type;
		this.preparationTime = preparationTime;
		this.servingTime = servingTime;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the preparationTime
	 */
	public int getPreparationTime() {
		return preparationTime;
	}

	/**
	 * @param preparationTime the preparationTime to set
	 */
	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}

	/**
	 * @return the servingTime
	 */
	public int getServingTime() {
		return servingTime;
	}

	/**
	 * @param servingTime the servingTime to set
	 */
	public void setServingTime(int servingTime) {
		this.servingTime = servingTime;
	}

	@Override
	public String toString() {
		return "Food [type=" + type + ", preparationTime=" + preparationTime + ", servingTime=" + servingTime + "]";
	}
}
