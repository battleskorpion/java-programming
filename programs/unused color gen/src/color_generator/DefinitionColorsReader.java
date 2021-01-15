package color_generator;

import java.io.*; 

public class DefinitionColorsReader {
	static final String def_filename = "definition.csv"; 
	
	public void readDefFileColors() {
		try {
			File def_file = new File(def_filename); 
			FileInputStream file_input = new FileInputStream(def_file);
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
