/**
 * 
 */
package iuh.fit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 21 May 2024 - 1:43:04 am
 */

enum Size {
	LARGE, MEDIUM, SMALL
}

@Entity(name = "beverages")
public class Beverage extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7474907497518495732L;
	@Enumerated(EnumType.STRING)
	private Size size;
	@jakarta.persistence.Column(name = "supplier_name", nullable = false)
	private String supplierName;

	public Beverage() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param description
	 * @param onSpecial
	 * @param size
	 * @param supplierName
	 */
	public Beverage(String id, String name, double price, String description, boolean onSpecial, Size size,
			String supplierName) {
		super(id, name, price, description, onSpecial);
		this.size = size;
		this.supplierName = supplierName;
	}

	/**
	 * @return the size
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Size size) {
		this.size = size;
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
		return "Beverage [size=" + size + ", supplierName=" + supplierName + "]";
	}

}
