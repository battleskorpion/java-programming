package pong;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

public class PongBoard {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		/********************/
		/* VARIABLE SECTION */
		/********************/
		PongBoard window = new PongBoard(); 
		
		/*******************/
		/* OPEN GUI WINDOW */
		/*******************/
		try {
			window.open(); 
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

	/********************************************/
	/* 				METHOD SECTION 				*/ 
	/********************************************/ 
	public void open()
	/****************************************************************/
	/* PRECONDITION:  GUI INSTANCE NEEDS TO BE DISPLAYED            */
	/* POSTCONDITION: CREATES THE GUI DISPLAY AND OPENS THE DISPLAY	*/
	/****************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Display display = Display.getDefault();			// MANAGES THE CONNECTION BETWEEN SWT 
														// AND THE UNDERLYING OPERATING SYSTEM 
		
		/**********************************************/
		/* METHOD TO CREATE CONTENTS OF SHELL/DISPLAY */
		/**********************************************/
		createContents();

		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shell.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shell.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shell.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shell.isDisposed()) 
		{
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}
	
	protected void createContents()
	/****************************************************************/
	/* PRECONDITION:  CONTENTS OF WINDOW NEED TO BE CREATED         */
	/* POSTCONDITION: ADDS CONTENT TO WINDOW 						*/
	/****************************************************************/
	{
		shell = new Shell();
		shell.setSize(900, 750);
		shell.setText("SWT Application");
		shell.setLayout(null);

	}
}
