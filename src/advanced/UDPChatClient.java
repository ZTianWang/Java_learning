package advanced;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPChatClient {

	public static void main(String[] args) {
Thread t = new Thread(() -> {
			
			System.out.println("Client run...");
//			byte[] buffer;
//			Scanner scanner;
			
			try (DatagramSocket socket = new DatagramSocket();
					Scanner scanner = new Scanner(System.in);) {
				
				while (true) {
					
					System.out.print("I: ");
					String msg = scanner.nextLine();
					byte[] buffer = msg.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, 
							InetAddress.getLocalHost(), 8080);
					socket.send(sendPacket);
					if (msg.equals("bye")) {
						System.out.println("Exit...");
						break;
					}
					
					buffer = new byte[512];
					DatagramPacket RecPacket = new DatagramPacket(buffer, buffer.length);
					socket.receive(RecPacket);
					int len = RecPacket.getLength();
					String recMsg = new String(buffer, 0, len);
					System.out.println("Sever: "+ recMsg);
					if (recMsg.equalsIgnoreCase("bye")) {
						System.out.println("Sever exit.");
						break;
					}
					
				}
				System.out.println("Chat end.");
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		t.start();
		
	}
}
