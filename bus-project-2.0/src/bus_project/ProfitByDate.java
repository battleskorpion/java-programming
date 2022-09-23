/********************************************/
/* INFORMATION SECTION 						*/
/* ProfitByDate.java						*/
/* Darian Siembab 							*/
/* 											*/
/* WINDOW CLASS TO DISPLAY COMPANY PROFIT 	*/
/* ON A SPECIFIED DATE						*/
/********************************************/ 
package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import java.time.LocalDate;								// FOR STORING DATES
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import java.util.function.Function;						// REPRESENTS A FUNCTION THAT ACCEPTS ONE 
														// ARGUMENT AND PRODUCES A RESULT.
import org.eclipse.swt.SWT;								// THIS CLASS PROVIDES ACCESS TO A SMALL 
														// NUMBER OF SWT SYSTEM-WIDE METHODS, AND
														// IN ADDITION DEFINES THE PUBLIC CONSTANTS 
														// PROVIDED BY SWT. 
import org.eclipse.swt.events.SelectionAdapter;			// THIS ADAPTER CLASS PROVIDES DEFAULT 
														// IMPLEMENTATIONS FOR THE METHODS DESCRIBED
														// BY THE SELECTIONLISTENER INTERFACE. 
import org.eclipse.swt.events.SelectionEvent;			// SELECTION EVENTS ARE SENT AS A RESULT OF 
														// WIDGETS BEING SELECTED. 
import org.eclipse.swt.widgets.Button;					// REPRESENTS A SELECTABLE USER INTERFACE 
														// OBJECT THAT ISSUES NOTIFICATION WHEN 
														// PRESSED AND RELEASED. 
import org.eclipse.swt.widgets.Combo;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.DateTime;				// FOR SWT WINDOWS/WIDGETS (CALENDAR AND 
														// DATE/TIME SELECTION)
import org.eclipse.swt.widgets.Display;					// RESPONSIBLE FOR MANAGING THE CONNECTION 
														// BETWEEN SWT AND THE UNDERLYING OPERATING
														// SYSTEM.
import org.eclipse.swt.widgets.Label;					// REPRESENTS A NON-SELECTABLE USER INTERFACE 
														// OBJECT THAT DISPLAYS A STRING OR IMAGE 
														// (OR HORIZONTAL/VERTICAL LINE).
import org.eclipse.swt.widgets.Shell;					// REPRESENTS THE "WINDOWS" WHICH THE DESKTOP
														// OR "WINDOW MANAGER" IS MANAGING. 
import org.eclipse.wb.swt.SWTResourceManager;			// FOR SWT WINDOWS/WIDGETS	

import bus_project.localization.Messages;

import org.eclipse.swt.widgets.Table;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.TableColumn;				// FOR SWT WINDOWS/WIDGETS	

public class ProfitByDate extends AbstractBusProgramWindow
{

	/********************/
	/* VARAIBLE SECTION */
	/********************/ 
	protected Shell shlProfitByDate;					// SHELL WHICH REPRESENTS THIS WINDOW
	private ArrayList<Customer> customers; 				// LIST OF CUSTOMERS
	private ArrayList<Customer> customersSorted;		// SORTED LIST OF CUSTOMERS 
														// BY sortBY FILTER
	private ArrayList<LocalDate> dates; 	 			// LIST OF DATES OF TRIPS
	private Table customersTable;						// TABLE OF CUSTOMERS
	private int sortBy = 0; 							// sort by filter
														// srtBy = 0, sort by date increasing
														// srtBy = 1, sort by date decreasing
														// srtBy = 2 sort by profit increasing
														// srtBy = 3 sort by profit decreasing
	
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	public ProfitByDate (ArrayList<Customer> cstmrs) 
	{
		customers = cstmrs; 
		dates = BusCalculation.getDates(); 	
	}
	
	/****************************
	 * @wbp.parser.entryPoint	*
	 ****************************/
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
		shlProfitByDate.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlProfitByDate.forceActive();							// SO WINDOW WILL BE FOCUSED 
																// WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlProfitByDate.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlProfitByDate.isDisposed()) 
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
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Function<Customer, String> customerProfit; 		// REPRESENTS A FUNCTION WHICH RETURNS 
														// PROFIT FROM A CUSTOMER 
		
		shlProfitByDate = new Shell();
		shlProfitByDate.setSize(880, 330);
		shlProfitByDate.setText(Messages.getString
				("ProfitByDate.shlProfitByDate.text")); 								//$NON-NLS-1$
		
		DateTime dateTime = new DateTime(shlProfitByDate, SWT.BORDER | SWT.CALENDAR);
		dateTime.setBounds(10, 10, 233, 151);

		Combo comboDatesList = new Combo(shlProfitByDate, SWT.NONE);
		comboDatesList.setBounds(10, 167, 91, 23);
		
		Label lblProfit = new Label(shlProfitByDate, SWT.NONE);
		lblProfit.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 10, SWT.NORMAL));
		lblProfit.setBounds(10, 206, 91, 25);
		lblProfit.setText(Messages.getString("lblProfit.text")); 						//$NON-NLS-1$
		
		Label lblAmtProfit = new Label(shlProfitByDate, SWT.RIGHT);
		lblAmtProfit.setAlignment(SWT.LEFT);
		lblAmtProfit.setText(Messages.getString("ProfitByDate.lblAmtProfit.text")); 	//$NON-NLS-1$
		lblAmtProfit.setFont(SWTResourceManager.getFont("Segoe UI Light", 12, SWT.NORMAL));
		lblAmtProfit.setBounds(107, 203, 136, 25);
		
		Button btnExit = new Button(shlProfitByDate, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				closeSubWindow(rootShell, shlProfitByDate); 
			}
		});
		btnExit.setBounds(10, 258, 75, 25);
		btnExit.setText(Messages.getString("btnExit.text"));
		
		Label lblNewLabel = new Label(shlProfitByDate, SWT.CENTER);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(258, 10, 507, 15);
		lblNewLabel.setText(Messages.getString("ProfitByDate.lblTable.text"));			//$NON-NLS-1$
		
		Label label = new Label(shlProfitByDate, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 198, 233, 2);
		
		customersTable = new Table(shlProfitByDate, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText("!AddCustomer.customersTable.toolTipText!");
		customersTable.setLinesVisible(true);
		customersTable.setHeaderVisible(true);
		customersTable.setBounds(249, 31, 605, 252);
		
		TableColumn tblclmnID = new TableColumn(customersTable, SWT.NONE);
		tblclmnID.setWidth(60);
		tblclmnID.setText("ID");
		
		TableColumn tblclmnName = new TableColumn(customersTable, SWT.NONE);
		tblclmnName.setWidth(160);
		tblclmnName.setText("Name");
		
		TableColumn tblclmnDate = new TableColumn(customersTable, SWT.NONE);
		tblclmnDate.setWidth(100);
		tblclmnDate.setText("Date");
		
		TableColumn tblclmnSize = new TableColumn(customersTable, SWT.NONE);
		tblclmnSize.setWidth(100);
		tblclmnSize.setText("Group Size");
		
		TableColumn tblclmnRefunds = new TableColumn(customersTable, SWT.NONE);
		tblclmnRefunds.setWidth(80);
		tblclmnRefunds.setText("Refunds");
		
		TableColumn tblclmnProfit = new TableColumn(customersTable, SWT.NONE);
		tblclmnProfit.setWidth(100);
		tblclmnProfit.setText(Messages.getString("tblclmnProfit.text")); 				//$NON-NLS-1$
		tblclmnProfit.setAlignment(SWT.RIGHT); 

		sortCustomers(); 
	
		customerProfit = Customer::getTotalPriceFormatted;
		updateComboBox(comboDatesList, dates); 
		updateTable(customersTable, customersSorted, customerProfit); 
		
		/* event handlers */ 
		dateTime.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				LocalDate date = LocalDate.parse(dateTimeToLocalDateStringFormat(dateTime.getDay(), 
						dateTime.getMonth(), dateTime.getYear()));
			
				lblAmtProfit.setText(BusFinances.currency.getSymbol(Messages.getLocale())
						+ BusFinances.getProfitOnDate(date)); 
			}
		});
		comboDatesList.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				int index = comboDatesList.getSelectionIndex(); 
				LocalDate date = dates.get(index); 
				
				// move calendar to date 
				dateTime.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
				
				// update number of buses text
				lblAmtProfit.setText(BusFinances.currency.getSymbol(Messages.getLocale())
						+ BusFinances.getProfitOnDate(date)); 
		
			}
		});
	}
	
	@SuppressWarnings("unchecked")		// TO SUPRESS WARNING ABOUT "TYPE SAFETY: UNCHECKED CAST...."
	private void sortCustomers() 
	/************************************************************************************************/
	/* PRECONDITION:  CUSTOMERS NEEDS TO BE SORTED													*/
	/* POSTCONDITION: SORTS CUSTOMERS BY SORT BY FILTER INTO SORTED CUSTOMERS LIST					*/
	/************************************************************************************************/
	{
		customersSorted =  (ArrayList<Customer>)customers.clone(); 
			
		switch (sortBy) 
		{
			case 0: 
				customersSorted.sort(new Customer.CompareDate());
				break; 
			case 1: 
				//customersSorted.sort(new Customer.CompareSize());
				break; 
			case 2: 
				customersSorted.sort(new Customer.CompareTotalPrice());
				break; 
			case 3: 
				break; 
			default: 
				break; 
		}
	}
}
