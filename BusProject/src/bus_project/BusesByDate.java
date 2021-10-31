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
import java.time.LocalDate;								// FOR STORING DATES
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import org.eclipse.swt.widgets.Display;				    // FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Shell;                   // FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.DateTime;                // FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.SWT;                             // FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Combo;                   // FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Label;                   // FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Button;                  // FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.events.SelectionAdapter;         // FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.events.SelectionEvent;           // FOR SWT WINDOWS/WIDGETS	
import org.eclipse.wb.swt.SWTResourceManager;           // FOR SWT WINDOWS/WIDGETS	

import bus_project.localization.Messages;

public class BusesByDate extends AbstractBusProgramWindow 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected Shell shlBusesByDate;										// SHELL OF WINDOW
	private ArrayList<Customer> customers; 								// CUSTOMERS LIST
	private ArrayList<LocalDate> dates = BusCalculation.getDates(); 	// TRIP DATES LIST
	
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
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}

	protected void createContents(Shell rootShell) 
	/********************************************************************************/
	/* PRECONDITION:  WINDOW IS TO BE OPENED, ELEMENTS NEED TO BE ADDED TO WINDOW	*/
	/* POSTCONDITION: ADDS ELEMENTS TO WINDOW										*/
	/********************************************************************************/
	{
		shlBusesByDate = new Shell();
		shlBusesByDate.setSize(270, 412);
		shlBusesByDate.setText(messages.getString
				("BusesByDate.shlBusesByDate.text")); 			//$NON-NLS-1$
		
		DateTime dateTime = new DateTime(shlBusesByDate, SWT.BORDER | SWT.CALENDAR);
		
		dateTime.setBounds(10, 10, 233, 151);

		Combo combo = new Combo(shlBusesByDate, SWT.NONE);
		combo.setBounds(146, 167, 97, 23);
		updateComboBox(combo, dates); 
		
		Label lblBuses = new Label(shlBusesByDate, SWT.NONE);
		lblBuses.setBounds(10, 196, 120, 23);
		lblBuses.setText(messages.getString("lblBuses.text")); 							//$NON-NLS-1$
		
		Label lblNumBuses = new Label(shlBusesByDate, SWT.RIGHT);
		lblNumBuses.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNumBuses.setBounds(146, 196, 97, 25);
		
		Button btnExit = new Button(shlBusesByDate, SWT.NONE);
		btnExit.setBounds(168, 338, 75, 25);
		btnExit.setText(messages.getString("btnExit.text")); 							//$NON-NLS-1$
		
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
