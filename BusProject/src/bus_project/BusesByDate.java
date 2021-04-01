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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BusesByDate extends AbstractProgramWindow 
{

	// TODO: instance var section, label vars
	protected Shell shlBusesByDate;
	private ArrayList<Customer> customers; 
	private ArrayList<LocalDate> dates = BusCalculation.getDates(); 
	
	// TODO: constructor section
	public BusesByDate (ArrayList<Customer> cstmrs) 
	{
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
		Display display = Display.getDefault();					// MANAGES THE CONNECTION BETWEEN SWT 
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
			// TODO: 
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Shell rootShell) 
	{
		// TODO: label method calls
		shlBusesByDate = new Shell();
		shlBusesByDate.setSize(270, 270);
		shlBusesByDate.setText("Buses by Date");
		
		// TODO: label dateTime
		DateTime dateTime = new DateTime(shlBusesByDate, SWT.BORDER | SWT.CALENDAR);
		
		// TODO: label method calls
		dateTime.setBounds(10, 10, 233, 151);

		// TODO: label combo
		Combo combo = new Combo(shlBusesByDate, SWT.NONE);
		
		// TODO: label method calls
		combo.setBounds(10, 167, 91, 23);
		updateComboBox(combo, dates); 
		
		// TODO: label label
		Label lblBuses = new Label(shlBusesByDate, SWT.NONE);
		// TODO: label method calls
		lblBuses.setBounds(10, 196, 42, 15);
		lblBuses.setText("Buses: ");
		
		// TODO: label label
		Label lblNumBuses = new Label(shlBusesByDate, SWT.RIGHT);
		// TODO: label method calls
		lblNumBuses.setBounds(46, 196, 55, 15);
		
		// TODO: label button
		Button btnQuit = new Button(shlBusesByDate, SWT.NONE);
		// TODO: label method calls
		btnQuit.setBounds(168, 196, 75, 25);
		btnQuit.setText("Exit");

		/* event handlers */ 
		
		dateTime.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				LocalDate date = LocalDate.parse(StringToLocalDateFormat(dateTime.getDay(), dateTime.getMonth(), dateTime.getYear()));
				 
				if (dates.contains(date)) 
				{
				// update combo box maybe ?
				//	combo.set
					
					lblBuses.setText(BusCalculation.getNumBuses(date) + "");
				}
			}
		});
		combo.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				int index = combo.getSelectionIndex(); 
				LocalDate date = dates.get(index); 
				
				// move calendar to date 
				dateTime.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
				
				// update number of buses text
				lblBuses.setText(BusCalculation.getNumBuses(date) + "");
		
			}
		});
	}
	
	
}
