package bus_project;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;

public class ListCustomers extends AbstractProgramWindow {

	protected Shell shlListCustomers;
	private ArrayList<Customer> customers; 
	private ArrayList<Customer> customersSorted; 
	private int sortBy; 									// sort by filter		 
															// srtBy = 0, sort by name  
															// srtBy = 1, sort by size
	private Table customersTable;
	
	public ListCustomers (ArrayList<Customer> cstmrs) {
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
		Display display = Display.getDefault();				// MANAGES THE CONNECTION BETWEEN SWT 
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
		shlListCustomers.forceActive();						// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlListCustomers.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlListCustomers.isDisposed()) 
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
		
		shlListCustomers = new Shell();
		shlListCustomers.setSize(352, 500);
		shlListCustomers.setText("List Customers");
		
		Button btnExit = new Button(shlListCustomers, SWT.NONE);
		btnExit.setText("Exit");
		btnExit.setBounds(128, 426, 75, 25);
		
		customersTable = new Table(shlListCustomers, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText("");
		customersTable.setLinesVisible(true);
		customersTable.setBounds(10, 40, 316, 380);
		
		// update table with sorted list, sort list depending on sort method specified
		
		sortCustomers(); 
		updateTable(customersTable, customersSorted); 
		
		Label lblCustomers = new Label(shlListCustomers, SWT.NONE);
		lblCustomers.setText("Customers: ");
		lblCustomers.setBounds(140, 10, 63, 15);
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				rootShell.forceActive(); 
				shlListCustomers.close(); 
			}
		});
	}
	
	public void setSortByName() {
		sortBy = 0;  	// set sort by filter to name
	}
	
	public void setSortBySize() {
		sortBy = 1; 	// set sort by filter to size
	}
	
	@SuppressWarnings("unchecked")		// TO SUPRESS WARNING ABOUT "TYPE SAFETY: UNCHECKED CAST...."
	private void sortCustomers() {
		customersSorted =  (ArrayList<Customer>) customers.clone(); 
		
		switch (sortBy) {
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
