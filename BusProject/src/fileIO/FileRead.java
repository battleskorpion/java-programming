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
	{
		// scan file and read from it 
		Scanner file_scan;
		try 
		{
			file_scan = new Scanner(f);
		} 
		catch (FileNotFoundException e) 
		{
			return null; 
		} 
		
		StringBuffer buffer = new StringBuffer(); 
		while (file_scan.hasNextLine()) 
		{
			buffer.append(file_scan.nextLine() + System.lineSeparator());
		}
		String fileContents = buffer.toString();
		
		// testing 
		//System.out.println("next file: " + fileContents);
		
		//closing the Scanner object
		file_scan.close();
		
		return fileContents;
	}
}
