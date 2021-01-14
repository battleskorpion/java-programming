/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* NOVEMBER 16, 2020								*/
/* PROGRAM 8										*/
/* PROJECT 7-3 										*/
/* THIS PROGRAM WILL CONVERT NUMERIC GRADES TO   	*/
/* LETTER GRADES USING A SPECIFIC CONVERSION CHART	*/
/****************************************************/

/*******************/
/* PACKAGE SECTION */
/*******************/
package letterGradeConverter;

/******************/
/* IMPORT SECTION */
/******************/
import javax.swing.*; 					// FOR JFRAME, JPANEL, AND JOPTIONPANE (WINDOWS)

public class letterGradeConverter 
{
	public static void main (String[] args)
	{ 
		/******************/
		/* PROMPT SECTION */
		/******************/
		final String error_prompt1 = "Must enter a positive integer!"; 
		final String prompt1 = "Enter a numerical grade (must be positive): ";
		
		/********************/
		/* VARIABLE SECTION */
		/********************/
		String letterGrade; 			// CONVERTED NUMERICAL GRADE IN LETTER GRADE FORMAT
		double numericalGrade; 			// USER-ENTERED NUMERICAL GRADE TO BE CONVERTED TO THE EQUIVALENT LETTER GRADE
		boolean runProgram; 			// RUN PROGRAM AGAIN: false = NO, true = YES
		
		/****************************/
		/* USER DESCRIPTION SECTION */
		/****************************/
		JOptionPane.showMessageDialog(null, "This program will convert numeric grades to letter grades.", 
				  "Program Description", JOptionPane.PLAIN_MESSAGE);

		/**************************/
		/* RUN PROGRAM AGAIN LOOP */
		/**************************/
		do 
		{ 
			/*****************/
			/* INPUT SECTION */
			/*****************/
			
			/***********************************/ 
			/* METHOD TO INPUT NUMERICAL GRADE */
			/***********************************/ 
			numericalGrade = inputDouble(prompt1, error_prompt1, ">="); 
			
			/***********************/
			/* CALCULATION SECTION */
			/***********************/
			
			/************************************/
			/* METHOD TO CALCULATE LETTER GRADE */
			/************************************/
			letterGrade = convertGrade(numericalGrade); 
			
			/******************/
			/* OUTPUT SECTION */
			/******************/
			
			/***************************/
			/* METHOD TO DISPLAY GRADE */
			/***************************/ 
			displayGrade(numericalGrade, letterGrade); 
			
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

	static String convertGrade(double grd)
	/******************************************************************************************************/
	/* PRECONDITION:  PROGRAM HAS ACCEPTED A NUMERICAL GRADE TO BE CONVERTED TO A LETTER GRADE			  */					  
	/* POSTCONDITION: THE NUMERICAL GRADE IS CONVERTED TO ITS CORRESPONDING LETTER GRADE				  */
	/******************************************************************************************************/
	{
		/**********************************************************************************/
		/* IF-ELSE-IF STATEMENTS TO DETERMINE LETTER GRADE BASED ON NUMERICAL GRADE VALUE */
		/**********************************************************************************/
		if (grd < 60) 
		{ 
			return "F"; 
		}
		else if (grd >= 60 && grd < 62) 
		{
			return "D-";
		}
		else if (grd >= 62 && grd < 66)
		{
			return "D"; 
		}
		else if (grd >= 66 && grd < 70)
		{
			return "D+";
		}
		else if (grd >= 70 && grd < 72) 
		{
			return "C-"; 
		}
		else if (grd >= 72 && grd < 76)
		{
			return "C"; 
		}
		else if (grd >= 76 && grd < 80)
		{
			return "C+"; 
		}
		else if (grd >= 80 && grd < 82) 
		{
			return "B-"; 
		}
		else if (grd >= 82 && grd < 86)
		{
			return "B"; 
		}
		else if (grd >= 86 && grd < 90)
		{
			return "B+";
		}
		else if (grd >= 90 && grd < 92) 
		{
			return "A-"; 
		}
		else if (grd >= 92 && grd < 96)
		{
			return "A"; 
		}
		else 
		{
			return "A+"; 
		}
	}
	
	static void displayGrade(double nmbrGrd, String lttrGrd) 
	/******************************************************************************************************/
	/* PRECONDITION:  PROGRAM HAS CALCULATED THE CORRESPONDING LETTER GRADE TO A NUMERICAL GRADE VALUE    */					  
	/* POSTCONDITION: DISPLAYS THE NUMERICAL GRADE INPUTTED AND THE CORRESPONDING LETTER GRADE 			  */
	/******************************************************************************************************/
	{
		JOptionPane.showMessageDialog(null, "Numerical Grade: " + nmbrGrd 
				+ "\n" + "Letter Grade: " + lttrGrd); 
	}
	
	static double inputDouble (String prmpt, String err_prmpt, String op)
	/******************************************************************************************************/
	/* PRECONDITION:  PROGRAM REQUIRES USER TO INPUT A FLOATING POINT VALUE OF TYPE DOUBLE WITH SPECIFIC  */
	/* 				  REQUIREMENTS   																	  */			
	/* POSTCONDITION: PROMPTS USER FOR A VALUE MEETING REQUIREMENTS AND RETURNS THE VALUE ONCE IT MEETS   */
	/*                REQUIREMENTS 									     								  */
	/******************************************************************************************************/
	/* op PARAMETER: SIGNIFIES WHETHER THE VALUE THE USER INPUTS HAS TO BE CHECKED TO BE LESS THAN 0 (<), */
	/* LESS THAN OR EQUAL (<=), GREATER THAN (>), GREATER THAN OR EQUAL TO 0 (>=), OR NOT EQUAL TO 0 (!=) */
	/******************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		double val; 					// VALUE INPUTTED BY USER

		/****************/
		/* INPUT NUMBER */
		/****************/
		
		/**************************/
		/* INPUT ERROR CHECK LOOP */
		/**************************/
		do 
		{
			val = Double.parseDouble(JOptionPane.showInputDialog(prmpt));

			/************************************************/
			/* ERROR MESSAGE IF NUMBER INPUTTED IS IMPROPER */
			/************************************************/
			if ((val < 0 && op.equals(">=")) || (val <= 0 && op.equals(">")) 
		 	 || (val > 0 && op.equals("<=")) || (val >= 0 && op.equals("<")) 
			 || (val == 0 && op.equals("!="))) 
			{
				JOptionPane.showMessageDialog(null, err_prmpt, "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		} 
		while ((val < 0 && op.equals(">=")) || (val <= 0 && op.equals(">")) 
			|| (val > 0 && op.equals("<=")) || (val >= 0 && op.equals("<")) 
			|| (val == 0 && op.equals("!=")));

		return val;
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
		int runProgram; 				// INDICATES WHETHER USER WISHES TO RUN THE PROGRAM AGAIN
		
		/*****************/
		/* INPUT SECTION */
		/*****************/
		runProgram = JOptionPane.showConfirmDialog(null, "Would you like to run the program again?", 
				"Run Program Again?", JOptionPane.YES_NO_OPTION); 
		// USER SELECTS YES: runProgram = 0
		// USER SELECTS NO:  runProgram = 1
		
		/******************************************/
		/* RETURN EITHER TRUE OR FALSE		      */
		/* DEPENDING ON VALUE OF runProgram	INPUT */
		/******************************************/
		return runProgram == 0;
	}
}
