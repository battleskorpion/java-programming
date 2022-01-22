package trayprogmulti;

/******************/
/* IMPORT SECTION */
/******************/
import java.net.*;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import java.io.*; 

public class TrayProgServerDaemon extends Thread 
{
	
	//public boolean continueProcess; 
	private TrayProgMulti menu; 
	private ArrayList<TrayProgClientHandler> clientHandlers = new ArrayList<TrayProgClientHandler>(); 
	private String command; 
	private int port = 6540; 	//6542 default
	
	public TrayProgServerDaemon(TrayProgMulti menu) 
	{
		if (menu == null) {
			System.out.println("Fatal error. Menu is null."); 
			return; 
		}
		this.menu = menu; 
		start(); 
	}
	
	public void run() 
	{
		if (!Thread.interrupted()) 
		{
			try 
			{
				System.out.println("server daemon starting...");
				
				ServerSocket serverSocket = new ServerSocket(port); 
				System.out.println("initialized server socket");
				
				while (!Thread.interrupted()) 
				{
					Socket socketToClient = serverSocket.accept(); 
					
		            Display.getDefault().asyncExec(new Runnable() 
		            {
		                public void run() 
		                {
		                	try {
		                		//System.out.println(menu); 
		                		menu.addClient(socketToClient.getInetAddress()); 
		                	}
		                	catch(NullPointerException exc) {
		                		exc.printStackTrace();
		                	}
		                }
		             });
					
		            
					TrayProgClientHandler clientHandler = new TrayProgClientHandler(socketToClient, this); 
					clientHandlers.add(clientHandler); 
					System.out.println("iteration"); 
					//socketToClient.close(); 
				}
				
				serverSocket.close();
			} 
			catch (Exception exc) 
			{
				System.out.println("Error: " + exc.toString()); 
				exc.printStackTrace();
			}
		}
		
		System.out.println("server daemon ending"); 
	}
	
	//public void terminate() {
	//	continueProcess = false; 
	//}
	
	/**
	 * 
	 * @param command disk command to send to client handler 
	 */
	public void setDiskCommand(String command) 
	{
		this.command = command; 
		for (TrayProgClientHandler clientHandler: clientHandlers) 
		{
			clientHandler.setDiskCommand(command);  
		}
	}
	
	public String getDiskCommand()
	{
		return this.command; 
	}
	
	public void executeDiskCommand() 
	{
		synchronized(this) 
		{
			this.notifyAll();
		}
			//for (TrayProgClientHandler clientHandler: clientHandlers) {
			//	
			//	clientHandler.notify(); 
			//}
	}
	
}
