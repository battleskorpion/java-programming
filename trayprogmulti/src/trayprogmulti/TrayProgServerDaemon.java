package trayprogmulti;

/******************/
/* IMPORT SECTION */
/******************/
import java.net.*;

import org.eclipse.swt.widgets.Display;

import java.io.*; 

public class TrayProgServerDaemon extends Thread {
	
	//public boolean continueProcess; 
	private TrayProgMulti menu; 
	
	public TrayProgServerDaemon(TrayProgMulti menu) {
		this.menu = menu; 
		start(); 
	}
	
	public void run() {
		if (!Thread.interrupted()) {
			try {
				System.out.println("server daemon starting...");
				
				ServerSocket serverSocket = new ServerSocket(6543); 
				System.out.println("initialized server socket");
				
				while (!Thread.interrupted()) {
					Socket socketToClient = serverSocket.accept(); 
					
					/* TEST */ 
		            Display.getDefault().asyncExec(new Runnable() {
		                public void run() {
		                	menu.addClient(socketToClient.getInetAddress()); 
		                }
		             });
					
					new TrayProgClientHandler(socketToClient); 
					System.out.println("iteration"); 
					//socketToClient.close(); 
				}
				
				serverSocket.close();
			} 
			catch (Exception exc) {
				System.out.println("Error: " + exc.toString()); 
				exc.printStackTrace();
			}
		}
		
		System.out.println("server daemon ending"); 
	}
	
	//public void terminate() {
	//	continueProcess = false; 
	//}
	
	
}
