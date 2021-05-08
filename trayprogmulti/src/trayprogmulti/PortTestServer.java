package trayprogmulti;

import java.net.*;
import java.io.*;
import java.util.*; 

public class PortTestServer {
	
	public static void main(String args[]) {
		try {
			System.out.println("initializing..."); 
			
			ServerSocket serverSocket = new ServerSocket(5555); 

			//while (true) {
				Socket socketToClient = serverSocket.accept(); 
				
				PrintStream clientOutput = new PrintStream(socketToClient.getOutputStream(), true);
				clientOutput.println(new Date()); 
				
				socketToClient.close(); 
			//} 
			serverSocket.close(); 
		}
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString()); 
		} 
		
	}
}
