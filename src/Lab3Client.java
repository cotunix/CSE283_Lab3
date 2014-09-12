import java.net.*;
import java.io.*;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Lab3Client {
	
	public static void main(String args[]) throws IOException, ScriptException {
		InetAddress i;
		// ScriptEngine, found documentation on stackOverflow, will evaluate
		// functions in string form.
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		try {
			i = InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
			return;
		}
		try {
			int port = Integer.parseInt(args[1]);
			Socket sock = new Socket(i, port);
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));

			System.out.println("Requesting work from server");
			out.println("GET WORK");
			System.out.println("Server: " + in.readLine());

			String ques = in.readLine();
			System.out.println("Server: " + ques);
			Object answer = engine.eval(ques);
			System.out.println("Sending answer back to server: " + answer);

			out.println("PUT ANSWER");
			out.println(answer);
			System.out.println("Server: " + in.readLine());
			sock.close();
		} catch (SocketException e) {
			System.out.println("Could not connect to server.");
		}
	}
}
