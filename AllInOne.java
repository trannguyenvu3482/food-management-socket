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
						dos.flush();
						break;
					case 2:
						oos.flush();
						break;
					case 3:
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
