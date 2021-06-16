/********************************************/
/* INFORMATION SECTION 						*/
/* FileRead.java							*/
/* Darian Siembab 							*/
/* 											*/
/* CLASS FOR READING CONTENTS OF A FILE  	*/
/********************************************/ 

package fileIO;

/******************/
/* IMPORT SECTION */
/******************/
import java.io.File;									// FOR FILE I/O 
import java.io.FileNotFoundException;					// FOR FILE I/O 
import java.util.Scanner;								// READS IN FILE TEXT 
import abstractProgramWindow.*;							// ABSTRACT WINDOW CLASS TO BE INHERITED
														// BY WINDOW CLASSES, INCLUDES COMMON	
														// METHODS AND VARIABLES 	

public class FileRead extends AbstractProgramWindow
{
	public static String readFile(File f) 
	/************************************************************************************************/
	/* PRECONDITION:  CONTENTS OF FILE ARE NEEDED	  												*/
	/* POSTCONDITION: READS CONTENTS FROM FILE (LINES ARE SEPERATED BY System.lineSeperator 		*/
	/* 				  (ON UNIX SYSTEMS, IT RETURNS "\n"; ON MICROSOFT WINDOWS SYSTEMS IT RETURNS 	*/
	/*				  "\r\n") AND COMBINS DATA INTO A SINGLE STRING									*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/ 
		Scanner file_scan;								// SCAN FILE AND READ FROM IT 
		StringBuffer buffer = new StringBuffer(); 		// STRING BUFFER TO READ FILE LINE BY LINE 
														// AND CREATE STRING CONTAINING FILE DATA
		String fileContents; 							// FILE DATA (LINES SEPERATED BY 
														// System.lineSeperator)
		
		/***********************************/
		/* TRY TO INSTANTIATE FILE SCANNER */
		/***********************************/
		try 
		{
			file_scan = new Scanner(f);
		} 
		
		/*************************************************************************/
		/* IF SCANNER COULD NOT BE INSTANTIATED RETURN NULL (FILE WAS NOT FOUND) */
		/*************************************************************************/
		catch (FileNotFoundException e) 
		{
			return null; 
		} 
		
		/********************************************************************/
		/* APPEND LINE TO STRING BUFFER WHILE THERE IS ANOTHER LINE TO READ */
		/********************************************************************/
		while (file_scan.hasNextLine()) 
		{
			buffer.append(file_scan.nextLine() + System.lineSeparator());
		}
		
		fileContents = buffer.toString();				// CREATE FILE CONTENTS STRING
		file_scan.close();								// CLOSE THE SCANNER 
		return fileContents;							// RETURN FILE DATA STRING
	}
}
