package provinceGen;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.image4j.codec.bmp.BMPDecoder;

/**
 * should be class which calls province gen, state gen, etc., when called, also should be parent class for easy variable sharing between generation classes 
 * 
 * @author Darian Siembab
 *
 */
public class MapGeneration {
	protected static final int HEIGHTMAP_SEA_LEVEL = 95; 
	public static final Color SEA_LEVEL_RGB = new Color(HEIGHTMAP_SEA_LEVEL, HEIGHTMAP_SEA_LEVEL, HEIGHTMAP_SEA_LEVEL); ;
	public static final int SEA_LEVEL_INT_RGB = ((SEA_LEVEL_RGB.getRed() << 8) + SEA_LEVEL_RGB.getGreen()) << 8 + SEA_LEVEL_RGB.getBlue(); 
	protected static int imageWidth = 4608; 	// 1024, 512, 256 works	// 5632 - default
	protected static int imageHeight = 2816;	// 1024, 512, 256 works	// 2048 - default
	protected static int numSeedsY = 32; 		// 64 is ok	// 64^2 = 4096 
	protected static int numSeedsX = 32; 		// 64 is ok // 64^2 = 4096
	protected static int numSeeds = numSeedsX * numSeedsY; 
	protected static ArrayList<ArrayList<ArrayList<Integer>>> points;  							// stored y, x; stores rgb, seed (0 or 1), land/sea (0 or 1)
	protected static ArrayList<ArrayList<Point>> seeds = new ArrayList<ArrayList<Point>>(2); 									
	protected static ArrayList<HashMap<Integer, HashMap<Point, Integer>>> seedsRGBValueMaps 
			= new ArrayList<HashMap<Integer, HashMap<Point, Integer>>>(2); 
	protected static HashMap<Point, Integer> seedsRGBValues = new HashMap<Point, Integer>((numSeeds) / 4); 
	protected static int rgb_white; 
	protected static BufferedImage heightmap; 			// elevation data heightmap 
	protected static BufferedImage stateBorderMap; 		// heightmap of preferred borders 
	private boolean constructorCalled = false; 			// whether the constructor of this class has been called previously
	
	public MapGeneration() {
		/**
		 * calculate variables, load necessary data 
		 */
		if(!constructorCalled)
		{
			// Color -> int
			rgb_white = Color.white.getRed(); 
			rgb_white = (rgb_white << 8) + Color.white.getGreen();
			rgb_white = (rgb_white << 8) + Color.white.getBlue(); 
			
			/** 
			 * load bmp images
			 */
			heightmap = loadBMPImage("heightmap.bmp");  
			stateBorderMap = loadBMPImage("state_borders.bmp"); 
			imageWidth = heightmap.getWidth(); 			
			imageHeight = heightmap.getHeight(); 	// may break things but good idea
			
			initializePoints(); 
			
			constructorCalled = true; 
		} 
	}
	
	/**
	 * Loads a BMP image named <code>heightmap.bmp</code> using {@link BMPDecoder} 
	 * @see BMPDecoder
	 */
	protected static BufferedImage loadBMPImage(String file)
	{
		try 
		{
			return BMPDecoder.read(new File(file));
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	/**
	 * Initializes ArrayList of points (x, y, point information)
	 * @see ArrayList
	 */
	protected static void initializePoints() {
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
}
