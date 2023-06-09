package advanced;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPChatServer {

	public static void main(String[] args) {
		
		Thread thread = new Thread(() -> {
			
			boolean control = true;
			
			System.out.println("Server run...");

			try (ServerSocket server = new ServerSocket(8080);
					Socket socket = server.accept();
					DataInputStream in = new DataInputStream(socket.getInputStream());
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					BufferedReader infoIn = new BufferedReader(new InputStreamReader(System.in));
					) {
				
				while (control) {
					String str = in.readUTF();
					System.out.printf("client: %s\n", str);
					
					if (str.equals("bye")) {
						System.out.println("client already exited.");
						System.out.println("server exit.");
						break;
					}
					
					System.out.print("me: ");
					String infoStr = infoIn.readLine();
					out.writeUTF(infoStr);
					out.flush();
					
					if (infoStr.equals("bye")) {
						System.out.println("server exit.");
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, "server thread");
		
		thread.start();
		
	}
}
