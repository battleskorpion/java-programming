package provinceGen;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import abstractProgram.fileIO.FileRead; 
import abstractProgram.fileIO.FileWrite;

/**
 * Define provinces in definition file (definition.csv) 
 * 
 * @author Darian Siembab
 * @see FileRead
 * @see FileWrite
 */
public class DefineProvinces {
	
	private static FileWrite fileWrite; 	 
	
	private static HashMap<Point, Integer> provinceID = new HashMap<Point, Integer>(32 * 32); 
	
	public static void defineProvinces(ArrayList<ArrayList<ArrayList<Integer>>> points) {
		/**
		 * Definition file to use 
		 */
		File definitionFile = new File("definition.csv");
		
		try {
			fileWrite = new FileWrite(definitionFile);
		} catch (IOException e) {
			e.printStackTrace();
			return; 
		} 
		
		/**
		 * initial line must be included in csv file 
		 */
		fileWrite.writeToFile("0;0;0;0;sea;false;unknown;2\r\n"); 
		
		/**
		 * first province number (0 reserved for column declaration) 
		 */
		int count = 1; 
		for (ArrayList<ArrayList<Integer>> y : points) {
			for (ArrayList<Integer> x : y) {
				int rgb = x.get(0); 
				int red = (rgb >> 16) & 0xFF;
				int green = (rgb >> 8) & 0xFF;
				int blue = rgb & 0xFF; 
				String type; 
				Boolean coastal = false; 
				String terrain = "unknown"; 
				int continent = 2; 				// just a default (0 gives some errors) 
				
				if (x.get(1) == 0) {
					type = "sea"; 
					terrain = "ocean"; 
				}
				else {
					type = "land"; 
				}
				
				fileWrite.writeToFile(count  + ";" + red + ";" + green + ";" + blue + ";" + type + ";" + coastal + ";" + terrain + ";" + continent + "\r\n"); 
			}
		}
		
		/**
		 * important to close file writing 
		 */
		fileWrite.close(); 
	}
}
