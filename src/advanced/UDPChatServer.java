package advanced;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPChatServer {

	public static void main(String[] args) {

		Thread t = new Thread(() -> {

			System.out.println("Server run...");

			DatagramSocket socket = null;
			Scanner scanner = null;
			try {
				
				socket = new DatagramSocket(8080);
				scanner = new Scanner(System.in);
				
				while (true) {

					byte[] buffer = new byte[512];
					DatagramPacket RecPacket = new DatagramPacket(buffer, buffer.length);
					socket.receive(RecPacket);
						int len = RecPacket.getLength();

					String recMsg = new String(buffer, 0, len);
					System.out.println("Client: " + recMsg);
					if (recMsg.equalsIgnoreCase("bye")) {
						System.out.println("Client exit.");
						break;
					}

					System.out.print("I: ");
					String msg = scanner.nextLine();
					buffer = msg.getBytes();
					InetAddress address = RecPacket.getAddress();
					int port = RecPacket.getPort();
//					System.out.println(address.getHostName()+address.getHostAddress()+port);
					DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, 
							address, port);
					socket.send(sendPacket);
					if (msg.equals("bye")) {
						System.out.println("Exit...");
						break;
					}
				}

				System.out.println("Chat end.");
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if (socket != null) {
					socket.close();
				}
				
				if (scanner != null) {
					scanner.close();
				}
			}
		});

		t.start();

	}
}
