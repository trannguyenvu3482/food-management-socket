/**
 * 
 */
package iuh.fit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 21 May 2024 - 1:26:08 am
 */
public class Main {

	public static void main(String[] args) {
		// Test connection JPA by creating EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("food-management-server");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.getTransaction().commit();

	}
}
