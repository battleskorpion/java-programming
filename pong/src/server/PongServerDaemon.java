package server;

/******************/
/* IMPORT SECTION */
/******************/
import java.net.*;

import java.io.*; 

public class PongServerDaemon extends Thread {
	
	public PongServerDaemon() {
		start(); 
	}
	
	public void run() {
		if (!Thread.interrupted()) {
			try {
				System.out.println("Server daemon starting...");
					
				ServerSocket serverSocket = new ServerSocket(6542); 
				System.out.println("Initialized server socket.");
				
				while (!Thread.interrupted()) {
					Socket socketToClient = serverSocket.accept(); 
					
					new PongClientHandler(socketToClient); 
					//System.out.println("iteration"); 
					//socketToClient.close(); 
				}
				
				serverSocket.close();
			}
			catch (Exception exc) {
				System.out.println("Error: " + exc.toString()); 
			}
		}
		
		System.out.println("Server daemon ending."); 
	}
}

