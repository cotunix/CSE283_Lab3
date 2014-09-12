import java.net.*;
import java.io.*;

public class Lab3Server {
	public static final int PORT = 4444;

	public static void main(String args[]) throws IOException {
		ServerSocket sock;
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
			PrintWriter out = new PrintWriter(clientSock.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSock.getInputStream()));
			String input = "";
			while (true) {
				if ((input = in.readLine()) != null) {
				switch (input) {
				case ("GET WORK"): {
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
					out.println((Math.random() * 100) + " " + operation + " "
							+ (Math.random() * 100));
					break;
				}
				case ("PUT ANSWER"): {
					System.out.println("Answer:" + in.readLine());
					out.println("AMP OK");

					break;
				}
				}
			}
				else
					break;
			}
			

	clientSock.close();
	sock.close();
		}

	}

