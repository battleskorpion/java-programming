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
			
			clientOutput.close(); 
			clientSocket.close(); 
		}
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString()); 
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
	//		//clientSocket.close();
	//	} catch (IOException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	} 
	//}
}
