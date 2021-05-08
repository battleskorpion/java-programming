package trayprogmulti;

import java.net.*;
import java.io.*; 

public class TrayProgServerDaemon extends Thread {
	
	public TrayProgServerDaemon() {
		start(); 
	}
	
	public void run() {
		try {
			System.out.println("server daemon starting...");
			
			ServerSocket serverSocket = new ServerSocket(5555); 
			
			while(true) {
				Socket socketToClient = serverSocket.accept(); 
				
				new TrayProgClientHandler(socketToClient); 
			}
		}
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString()); 
		}
		
		System.out.println("server daemon ending"); 
	}
}
