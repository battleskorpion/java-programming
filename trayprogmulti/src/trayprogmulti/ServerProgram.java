/****************************************************/ 
/* INFORMATION SECTION 								*/
/* Darian Siembab & Jared Windle					*/
/* etc.												*/ 
/****************************************************/

package trayprogmulti;

import abstractProgram.abstractProgram.*;
import localization.Messages;

public class ServerProgram extends RunProgram {
	
	public ServerProgram() {
		super(new Messages(), false); 
	}
	
	public static void main(String[] args) {
		new ServerProgram().runProgram();
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
		this.mainWindow = new TrayProgMulti(this);
			
		/*************************/
		/* METHOD TO OPEN WINDOW */
		/*************************/
		openWindow(this); 
	}
	
}
