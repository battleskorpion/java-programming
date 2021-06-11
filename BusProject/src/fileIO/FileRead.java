package fileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

import abstractProgramWindow.AbstractProgramWindow;

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
			JOptionPane.showMessageDialog(null, "Error: file not found.");
			return null; 
		} 
		
		StringBuffer buffer = new StringBuffer(); 
		while (file_scan.hasNextLine()) 
		{
			buffer.append(file_scan.nextLine() + System.lineSeparator());
		}
		String fileContents = buffer.toString();
		// testing 
		System.out.println("next file: " + fileContents);
		//closing the Scanner object
		file_scan.close();
		
		return fileContents;
	}
}
