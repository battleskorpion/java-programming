/********************************************/
/* INFORMATION SECTION 						*/
/* Settings.java							*/
/* Darian Siembab 							*/
/* 											*/
/* LOADS AND SAVES PROGRAM SETTINGS 		*/
/********************************************/ 
package bus_project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import fileIO.*; 

public class Settings 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	public static ArrayList<ArrayList<String>>  		// TWO ARRAYLISTS: 
			settingsList; 								// 0: KEY
														// 1: VALUE											
	public static boolean skiVideoEnabled = true; 
	public static final String settingsFilePath = "settings_busProgram.txt"; 
	public static File settingsFile = null; 
	
	public static void readSettings()
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
	
	public static void saveSettings()
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
	
	public static void updateSettingsList()
	/****************************************************************/
	/* PRECONDITION:  SETTINGS LIST NEEDS TO BE UPDATED   			*/
	/* POSTCONDITION: UPDATES SETTINGS LIST WITH CURRENT SETTINGS	*/
	/****************************************************************/
	{
		settingsList = new ArrayList<ArrayList<String>>();
		settingsList.add(new ArrayList<String>()); 
		settingsList.add(new ArrayList<String>()); 
		
		settingsList.get(0).add("skiVideoEnabled"); 
		settingsList.get(1).add(Boolean.toString(skiVideoEnabled)); 
	}
	
	public static void updateSettings(String key, String value)
	/****************************************************************/
	/* PRECONDITION:  A PROGRAM SETTING NEEDS TO BE UPDATED 		*/
	/* POSTCONDITION: SETS PROGRAM SETTING TO NEW VALUE				*/
	/****************************************************************/
	{
		/***********************************************/
		/* TRIM IN CASE OF LEADING/TRAILING WHITESPACE */
		/***********************************************/
		key = key.trim(); 
		value = value.trim(); 
		
		switch(key)
		{
		case "skiVideoEnabled":
			switch (value)
			{
			case "true": 
				skiVideoEnabled = true; 
				System.out.println("true"); 
				break; 
			case "false":
				skiVideoEnabled = false; 
				System.out.println("false"); 
				break; 
			default: 
				break; 
			}
		default: 
			break; 
		}
	}
	
	private static void writeSettings(File settingsFile, 
	/****************************************************************/
	/* PRECONDITION:  SETTINGS FILE EXISTS				  			*/
	/* POSTCONDITION: WRITES SETTINGS TO SETTINGS FILE				*/
	/****************************************************************/
			ArrayList<ArrayList<String>> settingsList)
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
