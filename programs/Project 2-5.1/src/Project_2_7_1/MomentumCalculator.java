/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/ 
/* SEPTEMBER 29, 2020								*/
/* PROGRAM 1										*/
/* PROJECT 2-5										*/
/* THIS PROGRAM WILL TAKE AN OBJECT'S MASS (kg)	    */
/* AND VELOCITY (m/s) AND CALCULATE AND PRINT 		*/
/* ITS' MOMENTUM (kg*m/s)							*/
/****************************************************/ 

package Project_2_7_1;

/******************/
/* IMPORT SECTION */
/******************/
import javax.swing.*;    				// FOR JFRAME, JPANEL, AND JOPTIONPANE (WINDOWS) 

public class MomentumCalculator 
{
	public static void main(String[] args)
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		float mass; 					// MASS OF THE OBJECT
		float velocity;					// VELOCITY OF THE OBJECT
		double momentum;				// MOMENTUM OF THE OBJECT (MASS * VELOCITY) 
		
		/****************************/
	    /* USER DESCRIPTION SECTION */ 
	    /****************************/
	    JOptionPane.showMessageDialog(null, "This program will take an objects mass "
	    	+ "(in kilograms) and velocity (in meters/second)\n"
	    	+ "and calculate and print its' momentum (in kilograms*meters/second.)", 
	    	"Program Description", JOptionPane.PLAIN_MESSAGE);
	    
	    /*****************/
	    /* INPUT SECTION */ 
	    /*****************/
	    	
	    /**************/
	    /* INPUT MASS */
	    /**************/
	    mass = Float.parseFloat(JOptionPane.showInputDialog
	    ("Enter the mass of the object (kg): ", "0")); 	
	    																
	    /******************/
	    /* INPUT VELOCITY */
	    /******************/
	    velocity = Float.parseFloat(JOptionPane.showInputDialog
	    ("Enter the velocity of the object (m/s): ", "0")); 		
	    
	    /***********************/
	    /* CALCULATION SECTION */ 
	    /***********************/
	    momentum = mass * velocity; 	// MOMENTUM OF AN OBJECT = MASS * VELOCITY
	    
	    /******************/ 
	    /* OUTPUT SECTION */
	    /******************/ 
	    JOptionPane.showMessageDialog(null, "Mass: " + mass + "\n" + "Velocity: " + velocity 
	    	+ "\n\n" + "The momentum of the object is " + momentum + " kg*m/s"); 		
	}
}
