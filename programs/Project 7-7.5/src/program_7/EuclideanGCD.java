/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* NOVEMBER 10, 2020								*/
/* PROGRAM 7										*/
/* PROJECT 7-5										*/
/* THIS PROGRAM WILL DISPLAY THE GREATEST COMMON    */
/* DIVISOR (GCD) OF TWO POSITIVE INTEGERS.			*/
/****************************************************/ 

package program_7;

/******************/
/* IMPORT SECTION */ 
/******************/ 
import javax.swing.*;    				// FOR JFRAME, JPANEL, AND JOPTIONPANE (WINDOWS) 

public class EuclideanGCD 
{
	public static void main (String[] args) 
	{ 
		/******************/
		/* PROMPT SECTION */
		/******************/
		final String error_prompt1 = "Enter another positive integer: "; 
		final String prompt1 = "Enter a positive integer: "; 
		
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int gcd; 						// GREATEST COMMON DIVISOR OF int1 AND int2
		int num1; 						// FIRST POSITIVE INTEGER INPUTTED
		int num2; 						// SECOND POSITIVE INTEGER INPUTTED
		boolean runProgram; 			// RUN PROGRAM AGAIN: false = no, true = yes
		
		/****************************/
		/* USER DESCRIPTION SECTION */
		/****************************/
		JOptionPane.showMessageDialog(null, "This program will display the greatest common divisor (GCD)"
				+ "\nof two positive integers, through the use of the Ecluidean algorithm.", 
				  "Program Description", JOptionPane.PLAIN_MESSAGE);
		
		/**************************/
		/* RUN PROGRAM AGAIN LOOP */
		/**************************/
		do 
		{ 
			/*****************/
			/* INPUT SECTION */
			/*****************/
			
			/****************************/
			/* METHOD TO INPUT INTEGERS */
			/****************************/ 
			num1 = inputInt(prompt1, error_prompt1, ">="); 
			num2 = inputInt(prompt1, error_prompt1, ">="); 
			
			/***********************/
			/* CALCULATION SECTION */
			/***********************/
			
			/***************************/
			/* METHOD TO CALCULATE GCD */
			/***************************/
			gcd = calculateGCD(num1, num2); 
			
			/******************/
			/* OUTPUT SECTION */
			/******************/
			
			/*************************/
			/* METHOD TO DISPLAY GCD */
			/*************************/
			outputGCD(num1, num2, gcd); 
			
			/***************************************/
			/* METHOD FOR RUN PROGRAM AGAIN PROMPT */
			/***************************************/
			runProgram = runProgramPrompt(); 
		}
		while (runProgram == true);
	}
	
	/******************************************************************************************************/ 
	/*											 METHOD SECTION 										  */ 
	/******************************************************************************************************/ 
	
	static int inputInt(String prmpt, String err_prmpt, String op)
	/******************************************************************************************************/
	/* PRECONDITION: NUMERICAL INPUT OF AN INTEGER IS REQUIRED    										  */  
	/* POSTCONDITION: THE VALUE INPUTTED BY THE USER IS RETURNED WHEN IT IS CORRECT						  */
	/* 																									  */
	/* op PARAMETER: SIGNIFIES WHETHER THE VALUE THE USER INPUTS HAS TO BE CHECKED TO BE LESS THAN 0 (<), */
	/* LESS THAN OR EQUAL (<=), GREATER THAN (>), GREATER THAN OR EQUAL TO 0 (>=), OR NOT EQUAL TO 0 (!=) */
	/******************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/ 
		int val; 					// VALUE INPUTTED BY USER
		
		/****************/
		/* INPUT NUMBER */
		/****************/
		do 
		{
			val = Integer.parseInt(JOptionPane.showInputDialog(prmpt)); 
			
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
	
	static int calculateGCD(int n1, int n2)  
	/******************************************************************************************************/ 
	/* PRECONDITION:  PROGRAM REQURIES THE GREATEST COMMON DIVISOR TO BE CALCULATED OF TWO POSITIVE 	  */ 
	/* 				  INTEGERS  												   						  */					  
	/* POSTCONDITION: GREATEST COMMON DIVISOR OF TWO POSITIVE INTEGERS IS CALCULATED USING ECLUIDEAN 	  */
	/* 				  ALGORITHM												   				  		  	  */
	/******************************************************************************************************/ 
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int remainder; 						// remainder of larger / smaller (larger % smaller) 
		int larger = Math.max(n1, n2); 		// larger value of n1 and n2 
		int smaller = Math.min(n1, n2); 	// smaller value of n1 and n2
		
		/****************************/ 
		/* EUCLIDEAN ALGORITHM LOOP */
		/****************************/ 
		do 
		{ 
			/***************************************************************************************/ 
			/* remainder IS SET EQUAL TO THE REMAINDER OF THE LARGER VALUE DIVIDED BY THE SMALLER, */
			/* THEN THE larger VALUE IS REPLACED BY THE PREVIOUS smaller VALUE, 				   */
			/* THE smaller VALUE IS THEN REPLACED BY THE PREVIOUS CALCULATED remainder VALUE,      */
			/* AND THE STEPS ARE REPEATED UNTIL remainder IS EQUAL TO 0.						   */	
			/* THE FINAL VALUE OF larger WILL THEN BE THE GCD OF n1 and n2, 					   */
			/***************************************************************************************/
			remainder = larger % smaller; 
			
			larger = smaller; 
			smaller = remainder; 
		}
		while (remainder != 0); 
		
		/********************************************************************************************/ 
		/* THE VALUE OF larger WHEN THE ALGORITHM IS COMPLETED IS THE GCD, SO ITS VALUE IS RETURNED */
		/********************************************************************************************/ 
		return larger; 
	}
	
	
	static void outputGCD(int n1, int n2, int gcd) 
	/******************************************************************************************************/ 
	/* PRECONDITION:  PROGRAM HAS CALCULATED THE GCD OF TWO NUMBERS AND NEEDS TO DISPLAY RESULTS 		  */					  
	/* POSTCONDITION: THE GCD IS DISPLAYED ALONG WITH THE TWO NUMBERS WHICH THE GCD IS OF 			      */
	/******************************************************************************************************/
	{ 
		/******************/
		/* OUTPUT SECTION */
		/******************/
		JOptionPane.showMessageDialog(null,  "The greatest common divisor of " + n1 + " and " + n2 + " is " 
				+ gcd);
	}
	
	
	static boolean runProgramPrompt()  
	/******************************************************************************************************/ 
	/* PRECONDITION:  PROGRAM NEEDS TO PROMPT THE USER, IF THE PROGRAM SHOULD BE RUN AGAIN, OR NOT   	  */					  
	/* POSTCONDITION: USER IS ASKED IF THEY WOULD LIKE TO RUN THE PROGRAM AGAIN (YES OR NO);              */
	/* 				  USER'S INPUT IS RETURNED															  */
	/******************************************************************************************************/	
	{ 
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int runProgram; 	// INDICATES WHETHER USER WISHES TO RUN THE PROGRAM AGAIN
		
		/*****************/
		/* INPUT SECTION */
		/*****************/
		runProgram = JOptionPane.showConfirmDialog(null, "Would you like to run the program again?", "t", 
				JOptionPane.YES_NO_OPTION); 
		// USER SELECTS YES: runProgram = 0
		// USER SELECTS NO:  runProgram = 1
		
		/***********************************************/ 
		/* IF STATEMENT TO RETURN EITHER TRUE OR FALSE */
		/* DEPENDING ON VALUE OF runProgram	INPUT 	   */
		/***********************************************/ 
		if (runProgram == 0) 
		{ 
			return true; 
			
		}
		else 
		{
			return false; 
		}
	}
}
