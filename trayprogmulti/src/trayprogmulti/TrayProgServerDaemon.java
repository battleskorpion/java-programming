package trayprogmulti;

import java.net.*;
import java.io.*; 

public class TrayProgServerDaemon extends Thread {
	
	//public boolean continueProcess; 
	
	public TrayProgServerDaemon() {
		start(); 
	}
	
	public void run() {
		if (!Thread.interrupted()) {
			try {
				System.out.println("server daemon starting...");
				
				ServerSocket serverSocket = new ServerSocket(5555); 
				System.out.println("initialized server socket");
				
				while (!Thread.interrupted()) {
					Socket socketToClient = serverSocket.accept(); 
					
					new TrayProgClientHandler(socketToClient); 
					System.out.println("iteration"); 
				}
				
				serverSocket.close();
			} 
			catch (Exception exc) {
				System.out.println("Error: " + exc.toString()); 
			}
		}
		
		System.out.println("server daemon ending"); 
	}
	
	//public void terminate() {
	//	continueProcess = false; 
	//}
	
	
}
