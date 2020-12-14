/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* SEPTEMBER 29, 2020								*/
/* PROGRAM 4										*/
/* PROJECT 3-3										*/
/* THIS PROGRAM WILL TAKE AN OBJECT'S MASS (kg)	    */
/* AND VELOCITY (m/s) AND CALCULATE AND PRINT 		*/
/* ITS MOMENTUM (kg*m/s), AS WELL AS ITS KINETIC 	*/
/* ENERGY (KE) IN JOULES							*/
/****************************************************/ 

package objectPhysicsCalculator3_3;
							
import javax.swing.*;    										// FOR JFRAME, JPANEL, AND JOPTIONPANE (WINDOWS)
import java.text.NumberFormat;									// FOR FORMATTING NUMBERS IN OUTPUT

public class ObjectPhysicsCalculator 
{
	public static void main(String[] args)
	{
		/******************/ 
		/* PROMPT SECTION */
		/******************/ 
		String prompt1 = "Enter the mass of the object (kg) (MUST BE > 0): ";
		String prompt2 = "Enter the velocity of the object (m/s) (must be positive): ";
		String error_prompt1 = "Mass of an object must be greater than 0!";
		String error_prompt2 = "Velocity of an object must be positive!";
		
		/********************/
		/* VARIABLE SECTION */
		/********************/
		double kinetic_energy; 									// KINETIC ENERGY OF THE OBJECT (1/2MV^2) 
		double mass; 											// MASS OF THE OBJECT
		double momentum; 										// MOMENTUM OF THE OBJECT (MASS * VELOCITY) 
		NumberFormat nf = NumberFormat.getInstance(); 			// FOR FORMATTING NUMBERS IN OUTPUT
		double velocity; 										// VELOCITY OF THE OBJECT
		
		/****************************/      
	    /* USER DESCRIPTION SECTION */ 
	    /****************************/
	    JOptionPane.showMessageDialog(null, "This program will take an objects mass (in kilograms) and velocity "
	    		+ "(in meters/second)\n and calculate and print its momentum (in kilograms*meters/second.)\n"  
	    		+ "as well as its kinetic energy (KE) in joules", "Program Description", JOptionPane.PLAIN_MESSAGE);
	    
	    /*****************/
	    /* INPUT SECTION */ 
	    /*****************/
	    
	    /**************/
	    /* INPUT MASS */
	    /**************/
	    mass = inputDouble(prompt1, error_prompt1, ">"); 
	    
	    /******************/
	    /* INPUT VELOCITY */
	    /******************/
	    velocity = inputDouble(prompt2, error_prompt2, ">="); 
	    
	    /***********************/
	    /* CALCULATION SECTION */ 
	    /***********************/
	    momentum = mass * velocity; 							// MOMENTUM OF AN OBJECT = MASS * VELOCITY
	    kinetic_energy = 0.5 * mass * (velocity * velocity); 	// KINETIC ENERGY (KE) OF A MOVING OBJECT = (1/2)MV^2
	    
	    /******************/ 
	    /* OUTPUT SECTION */
	    /******************/ 
	    nf.setMaximumFractionDigits(2); 						// SETS NUMBERS FORMATTED WITH nf TO HAVE A MAXIMUM OF 2 DECIMAL DIGITS
	    
	    JOptionPane.showMessageDialog(null,  "Mass: " + nf.format(mass) + "\n" + "Velocity: " + nf.format(velocity) 
		    	+ "\n\n" + "The momentum of the object is " + nf.format(momentum) + " kg*m/s" + "\n" 	
	    		+ "The kinetic energy (KE) of the object is " + nf.format(kinetic_energy) + " J"); 					
	}
	
	/********************/ 
	/* FUNCTION SECTION */ 
	/********************/ 
	
	static double inputDouble(String prmpt, String err_prmpt, String op)
	/***************************************************************************************************/
	/* PRECONDITION: DIALOG BOX ASKING USER TO INPUT A FLOATING POINT VALUE OF TYPE DOUBLE IS REQUIRED */  
	/* POSTCONDITION: THE VALUE INPUTTED BY THE USER IS RETURNED 									   */
	/***************************************************************************************************/
	{
		// op PARAMETER: SIGNIFIES WHETHER THE VALUE THE USER INPUTS HAS TO BE CHECKED TO BE LESS THAN 0 (<),
		// LESS THAN OR EQUAL (<=), GREATER THAN (>), GREATER THAN OR EQUAL TO 0 (>=), OR NOT EQUAL TO 0 (!=)
	
		/********************/
		/* VARIABLE SECTION */
		/********************/ 
		double val; 			// VALUE INPUTTED BY USER
		
		/****************/
		/* INPUT NUMBER */
		/****************/
		do 
		{
			val = Double.parseDouble(JOptionPane.showInputDialog(prmpt)); 
			
			/*****************/
	    	/* ERROR MESSAGE */
	    	/*****************/ 
			if ((val < 0 && op == ">=") || (val <= 0 && op == ">") || (val > 0 && op == "<=") || (val >= 0 && op == "<") || (val == 0 && op == "!="))
			{
				JOptionPane.showMessageDialog(null, err_prmpt, "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		} 
		while ((val < 0 && op == ">=") || (val <= 0 && op == ">") || (val > 0 && op == "<=") || (val >= 0 && op == "<") || (val == 0 && op == "!=")); 

		return val; 
	}
	
}



