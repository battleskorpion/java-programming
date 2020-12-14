/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* OCTOBER 22, 2020									*/
/* PROGRAM 6										*/
/* PROJECT 4-7										*/
/* THIS PROGRAM WILL DISPLAY THE POWERS OF TWO,     */
/* DEPENDING ON THE EXPONENTS THE USER ENTERS.  	*/
/****************************************************/ 

package program_6;

/******************/
/* IMPORT SECTION */ 
/******************/ 
import java.text.NumberFormat;							// FOR FORMATTING NUMBERS IN OUTPUT 
import javax.swing.*;    								// FOR JFRAME, JPANEL, AND JOPTIONPANE (WINDOWS) 

public class PowersOfTwo
{
	public static void main(String[] args) 
	{	
		/******************/ 
		/* PROMPT SECTION */
		/******************/ 
		final String prompt1 = "Enter a positive exponent: "; 
		final String error_prompt1 = "Only POSITIVE exponent values are accepted!"; 
		final String run_prompt = "Would you like to run the program again?"; 
		
		/********************/
		/* VARIABLE SECTION */
		/********************/
		double ans; 									// VALUE OF 2 ^ EXPONENT 
		double exp; 									// POSITIVE EXPONENT VALUE ENTERED
		NumberFormat nf = NumberFormat.getInstance(); 	// FOR FORMATTING NUMBERS IN OUTPUT
		int runProgram; 								// RUN PROGRAM AGAIN: 0 = YES, 1 = NO
		
		/****************************/
	    /* USER DESCRIPTION SECTION */ 
	    /****************************/
		JOptionPane.showMessageDialog(null, "This program will calculate and display positive powers of two, " 
				+ "\ndepending on the exponent which the user enters", "Program Description", JOptionPane.PLAIN_MESSAGE);
		
		/**************************/
		/* RUN PROGRAM AGAIN LOOP */
		/**************************/
		do 
		{
			/*****************/
		    /* INPUT SECTION */ 
		    /*****************/
			
			/******************/ 
			/* INPUT EXPONENT */
			/******************/
			exp = inputDouble(prompt1, error_prompt1, ">="); 
			
			/***********************/		
		    /* CALCULATION SECTION */ 
		    /***********************/
			ans = Math.pow(2, exp); 					// CALCULATES 2 ^ EXPONENT
			
			/******************/ 
		    /* OUTPUT SECTION */
		    /******************/
			nf.setMaximumFractionDigits(4);				// NUMBERS USING FORMAT WILL HAVE MAX OF 4 DECIMAL DIGITS
			
			JOptionPane.showMessageDialog(null,  "The base 2 to the power of " + nf.format(exp) + " equals " + nf.format(ans)); 

			/*********************/
			/* RUN PROGRAM AGAIN */
			/*********************/
			runProgram = JOptionPane.showConfirmDialog(null, run_prompt, "t", JOptionPane.YES_NO_OPTION); 
			// USER SELECTS YES: runProgram = 0
			// USER SELECTS NO:  runProgram = 1
		}
		while (runProgram == 0); 
	}
	
	/******************************************************************************************************/ 
	/*											FUNCTION SECTION 										  */ 
	/******************************************************************************************************/ 
	
	static double inputDouble(String prmpt, String err_prmpt, String op)
	/******************************************************************************************************/
	/* PRECONDITION: DIALOG BOX ASKING USER TO INPUT A FLOATING POINT VALUE OF TYPE DOUBLE IS REQUIRED    */  
	/* POSTCONDITION: THE VALUE INPUTTED BY THE USER IS RETURNED 									      */
	/* 																									  */
	/* op PARAMETER: SIGNIFIES WHETHER THE VALUE THE USER INPUTS HAS TO BE CHECKED TO BE LESS THAN 0 (<), */
	/* LESS THAN OR EQUAL (<=), GREATER THAN (>), GREATER THAN OR EQUAL TO 0 (>=), OR NOT EQUAL TO 0 (!=) */
	/******************************************************************************************************/
	{
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
			
			/************************************************/
	    	/* ERROR MESSAGE IF NUMBER INPUTTED IS IMPROPER */
	    	/************************************************/ 
			if ((val < 0 && op.equals(">=")) || (val <= 0 && op.equals(">")) || 
				(val > 0 && op.equals("<=")) || (val >= 0 && op.equals("<")) || 
				(val == 0 && op.equals("!=")))
			{
				JOptionPane.showMessageDialog(null, err_prmpt, "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		} 
		while ((val < 0 && op.equals(">=")) || (val <= 0 && op.equals(">")) || 
			   (val > 0 && op.equals("<=")) || (val >= 0 && op.equals("<")) || 
			   (val == 0 && op.equals("!="))); 

		return val; 
	}
	
}

