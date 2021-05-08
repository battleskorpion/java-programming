package trayprogmulti;

import java.net.*;
import java.io.*;
import java.util.*; 

public class PortTestClient {
	
	public static void main(String args[]) {
		
		try {
			Socket socket = new Socket("192.168.0.245", 5555); 
			
			InputStream is = socket.getInputStream(); 
			
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(is)); 
			
			String time = serverInput.readLine(); 
			System.out.println("Time: " + time); 
			
			socket.close(); 
		}
		catch (Exception exc) {
			System.out.println("Error: " + exc.toString()); 
		}
	}
}
