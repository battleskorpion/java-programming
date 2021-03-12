package bus_project;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

public class RemoveCustomer extends AbstractProgramWindow{

	protected Shell shlRemoveCustomer;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RemoveCustomer window = new RemoveCustomer();
			window.open();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
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
		shlRemoveCustomer.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlRemoveCustomer.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlRemoveCustomer.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlRemoveCustomer.isDisposed()) 
		{
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlRemoveCustomer = new Shell();
		shlRemoveCustomer.setSize(600, 400);
		shlRemoveCustomer.setText("Remove Customer");
		
		Button btnExit = new Button(shlRemoveCustomer, SWT.NONE);
		btnExit.setText("Exit");
		btnExit.setBounds(499, 326, 75, 25);

	}

}
