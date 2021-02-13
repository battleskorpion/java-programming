package trianglesss; 

/******************************************/
/* TRIANGLELAB5 OBJECT TO DEMONSTRATE THE */
/* TOSTRING() METHOD                      */
/******************************************/

public class TriangleLab5 
{
	// THREE SIDES OF THE TRIANGLE
	private double side1, side2, side3;
	
	public TriangleLab5 ()
	/***********************************************/
	/* DEFAULT CONSTRUCTOR:  Initializes all three */
	/*                       of the triangle       */
	/***********************************************/
	
	{
		side1 = 0;
		side2 = 0;
		side3 = 0;
	}
	
	public TriangleLab5 (double s1, double s2, double s3)
	/****************************************************/
	/* INITIALIZING CONSTRUCTOR:  Initializes all three */
	/*                             of the triangle      */
	/****************************************************/
	
	{
		side1 = s1;
		side2 = s2;
		side3 = s3;
	}
	
	private double getPerimeter ()
	/*************************************************************/
	/* precondition:  Program has three sides of the triangle    */
	/* postcondition: Function returns perimeter of the triangle */
	/* NOTE:          Method private can only be accessed from   */
	/*                within this object                         */
	/*************************************************************/
	{
		return side1 + side2 + side3;
	}
	
	private double getArea ()
	/***********************************************************/
	/* precondition:   Program has three sides of the triangle */
	/* postcondition:  Function returns area of the triangle   */
	/* NOTE:          Method private can only be accessed from */
	/*                within this object                       */
	/***********************************************************/
	{
		// GET PERIMETER OF THE TRIANGLE
		double per = getPerimeter ();
		
		return Math.sqrt ((per/2) * 
			              (per/2 - side1) *
			              (per/2 - side2) *
			              (per/2 - side3));
	}  
		
	public String toString()
	/*****************************************************/
	/* OVERRIDES THE TOSTRING IN THE PARENT CLASS OBJECT */
	/* AND CREATES A STRING REPRESENTATION OF THIS OBJECT */
	/******************************************************/
	{
		String str;	// String representation
		
		str = "Side1:    " + side1 + "\n" +
			  "Side2:    " + side2 + "\n" +
			  "Side3:    " + side3 + "\n" +
			  "Permeter: " + getPerimeter () + "\n" +
			  "Area:     " + getArea();
			 
		return str;
	} 
}

