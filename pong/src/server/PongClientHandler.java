package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class PongClientHandler extends Thread {
	
	private Socket clientSocket; 
	
	public PongClientHandler(Socket socket) {
		clientSocket = socket; 
		start(); 
	}
	
	public void run() {
		String clientData; 
		
		System.out.println("Client handler starting..."); 
		
		try {
			/* setup socket i/o */ 
			InputStream is = clientSocket.getInputStream(); 
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(is)); 
			PrintStream serverOutput = new PrintStream(clientSocket.getOutputStream(), true);
			
			// stuff
			serverOutput.println("brick.");
			clientData = clientInput.readLine(); 
			System.out.println("Client: " + clientData); 
			
			/* close client socket */ 
			serverOutput.close(); 
			clientSocket.close(); 
		}
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString()); 
		}
		
		System.out.println("Client handler terminated."); 
	}
}
