/********************************************/
/* INFORMATION SECTION 						*/
/* FileRead.java							*/
/* Darian Siembab 							*/
/* 											*/
/* CLASS FOR WRITING CONTENTS TO A FILE  	*/
/********************************************/ 

package fileIO;

/******************/
/* IMPORT SECTION */
/******************/
import java.io.BufferedWriter;							// FOR FILE I/O 
import java.io.File;									// FOR FILE I/O 
import java.io.FileWriter;								// FOR FILE I/O 
import java.io.IOException;								// FOR FILE I/O 
import java.io.PrintWriter;								// FOR FILE I/O 

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
