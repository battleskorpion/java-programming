package province_generator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import net.sf.image4j.codec.bmp.*; 

public class ProvinceGen 
{
	public static void main (String args[]) {
		// variable section 
		BufferedImage image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB); 
		int rgb; 
		Color color; 
		Random random = new Random(); 
		
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				// set color
				//color = new Color(255, 255, 255); // white
				color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)); 
				
				// Color -> int
				rgb = color.getRed(); 
				rgb = (rgb << 8) + color.getGreen();
				rgb = (rgb << 8) + color.getBlue();
				
				// set color at pixel cords
				image.setRGB(x, y, rgb);
			}
		}
		try {
			BMPEncoder.write(image, new File("output.bmp"));
		}
		catch (IOException exc) {
			
		}
		
		//convert rgb int to r, g, b 
		/*
		int r = (int) ((Math.pow(256,3) + rgbs[k]) / 65536);
		int g = (int) (((Math.pow(256,3) + rgbs[k]) / 256 ) % 256 );
		int b = (int) ((Math.pow(256,3) + rgbs[k]) % 256); 
		*/
		// also works: 
		/*
		int red = (rgb >> 16) & 0xFF;
		int green = (rgb >> 8) & 0xFF;
		int blue = rgb & 0xFF;
		*/	
	}
}
