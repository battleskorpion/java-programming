/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/ 
/* SEPTEMBER 29, 2020								*/
/* PROGRAM 3										*/
/* PROJECT 3-2  									*/
/* THIS PROGRAM WILL TAKE THE RADIUS OF A SPHERE	*/
/* AND CALCULATE AND PRINT THE SPHERE'S DIAMETER,	*/
/* CIRCUMFERENCE, SURFACE AREA, AND VOLUME			*/
/****************************************************/ 
package Project3_2;

/******************/
/* IMPORT SECTION */
/******************/
import javax.swing.*;    					// FOR JFRAME, JPANEL, AND JOPTIONPANE (WINDOWS)

public class SphereDimensionsCalculator 
{
	/*********************/
	/* CONSTANTS SECTION */ 
	/*********************/
	static final double π = 3.14;			// TO REPRESENT THE CONSTANT PI (π) IN CALCULATIONS
	
	public static void main(String[] args) 
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		double circumference; 				// CIRCUMFERENCE OF SPHERE
		double diameter; 					// DIAMETER OF SPHERE
		double radius; 						// RADIUS OF SPHERE 
		double surface_area; 				// SURFACE AREA (SA) OF SPHERE
		double volume; 						// VOLUME OF SPHERE
		
		/****************************/
	    /* USER DESCRIPTION SECTION */ 
	    /****************************/
	    JOptionPane.showMessageDialog(null, "This program will take the radius of a sphere and calculate\n"
	    		+ "the sphere's diameter, circumference, surface area, and volume.", "Program Description", 
	    		JOptionPane.PLAIN_MESSAGE);

	    /*****************/
	    /* INPUT SECTION */ 
	    /*****************/
	    
	    /****************/
	    /* INPUT RADIUS */ 
	    /****************/
	    do 
	    { 
	    	radius = Double.parseDouble(JOptionPane.showInputDialog("Enter the radius of the sphere: (must be positive!)")); 
	    }
	    while (radius < 0); 
	    
	    /***********************/
	    /* CALCULATION SECTION */ 
	    /***********************/
	    diameter = radius * 2; 																// DIAMETER OF SPHERE = 2r
	    circumference = diameter * π; 														// CIRCUMFERENCE OF SPHERE = DIAMETER (OR 2r) * π
	    surface_area = radius * radius * π * 4; 											// SURFACE AREA OF SPHERE = π * r^2 * 4 
	    volume = radius * radius * radius * π * (4/3); 										// VOLUME OF SPHERE = (4/3) * π * r^3
	    
	    /******************/ 
	    /* OUTPUT SECTION */
	    /******************/
	    JOptionPane.showMessageDialog(null, "Sphere Dimensions: \n\n" 						// PRINTS THE CALCULATED DIMENSIONS OF THE SPHERE:
	    		+ "Radius: " + radius + "\n"												// RADIUS, 
	    		+ "Diameter: " + diameter + "\n" + "Circumference: " + circumference 		// DIAMETER, CIRCUMFERENCE, 
	    		+ "\n" + "Surface Area: " + surface_area + "\n" + "Volume: " + volume); 	// SURFACE AREA, AND VOLUME
	}
}
