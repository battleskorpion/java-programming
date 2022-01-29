package trayprogmulti;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.function.*;

import org.eclipse.swt.widgets.Button; 

public class TrayProgClientHandler extends Thread 
{
	
	private Socket clientSocket; 
	private String command; 		//disk command
	private TrayProgServerDaemon serverDaemon; 
	
	public TrayProgClientHandler(Socket socket, TrayProgServerDaemon serverDaemon) 
	{
		clientSocket = socket;
		this.serverDaemon = serverDaemon; 
		start(); 
	}
	
	public void run() 
	{
		System.out.println("client handler starting..."); 
		synchronized(serverDaemon) 
		{ 
			try 
			{
				PrintStream clientOutput = new PrintStream(clientSocket.getOutputStream(), true); 
				
				System.out.println("Client handler waiting on server"); 
				while (!clientSocket.isClosed())
				{
					System.out.println("BEFORE WAIT");
					
					/**
					 * waits 
					 */
					serverDaemon.wait();
					
					System.out.println("Client handler sending command"); 		// TODO: debug only
					clientOutput.println(command); 								
					switch (command.toLowerCase()) 
					{
					case "open":
						CDUtils.open(); 
						break; 
					case "close":
						CDUtils.close(); 
						break;
					case "debug":
						System.out.println("Debug command set from server daemon and sent to client"); 
						break; 
					default: 
						CDUtils.defaultCommand(); 
						break; 
					}
					
					//clientOutput.close(); 
					//clientSocket.close();
				}
			}
			catch (Exception exc) 
			{
				System.out.println("Error: " + exc.toString()); 
				exc.printStackTrace();
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
	//		//clientSocket.close();
	//	} catch (IOException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	} 
	//}
	
	public void setDiskCommand(String command) {
		this.command = command; 
	}
	
	public String getDiskCommand() {
		return this.command; 
	}
}
