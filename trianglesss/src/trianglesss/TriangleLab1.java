package trianglesss; 

/************************************************/ 
/* TRIANGLELAB1 TO DEMONSTRATE SIMPLE EQUATIONS */
/************************************************/

import java.util.*; 

public class TriangleLab1 
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
    	
    	// CALCULATE PERIMETER OF THE TRIANGLE
    	double perimeter = side1 + side2 + side3;
    	
    	// CALCULATE AREA OF THE TRIANGLE
    	double area = Math.sqrt ((perimeter/2)* 
    		                     (perimeter/2-side1) *
    		                     (perimeter/2-side2) *
    		                     (perimeter/2-side3));               
    		                   
    	// OUTPUT INFORMATION OF THE TRIANGLE	        
   		System.out.println ("\nOUTPUT LAB1");
   		System.out.println ("Side1:     " + side1);
   		System.out.println ("Side2:     " + side2);
   		System.out.println ("Side3:     " + side3);
   		System.out.println ("Perimeter: " + perimeter);
   		System.out.println ("Area:      " + area);
    }
}

// Darian Siembab
// TriangleLab1
// Program accepts lengths (abstract units) of 3 sides of a triangle 
// and calculates and displays the triangle's perimeter and area

