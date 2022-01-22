/********************************************/
/* INFORMATION SECTION 						*/
/* Darian Siembab 							*/
/* October 30, 2021							*/ 
/* Settings.java							*/
/* 											*/
/* ABSTRACT CLASS FOR LOADING AND SAVING	*/
/* PROGRAM SETTINGS 						*/
/********************************************/ 
package abstractProgram.abstractProgram;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import abstractProgram.fileIO.FileRead;
import abstractProgram.fileIO.FileWrite;

/******************/
/* IMPORT SECTION */
/******************/ 	

public abstract class AbstractProgramSettings {

	/********************/
	/* VARIABLE SECTION */
	/********************/
	public static ArrayList<ArrayList<String>>  		// TWO ARRAYLISTS: 
			settingsList; 								// 0: KEY
														// 1: VALUE											
	public final String settingsFilePath;  
	public File settingsFile = null; 
	
	/**
	 * 
	 * @param settingsFilePath The system file path of the settings file. 
	 * A local file path (simply the name of the file) is acceptable) 
	 */
	public AbstractProgramSettings(String settingsFilePath) {
		this.settingsFilePath = settingsFilePath; 
	}
	
	public void readSettings()
	/****************************************************************/
	/* PRECONDITION:  SETTINGS NEED TO BE READ FROM SETTINGS FILE   */
	/* POSTCONDITION: READS SETTINGS FROM SETTINGS FILE, CREATES  	*/
	/*				  NEW SETTINGS FILE IF NECESSARY, AND UPDATES	*/
	/*				  SETTINGS.										*/
	/****************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		String[] data; 									// DATA FROM FILE
		String key; 									// SETTING NAME 
		String value; 									// SETTING VALUE
		
		/**********************************/
		/* METHOD TO CREATE SETTINGS LIST */
		/**********************************/
		updateSettingsList(); 							
		
		settingsFile = new File(settingsFilePath); 
		try 
		{
			data = FileRead.readFile(settingsFile).split("\n");
		}
		catch(Exception exc)
		{
			data = null; 
		}
		
		/******************************************/ 
		/* IF FILE DOES NOT EXIST CREATE NEW FILE */
		/******************************************/ 
		if (data == null)
		{
			try 
			{
				settingsFile.createNewFile();
			} 
			catch (IOException e) 
			{
				JOptionPane.showMessageDialog(null, "Error: settings cannot be saved.");
				return;
			} 
			writeSettings(settingsFile, settingsList); 
			
			return; 
		}
 		
		/***************************/ 
		/* READ FROM SETTINGS FILE */
		/***************************/ 
		for (String datum: data)
		{
			if (datum.contains(":"))
			{
				key = datum.substring(0, datum.indexOf(":")); 
				value = datum.substring(datum.indexOf(":") + 1); 
				if (settingsList.get(0).contains(key))
				{
					updateSettings(key, value); 
				}
			}
		}
		
		/**********************************/
		/* METHOD TO UPDATE SETTINGS LIST */
		/**********************************/
		updateSettingsList(); 
	}
	
	public void saveSettings(File settingsFile)
	/****************************************************************/
	/* PRECONDITION:  SETTINGS NEED TO BE SAVED TO SETTINGS FILE   	*/
	/* POSTCONDITION: IF SETTINGS FILE EXISTS, SAVES PROGRAM 		*/
	/*				  SETTINGS TO FILE								*/
	/****************************************************************/
	{
		/***************************************************************/
		/* IF SETTINGS FILE HAS NOT BEEN FOUND/CREATED, 			   */
		/* CAN NOT SAVE SETTINGS, AND SETTINGS WONT HAVE BEEN CHANGED, */
		/***************************************************************/
		if (settingsFile != null)
		{
			updateSettingsList(); 
			writeSettings(settingsFile, settingsList); 
		}
	}
	
	public abstract void updateSettingsList(); 
	/****************************************************************/
	/* PRECONDITION:  SETTINGS LIST NEEDS TO BE UPDATED   			*/
	/* POSTCONDITION: UPDATES SETTINGS LIST WITH CURRENT SETTINGS	*/
	/****************************************************************/
	/* example implementation 
	{
		
		settingsList = new ArrayList<ArrayList<String>>();
		settingsList.add(new ArrayList<String>()); 
		settingsList.add(new ArrayList<String>()); 
		
		settingsList.get(0).add("VideoEnabled"); 
		settingsList.get(1).add(Boolean.toString(VideoEnabled)); 
			// VideoEnabled would be a defined boolean global variable
	}
	*/ 
	
	public abstract void updateSettings(String key, String value); 
	/****************************************************************/
	/* PRECONDITION:  A PROGRAM SETTING NEEDS TO BE UPDATED 		*/
	/* POSTCONDITION: SETS PROGRAM SETTING TO NEW VALUE				*/
	/****************************************************************/
	/* example implementation 
	{
		
		key = key.trim(); 			// TRIM IN CASE OF LEADING/TRAILING WHITESPACE 
		value = value.trim(); 		// TRIM IN CASE OF LEADING/TRAILING WHITESPACE 
		
		switch(key)
		{
		case "VideoEnabled":
			switch (value)
			{
			case "true": 
				VideoEnabled = true; 
					// VideoEnabled would be a defined boolean global variable
				break; 
			case "false":
				VideoEnabled = false; 
					// VideoEnabled would be a defined boolean global variable
				break; 
			default: 
				break; 
			}
		default: 
			break; 
		}
	}
	*/  
	
	private void writeSettings(File settingsFile, 
			ArrayList<ArrayList<String>> settingsList)
	/****************************************************************/
	/* PRECONDITION:  SETTINGS FILE EXISTS				  			*/
	/* POSTCONDITION: WRITES SETTINGS TO SETTINGS FILE				*/
	/****************************************************************/
	{
		FileWrite fileWrite = null; 
		try
		{
			fileWrite = new FileWrite(settingsFile); 
			if (settingsList.size() > 0)
			{
				for (int i = 0; i < settingsList.get(0).size(); i++)
				{
					fileWrite.writeToFile(settingsList.get(0).get(i) 
							+ ": " + settingsList.get(1).get(i)); 
				}
			}
		}
		catch (Exception exc)
		{
			JOptionPane.showMessageDialog(null, "Error: issue with saving settings.");
			exc.printStackTrace();
			return; 
		}
		finally 
		{
			if (fileWrite != null)
			{
				fileWrite.close(); 
			}
		}
	}
	
}
