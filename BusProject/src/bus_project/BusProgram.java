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
import abstractProgram.abstractProgram.*;				// FOR AbstractProgram CLASSES 
import bus_project.localization.Messages;

public class BusProgram extends RunProgram {
	
	public BusProgram() {
		super(new Messages(), true, "This program helps schedule customer trips "
				+ "on selected dates, \nand calculate company profit and buses needed.");  
	}
	
	public static void main(String[] args) {
		new BusProgram().runProgram();
	}
	
	/************************************************************************************************/
	/*											METHOD SECTION 										*/
	/************************************************************************************************/
	
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
		this.mainWindow = new BusProgramMenu(this);
			
		/*************************/
		/* METHOD TO OPEN WINDOW */
		/*************************/
		openWindow(this); 
	}

}
