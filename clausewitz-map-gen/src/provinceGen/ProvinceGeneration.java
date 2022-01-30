package provinceGen;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RejectedExecutionException;

import net.sf.image4j.codec.bmp.*; 
import org.spongepowered.noise.*;
import org.spongepowered.noise.module.source.Simplex;

/**
 * Based off of Voronoi Diagrams / Voronoi division: 
 * <link>https://en.wikipedia.org/wiki/Voronoi_diagram</link> 
 * 
 * @author Darian Siembab
 */
public class ProvinceGeneration extends MapGeneration {
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
	
	//public static int INT_WHITE = ((Color.white.getRed() << 8) + Color.white.getGreen()) << 8 + Color.white.getBlue(); 						// stored y, x; stores rgb, seed (0 or 1), land/sea (0 or 1)
	private static ArrayList<Point> seedsLand = new ArrayList<Point>(numSeedsY * numSeedsX); 	// values of point stored as x, y
	private static ArrayList<Point> seedsSea = new ArrayList<Point>(numSeedsY * numSeedsX); 	// values of point stored as x, y
	//private HashMap<Point, Point> closestSeedToPoint; 											
	// TODO: private static HashMap<Point, Integer> seedsLandRGBValue = new HashMap<Point, Integer>((numSeedsY * numSeedsX) / 4); 
	// TODO: private static HashMap<Point, Integer> seedsSeaRGBValue = new HashMap<Point, Integer>((numSeedsY * numSeedsX) / 4); 
	//private static volatile HashMap<Point, Point> offset = new HashMap<Point, Point>((imageWidth * imageHeight)); 	
	//private static ArrayList<Integer> seeds = new ArrayList<Integer>(); 		// just stores rgb values 
	//private static ArrayList<Point> seedsCoords = new ArrayList<Point>(); 
	private static BufferedImage image; 
	//private static BufferedImage testImage;		
	//private static int testWidth = 100; 
	private static ClausewitzMapGenMenu parentMenu; 
	private static HashMap<Integer, HashMap<Point, Integer>> seedsLandRGBValueMaps = new HashMap<Integer, HashMap<Point, Integer>>(); 
	private static HashMap<Integer, HashMap<Point, Integer>> seedsSeaRGBValueMaps  = new HashMap<Integer, HashMap<Point, Integer>>(); 
	private static ArrayList<Integer> stateBorderMapValues = new ArrayList<Integer>(); 
	private static ArrayList<Integer> stateBorderMapValuesLand = new ArrayList<Integer>(); 
	private static ArrayList<Integer> stateBorderMapValuesSea = new ArrayList<Integer>(); 
	//public static void main (String args[]) 
	//{
	//	provinceGeneration(); 
	//}
	
	/**
	 * Constructors
	 */
	public ProvinceGeneration() {
		this(null); 
	}
	
	public ProvinceGeneration(ClausewitzMapGenMenu parentWindow) {
		ProvinceGeneration.parentMenu = parentWindow; 
	}
	
	/**
	 * assumes a ClausewitzMapGenMenu parentWindow 
	 */
	public void provinceGeneration() {
		
		Color color; 						// Random color (light if land and darker if sea)
		Random random = new Random(); 		// Random number generator for generating colors
		long start = System.nanoTime();		// Province generation time 
		
		/**
		 * set progress stage of province generation
		 */
		parentMenu.setProgressStage("Preparing...", 5);  
		
		/**
		 * add seed rgb value hashmaps to list of hashmaps
		 */
		seedsRGBValueMaps.add(seedsLandRGBValueMaps);
		seedsRGBValueMaps.add(seedsSeaRGBValueMaps);
		
		parentMenu.increaseProgress(); 
		
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB); 
		//testImage = new BufferedImage(testWidth, testWidth, BufferedImage.TYPE_INT_RGB); 	
		parentMenu.increaseProgress(); 
		
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
		// TODO: REWRITE FOR STATE BORDERS IMPLEMENTATION 
		for (int y = imageHeight / numSeedsY / 2 - 1; y < imageHeight; y+= imageHeight / numSeedsY) {			// int y = numSeedsY / 2 - 1 worked sometimes
			for (int x = imageWidth / numSeedsX / 2 - 1; x < imageWidth; x+= imageWidth / numSeedsX) { 			// int x = numSeedsX / 2 - 1 worked sometimes
				// set color
				int xOffset = random.nextInt(imageWidth  / numSeedsX - 1) - (imageWidth  / numSeedsX / 2 - 1); 	// -3 to 3		// should make variables	// int xOffset = random.nextInt(numSeedsX - 1) - (numSeedsX / 2 - 1); 
				int yOffset = random.nextInt(imageHeight / numSeedsY - 1) - (imageHeight / numSeedsY / 2 - 1); 	// -3 to 3		// should make variables	// int yOffset = random.nextInt(numSeedsY - 1) - (numSeedsY / 2 - 1);
				int seedX = x + xOffset;		// x-value of seed
				int seedY = y + yOffset; 		// y-value of seed
				int rgb; 						// rgb color int value
				
				/* heightmap color stuff */
				int heightmapHeight; 														// color (gray) value (height value) of heightmap
				int stateMapColor;
				
				heightmapHeight = (heightmap.getRGB(seedX, seedY) >> 16) & 0xFF; 			// heightmap is in grayscale meaning only need to find red value to get height value at point. 
				stateMapColor = stateBorderMap.getRGB(seedX, seedY); 
				if (!stateBorderMapValues.contains(stateMapColor)); 
				{	
					stateBorderMapValues.add(stateMapColor); 
					if (heightmapHeight < HEIGHTMAP_SEA_LEVEL) {
						stateBorderMapValuesSea.add(stateMapColor); 
						seedsSeaRGBValueMaps.put(stateMapColor, new HashMap<Point, Integer>()); 
					}
					else {
						stateBorderMapValuesLand.add(stateMapColor); 
						seedsLandRGBValueMaps.put(stateMapColor, new HashMap<Point, Integer>()); 
					} 
					
				}
				
				//boolean colorExists; 
				aa: 
				do {
					//colorExists = false; 
					
					/* prov color */
					if (heightmapHeight < HEIGHTMAP_SEA_LEVEL) {
						/**
						 * generate new color until unique color generated (color does not exist already) 
						 */
						color = new Color(random.nextInt(64), random.nextInt(64), random.nextInt(64)); 
						
						// Color -> int
						rgb = color.getRed(); 
						rgb = (rgb << 8) + color.getGreen();
						rgb = (rgb << 8) + color.getBlue();		
					}
					else {
						/**
						 * generate new color until unique color generated (color does not exist already) 
						 */
						color = new Color(random.nextInt(192) + 64, random.nextInt(192) + 64, random.nextInt(192) + 64); 
						
						// Color -> int
						rgb = color.getRed(); 
						rgb = (rgb << 8) + color.getGreen();
						rgb = (rgb << 8) + color.getBlue();
					} 	
						
					/**
					 * check if color already exists
					 */
					if (seedsRGBValues.containsValue(rgb)) {
						continue aa; 
					}
//					for (HashMap<Integer, HashMap<Point, Integer>> seedColorValuesHashMaps: seedsRGBValues) {
//						for (int i = 0; i < seedColorValuesHashMaps.size(); i++) {
//							HashMap<Point, Integer> seedColorValues = seedColorValuesHashMaps.get(stateBorderMapValues.get(i)); 
//								
//							try {
//								if(seedColorValues.containsValue(rgb)) {
//									//colorExists = true; 
//									continue aa; 
//								} 
//							} 
//							catch (NullPointerException exc) {
//								System.out.println("iteration: " + i);
//								exc.printStackTrace();
//								return; 
//							}
//							catch (Exception exc) {
//								System.out.println("iteration: " + i);
//								exc.printStackTrace();
//								return; 
//							}
//						} 
//					}
				}
				while(false); 
				
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
				
				// add point to seeds array 
				// x and y needed
				Integer rgbInteger = rgb; 
				Point point = new Point(seedX, seedY); 
				if (heightmapHeight < HEIGHTMAP_SEA_LEVEL) {
					seedsSea.add(point); 
					seedsSeaRGBValueMaps.get(stateMapColor).put(point, rgbInteger);
					//seedsSeaRGBValue.put(point, rgb); 
				}
				else {
					seedsLand.add(point); 
					seedsLandRGBValueMaps.get(stateMapColor).put(point, rgbInteger); 
					//seedsLandRGBValue.put(point, rgb); 
				}
				seedsRGBValues.put(point, rgbInteger); 

				// set color at pixel cords
				try {
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
	
		/**
		 * set progress stage of province generation
		 */
		//parentWindow.setProgressStage("Determining colors...", imageHeight);  
		
//		final int widthPerSeed = imageWidth  / numSeedsX; 
//		final int heightPerSeed = imageHeight / numSeedsY; 
		
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
		
//		// EXPERIMENTAL JFA - SLOW :(
		//
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
	
		ForkColorDetermination forkColorDetermination = new ForkColorDetermination(); 
		ForkJoinPool forkJoinPool = new ForkJoinPool(); 
		try {
			forkJoinPool.invoke(forkColorDetermination); 
		} 
		catch(NullPointerException exc) {
			exc.printStackTrace();
		}
		catch(RejectedExecutionException exc) {
			exc.printStackTrace();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	
		// to output file prior to special changes
		//try 
		//{
		//	BMPEncoder.write(image, new File("outputprev.bmp"));
		//}
		//catch (IOException exc) 
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
		
		long end = System.nanoTime();
		double durationInMilliseconds = 1.0 * (end - start) / 1000000;
		System.out.println("Time: " + durationInMilliseconds + " milliseconds."); 
		
		// testing
		//
//		try {
//			BMPEncoder.write(testImage, new File("test.bmp"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		DefineProvinces.defineProvinces(points); 
	}
	
	//// function takes forever
	//
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
	
// SLOW! :(
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
	
	static boolean j = false; 
	// function is faster
	//
	private static int determineColor(int x, int xOffset, int y, int yOffset, final HashMap<Point, Integer> seedsRGBValue)  // ArrayList<Point> seeds
	{
		int nearestColor = rgb_white; 			// color of nearest seed (int value) 
			           							// (default white)
		int dist = Integer.MAX_VALUE; 			// select a big number
		
		if (!j) {
			System.out.println(seedsRGBValue.keySet().size()); //TODO: Test
			j = true; 
		} 
		
		//Point point = new Point(x + xOffset, y + yOffset); 
		
		//determineClosestPoint(point, seedsRGBValue); 
		
		// iterate through each seed
		//for (int s = 0; s < seeds.size(); s++) {
		for (Iterator<Point> pointIterator = seedsRGBValue.keySet().iterator(); pointIterator.hasNext();) {
			Point point = pointIterator.next(); 
			
			// calculate the difference in x and y direction
			int xdiff = point.x - (x + xOffset);			
			int ydiff = point.y - (y + yOffset);			
		
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
	
	// this is slower :(
	//
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
	 * Pixel color determination using {@link RecursiveAction} for multithreading efficency. 
	 * 
	 * @author Darian Siembab
	 * @see RecursiveAction
	 * @see Simplex
	 */
	public static class ForkColorDetermination extends RecursiveAction {
		/**
		 * Auto-generated serialVersionUID
		 */
		private static final long serialVersionUID = 7925866053687723919L;
		
		protected static int splitThreshhold = 8;		// split until 1 row each 
		
		/**
		 * y-value to start at
		 */
		private int startY;
		
		/**
		 * y-value to go until (do not do work at this y-value, do work up to this y-value)
		 */
		private int endY; 
		
		/**
		 * number of y-values to work with
		 */
		private int dy; 
		
		/**
		 * simplex noise to offset color determination
		 */
		private Simplex noise; 
		
		/**
		 * constructor (y set as 0 to imageHeight). Reccommended constructor for initial initialization. 
		 */
		public ForkColorDetermination() {
			startY = 0;
			endY = imageHeight; 
			dy = endY - startY; 
			noise = new Simplex(); 
			noise.setNoiseQuality(NoiseQualitySimplex.SMOOTH); 
		}
		
		/**
		 * constructor 
		 */ 
		public ForkColorDetermination(int startY, int endY) {
			this.startY = startY;
			this.endY = endY; 
			dy = endY - startY; 
			noise = new Simplex(); 
			noise.setNoiseQuality(NoiseQualitySimplex.SMOOTH); 
		}
		
		@Override
		protected void compute() {
			if (dy <= splitThreshhold) {
				computeDirectly(); 
				return; 
			}
			
			int split = dy / 2; 
			
			invokeAll(new ForkColorDetermination(startY, startY + split), new ForkColorDetermination(startY + split, endY));
		}
		
		/**
		 * Determine color for each point 
		 */
		protected void computeDirectly() {
			final int widthPerSeed = imageWidth  / numSeedsX; 
			final int heightPerSeed = imageHeight / numSeedsY; 
			
			try {
				for (int y = startY; y < endY; y++) {
					for (int x = 0; x < imageWidth; x++) {
						int xOffset = (int) (((widthPerSeed)  * ((noise.getValue(x * 0.005, y * 0.005, 0.0) - 1) * 0.5)));		// * ((noise.getValue(x * 0.005, y * 0.005, 0.0) - 1) * 0.5)));	 good values for 32*32 seeds and 4608 * 2816 image 
						int yOffset = (int) (((heightPerSeed) * ((noise.getValue(x * 0.005, y * 0.005, 1.0) - 1) * 0.5))); 
						int rgb;
						int heightmapValue = heightmap.getRGB(x, y); 
						int stateBorderValue = stateBorderMap.getRGB(x, y); 
						
						if (((heightmapValue >> 16) & 0xFF) < HEIGHTMAP_SEA_LEVEL) {
							if (stateBorderMapValuesSea.contains(stateBorderValue)) {
								rgb = determineColor(x, xOffset, y, yOffset, seedsSeaRGBValueMaps.get(stateBorderValue)); 
							}
							else if (seedsLandRGBValueMaps.get(stateBorderValue) != null){
								rgb = determineColor(x, xOffset, y, yOffset, seedsLandRGBValueMaps.get(stateBorderValue)); 
							}
							else 
							{
								rgb = determineColor(x, xOffset, y, yOffset, seedsRGBValues); 
							}
						}
						else {
							if (stateBorderMapValuesLand.contains(stateBorderValue)) {
								rgb = determineColor(x, xOffset, y, yOffset, seedsLandRGBValueMaps.get(stateBorderValue)); 
								//System.out.println(seedsLandRGBValueMaps.get(stateBorderValue)); //TODO: TEST
							}
							else if (seedsSeaRGBValueMaps.get(stateBorderValue) != null){
								rgb = determineColor(x, xOffset, y, yOffset, seedsSeaRGBValueMaps.get(stateBorderValue)); 
							}
							else
							{
								rgb = determineColor(x, xOffset, y, yOffset, seedsRGBValues); 
							}
						}
						
						points.get(y).get(x).set(0, Integer.valueOf(rgb)); 	
						image.setRGB(x, y, rgb);
//						try { 
//							if (testWidth / 2 + xOffset < testWidth && testWidth / 2 + yOffset < testWidth) {
//								testImage.setRGB(testWidth / 2 + xOffset, testWidth / 2 + yOffset, rgb); 
//							} 
//						} 
//						catch (Exception exc) { 
//							
//						}
						//offset.put(new Point(x, y), new Point(xOffset, yOffset)); 
					} 	
					//parentMenu.increaseProgress(); 	// TODO: KNOW HOW TO FIX THIS NOW (THREAD SYNC STUFF, TRAY PROGRAM) 
				} 
			} 
			catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}
	
}

