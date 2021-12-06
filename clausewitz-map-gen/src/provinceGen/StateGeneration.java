package provinceGen;

import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * @author Darian Siembab
 *
 */
public class StateGeneration {

	private static ArrayList<ArrayList<ArrayList<Integer>>> points;  
	
	public StateGeneration(ArrayList<ArrayList<ArrayList<Integer>>> points, ArrayList<Point> seedsLand, ArrayList<Point> seedsSea) {
		this.points = points; 
	}
}
