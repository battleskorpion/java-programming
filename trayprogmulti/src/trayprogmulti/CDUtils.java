package trayprogmulti;

import java.io.File;
import java.io.FileWriter;
 
public class CDUtils 
{
 
	private CDUtils() {  }
 
	public static void open() 
	{
 
		try 
		{
			File file = new File("C:\\BC5\\Projects\\diskDrive\\diskdrive.exe"); 
			Runtime.getRuntime().exec( new String[]{file.getPath(), "open"}).waitFor();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
 
	public static void close() 
	{
 
		try 
		{
			File file = new File("C:\\BC5\\Projects\\diskDrive\\diskdrive.exe"); 
			Runtime.getRuntime().exec( new String[]{file.getPath(), "close"}).waitFor();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void defaultCommand()
	{
		try 
		{
			File file = new File("C:\\BC5\\Projects\\diskDrive\\diskdrive.exe"); 
			Runtime.getRuntime().exec( new String[]{file.getPath(), "default"}).waitFor();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
 
	public static void main(String[] args) 
	{
		javax.swing.JOptionPane.showConfirmDialog((java.awt.Component)
				null, "Press OK to open CD", "CDUtils",
				javax.swing.JOptionPane.DEFAULT_OPTION);
		CDUtils.open();
		javax.swing.JOptionPane.showMessageDialog((java.awt.Component)
				null, "ok");
 
  }
}
