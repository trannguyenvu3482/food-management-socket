/**
 * 
 */
package iuh.fit.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import iuh.fit.entity.Food;
import iuh.fit.entity.Food.Type;
import iuh.fit.entity.Item;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 21 May 2024 - 8:24:22 pm
 */
public class Client {
	public static void main(String[] args) {
		try (Socket socket = new Socket("DUZ-PC", 8201);
				Scanner sc = new Scanner(System.in);
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

			dos.flush();
			while (true) {
				System.out.println("1. Add new food");
				System.out.println("2. List items of supplier name");
				System.out.println("3. List food and cost");
				System.out.println("0. Exit");
				System.out.println("=".repeat(40));
				System.out.print("Enter your choice (1-3 or 0): ");

				int choice = sc.nextInt();
				System.out.println("Client: Choice: " + choice);
				dos.writeInt(choice);

				switch (choice) {
				case 1:
					sc.nextLine();
					// Food(String id, String name, double price, String description, boolean
					// onSpecial, Type type, int preparationTime, int servingTime)
					System.out.print("Enter id: ");
					String id;
					do {
						id = sc.nextLine();
						if (!id.matches("F\\d{3}")) {
							System.out.println("Client: Invalid id");
						}
					} while (!id.matches("F\\d{3}"));

					System.out.print("Enter name: ");
					String name = sc.nextLine();
					System.out.print("Enter price: ");
					double price = sc.nextDouble();
					sc.nextLine();
					System.out.print("Enter description: ");
					String description = sc.nextLine();
					System.out.print("Enter onSpecial (true/false): ");
					boolean onSpecial = sc.nextBoolean();
					System.out.print("Enter type (1 = APPETIZER, 2 = MAIN_COURSE, 3 = DESSERT): ");
					int type = sc.nextInt();
					Type foodType = Type.values()[type - 1];
					System.out.print("Enter preparation time: ");
					int preparationTime = sc.nextInt();
					System.out.print("Enter serving time: ");
					int servingTime = sc.nextInt();
					Food food = new Food(id, name, price, description, onSpecial, foodType, preparationTime,
							servingTime);
					oos.writeObject(food);

					boolean result = dis.readBoolean();
					if (result) {
						System.out.println("Client: Add food successfully");
					} else {
						System.out.println("Client: Add food failed");
					}

					System.out.println("Press Enter to continue...");
					sc.nextLine();
					sc.nextLine();
					dos.flush();
					break;

				case 2:
					sc.nextLine();
					System.out.print("Enter supplier name: ");
					String supplierName = sc.nextLine();
					dos.writeUTF(supplierName);
					dos.flush();

					@SuppressWarnings("unchecked")
					List<Item> foods = (List<Item>) ois.readObject();
					System.out.println("Client: " + foods.size() + " items found");
					for (Item item : foods) {
						System.out.println(item);
					}
					break;
				case 3:
					@SuppressWarnings("unchecked")
					Map<Food, Double> foodCosts = (Map<Food, Double>) ois.readObject();
					System.out.println("Client: " + foodCosts.size() + " food found");
					for (Map.Entry<Food, Double> entry : foodCosts.entrySet()) {
						System.out.println(entry.getKey() + " - Cost: " + entry.getValue());
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
