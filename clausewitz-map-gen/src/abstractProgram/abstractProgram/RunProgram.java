/****************************************************/ 
/* INFORMATION SECTION 								*/
/* Darian Siembab									*/
/* October 30, 2021									*/
/* Run Program 										*/
/* 													*/
/* Class for running an extensive program  			*/
/****************************************************/ 

package abstractProgram.abstractProgram;

import javax.swing.JOptionPane;

import bus_project.localization.Messages;

public abstract class RunProgram {
	
	public AbstractProgramWindow window; 	// program menu window
	private boolean askRunProgram;			// whether or not to ask 
											// whether or not to run the program again 
	private String userDescription; 		// program description displayed to user
	private RunProgram program = this; 
	protected Messages messages; 

	public RunProgram() {
		this(false, null);  			// default askRunProgram to false, default no user description
	}
	
	public RunProgram(boolean askRunProgram) {
		this(askRunProgram, null);  	// default no user description
	}
	
	public RunProgram(String userDescription) {
		this(false, userDescription);	// default askRunProgram to false
	}

	public RunProgram(boolean askRunProgram, String userDescription) {
		this.askRunProgram = askRunProgram; 
		this.userDescription = userDescription; 
		messages = new Messages();
	}
	
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
					program.window.open(); 				
					runProgramResponse = runProgramPrompt(); 
				}
			} 
			else {
				runProgramResponse = 0; 
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
	
	public void languageChanged(int index)
	/************************************************************************************************/ 
	/* PRECONDITION:  USER HAS SELECTED A LANGUAGE/LOCALE											*/					  
	/* POSTCONDITION: CHANGES LOCALE TO SELECTED LOCALE AND RESTARTS PROGRAM TO REFLECT CHANGES		*/
	/************************************************************************************************/
	{
		// 0 - en_US
		// 1 - es_ES
		messages.setLocale(messages.programLocales().get(index));	
		this.window.close(); 
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
