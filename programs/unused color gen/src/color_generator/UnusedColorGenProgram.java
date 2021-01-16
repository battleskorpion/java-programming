package color_generator;

import java.io.IOException;
import java.util.List;

public class UnusedColorGenProgram {
	public static void main(String args[]) {
		try {
			List<String[]> csv_data = DefinitionColorsReader.readCSV();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
