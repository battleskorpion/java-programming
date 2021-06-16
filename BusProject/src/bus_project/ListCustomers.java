/********************************************/
/* INFORMATION SECTION 						*/
/* ListCustomers.java						*/
/* Darian Siembab 							*/
/* 											*/
/* WINDOW CLASS FOR LISTING CUSTOMERS		*/
/* BY A SPECIFIED FILTER					*/
/********************************************/ 
package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import org.eclipse.swt.SWT;								// THIS CLASS PROVIDES ACCESS TO A SMALL 
														// NUMBER OF SWT SYSTEM-WIDE METHODS, AND
														// IN ADDITION DEFINES THE PUBLIC CONSTANTS 
														// PROVIDED BY SWT. 
import org.eclipse.swt.widgets.Display;					// RESPONSIBLE FOR MANAGING THE CONNECTION 
														// BETWEEN SWT AND THE UNDERLYING OPERATING
														// SYSTEM.
import org.eclipse.swt.widgets.Shell;					// REPRESENTS THE "WINDOWS" WHICH THE DESKTOP
														// OR "WINDOW MANAGER" IS MANAGING. 	
import org.eclipse.swt.widgets.Button;					// REPRESENTS A SELECTABLE USER INTERFACE 
														// OBJECT THAT ISSUES NOTIFICATION WHEN 
														// PRESSED AND RELEASED. 
import org.eclipse.swt.widgets.Label;					// REPRESENTS A NON-SELECTABLE USER INTERFACE 
														// OBJECT THAT DISPLAYS A STRING OR IMAGE 
														// (OR HORIZONTAL/VERTICAL LINE).
import org.eclipse.swt.events.SelectionAdapter;			// THIS ADAPTER CLASS PROVIDES DEFAULT 
														// IMPLEMENTATIONS FOR THE METHODS DESCRIBED
														// BY THE SELECTIONLISTENER INTERFACE. 
import org.eclipse.swt.events.SelectionEvent;			// SELECTION EVENTS ARE SENT AS A RESULT OF 
														// WIDGETS BEING SELECTED. 
import org.eclipse.swt.widgets.Table;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.TableColumn;				// FOR SWT WINDOWS/WIDGETS	

public class ListCustomers extends AbstractBusProgramWindow 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected Shell shlListCustomers;					// SHELL WHICH REPRESENTS THIS WINDOW
	private ArrayList<Customer> customers; 				// LIST OF CUSTOMERS
	private ArrayList<Customer> customersSorted; 		// SORTED LIST OF CUSTOMERS 
														// (BY DESIGNATED FILTER)
	private Table customersTable;						// DISPLAYS CUSTOMERS LIST
	private int sortBy; 								// SORT BY FILTER		
	
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/ 
	public ListCustomers (ArrayList<Customer> cstmrs) 
	{
		customers = cstmrs;  
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
		Display display = Display.getDefault();			// MANAGES THE CONNECTION BETWEEN SWT 
														// AND THE UNDERLYING OPERATING SYSTEM 

		/**********************************************/
		/* METHOD TO CREATE CONTENTS OF SHELL/DISPLAY */
		/**********************************************/
		createContents(rootShell);
		
		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shlListCustomers.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlListCustomers.forceActive();					// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlListCustomers.layout();
		
		/*******************************/
		/* WHILE SHELL IS NOT DISPOSED */
		/*******************************/
		while (!shlListCustomers.isDisposed()) 
		{
			/*******************************************/
			/* SLEEP DISPLAY IF THERE IS NOTHING TO DO */
			/*******************************************/
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}
	
	protected void createContents(Shell rootShell)
	/*************************************************/
	/* PRECONDITION:  WINDOW NEEDS ELEMENTS 		 */
	/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW */
	/*************************************************/
	{
		shlListCustomers = new Shell();
		shlListCustomers.setSize(550, 360);
		shlListCustomers.setText(Messages.getString
				("ListCustomers.shlListCustomers.text")); 								//$NON-NLS-1$
		
		Button btnExit = new Button(shlListCustomers, SWT.NONE);
		btnExit.setText(Messages.getString("btnExit.text")); 							//$NON-NLS-1$
		btnExit.setBounds(10, 289, 516, 25);
		
		// update table with sorted list, sort list depending on sort method specified
		
		sortCustomers();
		
		Label lblCustomers = new Label(shlListCustomers, SWT.CENTER);
		lblCustomers.setText(Messages.getString("lblCustomers.text")); 					//$NON-NLS-1$
		lblCustomers.setBounds(10, 10, 516, 15);
		
		customersTable = new Table(shlListCustomers, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText("!AddCustomer.customersTable.toolTipText!");
		customersTable.setLinesVisible(true);
		customersTable.setHeaderVisible(true);
		customersTable.setBounds(10, 31, 516, 252);
		
		TableColumn tableColumn_ID = new TableColumn(customersTable, SWT.NONE);
		tableColumn_ID.setWidth(60);
		tableColumn_ID.setText("ID");
		
		TableColumn tableColumn_Name = new TableColumn(customersTable, SWT.NONE);
		tableColumn_Name.setWidth(160);
		tableColumn_Name.setText("Name");
		
		TableColumn tableColumn_Date = new TableColumn(customersTable, SWT.NONE);
		tableColumn_Date.setWidth(100);
		tableColumn_Date.setText("Date");
		
		TableColumn tableColumn_GroupSize = new TableColumn(customersTable, SWT.NONE);
		tableColumn_GroupSize.setWidth(100);
		tableColumn_GroupSize.setText("Group Size");
		
		TableColumn tableColumn_Refunds = new TableColumn(customersTable, SWT.NONE);
		tableColumn_Refunds.setWidth(80);
		tableColumn_Refunds.setText("Refunds");
		
		sortCustomers(); 
		updateTable(customersTable, customersSorted); 
		
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			/****************************************************************************************/
			/* PRECONDITION:  SENT WHEN CONTROL IS SELECTED								  			*/
			/* POSTCONDITION: CLOSES THIS WINDOW					 								*/
			/****************************************************************************************/
			{
				closeSubWindow(rootShell, shlListCustomers); 
			}
		});
	}
	
	public void setSortByName() 
	/************************************************************************************************/
	/* PRECONDITION:  SORT BY FILTER NEEDS TO BE SET TO SORT BY NAME		  						*/
	/* POSTCONDITION: SETS SORT BY FILTER TO SORT BY NAME					  						*/
	/************************************************************************************************/
	{
		sortBy = 0;  	// set sort by filter to name
	}
	
	public void setSortBySize() 
	/************************************************************************************************/
	/* PRECONDITION:  SORT BY FILTER NEEDS TO BE SET TO SORT BY SIZE		  						*/
	/* POSTCONDITION: SETS SORT BY FILTER TO SORT BY SIZE					  						*/
	/************************************************************************************************/
	{
		sortBy = 1; 	// set sort by filter to size
	}
	
	@SuppressWarnings("unchecked")		// TO SUPRESS WARNING ABOUT "TYPE SAFETY: UNCHECKED CAST...."
										// WITH ArrayList CAST 
	private void sortCustomers() 
	/************************************************************************************************/
	/* PRECONDITION:  CUSTOMERS LIST NEEDS TO BE SORTED								  				*/
	/* POSTCONDITION: SORTS CUSTOMERS LIST BY CURRENT FILTER (STORED IN SEPERATE LIST, 				*/
	/*				  CUSTOMERS LIST UNAFFECTED)													*/
	/************************************************************************************************/
	{
		/*****************************************************************/
		/* CREATE NEW SORTED CUSTOMERS LIST AS A CLONE OF CUSTOMERS LIST */
		/*****************************************************************/
		customersSorted = (ArrayList<Customer>) customers.clone(); // "TYPE SAFETY: UNCHECKED 
																   // CAST...." WARNING SUPPRESSED
		
		/*****************************************************/ 
		/* SORT CUSTOMERS LIST DEPENDING ON SPECIFIED FILTER */
		/*****************************************************/ 
		switch (sortBy) 
		{
			case 0: 
				customersSorted.sort(new Customer.CompareName());
				break; 
			case 1: 
				customersSorted.sort(new Customer.CompareSize());
				break; 
			default: 
				break; 
		}
	}
	
}
