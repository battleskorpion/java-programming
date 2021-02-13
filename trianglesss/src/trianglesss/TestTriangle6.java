package trianglesss; 

/****************************************/
/* TESTTRIANGLE6 TO DEMONSTRATE ALL THE */
/* CONSTRUCTORS OF THE TRIANGLE OBJECT  */
/****************************************/

import java.util.Scanner;

public class TestTriangle6
{
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
    	
    	reader.close(); 
    	
    	System.out.println ("\nOUTPUT LAB6");
    	System.out.println ();
    	
    	// INSTANTIATE TRIANGLELAB6 WITH DEFAULT CONSTRUCTOR
    	TriangleLab6 triangle1 = new TriangleLab6();
    	System.out.println ("DEFAULT TRIANGLE");
    	System.out.println (triangle1);
    	System.out.println();
    	
    	// INSTANTIATE TRIANGLELAB6 WITH INITIALIZING CONSTRUCTOR
    	TriangleLab6 triangle2 = new TriangleLab6 (side1, side2, side3);
    	System.out.println ("INITIALIZED TRIANGLE");
    	System.out.println (triangle2);
    	System.out.println ();
    	
    	
    	// INSTANTIATE TRIANGLELAB6 WITH COPY CONSTRUCTOR
    	TriangleLab6 triangle3 = new TriangleLab6 (triangle2);
    	System.out.println ("COPY TRIANGLE");
    	System.out.println (triangle3);
    }
}

// Darian Siembab
// TestTriangle6
// Program accepts lengths (abstract units) of 3 sides of a triangle 
// and calculates and displays the triangle's perimeter and area
// Program also demonstrates function calls and use of all constructor types,
// as well as demonstrating use of the toString() method
