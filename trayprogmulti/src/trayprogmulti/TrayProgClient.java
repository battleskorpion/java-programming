package trayprogmulti;

import java.net.*;
import java.io.*;

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
			Runtime.getRuntime().exec(command); 
			System.out.println("command received: " + command); 
			
			socket.close(); 
		}
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString()); 
		}
	}
}


