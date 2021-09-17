package blackjack;

import javax.swing.JOptionPane;

public class Blackjack 
{
	// variables
	public static BlackjackTable window; 
	public static final String title = "Blackjack";
	public static final int VALUE_LIMIT = 21; 					// 21 is max in Blackjack, over 21 is a bust. 
	public static final int INITIAL_CARDS = 2; 					// number of cards to deal initially
	
	public static void main(String[] args)
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/ 
		int runProgramResponse; 
		
		/****************************/
		/* USER DESCRIPTION SECTION */
		/****************************/
		JOptionPane.showMessageDialog(null, title + "!", title, JOptionPane.PLAIN_MESSAGE);
		
		/******************************************************/
		/* METHOD TO READ PROGRAM SETTINGS FROM SETTINGS FILE */
		/******************************************************/
		//Settings.readSettings(); 
		
		/***************************/
		/* RUN PROGRAM AGAIN LOOP  */
		/***************************/
		/* YES: 	runProgram = 0 */
		/* NO:  	runProgram = 1 */
		/* CANCEL: 	runProgram = 2 */
		/***************************/
		do 
		{
			/***********************/
			/* REOPEN PROGRAM LOOP */
			/***********************/
			runProgram(); 
			runProgramResponse = runProgramPrompt(); 	
			while (runProgramResponse == 2)
			{
				/***************************/
				/* METHOD TO REOPEN WINDOW */
				/***************************/ 
				window.open(); 				
				runProgramResponse = runProgramPrompt(); 
			}
		}
		while (runProgramResponse == 0);
		
		/****************************************************/
		/* METHOD TO SAVE PROGRAM SETTINGS TO SETTINGS FILE */
		/****************************************************/
		//Settings.saveSettings(); 
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
	/************************************************************************************************/ 
	/* PRECONDITION:  PROGRAM NEEDS TO BE RAN  														*/					  
	/* POSTCONDITION: CREATES NEW PROGRAM MENU AND BEGINS PROGRAM									*/
	/************************************************************************************************/	
	{
		/*****************************************/
		/* INSTANTIATE NEW MENU (RESETS PROGRAM) */
		/*****************************************/
		window = new BlackjackTable();
			
		/*************************/
		/* METHOD TO OPEN WINDOW */
		/*************************/
		openWindow(); 
	}
	
	//public static void languageChanged(int index)
	///************************************************************************************************/ 
	///* PRECONDITION:  USER HAS SELECTED A LANGUAGE/LOCALE											*/					  
	///* POSTCONDITION: CHANGES LOCALE TO SELECTED LOCALE AND RESTARTS PROGRAM TO REFLECT CHANGES		*/
	///************************************************************************************************/
	//{
	//	// 0 - en_US
	//	// 1 - es_ES
	//	Messages.setLocale(Messages.programLocales().get(index));	
	//	window.close(); 
	//	runProgram(); 
	//}
	
	public static void openWindow()
	/************************************************************************************************/ 
	/* PRECONDITION:  PROGRAM WINDOW NEEDS TO BE OPENED/REOPENED									*/					  
	/* POSTCONDITION: TRIES TO OPEN WINDOW, DISPLAYS ERROR IF NOT POSSIBLE 							*/
	/************************************************************************************************/
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
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured in main program.");
		}
	}
	
}