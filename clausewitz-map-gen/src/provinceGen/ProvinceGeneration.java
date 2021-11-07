package provinceGen;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import net.sf.image4j.codec.bmp.*; 
import org.spongepowered.noise.*;
import org.spongepowered.noise.module.source.Simplex;

/**
 * Based off of Voronoi Diagrams / Voronoi division: 
 * <link>https://en.wikipedia.org/wiki/Voronoi_diagram</link> 
 * 
 * @author Darian Siembab
 */
public class ProvinceGeneration {
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
	
	public static int imageWidth = 4608; 	// 1024, 512, 256 works	// 5632 - default
	public static int imageHeight = 2816;	// 1024, 512, 256 works	// 2048 - default
	//public static int INT_WHITE = ((Color.white.getRed() << 8) + Color.white.getGreen()) << 8 + Color.white.getBlue(); 
	public static final int HEIGHTMAP_SEA_LEVEL = 95; 
	// SEA LEVEL COLOR/MAX 
	public static final Color SEA_LEVEL_RGB = new Color(HEIGHTMAP_SEA_LEVEL, HEIGHTMAP_SEA_LEVEL, HEIGHTMAP_SEA_LEVEL); ;
	public static final int SEA_LEVEL_INT_RGB = ((SEA_LEVEL_RGB.getRed() << 8) + SEA_LEVEL_RGB.getGreen()) << 8 + SEA_LEVEL_RGB.getBlue(); 
	public static int numSeedsY = 32; 		// 64 is ok	// 64^2 = 4096
	public static int numSeedsX = 32; 		// 64 is ok // 64^2 = 4096
	private static ArrayList<ArrayList<ArrayList<Integer>>> points;  							// stored y, x
	private ArrayList<Point> seedsLand = new ArrayList<Point>(numSeedsY * numSeedsX); 	//// values of point stored as x, y
	private ArrayList<Point> seedsSea = new ArrayList<Point>(numSeedsY * numSeedsX); 	//// values of point stored as x, y
	private HashMap<Point, Point> closestSeedToPoint; 											// TODO: EXPERIMENTAL WITH JFA
	private HashMap<Point, Integer> seedsLandRGBValue = new HashMap<Point, Integer>((numSeedsY * numSeedsX) / 4); 
	private HashMap<Point, Integer> seedsSeaRGBValue = new HashMap<Point, Integer>((numSeedsY * numSeedsX) / 4); 
	//private static ArrayList<Integer> seeds = new ArrayList<Integer>(); 		// just stores rgb values 
	//private static ArrayList<Point> seedsCoords = new ArrayList<Point>(); 
	private static int rgb_white; 
	private static BufferedImage image; 
	private static BufferedImage heightmap; 
	private ClausewitzMapGenMenu parentMenu; 
	
	//public static void main (String args[]) 
	//{
	//	provinceGeneration(); 
	//}
	
	/**
	 * Constructors
	 */
	public ProvinceGeneration() {
		
	}
	
	public ProvinceGeneration(ClausewitzMapGenMenu parentWindow) {
		this.parentMenu = parentWindow; 
	}
	
	/**
	 * assumes a ClausewitzMapGenMenu parentWindow 
	 */
	public void provinceGeneration() {
		// variable section 
		//ProvinceGenProgressWindow progressWindow = new ProvinceGenProgressWindow(imageHeight); 
		Color color; 
		Random random = new Random(); 
		
		/**
		 * province generation time 
		 */
		long start = System.nanoTime();
		
		/**
		 * set progress stage of province generation
		 */
		parentMenu.setProgressStage("Preparing...", 5);  
		
		loadHeightmap(); 
		imageWidth = heightmap.getWidth(); 			
		imageHeight = heightmap.getHeight(); 	// may break things but good idea 
		parentMenu.increaseProgress(); 
		
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB); 
		parentMenu.increaseProgress(); 
		
		initializePoints(); 
		parentMenu.increaseProgress(); 
		
		// Color -> int
		rgb_white = Color.white.getRed(); 
		rgb_white = (rgb_white << 8) + Color.white.getGreen();
		rgb_white = (rgb_white << 8) + Color.white.getBlue();
		parentMenu.increaseProgress(); 
		
		// white canvas (unnecessary) 
		for (int y = 0; y < imageHeight; y++) {
			for (int x = 0; x < imageWidth; x++) {			
				// set color at pixel cords
				image.setRGB(x, y, rgb_white);
			}
		}
		parentMenu.increaseProgress(); 
		
		/**
		 * set progress stage of province generation
		 */
		parentMenu.setProgressStage("Generating seeds...", numSeedsY);  
		
		
		
		/* seeds */
		// (seed generation is very quick) 
		for (int y = imageHeight / numSeedsY / 2 - 1; y < imageHeight; y+= imageHeight / numSeedsY) {			// int y = numSeedsY / 2 - 1 worked sometimes
			for (int x = imageWidth / numSeedsX / 2 - 1; x < imageWidth; x+= imageWidth / numSeedsX) { 			// int x = numSeedsX / 2 - 1 worked sometimes
				// set color
				int xOffset = random.nextInt(imageWidth / numSeedsX - 1) - (imageWidth / numSeedsX / 2 - 1); 	// -3 to 3		// should make variables	// int xOffset = random.nextInt(numSeedsX - 1) - (numSeedsX / 2 - 1); 
				int yOffset = random.nextInt(imageHeight / numSeedsY - 1) - (imageHeight / numSeedsY / 2 - 1); 	// -3 to 3		// should make variables	// int yOffset = random.nextInt(numSeedsY - 1) - (numSeedsY / 2 - 1);
				int seedX = x + xOffset;	// x-value of seed
				int seedY = y + yOffset; 	// y-value of seed
				int rgb; 								// rgb color int value
				
				/* heightmap color stuff */
				int heightmapHeight; 												// color (gray) value (height value) of heightmap
				int heightmapOffsetRGB; 
				int red; 
				
				heightmapHeight = (heightmap.getRGB(seedX, seedY) >> 16) & 0xFF; 			// heightmap is in grayscale meaning only need to find red value to get height value at point. 
				//heightmapOffsetRGB = heightmap.getRGB(x + xOffset, y + yOffset); 
				//red = (heightmapOffsetRGB >> 16) & 0xFF;
				//int green = (heightmapRGB >> 8) & 0xFF;		// grayscale, only need one to compare
				//int blue = heightmapRGB & 0xFF;				// grayscale, only need one to compare
				
				if (heightmapHeight < HEIGHTMAP_SEA_LEVEL) {
					/* prov color */
					color = new Color(random.nextInt(64), random.nextInt(64), random.nextInt(64)); 
					
					// Color -> int
					rgb = color.getRed(); 
					rgb = (rgb << 8) + color.getGreen();
					rgb = (rgb << 8) + color.getBlue();		
				}
				else {
					/* prov color */
					color = new Color(random.nextInt(192) + 64, random.nextInt(192) + 64, random.nextInt(192) + 64); 
					
					// Color -> int
					rgb = color.getRed(); 
					rgb = (rgb << 8) + color.getGreen();
					rgb = (rgb << 8) + color.getBlue();
				}
				
				/* calculate sea or land prov. */  
				int type = 0; // 0: land
				if (heightmapHeight < HEIGHTMAP_SEA_LEVEL) {
					type = 1; // 1: sea  	
				}
				
				/* add point to points array */
				// x and y not needed as should correlate to place in arraylist
				points.get(Integer.valueOf(seedY)).get(Integer.valueOf(seedX))
				.set(0, rgb); 
				points.get(Integer.valueOf(seedY)).get(Integer.valueOf(seedX))
				.set(1, Integer.valueOf(1)); 	// "true" (for later) // (its a seed) 
				points.get(Integer.valueOf(seedY)).get(Integer.valueOf(seedX))
				.set(2, Integer.valueOf(type)); // type of province (sea or land) 
				//if (type == 1)
				//{
					//System.out.println(points.get(Integer.valueOf(y + yOffset)).get(Integer.valueOf(x + xOffset)).get(points.get(Integer.valueOf(y + yOffset)).get(Integer.valueOf(x + xOffset)).size() - 1)); 
					
				//}
				//System.out.println(points.get(Integer.valueOf(y + yOffset)).get(Integer.valueOf(x + xOffset)).get(2).toString()); 	
				
				
				// add point to seeds array 
				// x and y needed
				if (heightmapHeight < HEIGHTMAP_SEA_LEVEL) {
					Point point = new Point(seedX, seedY); 
					seedsSea.add(point); 
					seedsSeaRGBValue.put(point, rgb); 
					//seedsSea.get(seedsSea.size() - 1).add(Integer.valueOf(x + xOffset)); 
					//seedsSea.get(seedsSea.size() - 1).add(Integer.valueOf(y + yOffset));
					//seedsSea.get(seedsSea.size() - 1).add(rgb); 
					//seedsSea.get(seedsSea.size() - 1).add(1); 	// "true" (is sea) (not great but for now ok)	//TODO: Unnecessary  
				}
				else {
					Point point = new Point(seedX, seedY); 
					seedsLand.add(point); 
					seedsLandRGBValue.put(point, rgb); 
					//seedsLand.get(seedsLand.size() - 1).add(Integer.valueOf(x + xOffset)); 
					//seedsLand.get(seedsLand.size() - 1).add(Integer.valueOf(y + yOffset));
					//seedsLand.get(seedsLand.size() - 1).add(rgb); 
					//seedsLand.get(seedsLand.size() - 1).add(0); 	// "false" (not sea) (not great but for now ok)	//TODO: Unnecessary 
				}

				// set color at pixel cords
				try {
					//image.setRGB(x + xOffset, y + yOffset, rgb);
					image.setRGB(seedX, seedY, rgb);  
				}
				catch (ArrayIndexOutOfBoundsException exc) {
					exc.printStackTrace();
					System.out.println("x: " + (seedX)); 
					System.out.println("y: " + (seedY)); 
					return; 
					//continue; 	// skip rest of iteration 
				}
				
			}
			parentMenu.increaseProgress(); 
		}
		
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
		
		/**
		 * set progress stage of province generation
		 */
		//parentWindow.setProgressStage("Determining colors...", imageHeight);  
		
		final int widthPerSeed = imageWidth  / numSeedsX; 
		final int heightPerSeed = imageHeight / numSeedsY; 
//		JumpFloodingAlgorithm seaJumpFloodingAlgorithm = new JumpFloodingAlgorithm(numSeedsX, numSeedsY, imageWidth, imageHeight, seedsSea, parentMenu);
//		JumpFloodingAlgorithm landJumpFloodingAlgorithm = new JumpFloodingAlgorithm(numSeedsX, numSeedsY, imageWidth, imageHeight, seedsLand, parentMenu);
//		
//		int numSteps = (int) (imageWidth > imageHeight ? Math.log(imageWidth) / Math.log(2) : Math.log(imageHeight) / Math.log(2));
//		/**
//		 * set progress stage of province generation
//		 */
//		parentMenu.setProgressStage("Executing Jump Flooding Algorithm...", numSteps); 
//		closestSeedToPoint = seaJumpFloodingAlgorithm.ExecuteJumpFlooding(); 
		
		/**
		 * set progress stage of province generation
		 */
		parentMenu.setProgressStage("Determining colors...", imageHeight); 
		
//		//TODO: EXPERIMENTAL JFA
//		for (int y = 0; y < imageHeight; y++) {
//			for (int x = 0; x < imageWidth; x++) {
//				int rgb = rgb_white; 
//				int xOffset = (int) (((widthPerSeed) * ((noise.getValue(x * 0.032, y * 0.032, 0.0) - 1) * 0.1)));	
//				int yOffset = (int) (((heightPerSeed) * ((noise.getValue(x * 0.032, y * 0.032, 0.0) - 1) * 0.1))); 
//				if (((heightmap.getRGB(x, y) >> 16) & 0xFF) < HEIGHTMAP_SEA_LEVEL) {
//					if (closestSeedToPoint.get(new Point(x + xOffset, y + yOffset)) != null) {
//						rgb = seedsSeaRGBValue.get(closestSeedToPoint.get(new Point(x + xOffset, y + yOffset))); 
//						//rgb = determineColor(x, xOffset, y, yOffset, seedsSea, seedsSeaRGBValue); 
//					} 
//				}
//				else {
//					//rgb = determineColor(x, xOffset, y, yOffset, seedsLand, seedsLandRGBValue); 
//					if (closestSeedToPoint.get(new Point(x + xOffset, y + yOffset)) != null) {
//						rgb = seedsLandRGBValue.get(closestSeedToPoint.get(new Point(x + xOffset, y + yOffset))); 
//					} 
//				}
//				
//				points.get(y).get(x).set(0, Integer.valueOf(rgb)); 	
//				image.setRGB(x, y, rgb);
//			}
//			parentMenu.increaseProgress();
//		}
		
		// TODO: GOOD CODE, JUST TESTING JUMP FLOODING ALGORITHM
		// ~twice as fast using threads
		for (int y = 0; y < imageHeight; y++) {
			final int localY = y; 
			
			/* define new runnable */
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					for (int x = 0; x < imageWidth; x++) {
						int xOffset = (int) (((widthPerSeed) * ((noise.getValue(x * 0.032, localY * 0.032, 0.0) - 1) * 0.1)));	// (int) (((numSeedsX - 1) * ((noise.getValue(x * 0.085, localY * 0.085, 0.0) - 1) * 0.1)));
						int yOffset = (int) (((heightPerSeed) * ((noise.getValue(x * 0.032, localY * 0.032, 0.0) - 1) * 0.1))); // (int) (((numSeedsY - 1) * ((noise.getValue(x * 0.085, localY * 0.085, 0.0) - 1) * 0.1)));
						int rgb;
						
						if (((heightmap.getRGB(x, localY) >> 16) & 0xFF) < HEIGHTMAP_SEA_LEVEL) {
							rgb = determineColor(x, xOffset, localY, yOffset, seedsSea, seedsSeaRGBValue); 
						}
						else {
							rgb = determineColor(x, xOffset, localY, yOffset, seedsLand, seedsLandRGBValue); 
						}
		
						points.get(localY).get(x).set(0, Integer.valueOf(rgb)); 	
						image.setRGB(x, localY, rgb);
					}
				}
			}; 
			
			Thread thread = new Thread(runnable); 	// set new thread to be new thread of defined runnable 
			thread.start(); 						// start new thread 
			try {
				thread.join();						// Waits for thread to die 
				if (parentMenu != null) {
					parentMenu.setProgress(localY); 
				} 
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
				return; 
			} 
			catch (Exception e) {
				e.printStackTrace(); 
				return; 
			}
		}
// TODO: GOOD GOOD CODE END 
		
//		int availableThreadCount = Runtime.getRuntime().availableProcessors();
//		for (int y = 0; y < imageHeight; y+= imageHeight/availableThreadCount) {
//		//for (int y = 0; y < imageHeight; y++) {
//			final int localYStart = y; 
//			
//			/* define new runnable */
//			Runnable runnable = new Runnable() {
//				@Override
//				public void run() {
//					for (int localY = localYStart; localY < localYStart + imageHeight/availableThreadCount; localY++) {
//						for (int x = 0; x < imageWidth; x++) {
//							int xOffset = (int) (((widthPerSeed) * ((noise.getValue(x * 0.032, localY * 0.032, 0.0) - 1) * 0.1)));	// (int) (((numSeedsX - 1) * ((noise.getValue(x * 0.085, localY * 0.085, 0.0) - 1) * 0.1)));
//							int yOffset = (int) (((heightPerSeed) * ((noise.getValue(x * 0.032, localY * 0.032, 0.0) - 1) * 0.1))); // (int) (((numSeedsY - 1) * ((noise.getValue(x * 0.085, localY * 0.085, 0.0) - 1) * 0.1)));
//							int rgb;
//							
//							if (((heightmap.getRGB(x, localY) >> 16) & 0xFF) < HEIGHTMAP_SEA_LEVEL) {
//								rgb = determineColor(x, xOffset, localY, yOffset, seedsSea, seedsSeaRGBValue); 
//							}
//							else {
//								rgb = determineColor(x, xOffset, localY, yOffset, seedsLand, seedsLandRGBValue); 
//							}
//			
//							points.get(localY).get(x).set(0, Integer.valueOf(rgb)); 	
//							image.setRGB(x, localY, rgb);
//						}
//						if (parentMenu != null) {
//							parentMenu.increaseProgress();
//						}
//					} 
//					threadsRemaining -= 1; 
//				}
//			}; 
//			
//			Thread thread = new Thread(runnable); 	// set new thread to be new thread of defined runnable 
//			thread.start(); 						// start new thread 
////			try {
////				thread.join();						// Waits for thread to die  
////			} 
////			catch (InterruptedException e) {
////				e.printStackTrace();
////				return; 
////			} 
////			catch (Exception e) {
////				e.printStackTrace(); 
////				return; 
////			}
//		}
		
//		/* catch up */ 
//		while (threadsRemaining > 0) {
//			java.lang.Thread.onSpinWait();
//		}
// 
		// TODO: END OF GOOD CODE, JUST TESTING JUMP FLOODING ALGORITHM
		
		//try {
		//	thisThread.wait();
		//}
		//catch (InterruptedException e) {
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
		
		/**
		 * set progress stage of province generation
		 */
		parentMenu.setProgressStage("Modifying province borders...", 1); 
		
		// smooth province borders
		//for (int i = 0; i < 5; i++) 
		//{
			//provinceSmooth(); 	// will make setting to use smoothing instead? 
									// (also removes exclaves in process) 
			removeExclaves(); 		// doesn't seem to slow the program much
			parentMenu.increaseProgress();
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
		try {
			parentMenu.setProgressStage("Writing image...", 1);
			BMPEncoder.write(image, new File("output.bmp"));
			parentMenu.increaseProgress();
		}
		catch (IOException exc) {
			exc.printStackTrace();
			// error
		}
		
		parentMenu.setProgressStage("Generation complete.", 0); 
		//System.out.println(points.toString()); // NO
		//System.out.println(seedsSea.size());
		//System.out.println(seedsLand.size());
		
		long end = System.nanoTime();
		double durationInMilliseconds = 1.0 * (end - start) / 1000000;
		System.out.println("Time: " + durationInMilliseconds + " milliseconds."); 
	}
	
	private static void initializePoints() {
		points = new ArrayList<ArrayList<ArrayList<Integer>>>(imageHeight);
		
		for (int y = 0; y < imageHeight; y++) {
			points.add(new ArrayList<ArrayList<Integer>>(imageWidth));
			for (int x = 0; x < imageWidth; x++) {
				points.get(y).add(new ArrayList<Integer>(4)); 
				
				// add actual data 
				for (int i = 0; i < 4; i++) {
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
	
// TODO: SLOW! :(
//	/**
//	 * experimental function
//	 * @param source
//	 * @param seedsKeyHashMap
//	 * @author adapted from the answer from user obataku on 
//	 * https://stackoverflow.com/questions/11819082/find-closest-point-from-users-coordinates, 
//	 */
//	private static int determineClosestPoint(Point source, final HashMap<Point, Integer> seedsKeyHashMap) {
//		//final HashMap<Point, Integer> points;
//		/**
//		 * set view of the keys of seedsRGBValue (hashmap with desired seeds points as the key)
//		 */
//		final Set<Point> points = seedsKeyHashMap.keySet();
//		//final Point source = ...;
//		
//		/**
//		 * find minimum distance to another desired point from the source point
//		 */
//		final Point nearest = Collections.min(points, new Comparator<Point>() {
//			public int compare(final Point p1, final Point p2) {
//				/**
//				 * calculate euclidean distance, sqrt is not needed
//				 * because we only compare and do not need the real value
//				 */
//				return (int) p1.distanceSq(p2);		 
//			}
//		});
//		//final int color = seedsKeyHashMap.get(nearest);
//		return seedsKeyHashMap.get(nearest); 
//	}
	
	// TODO: function is faster
	private static int determineColor(int x, int xOffset, int y, int yOffset, ArrayList<Point> seeds, final HashMap<Point, Integer> seedsRGBValue) 
	{
		int nearestColor = rgb_white; 			// color of nearest seed (int value) 
			           							// (default white)
		int dist = Integer.MAX_VALUE; 			// select a big number
		
		//Point point = new Point(x + xOffset, y + yOffset); 
		
		//determineClosestPoint(point, seedsRGBValue); 
		
		// iterate through each seed
		for (int s = 0; s < seeds.size(); s++) {
			Point point = seeds.get(s); 
			
			// calculate the difference in x and y direction
			int xdiff = point.x - (x + xOffset);			// seeds.get(s).get(0) - (x + xOffset);
			int ydiff = point.y - (y + yOffset);			// seeds.get(s).get(1) - (y + yOffset);
		
	        // calculate euclidean distance, sqrt is not needed
	        // because we only compare and do not need the real value
	        int cdist = xdiff*xdiff + ydiff*ydiff;
	        
	        // is the current distance smaller than the old distance?
	        if (cdist < dist)
	        {
	        	nearestColor = seedsRGBValue.get(point);		// index 2 is rgb int value of seed // seeds.get(s).get(2)
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
						applyPointColor(x, y, point); 
					}
				}
				else if (left == right)
				{
					point = left; 
					applyPointColor(x, y, point); 
				}
				else if (above == below)
				{
					point = above; 
					applyPointColor(x, y, point); 	 
				}		
			}
		}
	}
	
	/**
     * Sets point color to value of point of point specified by <code>x</code> and <code>y</code> (order y, x) 
     * in points arraylist and sets <code>color</code> of image to the point color at <code>x</code> and <code>y</code>. 
     * @param x x-coordinate 
     * @param y y-coordinate
     * @param color color value to use 
     */
	private static void applyPointColor(int x, int y, int color)
	{
		points.get(y).get(x).set(0, Integer.valueOf(color)); 
		image.setRGB(x, y, color); 	
	}
	
	/**
	 * Remove single pixels fully surrounded by another color. 
	 */
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
	
	/**
	 * Loads a heightmap named <code>heightmap.bmp</code> using {@link BMPDecoder} 
	 * @see BMPDecoder
	 */
	private static void loadHeightmap()
	{
		try 
		{
			heightmap = BMPDecoder.read(new File("heightmap.bmp"));
		} 
		catch (IOException e) {
			e.printStackTrace();
			return; 
		}
		
	}
}
