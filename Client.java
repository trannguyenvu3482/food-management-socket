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
					dos.flush();
					break;

				case 2:
					break;
				case 3:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
