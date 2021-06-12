package bus_project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import fileIO.*; 

public class Settings 
{
	public static ArrayList<ArrayList<String>>  		// TWO ARRAYLISTS: 
			settingsList; 								// 0: KEY
														// 1: VALUE
														
	public static boolean skiVideoEnabled = true; 
	public static final String settingsFilePath = "settings_busProgram.txt"; 
	public static File settingsFile = null; 
	
	public static void readSettings()
	{
		String[] data; 									// DATA FROM FILE
		
		updateSettingsList(); 							// CREATES NEW SETTINGS LIST
		
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
			//JOptionPane.showMessageDialog(null, "Error: file not found.");
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
		String key; 
		String command; 
		
		for (String datum: data)
		{
			if (datum.contains(":"))
			{
				key = datum.substring(0, datum.indexOf(":")); 
				command = datum.substring(datum.indexOf(":") + 1); 
				if (settingsList.get(0).contains(key))
				{
					updateSettings(key, command); 
				}
			}
		}
		updateSettingsList(); 
	}
	
	public static void saveSettings()
	{
		// CAN NOT SAVE SETTINGS, AND SETTINGS WONT HAVE BEEN CHANGED, 
		// IF SETTINGS FILE HAS NOT BEEN FOUND/CREATED
		if (settingsFile != null)
		{
			updateSettingsList(); 
			writeSettings(settingsFile, settingsList); 
		}
	}
	
	public static void updateSettingsList()
	{
		settingsList = new ArrayList<ArrayList<String>>();
		settingsList.add(new ArrayList<String>()); 
		settingsList.add(new ArrayList<String>()); 
		
		settingsList.get(0).add("skiVideoEnabled"); 
		settingsList.get(1).add(Boolean.toString(skiVideoEnabled)); 
	}
	
	public static void updateSettings(String key, String command)
	{
		/***********************************************/
		/* TRIM IN CASE OF LEADING/TRAILING WHITESPACE */
		/***********************************************/
		key = key.trim(); 
		command = command.trim(); 
		
		switch(key)
		{
		case "skiVideoEnabled":
			switch (command)
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
	
	//prerequisite: file exists
	private static void writeSettings(File settingsFile, 
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
