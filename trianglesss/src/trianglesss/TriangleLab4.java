package trianglesss; 

/*********************************************/
/* TRIANGLELAB4 OBJECT TO DEMONSTATE CLASSES */
/* AND METHODS IN DIFFERENT FILES            */
/*********************************************/

public class TriangleLab4 
{
	// THREE SIDES OF THE TRIANGLE
	private double side1, side2, side3;
	
	public TriangleLab4 (double s1, double s2, double s3)
	/****************************************************/
	/* INITIALIZING CONSTRUCTOR:  Initializes all three */
	/*                             of the triangle      */
	/****************************************************/
	
	{
		side1 = s1;
		side2 = s2;
		side3 = s3;
	}
	
	public double getPerimeter ()
	/*************************************************************/
	/* precondition:  Program has three sides of the triangle    */
	/* postcondition: Function returns perimeter of the triangle */
	/*************************************************************/
	{
		return side1 + side2 + side3;
	}
	
	public double getArea ()
	/***********************************************************/
	/* precondiiton:   Program has three sides of the triangle */
	/* postcondition:  Function returns area of the triangle   */
	/***********************************************************/
	{
		// GET PERIMETER OF THE TRIANGLE
		double per = getPerimeter ();
		
		return Math.sqrt ((per/2) * 
			              (per/2 - side1) *
			              (per/2 - side2) *
			              (per/2 - side3));
	}   
}



