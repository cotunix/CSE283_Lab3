import java.net.*;
import java.io.*;

public class Lab3Server {
	public static final int PORT = 4444;

	public static void main(String args[]) throws IOException {
		while (true) {
			
		ServerSocket sock;
		int complete = 0;
		try {
			sock = new ServerSocket(PORT);
		} catch (SocketException e) {
			System.out
					.println("Could not open socket (Is the server already running?)");
			return;
		}

		System.out.println("Server waiting for connection on: "
				+ InetAddress.getLocalHost() + ":" + PORT);
		Socket clientSock = sock.accept();
		System.out.println("Established connection with"
				+ clientSock.getInetAddress() + ":" + clientSock.getPort());
		PrintWriter out = new PrintWriter(clientSock.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSock.getInputStream()));
		String input = "";
		while (true) {
			if ((input = in.readLine()) != null) {
				switch (input) {
				case ("GET WORK"): {
					System.out.println("Client requesting AMP");
					if (complete < Integer.parseInt(args[0])) {
						out.println("AMP WORK");
						String operation = "";
						switch ((int) Math.round(Math.random() * 3)) {
						case 0:
							operation = "+";
							break;
						case 1:
							operation = "-";
							break;
						case 2:
							operation = "*";
							break;
						default:
							operation = "/";
							break;
						}
						double op1 = Math.random() * 100;
						double op2 = Math.random() * 100;
						System.out.println("Sending client AMP: " + op1 + " "
								+ operation + " " + op2);
						out.println(op1 + " " + operation + " " + op2);
					} else {
						System.out.println("No work Available for client.");
						out.println("AMP NONE");
					}
					break;
				}
				case ("PUT ANSWER"): {
					System.out.println("Answer:" + in.readLine());

					out.println("AMP OK");
					complete++;

					break;
				}
				default: {
					System.out
							.println("ERROR: Did not understand client's request");
					out.println("AMP ERROR");
				}

				}

			} else {
				break;

			}

		}

		clientSock.close();
		sock.close();
		System.out.println("Connection terminated, restarting.");
		}
		
	}

}
