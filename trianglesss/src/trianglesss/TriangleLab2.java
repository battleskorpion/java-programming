package trianglesss; 

/*********************************************/
/* TRIANGLELAB2 TO DEMONSTRATE USING METHODS */
/*********************************************/

import java.util.*; 

public class TriangleLab2 
{
	public static double getPerimeter (double s1, double s2, double s3)
	/*************************************************************/
	/* precondition:  Program has three sides of the triangle    */
	/* postcondition: Function returns perimeter of the triangle */
	/*************************************************************/
	{
		return s1 + s2 + s3;
	}
	
	public static double getArea (double s1, double s2, double s3)
	/***********************************************************/
	/* precondiiton:   Program has three sides of the triangle */
	/* postcondition:  Function returns area of the triangle   */
	/***********************************************************/
	{
		// GET PERIMETER OF THE TRIANGLE
		double per = getPerimeter (s1, s2, s3);
		
		return Math.sqrt ((per/2) * 
			              (per/2 - s1) *
			              (per/2 - s2) *
			              (per/2 - s3));
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
    	
    	// FUNCTION CALL TO CALCULATE PERIMETER OF THE TRIANGLE
    	double perimeter = getPerimeter (side1, side2, side3);
    	
    	// FUNCTION CALL TO CALCULATE AREA OF THE TRIANGLE
    	double area = getArea (side1, side2, side3);
    	
    	reader.close(); 
    		                     
    	// OUTPUT INFORMATION OF THE TRIANGLE	        
   		System.out.println ("\nOUTPUT LAB2");
   		System.out.println ("Side1:     " + side1);
   		System.out.println ("Side2:     " + side2);
   		System.out.println ("Side3:     " + side3);
   		System.out.println ("Perimeter: " + perimeter);
   		System.out.println ("Area:      " + area);
    }
}

// Darian Siembab
// TriangleLab2
// Program accepts lengths (abstract units) of 3 sides of a triangle 
// and calculates and displays the triangle's perimeter and area
// Program also demonstrates function calls 

