/**
 * 
 */
package iuh.fit.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 21 May 2024 - 1:30:23 am
 */

@Entity(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1778374053841299439L;
	@Id
	@Column(name = "id", nullable = false, unique = true)
	private String id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "price", nullable = false)
	private double price;
	@Column(name = "description")
	private String description;

	@Column(name = "on_special")
	private boolean onSpecial;

	@ManyToMany(cascade = { jakarta.persistence.CascadeType.PERSIST, jakarta.persistence.CascadeType.MERGE })
	@JoinTable(name = "items_ingredients", joinColumns = @jakarta.persistence.JoinColumn(name = "item_id", referencedColumnName = "id"), inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id"))
	private Set<Ingredient> ingredients;

	protected Item() {
		super();
	}

	protected Item(String id, String name, double price, String description, boolean onSpecial) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.onSpecial = onSpecial;
	}

	/**
	 * @param name2
	 * @param price2
	 * @param description2
	 * @param onSpecial2
	 */
	public Item(String name2, double price2, String description2, boolean onSpecial2) {
		this("", name2, price2, description2, onSpecial2);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the onSpecial
	 */
	public boolean isOnSpecial() {
		return onSpecial;
	}

	/**
	 * @param onSpecial the onSpecial to set
	 */
	public void setOnSpecial(boolean onSpecial) {
		this.onSpecial = onSpecial;
	}

	@Override
	public String toString() {
		return "Item [description=" + description + ", id=" + id + ", name=" + name + ", onSpecial=" + onSpecial
				+ ", price=" + price + "]";
	}
}
