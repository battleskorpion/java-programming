package minesweeper;

import abstractProgram.abstractProgram.*;
import localization.Messages;

public class MinesweeperProgram extends RunProgram {
		//new HashMap<List<ArrayList<ArrayList<HashMap<LinkedList<T>, T>>>>, HashMap<HashMap<T, ArrayList<T>>, Boolean>>(); 
	public MinesweeperProgram() {
		super(new Messages(), false); 
	}
	
	public static void main(String[] args) {
		new MinesweeperProgram().runProgram();
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
		this.mainWindow = new Minesweeper(this);
			
		/*************************/
		/* METHOD TO OPEN WINDOW */
		/*************************/
		openWindow(this); 
	}
}
