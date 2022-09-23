/********************************************/
/* INFORMATION SECTION 						*/
/* FileRead.java							*/
/* Darian Siembab 							*/
/* 											*/
/* CLASS FOR WRITING CONTENTS TO A FILE  	*/
/********************************************/ 

package abstractProgram.fileIO;

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
	/********************/
	/* VARIABLE SECTION */
	/********************/ 
	private FileWriter fileWriter; 						// FILE FILEWRITER	
	private BufferedWriter fileBWriter; 				// FILE BUFFEREDWRITER
	private PrintWriter filePWriter; 					// FILE PRINTWRITER (FOR CONSOLE PRINT SYNTAX)

	public FileWrite(File file) throws IOException
	/************************************************************************************************/
	/* PRECONDITION:  CONSTRUCTOR								  									*/
	/* POSTCONDITION: THROWS IOException IF THE FILE EXISTS BUT IS A DIRECTORY RATHER THAN A   		*/
	/* 				  REGULAR FILE, DOES NOT EXIST BUT CANNOT BE CREATED, OR CANNOT BE OPENED FOR  	*/
	/* 				  ANY OTHER REASON, OVERWRITES FILE BY DEFAULT 									*/
	/************************************************************************************************/
	{
		fileWriter = new FileWriter(file, false);	
		fileBWriter = new BufferedWriter(fileWriter); 
		filePWriter = new PrintWriter(fileBWriter); 		// for println syntax
	}
		
	public FileWrite(File file, boolean append) throws IOException
	/************************************************************************************************/
	/* PRECONDITION:  CONSTRUCTOR								  									*/
	/* POSTCONDITION: THROWS IOException IF THE FILE EXISTS BUT IS A DIRECTORY RATHER THAN A 		*/
	/* 				  REGULAR FILE, DOES NOT EXIST BUT CANNOT BE CREATED, OR CANNOT BE OPENED FOR 	*/
	/* 				  ANY OTHER REASON, APPENDS TO FILE OR OVERWRITES FILE IF APPEND IS FALSE 		*/
	/************************************************************************************************/
	{
		fileWriter = new FileWriter(file, append);	
		fileBWriter = new BufferedWriter(fileWriter); 
		filePWriter = new PrintWriter(fileBWriter); 		// for println syntax
	}
	
	public boolean writeToFile(String data)
	/************************************************************************************************/
	/* PRECONDITION:  DATA NEEDS TO BE WRITTEN TO FILE								  				*/
	/* POSTCONDITION: WRITES DATA TO FILE USING println SYNTAX, RETURNS FALSE IF ERROR, ELSE TRUE	*/
	/************************************************************************************************/
	{
		/***********************************************************************************/
		/* TRY TO PRINT DATA TO FILE, RETURN FALSE IF EXCEPTION OCCURS, TRUE IF SUCCESSFUL */
		/***********************************************************************************/
		try 
		{
			filePWriter.println(data);
		}
		catch (Exception exc)
		{
			return false; 
		}
		return true; 
	}

	public void close()
	/************************************************************************************************/
	/* PRECONDITION:  FILE WRITER NEEDS TO BE CLOSED								  				*/
	/* POSTCONDITION: CLOSES FILE PRINT WRITER														*/
	/************************************************************************************************/
	{
		filePWriter.close(); 
	}
}
