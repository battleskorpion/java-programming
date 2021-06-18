package provinceGen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import net.sf.image4j.codec.bmp.*; 
import org.spongepowered.noise.*;
import org.spongepowered.noise.module.source.Simplex;

public class ProvinceGeneration 
{
	public static int imageHeight = 512;	// 1024 works	// 2048 - default
	public static int imageWidth = 512; 	// 1024 works	// 5632 - default
	
	public static Color WHITE = new Color(255, 255, 255); 
	public static int INT_WHITE = ((WHITE.getRed() << 8) + WHITE.getGreen()) << 8 + WHITE.getBlue(); 
	
	public static int numSeedsY = 64; 		// 64 is ok
	public static int numSeedsX = 64; 		// 64 is ok
	
	public static int HEIGHTMAP_SEA_LEVEL = 95; 
	
	private static ArrayList<ArrayList<ArrayList<Integer>>> points;  				// stored y, x
	private static ArrayList<ArrayList<Integer>> seeds = new ArrayList<ArrayList<Integer>>(); // values of point stored as x, y
	
	private static int rgb_white; 
	private static BufferedImage image; 
	private static BufferedImage heightmap; 
	
	public static void main (String args[]) 
	{
		// variable section 
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB); 
		//ProvinceGenProgressWindow progressWindow = new ProvinceGenProgressWindow(imageHeight); 
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
		
	//Runnable runnableProgressWindow = () -> {progressWindow.open(Thread.currentThread()); }; 
	//Thread threadProgressWindow = new Thread(runnableProgressWindow); 
	//threadProgressWindow.start();
	//try 
	//{
	//	Thread.currentThread().wait(); 
	//}
	//catch (Exception exc)
	//{
	//	
	//}
		
		// seeds 
		for (int y = imageHeight / numSeedsY / 2 - 1; y < imageHeight; y+= imageHeight / numSeedsY) 	// int y = numSeedsY / 2 - 1 worked sometimes
		{
			for (int x = imageWidth / numSeedsX / 2 - 1; x < imageWidth; x+= imageWidth / numSeedsX) 				// int x = numSeedsX / 2 - 1 worked sometimes
			{
				// set color
				int xOffset = random.nextInt(imageWidth / numSeedsX - 1) - (imageWidth / numSeedsX / 2 - 1); 	// -3 to 3		// should make variables	// int xOffset = random.nextInt(numSeedsX - 1) - (numSeedsX / 2 - 1); 
				int yOffset = random.nextInt(imageHeight / numSeedsY - 1) - (imageHeight / numSeedsY / 2 - 1); 	// -3 to 3		// should make variables	// int yOffset = random.nextInt(numSeedsY - 1) - (numSeedsY / 2 - 1);
				int rgb; 								// rgb color int value
				
				color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)); 
				
				// Color -> int
				rgb = color.getRed(); 
				rgb = (rgb << 8) + color.getGreen();
				rgb = (rgb << 8) + color.getBlue();
				
				// set color at pixel cords
				try
				{
					image.setRGB(x + xOffset, y + yOffset, rgb);
				}
				catch (ArrayIndexOutOfBoundsException exc)
				{
					exc.printStackTrace();
					System.out.println("x: " + (x + xOffset)); 
					System.out.println("y: " + (y + yOffset)); 
					//continue; 	// skip rest of iteration 
				}
				
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
			//progressWindow.setProgress(y); 
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
		
		//Simplex simplexNoise = new Simplex(); 
		Simplex noise = new Simplex(); 
		noise.setNoiseQuality(NoiseQualitySimplex.SMOOTH); 
		
		// works but slow
		//for (int y = 0; y < imageHeight; y++) 
		//{
		//	for (int x = 0; x < imageWidth; x++) 
		//	{
		//		//Noise.gradientCoherentNoise3D(x, y, 0.0, 1, NoiseQuality.STANDARD)
		//		//Noise.gradientCoherentNoise3D(x, y, 0.0, 1, NoiseQuality.STANDARD) garbage
		//		int xOffset = (int) (((numSeedsX - 1) * ((noise.getValue(x * 0.085, y * 0.085, 0.0) - 1) * 0.1)));	// test idk	// *3 is just a good number
		//		int yOffset = (int) (((numSeedsY - 1) * ((noise.getValue(x * 0.085, y * 0.085, 0.0) - 1) * 0.1)));
		//		//System.out.println(xOffset + ", " + yOffset); 
		//		int rgb = determineColor(x + xOffset, y + yOffset); 
		//		
		//		points.get(y).get(x).set(0, Integer.valueOf(rgb)); 
		//		System.out.println("x: " + x + " y: " + y); // ok working
		//		image.setRGB(x, y, rgb);
		//	}
		//}
		
		Thread thisThread = Thread.currentThread(); 
		// ~twice as fast using threads
		for (int y = 0; y < imageHeight; y++) 
		{
			
			final int localY = y; 
			Runnable runnable = new Runnable() 
			{
				public void run() 
				{
					for (int x = 0; x < imageWidth; x++) 
					{
						int xOffset = (int) (((numSeedsX - 1) * ((noise.getValue(x * 0.085, localY * 0.085, 0.0) - 1) * 0.1)));
						int yOffset = (int) (((numSeedsY - 1) * ((noise.getValue(x * 0.085, localY * 0.085, 0.0) - 1) * 0.1)));
						
						int rgb = determineColor(x + xOffset, localY + yOffset); 
						points.get(localY).get(x).set(0, Integer.valueOf(rgb)); 	
						//System.out.println("x: " + x + " y: " + localY); // ok working
						image.setRGB(x, localY, rgb);
					}
				}
			}; 
			
			Thread thread = new Thread(runnable); 
			thread.start(); 
			try {
				thread.join();
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		//try {
		//	thisThread.wait();
		//}
		//catch (InterruptedException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}

		// to output file prior to special changes
		//try 
		//{
		//	BMPEncoder.write(image, new File("outputprev.bmp"));
		//}
		//catch (IOException exc) s
		//{
		//	
		//}
		
		//System.out.println("work"); 
		
		// smooth province borders
		//for (int i = 0; i < 5; i++) 
		//{
			//provinceSmooth(); 	// will make setting to use smoothing instead? 
									// (also removes exclaves in process) 
			removeExclaves(); 		// doesn't seem to slow the program much
		//}
		
		// special test 
		//for (int i = 0; i < seeds.size(); i++)
		//{
		//	
		//	int rgb; 								// rgb color int value
		//		
		//	color = new Color(0, 0, 0); 
		//
		//	// Color -> int
		//	rgb = color.getRed(); 
		//	rgb = (rgb << 8) + color.getGreen();
		//	rgb = (rgb << 8) + color.getBlue();
		//	
		//	image.setRGB(seeds.get(i).get(0), seeds.get(i).get(1), rgb);
		//}
			
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
		
		//System.out.println(points.toString()); // NO
		System.out.println("end."); 
	}
	
	private static void initializePoints()
	{
		points = new ArrayList<ArrayList<ArrayList<Integer>>>(imageHeight);
		
		for (int y = 0; y < imageHeight; y++)
		{
			points.add(new ArrayList<ArrayList<Integer>>(imageHeight * 4));
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
	
	private static int determineColor(int x, int y) 
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
			            
		return nearestColor; 
	}
	
	// prerequisite: points assigned colors
	private static void provinceSmooth()
	{
		int left;			// color of left province
		int right;			// color of right province
		int above;			// color of province above
		int below;			// color of province below
		int point;			// color of this point
		Random random = new Random(); 
		
		// skip edges for now
		for (int y = 1; y < imageHeight - 1; y++)
		{
			for (int x = 1; x < imageWidth - 1; x++)
			{
				left  = points.get(y).get(x - 1).get(0); 
				right = points.get(y).get(x + 1).get(0); 
				above = points.get(y + 1).get(x).get(0); 
				below = points.get(y - 1).get(x).get(0); 
				point = points.get(y).get(x).get(0); 
				
				if (left == right && above == below)
				{
					if (point != left || point != right) 
					{
						if (random.nextBoolean())
						{
							point = left; 
						}
						else 
						{
							point = above; 	
						}
						points.get(y).get(x).set(0, Integer.valueOf(point)); 
						image.setRGB(x, y, point); 	
					}
				}
				else if (left == right)
				{
					point = left; 
					points.get(y).get(x).set(0, Integer.valueOf(point));
					image.setRGB(x, y, point); 	
				}
				else if (above == below)
				{
					point = above; 
					points.get(y).get(x).set(0, Integer.valueOf(point));
					image.setRGB(x, y, point); 		 
				}		
			}
		}
	}
	
	// remove single pixels fully surrounded by another color
	private static void removeExclaves()
	{
		int left;			// color of left province
		int right;			// color of right province
		int above;			// color of province above
		int below;			// color of province below
		int point;			// color of this point
		
		// skip edges for now
		for (int y = 1; y < imageHeight - 1; y++)
		{
			for (int x = 1; x < imageWidth - 1; x++)
			{
				left  = points.get(y).get(x - 1).get(0); 
				right = points.get(y).get(x + 1).get(0); 
				above = points.get(y + 1).get(x).get(0); 
				below = points.get(y - 1).get(x).get(0); 
				point = points.get(y).get(x).get(0); 
				
				// only need three comparisons to see if all match
				if (left != point && right != point && above != point && below != point)
				{
					point = left; 		// can set to any
					points.get(y).get(x).set(0, Integer.valueOf(point)); 
					image.setRGB(x, y, point); 	
				}
			}
		}
	}
	
	private static void loadHeightmap()
	{
		
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
