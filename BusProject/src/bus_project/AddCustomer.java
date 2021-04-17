package bus_project;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Control;

public class AddCustomer extends AbstractProgramWindow {

	protected Shell shlAddCustomer;
	private ArrayList<Customer> customers; 
	private Text nameField;
	private Text sizeField;
	private Table customersTable;
	private Text indexField;
	private DateTime dateTime; 
	
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	public AddCustomer (ArrayList<Customer> cstmrs) {
		customers = cstmrs; 
	}
	
	// TODO: method section
			
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
		shlAddCustomer.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlAddCustomer.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlAddCustomer.layout();
		
		/************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING TO DO */
		/************************************************************************/
		while (!shlAddCustomer.isDisposed()) 
		{
			// TODO: label if
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}

	/********************************************************************************/
	/* PRECONDITION:  WINDOW IS TO BE OPENED, ELEMENTS NEED TO BE ADDED TO WINDOW	*/
	/* POSTCONDITION: ADDS ELEMENTS TO WINDOW										*/
	/********************************************************************************/
	protected void createContents(Shell rootShell) {
		
		shlAddCustomer = new Shell();
		// TODO: label method calls
		shlAddCustomer.setSize(600, 400);
		shlAddCustomer.setText("Add Customer");
		
		Label lblName = new Label(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		lblName.setBounds(10, 13, 73, 15);
		lblName.setText("Name:");
		
		nameField = new Text(shlAddCustomer, SWT.BORDER);
		// TODO: label method calls
		nameField.setText("");
		nameField.setBounds(89, 10, 80, 24);
		
		Label lblSize = new Label(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		lblSize.setText("Group size: ");
		lblSize.setBounds(10, 43, 73, 15);
		
		sizeField = new Text(shlAddCustomer, SWT.BORDER);
		// TODO: label method calls
		sizeField.setBounds(89, 40, 80, 24);
		
		Label lblTripDate = new Label(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		lblTripDate.setBounds(10, 92, 73, 15);
		lblTripDate.setText("Trip Date: ");
		
		dateTime = new DateTime(shlAddCustomer, SWT.BORDER | SWT.CALENDAR);
		// TODO: label method calls
		dateTime.setBounds(10, 113, 233, 151);
		
		Button btnExit = new Button(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		btnExit.setBounds(499, 326, 75, 25);
		btnExit.setText("Exit");
		
		Button btnAdd = new Button(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		btnAdd.setBounds(89, 270, 75, 25);
		btnAdd.setText("Add");
		
		/***************/
		/* INDEX LABEL */
		/***************/ 
		Label lblIndex = new Label(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		lblIndex.setBounds(10, 71, 55, 15);
		lblIndex.setText("Index: ");
		
		/***************/
		/* INDEX FIELD */
		/***************/ 
		indexField = new Text(shlAddCustomer, SWT.BORDER);
		// TODO: label method calls
		indexField.setBounds(89, 70, 80, 24);
		
		/*******************/
		/* CUSTOMERS LABEL */
		/*******************/ 
		Label lblCustomers = new Label(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		lblCustomers.setBounds(388, 13, 63, 15);
		lblCustomers.setText("Customers: ");
		
		/***************/
		/* CUSTOMERS TABLE */
		/***************/ 
		customersTable = new Table(shlAddCustomer, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText("");
		customersTable.setBounds(258, 43, 316, 252);
		customersTable.setLinesVisible(true);
		shlAddCustomer.setTabList(new Control[]{nameField, sizeField, indexField, dateTime, btnAdd, customersTable, btnExit});
		// TODO: label if
		if (customers.size() > 0) {
			updateTable(customersTable, customers); 
		}
		
		/***********************/
		/* ADD CUSTOMER BUTTON */
		/***********************/ 
		btnAdd.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				Customer customer; 
				int index; 				// INDEX TO ADD CUSTOMER AT 
				
				// TODO: label try/catch
				try
				{
					// TODO: label if
					if (!legalCustomerAddition())
					{
						/***************/
						/* PRINT ERROR */
						/***************/
						JOptionPane.showMessageDialog(null, "Invalid customer detail(s)!"); 
					}
					else
					{
						// TODO: label method calls
						// TODO: label vars etc. 
						customer = new Customer(); 
						Text[] fields = {nameField, sizeField, indexField};	
						
						index = Integer.parseInt(indexField.getText()); 
						setCustomerDetails(customer, nameField, sizeField, indexField, index, dateTime); 
						clearInput(fields); 
						addCustomer(customers, customer, index, customersTable); 	
						
						BusFinances.addCustomerProfit(customer); 
						//TODO: comment out
						System.out.println(BusFinances.nf.format(BusFinances.getProfit())); 
					}
				}
				catch (Exception exc) 
				{
					//switch(exc.)
					/***************/
					/* PRINT ERROR */
					/***************/
					JOptionPane.showMessageDialog(null, "Error: Improper numerical input (or error)!"); 
				}
			}
		});
		
		/***************/
		/* EXIT BUTTON */
		/***************/ 
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			// TODO: label method
			public void widgetSelected(SelectionEvent e) 
			{
				rootShell.forceActive(); 
				shlAddCustomer.close(); 
			}
		});
	}
	
	// TODO: remove method
	//private void addCustomer(Customer cstmr, int indx, Table tbl) {
	//	customers.add(indx, cstmr);
	//	BusCalculation.scheduleTrip(cstmr);
	//	updateTable(tbl, customers); 
	//}
	
	// TODO: label method
	private boolean legalCustomerAddition() {
		
		if (nameField.getText().equals("") || sizeField.getText().equals("") || Integer.parseInt(indexField.getText()) < 0 || Integer.parseInt(indexField.getText()) > customers.size() || !vaildDate(dateTime))
		{
			return false; 
		}
		else 
		{
			return true; 
		} 
	}
	
	
}
