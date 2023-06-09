package advanced;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class TCPHChatClient {

	public static void main(String[] args) {
		
		Thread thread = new Thread(() -> {
			
			boolean control = true;
			
			System.out.println("Client run...");

			try(Socket socket = new Socket("127.0.0.1", 8080);
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					DataInputStream in = new DataInputStream(socket.getInputStream());
					BufferedReader infoIn = new BufferedReader(new InputStreamReader(System.in));
					) {
				
				while (control) {
					System.out.print("me: ");
					String infoStr = infoIn.readLine();
					out.writeUTF(infoStr);
					out.flush();
					
					if (infoStr.equals("bye")) {
						System.out.println("client exit");
						break;
					}
					
					String str = in.readUTF();
					System.out.printf("server: %s\n", str);
					if (str.equals("bye")) {
						System.out.println("server already exited");
						System.out.println("client exit");
						break;
					}
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, "client thread");
		
		thread.start();
		
	}
}
