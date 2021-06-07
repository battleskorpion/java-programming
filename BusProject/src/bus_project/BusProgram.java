/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* MARCH 7, 2021									*/
/* Bus Program										*/
/* 													*/
/* THIS PROGRAM WILL SCHEDULE CUSTOMER TRIPS     	*/
/* ON SELECTED DATES, AND CALCULATE COMPANY PROFIT  */
/* AND BUSES NEEDED	 								*/
/****************************************************/

/******************/
/* IMPORT SECTION */
/******************/
package bus_project;

import javax.swing.JOptionPane;							// FOR JOPTIONPANE DIALOG WINDOWS

public class BusProgram 
{
	public static BusProgramMenu window; 
	
	public static void main(String[] args) 
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/ 
		int runProgramResponse; 
		
		/****************************/
		/* USER DESCRIPTION SECTION */
		/****************************/
		JOptionPane.showMessageDialog(null, "This program will schedule customer trips "
				+ "on selected dates, \nand calculate company profit and buses needed."); 
		
		/**************************/
		/* RUN PROGRAM AGAIN LOOP */
		/**************************/
		do 
		{
			/***********************/
			/* REOPEN PROGRAM LOOP */
			/***********************/
			runProgram(); 
			runProgramResponse = runProgramPrompt(); 
			while (runProgramResponse == 2)
			{
				window.open(); 				// reopen window
				runProgramResponse = runProgramPrompt(); 
			}
		}
		while (runProgramResponse == 0);
	}
	
	/************************************************************************************************/
	/*											METHOD SECTION 										*/
	/************************************************************************************************/
	
	static int runProgramPrompt()  
	/************************************************************************************************/ 
	/* PRECONDITION:  PROGRAM NEEDS TO PROMPT THE USER, IF THE PROGRAM SHOULD BE RUN AGAIN, OR NOT  */					  
	/* POSTCONDITION: USER IS ASKED IF THEY WOULD LIKE TO RUN THE PROGRAM AGAIN (YES OR NO);        */
	/* 				  USER'S INPUT IS RETURNED														*/
	/************************************************************************************************/	
	{ 
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int runProgram; 				// INDICATES WHETHER USER WISHES TO RUN THE PROGRAM AGAIN
		
		/*****************/
		/* INPUT SECTION */
		/*****************/
		runProgram = JOptionPane.showConfirmDialog(null, "Would you like to run the program again?", 
				"Run Program Again?", JOptionPane.YES_NO_CANCEL_OPTION); 
		// USER SELECTS YES: 	runProgram = 0
		// USER SELECTS NO:  	runProgram = 1
		// USER SELECTS CANCEL: runProgram = 2
		
		/******************************************/
		/* RETURN EITHER TRUE OR FALSE		      */
		/* DEPENDING ON VALUE OF runProgram	INPUT */
		/******************************************/
		return runProgram; 
	}
	
	public static void runProgram()
	{
		/*****************************************/
		/* INSTANTIATE NEW MENU (RESETS PROGRAM) */
		/*****************************************/
		window = new BusProgramMenu();
			
		/*************************/
		/* METHOD TO OPEN WINDOW */
		/*************************/
		openWindow(); 
	}
	
	public static void languageChanged(int index)
	{
		// 0 - en_US
		// 1 - es_ES
		Messages.setLocale(Messages.programLocales().get(index));	
		window.close(); 
		runProgram(); 
	}
	
	public static void openWindow ()
	{
		/*****************************/
		/* METHOD TO OPEN GUI WINDOW */
		/*****************************/
		try 
		{
			window.open();
		} 
		catch (Exception e) 
		{
			//TODO: error joptionpane
			e.printStackTrace();
		}
	}

}
