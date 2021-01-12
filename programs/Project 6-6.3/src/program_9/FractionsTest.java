package program_9;

import javax.swing.JOptionPane;

public class FractionsTest {
	
	public static void main(String args[]) { 
		
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Fraction f1;   // FIRST FRACTION, VALUE SET BY USER
		Fraction f2;   // SECOND FRACTION, VALUE SET BY USER
		Fraction fAdd;			// f1 + f2
		Fraction fSubtract;     // f1 - f2
		Fraction fMultiply;		// f1 * f2
		Fraction fDivide;       // f1 % f2
		
		/****************************/
		/* USER DESCRIPTION SECTION */
		/****************************/
		
		/**************************/
		/* RUN PROGRAM AGAIN LOOP */
		/**************************/
		do {
		
			/*****************/
			/* INPUT SECTION */
			/*****************/
			f1 = Fraction.inputFraction("1st"); 
			f2 = Fraction.inputFraction("2nd"); 
			
			/***********************/
			/* CALCULATION SECTION */
			/***********************/
			
			/**************************/
			/* METHOD TO ADD FRACTION */
			/**************************/
			fAdd = f1.add(f2); 
			
			/*********************************/
			/* METHOD TO SUBTRACT f2 FROM f1 */
			/*********************************/
			fSubtract = f1.subtract(f2); 
			
			/********************************/
			/* METHOD TO MULTIPLY FRACTIONS */
			/********************************/
			fMultiply = f1.multiply(f2);
			
			/*****************************/
			/* METHOD TO DIVIDE f1 BY f2 */
			/*****************************/
			fDivide = f1.divide(f2);
		
			/******************/
			/* OUTPUT SECTION */
			/******************/
		
			/*******************************************/
			/* METHOD TO DISPLAY FRACTION CALCULATIONS */
			/*******************************************/
			displayFractionResults(f1, f2, fAdd, fSubtract, fMultiply, fDivide); 
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
	
	static void displayFractionResults(Fraction f1, Fraction f2, Fraction fdd, Fraction fsbtct, Fraction fmltpl, Fraction fdvd) 
	/******************************************************************************************************/
	/* PRECONDITION:   *					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		JOptionPane.showMessageDialog(null, "Fraction 1: " + f1 + "\n" + "Fraction 2: " + f2 + "\n\n"
				+ "Add: " + fdd + "\n" + "Subtract: " + fsbtct + "\n" + "Multiply: " + fmltpl + "\n" 
				+ "Divide: " + fdvd); 
	}
	
}
