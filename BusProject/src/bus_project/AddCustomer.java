package bus_project;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import javax.swing.JFrame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;

public class AddCustomer extends AbstractProgramWindow {

	protected Shell shlAddCustomer;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddCustomer window = new AddCustomer();
			window.open();
		} 
		catch (Exception e) 
		{
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
		shlAddCustomer.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlAddCustomer.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlAddCustomer.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlAddCustomer.isDisposed()) 
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
		
		shlAddCustomer = new Shell();
		shlAddCustomer.setSize(600, 400);
		shlAddCustomer.setText("Add Customer");
		
		Button btnExit = new Button(shlAddCustomer, SWT.NONE);
		btnExit.setBounds(499, 326, 75, 25);
		btnExit.setText("Exit");
		btnExit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				shlAddCustomer.close(); 
			}
		});
	}
}
