/********************************************/
/* INFORMATION SECTION 						*/
/* BusesByDate.java							*/
/* Darian Siembab 							*/
/* 											*/
/* WINDOW CLASS FOR DISPLAYING				*/
/* THE NUMBER OF BUSES REQUIRED FOR A GIVEN	*/
/* DATE					 					*/
/********************************************/ 
package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
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
import org.eclipse.wb.swt.SWTResourceManager;

public class BusesByDate extends AbstractBusProgramWindow 
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
	
	/**************************
	 * @wbp.parser.entryPoint *
	 **************************/
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
		shlBusesByDate.forceActive();					// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
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
		shlBusesByDate.setSize(270, 412);
		shlBusesByDate.setText(Messages.getString
				("BusesByDate.shlBusesByDate.text")); 			//$NON-NLS-1$
		
		// TODO: label dateTime
		DateTime dateTime = new DateTime(shlBusesByDate, SWT.BORDER | SWT.CALENDAR);
		
		// TODO: label method calls
		dateTime.setBounds(10, 10, 233, 151);

		// TODO: label combo
		Combo combo = new Combo(shlBusesByDate, SWT.NONE);
		// TODO: label method calls
		combo.setBounds(146, 167, 97, 23);
		updateComboBox(combo, dates); 
		
		// TODO: label label
		Label lblBuses = new Label(shlBusesByDate, SWT.NONE);
		// TODO: label method calls
		lblBuses.setBounds(10, 196, 120, 23);
		lblBuses.setText(Messages.getString("lblBuses.text")); 							//$NON-NLS-1$
		
		// TODO: label label
		Label lblNumBuses = new Label(shlBusesByDate, SWT.RIGHT);
		lblNumBuses.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		// TODO: label method calls
		lblNumBuses.setBounds(146, 196, 97, 25);
		
		// TODO: label button
		Button btnExit = new Button(shlBusesByDate, SWT.NONE);
		// TODO: label method calls
		btnExit.setBounds(168, 338, 75, 25);
		btnExit.setText(Messages.getString("btnExit.text")); 							//$NON-NLS-1$
		
		Label lblNumEmptySeats = new Label(shlBusesByDate, SWT.NONE);
		lblNumEmptySeats.setText("Number of seats available on date:");
		lblNumEmptySeats.setBounds(10, 225, 233, 23);
		
		Label lblNumSeatsTotal = new Label(shlBusesByDate, SWT.RIGHT);
		lblNumSeatsTotal.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNumSeatsTotal.setBounds(10, 254, 97, 25);
		
		Label lblNumberOfSeats = new Label(shlBusesByDate, SWT.NONE);
		lblNumberOfSeats.setText("Number of seats available to fill buses: ");
		lblNumberOfSeats.setBounds(10, 285, 233, 23);
		
		Label lblNumSeatsOnUsedBuses = new Label(shlBusesByDate, SWT.RIGHT);
		lblNumSeatsOnUsedBuses.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNumSeatsOnUsedBuses.setBounds(10, 314, 97, 25); 
		
		/* event handlers */ 
		
		dateTime.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				LocalDate date = LocalDate.parse(dateTimeToLocalDateStringFormat
						(dateTime.getDay(), dateTime.getMonth(), 
						dateTime.getYear()));
				 
				if (dates.contains(date)) 
				{
				// update combo box maybe ?
				//	combo.set
					
					lblNumBuses.setText(BusCalculation.getNumBuses(date) + "");
					
					lblNumSeatsTotal.setText("" + (BusCalculation.MAX_PAX - BusCalculation.getNumPaxOnDate(date)));
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
				dateTime.setDate(date.getYear(), date.getMonthValue() - 1, 
						date.getDayOfMonth());
				
				// update number of buses text
				lblNumBuses.setText(BusCalculation.getNumBuses(date) + "");
				
				lblNumSeatsTotal.setText("" + (BusCalculation.MAX_PAX - BusCalculation.getNumPaxOnDate(date))); 
		
			}
		});
			
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			/****************************************************************************************/
			/* PRECONDITION:  SENT WHEN CONTROL IS SELECTED								  			*/
			/* POSTCONDITION: CLOSES THIS WINDOW					 								*/
			/****************************************************************************************/
			{
				closeSubWindow(rootShell, shlBusesByDate); 	
			}
		});
	}
	
	
}
