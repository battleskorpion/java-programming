/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* FEBURARY 11, 2021								*/
/* PROGRAM 10										*/
/* PROJECT 10-2										*/
/* THIS PROGRAM WILL CALCULATE THE AVERAGE AND    	*/
/* NUMBERS GREATER THAN THE AVERAGE 				*/
/* FROM A LIST OF INPUTTED NUMBERS		 			*/
/****************************************************/

/******************/
/* IMPORT SECTION */
/******************/
package project_10_2;

import javax.swing.JOptionPane;			// FOR SIMPLE SWING WINDOWS

public class AverageDetailsOfFloatingPoints 
{
	
	public static void main (String args[]) throws InterruptedException 
	{
		/****************************/
		/* USER DESCRIPTION SECTION */
		/****************************/
		JOptionPane.showMessageDialog(null, "This program will calculate the average and numbers"
				+ "\n" + "greater than the average from a list of inputted numbers.");
		
		/**************************/
		/* RUN PROGRAM AGAIN LOOP */
		/**************************/
		do 
		{
			/********************************/
			/* INSTANTIATE AND RUN MAIN GUI */
			/********************************/
			AverageDetailsOfNumbersGUI GUI = new AverageDetailsOfNumbersGUI(10); 
			GUI.open(); 
		}
		while (runProgramPrompt() == true); 
		
	}
	
	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
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

