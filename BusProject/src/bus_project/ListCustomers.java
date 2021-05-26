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

	//TODO: label section, vars
	protected Shell shlListCustomers;
	private ArrayList<Customer> customers; 
	private ArrayList<Customer> customersSorted; 
	private int sortBy; 									// sort by filter		 
															// srtBy = 0, sort by name  
															// srtBy = 1, sort by size
	private Table customersTable;
	
	//TODO: label method
	public ListCustomers (ArrayList<Customer> cstmrs) {
		customers = cstmrs;  
	}
	
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	//TODO: label method more
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

	/*************************************************/
	/* PRECONDITION:  WINDOW NEEDS ELEMENTS 		 */
	/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW */
	/*************************************************/
	protected void createContents(Shell rootShell) {
		
		//TODO: label method calls/shell
		shlListCustomers = new Shell();
		shlListCustomers.setSize(352, 500);
		shlListCustomers.setText(Messages.getString("ListCustomers.shlListCustomers.text")); //$NON-NLS-1$
		
		//TODO: label button
		Button btnExit = new Button(shlListCustomers, SWT.NONE);
		//TODO: label method calls
		btnExit.setText(Messages.getString("btnExit.text")); //$NON-NLS-1$
		btnExit.setBounds(128, 426, 75, 25);
		
		//TODO: label table
		customersTable = new Table(shlListCustomers, SWT.BORDER | SWT.FULL_SELECTION);
		//TODO: label method calls
		customersTable.setToolTipText(Messages.getString("ListCustomers.customersTable.toolTipText")); //$NON-NLS-1$
		customersTable.setLinesVisible(true);
		customersTable.setBounds(10, 40, 316, 380);
		
		// update table with sorted list, sort list depending on sort method specified
		
		//TODO: label method calls
		sortCustomers(); 
		updateTable(customersTable, customersSorted); 
		
		Label lblCustomers = new Label(shlListCustomers, SWT.NONE);
		lblCustomers.setText(Messages.getString("lblCustomers.text")); //$NON-NLS-1$
		lblCustomers.setBounds(140, 10, 63, 15);
		//TODO: label listener
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			//TODO: label method
			public void widgetSelected(SelectionEvent e) 
			{
				//TODO: label method calls
				rootShell.forceActive(); 
				shlListCustomers.close(); 
			}
		});
	}
	
	//TODO: label method
	public void setSortByName() {
		sortBy = 0;  	// set sort by filter to name
	}
	
	//TODO: label method
	public void setSortBySize() {
		sortBy = 1; 	// set sort by filter to size
	}
	
	//TODO: label method
	@SuppressWarnings("unchecked")		// TO SUPRESS WARNING ABOUT "TYPE SAFETY: UNCHECKED CAST...." with arrayList cast 
	private void sortCustomers() {
		//TODO: label call
		customersSorted =  (ArrayList<Customer>) customers.clone(); 
		
		//TODO: label switch
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
