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
			clientOutput.println("open"); 			//"nircmd.exe cdrom open d:"
			CDUtils.open("D:\\"); 
			
			clientSocket.close(); 
		}
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString()); 
		}
	}
}
