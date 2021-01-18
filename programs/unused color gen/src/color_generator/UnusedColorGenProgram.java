package color_generator;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class UnusedColorGenProgram {
	
	
	public static void main(String args[]) throws IOException {
		List<String[]> csv_data = DefinitionColorsReader.readCSV();
		List<Color> current_colors = new ArrayList<Color>(); 
			
		int r; 
		int g; 
		int b; 
		
		
		// iteration through lines of data
		for (int i = 0; i < csv_data.size(); i++) {
			// each line 
			// find the three color numbers and make a new color using the rgb values
			// alpha value of 1.0 is implicit - good 
			r = Integer.parseInt(csv_data.get(i)[1]);
			g = Integer.parseInt(csv_data.get(i)[2]); 
			b = Integer.parseInt(csv_data.get(i)[3]); 
				
			Color c = new Color(r, g, b); 
			current_colors.add(c); 
		}
			
		// output color test
		for(int i = 0; i < current_colors.size(); i++) {
			System.out.println(current_colors.get(i) + ", number " + i); 
		}
		
		List<Color> new_colors = DefinitionColorsReader.makeNewColors(current_colors); 
		
		newColorsBMP(new_colors); 
	}
	
	static void newColorsBMP(List<Color> colors) {
		BMPFile unreserved_bmp = new BMPFile(); 
		
		BufferedImage unreserved_buffered = new BufferedImage(null, null, false, null); 
		unreserved_buffered.wid
	}
	
	
}
