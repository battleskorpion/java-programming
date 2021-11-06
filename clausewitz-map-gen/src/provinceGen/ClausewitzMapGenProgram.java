/****************************************************/ 
/* INFORMATION SECTION 								*/
/* Darian Siembab									*/
/* etc.												*/ 
/****************************************************/

package provinceGen;

import abstractProgram.abstractProgram.*;				// FOR AbstractProgram CLASSES 
import localization.Messages;

public class ClausewitzMapGenProgram extends RunProgram {
	
	public ClausewitzMapGenProgram() {
		super(new Messages(), false); 
	}
	
	public static void main(String[] args) {
		new ClausewitzMapGenProgram().runProgram(); 
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
		this.mainWindow = new ClausewitzMapGenMenu(this);
			
		/*************************/
		/* METHOD TO OPEN WINDOW */
		/*************************/
		openWindow(this); 
	}
	
}
