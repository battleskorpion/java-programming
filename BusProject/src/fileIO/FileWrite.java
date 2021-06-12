package fileIO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWrite 
{
	private FileWriter locWriter; 
	private BufferedWriter locBWriter; 
	private PrintWriter locPWriter; 
	//private File file; 
	
	// THROWS IOException IF THE FILE EXISTS BUT IS A DIRECTORY RATHER THAN A REGULAR FILE, 
	// DOES NOT EXIST BUT CANNOT BE CREATED, OR CANNOT BE OPENED FOR ANY OTHER REASON
	// OVERWRITES FILE BY DEFAULT
	public FileWrite(File file) throws IOException
	{
		//this.file = file; 
		locWriter = new FileWriter(file, false);	
		locBWriter = new BufferedWriter(locWriter); 
		locPWriter = new PrintWriter(locBWriter); 		// for println syntax
	}
		
	// THROWS IOException IF THE FILE EXISTS BUT IS A DIRECTORY RATHER THAN A REGULAR FILE, 
	// DOES NOT EXIST BUT CANNOT BE CREATED, OR CANNOT BE OPENED FOR ANY OTHER REASON
	// APPENDS TO FILE OR OVERWRITES FILE IF APPEND IS FALSE
	public FileWrite(File file, boolean append) throws IOException
	{
		//this.file = file; 
		locWriter = new FileWriter(file, append);	
		locBWriter = new BufferedWriter(locWriter); 
		locPWriter = new PrintWriter(locBWriter); 		// for println syntax
	}
	
	public boolean writeToFile(String data)
	{
		try 
		{
			locPWriter.println(data);
		}
		catch (Exception exc)
		{
			return false; 
		}
		return true; 
	}
	
	public void close()
	{
		locPWriter.close(); 
	}
}
