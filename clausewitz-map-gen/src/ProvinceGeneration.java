
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import net.sf.image4j.codec.bmp.*; 

public class ProvinceGeneration 
{
	public static int imageHeight = 1024;
	public static int imageWidth = 1024; 
	
	public static Color WHITE = new Color(255, 255, 255); 
	public static int INT_WHITE = ((WHITE.getRed() << 8) + WHITE.getGreen()) << 8 + WHITE.getBlue(); 
	
	public static int numSeedsY = 32; 
	public static int numSeedsX = 32; 
	
	private static ArrayList<ArrayList<ArrayList<Integer>>> points;  				// stored y, x
	private static ArrayList<ArrayList<Integer>> seeds = new ArrayList<ArrayList<Integer>>(); 
	
	static int rgb_white; 
	
	public static void main (String args[]) 
	{
		// variable section 
		BufferedImage image = new BufferedImage(imageHeight, imageWidth, BufferedImage.TYPE_INT_RGB); 
		Color color; 
		Random random = new Random(); 
		
		initializePoints(); 
		
		
		// Color -> int
		rgb_white = Color.white.getRed(); 
		rgb_white = (rgb_white << 8) + Color.white.getGreen();
		rgb_white = (rgb_white << 8) + Color.white.getBlue();
		
		// white canvas (unnecessary) 
		for (int y = 0; y < imageHeight; y++) 
		{
			for (int x = 0; x < imageWidth; x++) 
			{
				// set color
				//switch (random.nextInt(2)) {
				//case 0: 
				
				//	break;
				//default:  
				//	color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)); 
				//}
				
				// set color at pixel cords
				image.setRGB(x, y, rgb_white);
			}
		}
		
		// seeds 
		for (int y = numSeedsY / 2 - 1; y < imageHeight; y+= imageHeight / numSeedsY) 
		{
			for (int x = numSeedsX / 2 - 1; x < imageWidth; x+= imageWidth / numSeedsX) 
			{
				// set color
				int xOffset = random.nextInt(7) - 3; 	// -3 to 3		// should make variables
				int yOffset = random.nextInt(7) - 3; 	// -3 to 3		// should make variables
				int rgb; 								// rgb color int value
				
				color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)); 
				
				// Color -> int
				rgb = color.getRed(); 
				rgb = (rgb << 8) + color.getGreen();
				rgb = (rgb << 8) + color.getBlue();
				
				// set color at pixel cords
				image.setRGB(x + xOffset, y + yOffset, rgb);
				
				// add point to points array
				// x and y not needed as should correlate to place in arraylist
				points.get(Integer.valueOf(y + yOffset)).get(Integer.valueOf(x + xOffset))
				.add(rgb); 
				points.get(Integer.valueOf(y + yOffset)).get(Integer.valueOf(x + xOffset))
				.add(Integer.valueOf(1)); 	// "true" (for later) // update: idk // (its a seed) 
				
				// add point to seeds array 
				// x and y needed
				seeds.add(new ArrayList<Integer>(4)); 
				seeds.get(seeds.size() - 1).add(Integer.valueOf(x + xOffset)); 
				seeds.get(seeds.size() - 1).add(Integer.valueOf(y + yOffset));
				seeds.get(seeds.size() - 1).add(rgb); 
				seeds.get(seeds.size() - 1).add(Integer.valueOf(1)); 	// "true" (for later) // update: idk
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
		
		// set colors based on nearest seed
		// y, x inner for loop will scan horizontally 
		// adapted from https://stackoverflow.com/questions/22713688/2d-tile-map-generation-with-biomes
		for (int y = 0; y < imageHeight; y++) 
		{
			for (int x = 0; x < imageWidth; x++) 
			{
	            int nearestColor = rgb_white; 			// color of nearest seed (int value) 
	            										// (default white)
	            int dist = Integer.MAX_VALUE; 			// select a big number

	            // iterate through each seed
	            for (int s = 0; s < seeds.size(); s++) 
	            {

	                // calculate the difference in x and y direction
	                int xdiff = seeds.get(s).get(0) - x;
	                int ydiff = seeds.get(s).get(1) - y;

	                // calculate euclidean distance, sqrt is not needed
	                // because we only compare and do not need the real value
	                int cdist = xdiff*xdiff + ydiff*ydiff;

	                // is the current distance smaller than the old distance?
	                // if yes, take this biome
	                if (cdist < dist) 
	                {
	                    nearestColor = seeds.get(s).get(2);		// index 2 is rgb int value of seed
	                    dist = cdist;
	                }
	            }
	            
	            points.get(y).get(x).set(0, Integer.valueOf(nearestColor)); 
	            image.setRGB(x, y, nearestColor);
	        }
	    }
		
		// write image 
		try 
		{
			BMPEncoder.write(image, new File("output.bmp"));
		}
		catch (IOException exc) 
		{
			exc.printStackTrace();
			// error
		}
	}
	
	private static void initializePoints()
	{
		points = new ArrayList<ArrayList<ArrayList<Integer>>>(imageHeight);
		
		for (int y = 0; y < imageHeight; y++)
		{
			points.add(new ArrayList<ArrayList<Integer>>(imageHeight));
			for (int x = 0; x < imageWidth; x++)
			{
				points.get(y).add(new ArrayList<Integer>(4)); 
				
				// add actual data 
				for (int i = 0; i < 4; i++)
				{
					// white color default
					points.get(y).get(x).add(rgb_white); 			// white color default
					points.get(y).get(x).add(Integer.valueOf(0)); 	// 0 for false
				}
			}
		}
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
