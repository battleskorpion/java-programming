package trianglesss; 


/************************************/
/* TESTTRIANGLELAB4 TO TEST CLASSES */
/* AND METHODS IN DIFFERENT FILES   */
/************************************/

import java.util.Scanner;

public class TestTriangle4
{
	public static void main(String[] args) 
    {
		// INSTANTIATE OBJECT TO INPUT AND OUTPUT
    	Scanner reader = new Scanner(System.in);
    	
    	// DECLARE AND INPUT THREE SIDES OF THE TRIANGLE
    	System.out.println("Enter side1: "); 
    	double side1 = reader.nextDouble(); 
    	System.out.println("Enter side2: "); 
    	double side2 = reader.nextDouble(); 
    	System.out.println("Enter side3: "); 
    	double side3 = reader.nextDouble(); 
    	
    	// INSTANTITATE TRIANGLELAB4 OBJECT
    	TriangleLab4 triangle = new TriangleLab4 (side1, side2, side3);
    	
    	reader.close(); 
    		                     
    	// OUTPUT INFORMATION OF THE TRIANGLE	        
   		System.out.println ("\n OUTPUT LAB1");
   		System.out.println ("Side1:     " + side1);
   		System.out.println ("Side2:     " + side2);
   		System.out.println ("Side3:     " + side3);
   		System.out.println ("Perimeter: " + triangle.getPerimeter());
   		System.out.println ("Area:      " + triangle.getArea());
    }
}

// Darian Siembab
// TestTriangle4
// Program accepts lengths (abstract units) of 3 sides of a triangle 
// and calculates and displays the triangle's perimeter and area
// Program also demonstrates function calls and constructor, but in a separate class/file