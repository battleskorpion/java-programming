package trianglesss; 

/***************************************/
/* TRIANGLELAB3 TO DEMONSTRATE CLASSES */
/* IN THE SAME FILE AS MAIN METHOD     */
/***************************************/

import java.util.*; 

public class TriangleLab3 
{
	// THREE SIDES OF THE TRIANGLE
	private double side1, side2, side3;
	
	public TriangleLab3 (double s1, double s2, double s3)
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
	
    public static void main(String[] args) 
    {
    	// PROGRAM DESCRIPTION
    	System.out.println ("This program demonstrates the");
    	System.out.println ("constructors of the triangle object ");
    	System.out.println ();
    	
    	// INSTANTIATE OBJECT TO INPUT AND OUTPUT
    	Scanner reader = new Scanner(System.in);
    	
    	// DECLARE AND INPUT THREE SIDES OF THE TRIANGLE
    	System.out.println("Enter side1: "); 
    	double side1 = reader.nextDouble(); 
    	System.out.println("Enter side2: "); 
    	double side2 = reader.nextDouble(); 
    	System.out.println("Enter side3: "); 
    	double side3 = reader.nextDouble(); 
    	
    	// INSTANTITATE TRIANGLELAB3 OBJECT
    	TriangleLab3 triangle = new TriangleLab3 (side1, side2, side3);
    	
    	// FUNCTION CALL TO CALCULATE PERIMETER OF THE TRIANGLE
    	double perimeter = triangle.getPerimeter ();
    	
    	// FUNCTION CALL TO CALCULATE AREA OF THE TRIANGLE
    	double area = triangle.getArea ();
    	
    	reader.close(); 
    		                     
    	// OUTPUT INFORMATION OF THE TRIANGLE	        
   		System.out.println ("\nOUTPUT LAB3");
   		System.out.println ("Side1:     " + side1);
   		System.out.println ("Side2:     " + side2);
   		System.out.println ("Side3:     " + side3);
   		System.out.println ("Perimeter: " + perimeter);
   		System.out.println ("Area:      " + area);
    }
}

// Darian Siembab
// TriangleLab3
// Program accepts lengths (abstract units) of 3 sides of a triangle 
// and calculates and displays the triangle's perimeter and area
// Program also demonstrates function calls and constructor


