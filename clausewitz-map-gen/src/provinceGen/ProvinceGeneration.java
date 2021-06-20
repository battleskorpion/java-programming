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
	public static int imageHeight = 2048;	// 1024, 512, 256 works	// 2048 - default
	public static int imageWidth = 5632; 	// 1024, 512, 256 works	// 5632 - default
	
	public static Color WHITE = new Color(255, 255, 255); 
	public static int INT_WHITE = ((WHITE.getRed() << 8) + WHITE.getGreen()) << 8 + WHITE.getBlue(); 
	
	public static int HEIGHTMAP_SEA_LEVEL = 95; 
	
	// SEA LEVEL COLOR/MAX 
	public static final Color SEA_LEVEL_RGB = new Color(HEIGHTMAP_SEA_LEVEL, HEIGHTMAP_SEA_LEVEL, HEIGHTMAP_SEA_LEVEL); ;
	public static final int SEA_LEVEL_INT_RGB = ((SEA_LEVEL_RGB.getRed() << 8) + SEA_LEVEL_RGB.getGreen()) << 8 + SEA_LEVEL_RGB.getBlue(); 
	
	public static int numSeedsY = 64; 		// 64 is ok	// 64^2 = 4096
	public static int numSeedsX = 64; 		// 64 is ok // 64^2 = 4096
	
	private static ArrayList<ArrayList<ArrayList<Integer>>> points;  								// stored y, x
	private static ArrayList<ArrayList<Integer>> seedsLand = new ArrayList<ArrayList<Integer>>(); 	// values of point stored as x, y
	private static ArrayList<ArrayList<Integer>> seedsSea = new ArrayList<ArrayList<Integer>>(); 	// values of point stored as x, y
	//private static ArrayList<Integer> seeds = new ArrayList<Integer>(); 		// just stores rgb values 
	//private static ArrayList<Point> seedsCoords = new ArrayList<Point>(); 
	
	private static int rgb_white; 
	private static BufferedImage image; 
	private static BufferedImage heightmap; 
	
	public static void main (String args[]) 
	{
		// variable section 
		//ProvinceGenProgressWindow progressWindow = new ProvinceGenProgressWindow(imageHeight); 
		Color color; 
		Random random = new Random(); 
		
		loadHeightmap(); 
		imageWidth = heightmap.getWidth(); 			
		imageHeight = heightmap.getHeight(); 	// may break things but good idea 
		
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB); 
		
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
		
		/* seeds */  
	
		// (seed generation is very quick) 
		for (int y = imageHeight / numSeedsY / 2 - 1; y < imageHeight; y+= imageHeight / numSeedsY) 			// int y = numSeedsY / 2 - 1 worked sometimes
		{
			for (int x = imageWidth / numSeedsX / 2 - 1; x < imageWidth; x+= imageWidth / numSeedsX) 			// int x = numSeedsX / 2 - 1 worked sometimes
			{
				// set color
				int xOffset = random.nextInt(imageWidth / numSeedsX - 1) - (imageWidth / numSeedsX / 2 - 1); 	// -3 to 3		// should make variables	// int xOffset = random.nextInt(numSeedsX - 1) - (numSeedsX / 2 - 1); 
				int yOffset = random.nextInt(imageHeight / numSeedsY - 1) - (imageHeight / numSeedsY / 2 - 1); 	// -3 to 3		// should make variables	// int yOffset = random.nextInt(numSeedsY - 1) - (numSeedsY / 2 - 1);
				int rgb; 								// rgb color int value
				
				// heightmap color stuff
				int heightmapRGB; 
				int red; 

				heightmapRGB = heightmap.getRGB(x + xOffset, y + yOffset); 
				red = (heightmapRGB >> 16) & 0xFF;
				//int green = (heightmapRGB >> 8) & 0xFF;		// grayscale, only need one to compare
				//int blue = heightmapRGB & 0xFF;				// grayscale, only need one to compare
				
				if (red < HEIGHTMAP_SEA_LEVEL)
				{
					/* prov color */
					color = new Color(random.nextInt(64), random.nextInt(64), random.nextInt(64)); 
					
					// Color -> int
					rgb = color.getRed(); 
					rgb = (rgb << 8) + color.getGreen();
					rgb = (rgb << 8) + color.getBlue();
		
				}
				else 
				{
					/* prov color */
					color = new Color(random.nextInt(192) + 64, random.nextInt(192) + 64, random.nextInt(192) + 64); 
					
					// Color -> int
					rgb = color.getRed(); 
					rgb = (rgb << 8) + color.getGreen();
					rgb = (rgb << 8) + color.getBlue();
					
				}
				
				/* calculate sea or land prov. */  
				int type = 0; // 0: land
				// (heightmapRGB >> 16) & 0xFF to get red value (only value necessary since grayscale) 
				if (((heightmap.getRGB(x, y) >> 16) & 0xFF) < HEIGHTMAP_SEA_LEVEL)
				{
					type = 1; // 1: sea  	
				}
				
				/* add point to points array */
				// x and y not needed as should correlate to place in arraylist
				points.get(Integer.valueOf(y + yOffset)).get(Integer.valueOf(x + xOffset))
				.set(0, rgb); 
				points.get(Integer.valueOf(y + yOffset)).get(Integer.valueOf(x + xOffset))
				.set(1, Integer.valueOf(1)); 	// "true" (for later) // (its a seed) 
				points.get(Integer.valueOf(y + yOffset)).get(Integer.valueOf(x + xOffset))
				.set(2, Integer.valueOf(type)); // type of province (sea or land) 
				if (type == 1)
				{
					//System.out.println(points.get(Integer.valueOf(y + yOffset)).get(Integer.valueOf(x + xOffset)).get(points.get(Integer.valueOf(y + yOffset)).get(Integer.valueOf(x + xOffset)).size() - 1)); 
					
				}
				//System.out.println(points.get(Integer.valueOf(y + yOffset)).get(Integer.valueOf(x + xOffset)).get(2).toString()); 	
				
				
				// add point to seeds array 
				// x and y needed
				if (red < HEIGHTMAP_SEA_LEVEL)
				{
					seedsSea.add(new ArrayList<Integer>(4)); 
					seedsSea.get(seedsSea.size() - 1).add(Integer.valueOf(x + xOffset)); 
					seedsSea.get(seedsSea.size() - 1).add(Integer.valueOf(y + yOffset));
					seedsSea.get(seedsSea.size() - 1).add(rgb); 
					seedsSea.get(seedsSea.size() - 1).add(1); 	// "true" (is sea) (not great but for now ok)	//TODO: Unnecessary  
				}
				else 
				{
					seedsLand.add(new ArrayList<Integer>(4)); 
					seedsLand.get(seedsLand.size() - 1).add(Integer.valueOf(x + xOffset)); 
					seedsLand.get(seedsLand.size() - 1).add(Integer.valueOf(y + yOffset));
					seedsLand.get(seedsLand.size() - 1).add(rgb); 
					seedsLand.get(seedsLand.size() - 1).add(0); 	// "false" (not sea) (not great but for now ok)	//TODO: Unnecessary 
					
				}
				
				//seedsCoords.add(new Point(x + xOffset, y + yOffset)); 
				//seeds.add(rgb); 

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
					return; 
					//continue; 	// skip rest of iteration 
				}
				
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
		
		//Thread thisThread = Thread.currentThread(); 
		
		// ~twice as fast using threads
		for (int y = 0; y < imageHeight; y++) 
		{
			//System.out.println("y = " + y); // ok working
			final int localY = y; 
			Runnable runnable = new Runnable() 
			{
				public void run() 
				{
					for (int x = 0; x < imageWidth; x++) 
					{
						int xOffset = (int) (((numSeedsX - 1) * ((noise.getValue(x * 0.085, localY * 0.085, 0.0) - 1) * 0.1)));
						int yOffset = (int) (((numSeedsY - 1) * ((noise.getValue(x * 0.085, localY * 0.085, 0.0) - 1) * 0.1)));
						int rgb;
						
						if (((heightmap.getRGB(x, localY) >> 16) & 0xFF) < HEIGHTMAP_SEA_LEVEL)
						{
							rgb = determineColor(x, xOffset, localY, yOffset, seedsSea); 
						}
						else 
						{
							rgb = determineColor(x, xOffset, localY, yOffset, seedsLand); 
						}
		
						//System.out.println(rgb); 
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
				return; 
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
		System.out.println(seedsSea.size());
		System.out.println(seedsLand.size());
		System.out.println("end."); 
	}
	
	private static void initializePoints()
	{
		points = new ArrayList<ArrayList<ArrayList<Integer>>>(imageHeight);
		
		for (int y = 0; y < imageHeight; y++)
		{
			points.add(new ArrayList<ArrayList<Integer>>(imageWidth));
			for (int x = 0; x < imageWidth; x++)
			{
				points.get(y).add(new ArrayList<Integer>(4)); 
				
				// add actual data 
				for (int i = 0; i < 4; i++)
				{
					// white color default
					points.get(y).get(x).add(rgb_white); 			// white color default
					points.get(y).get(x).add(0); 					// 0 for false
					points.get(y).get(x).add(0);					// 0 default
				}
			}
		}
	}
	
	//// TODO: function takes forever
	//private static int determineColor(int x, int xOffset, int y, int yOffset) 
	//{
	//	int nearestColor = rgb_white; 			// color of nearest seed (int value) 
	//		           							// (default white)
	//	int dist = Integer.MAX_VALUE; 			// select a big number
	//	int sea = 0; 							// sea province? (0 = false)
	//	
	//	// iterate through each seeda
	//	for (int s = 0; s < seeds.size(); s++) 
	//	{
	//		// calculate the difference in x and y direction
	//		int xdiff = seeds.get(s).get(0) - (x + xOffset);
	//		int ydiff = seeds.get(s).get(1) - (y + yOffset);
    //
	//        // calculate euclidean distance, sqrt is not needed
	//        // because we only compare and do not need the real value
	//        int cdist = xdiff*xdiff + ydiff*ydiff;
	//        
	//        //System.out.println(x + ", " + y); 
	//        // calculate sea or land prov. 
	//        // (heightmapRGB >> 16) & 0xFF to get red value
	//        if (((heightmap.getRGB(x, y) >> 16) & 0xFF) < HEIGHTMAP_SEA_LEVEL)
	//        {
	//        	sea = 1;						// 1: true 
	//        	
	//        }
	//        //else {
	//        //	System.out.println(sea); 
	//        //}
	//        
	//        // is the current distance smaller than the old distance?
	//        // is it also the right type (sea/land?) 
	//        if (cdist < dist && seeds.get(s).get(3) == sea) 
	//        {
	//        	//System.out.println("sea: " + sea + ", sea: " + seeds.get(s).get(3) + "cdist: " + cdist + " dist: " + dist); 
	//        	nearestColor = seeds.get(s).get(2);		// index 2 is rgb int value of seed
	//        	dist = cdist;
	//        	
	//        }
	//        else 
	//        {
	//        	//System.out.println("sea: " + sea + ", sea: " + seeds.get(s).get(3) + "cdist: " + cdist + " dist: " + dist); 
	//        }
	//	}
	//		            
	//	return nearestColor; 
	//}
	
	// TODO: function is faster
	private static int determineColor(int x, int xOffset, int y, int yOffset, ArrayList<ArrayList<Integer>> seeds) 
	{
		int nearestColor = rgb_white; 			// color of nearest seed (int value) 
			           							// (default white)
		int dist = Integer.MAX_VALUE; 			// select a big number
		
		// iterate through each seed
		for (int s = 0; s < seeds.size(); s++) 
		{
			// calculate the difference in x and y direction
			int xdiff = seeds.get(s).get(0) - (x + xOffset);
			int ydiff = seeds.get(s).get(1) - (y + yOffset);
		
	        // calculate euclidean distance, sqrt is not needed
	        // because we only compare and do not need the real value
	        int cdist = xdiff*xdiff + ydiff*ydiff;
	        
	        // is the current distance smaller than the old distance?
	        // is it also the right type (sea/land?) 
	        if (cdist < dist)
	        {
	        	nearestColor = seeds.get(s).get(2);		// index 2 is rgb int value of seed
	        	dist = cdist;
	        	
	        }
		}
		
		return nearestColor; 
	}
	
	// TODO: this is slower :(
	//private static int determineColor(int x, int xOffset, int y, int yOffset, ArrayList<ArrayList<Integer>> seedsTypeList) 
	//{
	//	int nearestColor = rgb_white; 			// color of nearest seed (int value) 
	//			           						// (default white)
	//	//int dist = Integer.MAX_VALUE; 			// select a big number
	//	//int sea = 0; 							// sea province? (0 = false)
	//	
	//	/* Kd nearest-neighbor experimental stuff */ 
	//	List<KdPoint<Integer>> KdPoints = new ArrayList<>();
	//	for (int s = 0; s < seedsTypeList.size(); s++) 
	//	{
	//		KdPoints.add(new KdPoint<>(seedsTypeList.get(s).get(0), seedsTypeList.get(s).get(1)));	// 0, 1 for x, y
	//	}
	//	KdTree<Integer> tree = new KdTree<>(KdPoints);
	//	
	//	NNSolver<Integer> solver = new NNSolver<>(tree);
	//	//NNSolverOrchestrator<Integer> solverOrchestrator = new NNSolverOrchestrator<>(tree);
	//	
	//	// is seed? calculations unnecessary (and may mess up), so return color of seed
	//	if (points.get(y).get(x).get(1) == 1)	
	//	{
	//		nearestColor = points.get(y).get(x).get(0); 
	//		return nearestColor; 
	//	}
	//	
	//	//iterate through each seeda
	//	//for (int s = 0; s < seeds.size(); s++) 
	//	//{
	//		
	//		KdPoint<Integer> searchPoint = new KdPoint<>(x + xOffset, y + yOffset);
	//		KdPoint<Integer> nearestPoint = solver.findNearestPoint(searchPoint);
    //
	//		Point point = new Point(nearestPoint.getAxisValue(0), nearestPoint.getAxisValue(1)); 	//0: x, 1: y 
	//		int index = seedsCoords.indexOf(point); 
	//		if (index >= 0)
	//		{
	//		 	nearestColor = seeds.get(index);		// index 0 is rgb int value of seed
	//		 	return nearestColor; 
	//		}
	//		else
	//		{
	//			return -1; 
	//		}
	//	//}
	//}
	
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
		try 
		{
			heightmap = BMPDecoder.read(new File("heightmap.bmp"));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return; 
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
