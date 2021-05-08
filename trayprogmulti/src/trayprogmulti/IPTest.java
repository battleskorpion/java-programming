package trayprogmulti;

import java.net.*;

public class IPTest {
	
	public static void main (String[] args) {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println("address: \n" + ip); 
		}
		catch (UnknownHostException exc) {
			System.out.println("unknown host: \n" + exc.toString()); 
		}
	}
	
}
