package trayprogmulti;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.function.*; 

public class TrayProgClientHandler extends Thread {
	
	private Socket clientSocket; 
	
	public TrayProgClientHandler(Socket socket) {
		clientSocket = socket;
		start(); 
	}
	
	public void run() {
		System.out.println("client handler starting..."); 
		try {
			PrintStream clientOutput = new PrintStream(clientSocket.getOutputStream(), true); 
			Consumer<String> openFunction = x -> CDUtils.open("D:\\"); 	// lambda to reference tray open function 
			clientOutput.println(openFunction); 			//"nircmd.exe cdrom open d:"
			
			clientSocket.close(); 
		}
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString()); 
		}
	}
}
