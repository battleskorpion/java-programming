/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* MARCH 7, 2021									*/
/* 										*/
/* 									*/
/* THIS PROGRAM WILL     	*/
/* 				*/
/* 	 			*/
/****************************************************/

/******************/
/* IMPORT SECTION */
/******************/
package bus_project;

import javax.swing.JOptionPane;

public class BusProgram 
{
	public static void main(String[] args) 
	{
		// TODO: label 
		BusProgramMenu window; 
		
		/****************************/
		/* USER DESCRIPTION SECTION */
		/****************************/
		JOptionPane.showMessageDialog(null, "This program will do stuff "
				+ "\n" + "and stuff");
		
		/**************************/
		/* RUN PROGRAM AGAIN LOOP */
		/**************************/
		do 
		{
			// TODO: label 
			window = new BusProgramMenu();
			
			/*****************************/
			/* METHOD TO OPEN GUI WINDOW */
			/*****************************/
			try 
			{
				window.open();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
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
