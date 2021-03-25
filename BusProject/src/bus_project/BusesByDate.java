package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class BusesByDate extends AbstractProgramWindow {

	protected Shell shlBusesByDate;
	private ArrayList<Customer> customers; 
	private ArrayList<LocalDate> dates = BusCalculation.getDates(); 
	
	public BusesByDate (ArrayList<Customer> cstmrs) {
		customers = cstmrs; 
	}
	
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open(Shell rootShell)
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
		createContents(rootShell);
		
		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shlBusesByDate.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlBusesByDate.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlBusesByDate.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlBusesByDate.isDisposed()) 
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
	protected void createContents(Shell rootShell) {
		shlBusesByDate = new Shell();
		shlBusesByDate.setSize(270, 270);
		shlBusesByDate.setText("Buses by Date");
		
		DateTime dateTime = new DateTime(shlBusesByDate, SWT.BORDER | SWT.CALENDAR);
		dateTime.setBounds(10, 10, 233, 151);
		
		Combo combo = new Combo(shlBusesByDate, SWT.NONE);
		combo.setBounds(10, 167, 91, 23);
		updateComboBox(combo, dates); 
		
		Label lblBuses = new Label(shlBusesByDate, SWT.NONE);
		lblBuses.setBounds(10, 196, 42, 15);
		lblBuses.setText("Buses: ");
		
		Label lblNumBuses = new Label(shlBusesByDate, SWT.RIGHT);
		lblNumBuses.setBounds(46, 196, 55, 15);
		
		Button btnQuit = new Button(shlBusesByDate, SWT.NONE);
		btnQuit.setBounds(168, 196, 75, 25);
		btnQuit.setText("Exit");

	}
}
