package province_generator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import net.sf.image4j.codec.bmp.*; 

public class ProvinceGen 
{
	public static int imageHeight = 128;
	public static int imageWidth = 128; 
	
	public static Color WHITE = new Color(255, 255, 255); 
	public static int INT_WHITE = ((WHITE.getRed() << 8) + WHITE.getGreen()) << 8 + WHITE.getBlue(); 
	
	public static void main (String args[]) {
		// variable section 
		BufferedImage image = new BufferedImage(imageHeight, imageWidth, BufferedImage.TYPE_INT_RGB); 
		int rgb; 
		Color color; 
		Random random = new Random(); 
		ArrayList<ArrayList<Integer>> points = new ArrayList<ArrayList<Integer>>(); 
		
		for (int y = 0; y < imageHeight; y++) {
			for (int x = 0; x < imageWidth; x++) {
				// set color
				//switch (random.nextInt(2)) {
				//case 0: 
					color = new Color(255, 255, 255); // white
				//	break;
				//default:  
				//	color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)); 
				//}
				
				
				// Color -> int
				rgb = color.getRed(); 
				rgb = (rgb << 8) + color.getGreen();
				rgb = (rgb << 8) + color.getBlue();
				
				// set color at pixel cords
				image.setRGB(x, y, rgb);
			}
		}
		for (int y = 3; y < imageHeight; y+= imageHeight / 8) {
			for (int x = 3; x < imageWidth; x+= imageWidth / 8) {
				// set color
				int xOffset = random.nextInt(7) - 3; 
				int yOffset = random.nextInt(7) - 3; 
				
				color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)); 
				
				// Color -> int
				rgb = color.getRed(); 
				rgb = (rgb << 8) + color.getGreen();
				rgb = (rgb << 8) + color.getBlue();
				
				// set color at pixel cords
				image.setRGB(x + xOffset, y + yOffset, rgb);
				
				// add point to points array
				points.add(new ArrayList<Integer>(3)); 
				points.get(points.size() - 1).add(Integer.valueOf(x + xOffset)); 
				points.get(points.size() - 1).add(Integer.valueOf(y + yOffset));
				points.get(points.size() - 1).add(Integer.valueOf(1)); 	// "true" (for later) 
			}
		}
		
		/* bad */ 
		
		//int[] availablePoints = new int[4]; 
		//int numAvailablePoints = 0; 
		//int ctr = 0; 	// counts until no more actions can be taken
		//while (ctr < points.size()) {
		//	for (int i = 0; i < points.size(); i++) {
		//		if (image.getRGB(points.get(i).get(0) + 1, points.get(i).get(1)) == INT_WHITE) {
		//			availablePoints[0] = 1; 
		//			numAvailablePoints++; 
		//		}
		//		else {
		//			availablePoints[0] = 0; 
		//		}
		//		if (image.getRGB(points.get(i).get(0), points.get(i).get(1) + 1) == INT_WHITE) {
		//			availablePoints[1] = 1; 
		//			numAvailablePoints++; 
		//		}
		//		else {
		//			availablePoints[1] = 0; 
		//		}
		//		if (image.getRGB(points.get(i).get(0) - 1, points.get(i).get(1)) == INT_WHITE) {
		//			availablePoints[2] = 1; 
		//			numAvailablePoints++; 
		//		}
		//		else {
		//			availablePoints[2] = 0; 
		//		}
		//		if (image.getRGB(points.get(i).get(0), points.get(i).get(1) - 1) == INT_WHITE) {
		//			availablePoints[3] = 1; 
		//			numAvailablePoints++; 
		//		}
		//		else {
		//			availablePoints[3] = 0; 
		//		}
		//		
		//		switch (numAvailablePoints) {
		//		case 0: 
		//			ctr++; 
		//			break; 
		//		case 4: 
		//			switch(random.nextInt(4)) {
		//			case 0:
		//				image.setRGB(, image.getRGB(points.get(i).get(0), points.get(i).get(1)));
		//				break; 
		//			case 1: 
		//				break; 
		//			case 2: 
		//				break; 
		//			case 3:
		//				break; 
		//			}
		//			break; 
		//		}
		//	}
		//}
		
		// write image 
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
