package provinceGen;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * should be class which calls province gen, state gen, etc., when called, also should be parent class for easy variable sharing between generation classes 
 * 
 * @author Darian Siembab
 *
 */
public class MapGeneration {
	public static int imageWidth = 4608; 	// 1024, 512, 256 works	// 5632 - default
	public static int imageHeight = 2816;	// 1024, 512, 256 works	// 2048 - default
	public static final int HEIGHTMAP_SEA_LEVEL = 95; 
	public static final Color SEA_LEVEL_RGB = new Color(HEIGHTMAP_SEA_LEVEL, HEIGHTMAP_SEA_LEVEL, HEIGHTMAP_SEA_LEVEL); ;
	public static final int SEA_LEVEL_INT_RGB = ((SEA_LEVEL_RGB.getRed() << 8) + SEA_LEVEL_RGB.getGreen()) << 8 + SEA_LEVEL_RGB.getBlue(); 
	public static int numSeedsY = 32; 		// 64 is ok	// 64^2 = 4096 
	public static int numSeedsX = 32; 		// 64 is ok // 64^2 = 4096
	private static ArrayList<ArrayList<ArrayList<Integer>>> points;  							// stored y, x; stores rgb, seed (0 or 1), land/sea (0 or 1)
	private static ArrayList<Point> seedsLand = new ArrayList<Point>(numSeedsY * numSeedsX); 	// values of point stored as x, y
	private static ArrayList<Point> seedsSea = new ArrayList<Point>(numSeedsY * numSeedsX); 	// values of point stored as x, y										
	private static HashMap<Point, Integer> seedsLandRGBValue = new HashMap<Point, Integer>((numSeedsY * numSeedsX) / 4); 
	private static HashMap<Point, Integer> seedsSeaRGBValue = new HashMap<Point, Integer>((numSeedsY * numSeedsX) / 4); 
	private static int rgb_white; 
	private static BufferedImage heightmap; 
}
