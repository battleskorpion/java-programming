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

/*******************/
/* PACKAGE SECTION */
/*******************/
package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import javax.swing.JOptionPane;							// FOR JOptionPane DIALOG WINDOWS
import abstractProgram.abstractProgram.*;				// FOR AbstractProgram CLASSES 

public class BusProgram extends RunProgram
{
	public BusProgramMenu window; 
	
	public BusProgram() {
		super(true, "This program helps schedule customer trips "
				+ "on selected dates, \nand calculate company profit and buses needed."); 
	}
	
	public static void main(String[] args) 
	{
		new BusProgram().runProgram();
	}
	
	/************************************************************************************************/
	/*											METHOD SECTION 										*/
	/************************************************************************************************/
	
	public static void openWindow(BusProgram busProgram)
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
			busProgram.window.open();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured in main program.");
		}
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
	
	@Override
	public void startProgram()
	/************************************************************************************************/ 
	/* PRECONDITION:  PROGRAM NEEDS TO BE RAN  														*/					  
	/* POSTCONDITION: CREATES NEW PROGRAM MENU AND BEGINS PROGRAM									*/
	/************************************************************************************************/	
	{
		/*****************************************/
		/* INSTANTIATE NEW MENU (RESETS PROGRAM) */
		/*****************************************/
		this.window = new BusProgramMenu(this);
			
		/*************************/
		/* METHOD TO OPEN WINDOW */
		/*************************/
		openWindow(this); 
	}

}
