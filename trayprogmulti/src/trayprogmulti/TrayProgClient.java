package trayprogmulti;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.function.*; 

public class TrayProgClient {
	
	public static void main(String args[]) {
		
		try {
			String ip = "192.168.0.245"; 
			
			Socket socket = new Socket(ip, 5555); 
			
			InputStream is = socket.getInputStream(); 
			
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(is)); 
			
			//String time = serverInput.readLine(); 
			//System.out.println("Time: " + time); 
			String command = serverInput.readLine();
			System.out.println("command received: " + command); 
			switch (command.toLowerCase()) 
			{
			case "open":
				CDUtils.open("D:\\"); 
				break; 
			case "close":
				CDUtils.close("D:\\"); 
				break;
			default: 
				break; 
			}
			
			socket.close(); 
		}
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString()); 
		}
	}
}


