package trianglesss; 

/************************************/
/* TESTTRIANGLE5 TO DEMONSTRATE THE */
/* TOSTRING() METHOD                */
/************************************/

import java.util.Scanner;

public class TestTriangle5
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
    	
    	reader.close();
    	
    	// INSTANTITATE TRIANGLELAB4 OBJECT
    	TriangleLab5 triangle = new TriangleLab5 (side1, side2, side3);
    	
    	// OUTPUT INFORMATION OF THE TRIANGLE	        
   		System.out.println ("\n OUTPUT LAB1");
   		System.out.println (triangle);
    }
}

//Darian Siembab
//TestTriangle5
// Program accepts lengths (abstract units) of 3 sides of a triangle 
// and calculates and displays the triangle's perimeter and area
// Program also demonstrates function calls and constructor,  
// as well as demonstrating use of the toString() method