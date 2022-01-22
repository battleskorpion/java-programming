package trayprogmulti;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.function.*; 

public class TrayProgClient {
	
	public static void main(String args[]) {
		
		try {
			String ip = "0.0.0.0"; 	// 10.10.27.85 <-
			
			Socket socket = new Socket(ip, 6540); 
			
			InputStream is = socket.getInputStream(); 
			
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(is)); 
			System.out.println("connected to server");
			
			String command; 
			///synchronized(serverDaemon) 
			//{ 
				while (!socket.isClosed())
				{
					while ((command = serverInput.readLine()) != null) {
						System.out.println("command received: " + command); 
						switch (command.toLowerCase()) 
						{
						case "open":
							CDUtils.open(); 
							break; 
						case "close":
							CDUtils.close(); 
							break;
						case "debug":
							System.out.println("Debug command received.");
							break; 
						default: 
							CDUtils.defaultCommand(); 
							break; 
						}
					} 
				}
				
				socket.close(); 
			//}
		} 
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString());
			exc.printStackTrace();
		}
	}
}


