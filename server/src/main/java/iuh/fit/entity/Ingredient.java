/**
 * 
 */
package iuh.fit.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 21 May 2024 - 1:32:03 am
 */
@Entity(name = "ingredients")
public class Ingredient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6370461527579161116L;
	@Id
	@Column(name = "ingredient_id", nullable = false, unique = true)
	private String id;
	@Column(name = "ingredient_name", nullable = false)
	private String name;
	@Column(name = "unit", nullable = false)
	private String unit;
	@Column(name = "price", nullable = false)
	private double price;
	@Column(name = "quantity", nullable = false)
	private double quantity;
	@Column(name = "manufacturing_date", nullable = false)
	private LocalDate manufacturingDate;
	@Column(name = "expiry_date", nullable = false)
	private LocalDate expiryDate;
	@Column(name = "supplier_name", nullable = false)
	private String supplierName;

	@ManyToMany(cascade = { jakarta.persistence.CascadeType.PERSIST,
			jakarta.persistence.CascadeType.MERGE }, mappedBy = "ingredients")
	private Set<Item> items;

	public Ingredient() {
		super();
	}

	public Ingredient(String id, String name, String unit, double price, double quantity, LocalDate manufacturingDate,
			LocalDate expiryDate, String supplierName) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.quantity = quantity;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
		this.supplierName = supplierName;
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
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
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
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the manufacturingDate
	 */
	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}

	/**
	 * @param manufacturingDate the manufacturingDate to set
	 */
	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	/**
	 * @return the expiryDate
	 */
	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", unit=" + unit + ", price=" + price + ", quantity="
				+ quantity + ", manufacturingDate=" + manufacturingDate + ", expiryDate=" + expiryDate
				+ ", supplierName=" + supplierName + "]";
	}
}
