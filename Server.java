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