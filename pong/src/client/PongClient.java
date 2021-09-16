package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class PongClient {
	
	private static Socket socket; 
	
	public static void main(String args[]) {
		String serverData; 
		
		try {
			System.out.println("Client initializing connection..."); 
			
			/* setup ip/socket */ 
			String serverIP = null;//"74.104.186.144"; 	
			socket = new Socket(serverIP, 6542); 
			
			/* setup socket i/o */ 
			InputStream is = socket.getInputStream(); 
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(is)); 	// input from server to client
			PrintStream clientOutput = new PrintStream(socket.getOutputStream(), true); 	// output to server from client
			
			/* receive/send packets */ 
			serverData = serverInput.readLine(); 
			System.out.println("Data received: " + serverData); 
			String clientData = "brick."; 
			clientOutput.println(clientData); 
			
			/* close server connection */ 
			serverInput.close(); 
			is.close(); 
			socket.close(); 
			System.out.println("Connection to server closed."); 
		}
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString()); 
		}
		finally {
			if (socket != null) {
				try { 
					socket.close(); 
				} 
				catch(IOException socketExc) {
					socketExc.printStackTrace();
				}
			}
		}
	}
	
	//public void finalize () {
	//	try {
	//		super.finalize();
	//	} catch (Throwable e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
	//	try {
	//		socket.close();
	//	} catch (IOException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	} 
	//}
} 
