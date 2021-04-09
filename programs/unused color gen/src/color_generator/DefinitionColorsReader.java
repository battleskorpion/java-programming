package color_generator;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Random;

public class DefinitionColorsReader {
	public static int num_colors_make = 512; 
	
	public static List<String[]> readCSV() throws IOException {
		List<String[]> csv_data =
				Files.lines(Paths.get("C:\\Users\\daria\\Documents\\Paradox Interactive\\Hearts of Iron IV\\mod\\nadivided-dev\\map\\definition.csv"))
					.map(line -> line.split(";"))
					.collect(Collectors.toList());
		
		return csv_data; 
	} 
	
	public static List<Color> makeNewColors(List<Color> current_colors) {
		// part where make new colors in list
		List<Color> new_colors = new ArrayList<Color>(); 
		int r; 
		int g; 
		int b; 
		Random rand = new Random();
		Color c; 
		
		for (int i = 0; i < num_colors_make; i++) {
			
			do {
				r = rand.nextInt(256); 				// exclusive so 0-255
				g = rand.nextInt(256); 				// exclusive so 0-255
				b = rand.nextInt(256); 				// exclusive so 0-255
			
				c = new Color(r, g, b); 
			}
			while (current_colors.contains(c)); 
			
			new_colors.add(c); 
		}
		
		return new_colors; 
	}
}
