/****************************************************/ 
/* INFORMATION SECTION 								*/
/* Darian Siembab									*/
/* October 30, 2021									*/
/* Run Program 										*/
/* 													*/
/* Class for running an extensive program  			*/
/****************************************************/ 

package abstractProgram.abstractProgram;

import abstractProgram.localization.AbstractMessages;
import javax.swing.JOptionPane;

public abstract class RunProgram {
	
	public AbstractProgramWindow mainWindow; 	// program menu window
	private boolean askRunProgram;				// whether or not to ask 
												// whether or not to run the program again 
	private String userDescription; 			// program description displayed to user
	private RunProgram program = this; 
	private AbstractMessages messages; 
	
	/************************************************************************************************/
	/*										CONSTRUCTOR SECTION 									*/
	/************************************************************************************************/
	public RunProgram(AbstractMessages messages) {
		this(messages, false, null);  			// default askRunProgram to false, default no user description
	}
	
	public RunProgram(AbstractMessages messages, boolean askRunProgram) {
		this(messages, askRunProgram, null);  	// default no user description
	}
	
	public RunProgram(AbstractMessages messages, String userDescription) {
		this(messages, false, userDescription);	// default askRunProgram to false
	}

	public RunProgram(AbstractMessages messages, boolean askRunProgram, String userDescription) {
		this.messages = messages; 
		this.askRunProgram = askRunProgram; 
		this.userDescription = userDescription; 
	}
	
	/************************************************************************************************/
	/*											METHOD SECTION 										*/
	/************************************************************************************************/
	
	public void runProgram() {
		/********************/
		/* VARIABLE SECTION */
		/********************/ 
		int runProgramResponse; 
		
		/****************************/
		/* USER DESCRIPTION SECTION */
		/****************************/
		if (program.userDescription != null) {
			JOptionPane.showMessageDialog(null, userDescription); 
		} 
		
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
			startProgram(); 
			if (askRunProgram == true) {
				runProgramResponse = runProgramPrompt(); 	
				/****************************************/
				/* CANCEL CLOSE PROGRAM (REOPEN WINDOW) */ 
				/* IF CANCEL OPTION SELECTED 			*/
				/****************************************/
				while (runProgramResponse == 2)
				{
					/***************************/
					/* METHOD TO REOPEN WINDOW */
					/***************************/ 
					program.mainWindow.open(); 				
					runProgramResponse = runProgramPrompt(); 
				}
			} 
			else {
				runProgramResponse = 1; 
			}
		}
		while (runProgramResponse == 0);
		
		/****************************************************/
		/* METHOD TO SAVE PROGRAM SETTINGS TO SETTINGS FILE */
		/****************************************************/
		//Settings.saveSettings(); 
	}
	
	public static void openWindow(RunProgram program)
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
			program.mainWindow.open();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured in main program.");
		}
	}
	
	public void languageChanged(int index)
	/************************************************************************************************/ 
	/* PRECONDITION:  USER HAS SELECTED A LANGUAGE/LOCALE											*/					  
	/* POSTCONDITION: CHANGES LOCALE TO SELECTED LOCALE AND RESTARTS PROGRAM TO REFLECT CHANGES		*/
	/************************************************************************************************/
	{
		// 0 - en_US
		// 1 - es_ES
		AbstractMessages.setLocale(messages.programLocales().get(index));	
		this.mainWindow.close(); 
		startProgram(); 
	}
	
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
		int runProgramResponse; 			// INDICATES WHETHER USER WISHES TO RUN THE PROGRAM AGAIN
		
		/*****************/
		/* INPUT SECTION */
		/*****************/
		runProgramResponse = JOptionPane.showConfirmDialog(null, "Would you like to run the program again?", 
				"Run Program Again?", JOptionPane.YES_NO_CANCEL_OPTION); 
		// USER SELECTS YES: 	runProgram = 0
		// USER SELECTS NO:  	runProgram = 1
		// USER SELECTS CANCEL: runProgram = 2
		
		/******************************************/
		/* RETURN EITHER TRUE OR FALSE		      */
		/* DEPENDING ON VALUE OF runProgram	INPUT */
		/******************************************/
		return runProgramResponse; 
	}
	
	public abstract void startProgram(); 
	/************************************************************************************************/ 
	/* PRECONDITION:  MAIN PROGRAM NEEDS TO BE RAN (STARTED) 										*/					  
	/* POSTCONDITION: CREATES NEW PROGRAM MENU AND BEGINS PROGRAM									*/
	/************************************************************************************************/	
}
