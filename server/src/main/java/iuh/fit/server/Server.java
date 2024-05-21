/**
 * 
 */
package iuh.fit.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import iuh.fit.dao.FoodDAO;
import iuh.fit.dao.impl.FoodImpl;
import iuh.fit.entity.Food;
import iuh.fit.entity.Item;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 21 May 2024 - 8:14:51 pm
 */
public class Server {
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(8201)) {
			System.out.println("Server is running on port 8201");

			while (true) {
				System.out.println("Server: Waiting for client");
				Socket socket = server.accept();
				Server temp = new Server();

				System.out.println("Server: Client " + socket.getLocalAddress().getHostName() + " connected");

				new Thread(temp.new Handler(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class Handler implements Runnable {
		private Socket socket;
		private FoodDAO foodDAO;

		public Handler(Socket socket) {
			this.socket = socket;
			foodDAO = new FoodImpl();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

				int choice;
				while (true) {
					choice = dis.readInt();
					System.out.println("Server: Choice: " + choice);

					switch (choice) {
					case 1:
						// Get all foods
						Food food = (Food) ois.readObject();
						boolean result = foodDAO.addFood(food);
						dos.writeBoolean(result);
						dos.flush();
						break;
					case 2:
						// Get food by supplier name
						String supplierName = dis.readUTF();
						List<Item> foods = foodDAO.listItems(supplierName);
						System.out.println("Server: " + foods.size() + " items found");
						oos.writeObject(foods);
						oos.flush();
						break;
					case 3:
						// Get all food and cost
						oos.writeObject(foodDAO.listFoodAndCost());
						oos.flush();
						break;
					default:
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
