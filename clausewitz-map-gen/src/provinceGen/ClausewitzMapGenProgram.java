/****************************************************/ 
/* INFORMATION SECTION 								*/
/* Darian Siembab									*/
/* etc.												*/ 
/****************************************************/

package provinceGen;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import bus_project.BusProgramMenu;
import bus_project.Messages;
import bus_project.Settings;

public class ClausewitzMapGenProgram {

	public static ProvinceGenerationMenu window; 
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO: TODO: TODO: USE RunProgram FROM AbstractProgram
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
		Settings.saveSettings(); 
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
		window = new BusProgramMenu();
			
		/*************************/
		/* METHOD TO OPEN WINDOW */
		/*************************/
		openWindow(); 
	}
	
	public static void languageChanged(int index)
	/************************************************************************************************/ 
	/* PRECONDITION:  USER HAS SELECTED A LANGUAGE/LOCALE											*/					  
	/* POSTCONDITION: CHANGES LOCALE TO SELECTED LOCALE AND RESTARTS PROGRAM TO REFLECT CHANGES		*/
	/************************************************************************************************/
	{
		// 0 - en_US
		// 1 - es_ES
		Messages.setLocale(Messages.programLocales().get(index));	
		window.close(); 
		runProgram(); 
	}
}
