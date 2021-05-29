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
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

//TODO: if < MIN_CAPACITY passengers do not allow add customer? > 400 already on date?  

public class AddCustomer extends AbstractProgramWindow
{

	protected Shell shlAddCustomer;
	private ArrayList<Customer> customers; 
	private Text nameField;
	private Text sizeField;
	private Table customersTable;
	private Text idField;
	private DateTime dateTime; 
	
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	public AddCustomer (ArrayList<Customer> cstmrs) 
	{
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
	protected void createContents(Shell rootShell) 
	{
		shlAddCustomer = new Shell();
		// TODO: label method calls
		shlAddCustomer.setSize(800, 400);
		shlAddCustomer.setText(Messages.getString("AddCustomer.shlAddCustomer.text")); //$NON-NLS-1$
		
		Label lblName = new Label(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		lblName.setBounds(10, 13, 109, 15);
		lblName.setText(Messages.getString("lblName.text")); //$NON-NLS-1$
		
		nameField = new Text(shlAddCustomer, SWT.BORDER);
		// TODO: label method calls
		nameField.setText("");
		nameField.setBounds(140, 13, 103, 24);
		
		Label lblSize = new Label(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		lblSize.setText(Messages.getString("lblSize.text")); //$NON-NLS-1$
		lblSize.setBounds(10, 43, 109, 15);
		
		sizeField = new Text(shlAddCustomer, SWT.BORDER);
		// TODO: label method calls
		sizeField.setBounds(140, 43, 103, 24);
		
		Label lblTripDate = new Label(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		lblTripDate.setBounds(10, 92, 109, 15);
		lblTripDate.setText(Messages.getString("lblTripDate.text")); //$NON-NLS-1$
		
		dateTime = new DateTime(shlAddCustomer, SWT.BORDER | SWT.CALENDAR);
		// TODO: label method calls
		dateTime.setBounds(10, 113, 233, 151);
		
		Button btnExit = new Button(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		btnExit.setBounds(699, 326, 75, 25);
		btnExit.setText(Messages.getString("btnExit.text")); //$NON-NLS-1$
		
		Button btnAdd = new Button(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		btnAdd.setBounds(10, 270, 233, 25);
		btnAdd.setText(Messages.getString("btnAdd.text")); //$NON-NLS-1$
		
		/************/
		/* ID LABEL */
		/************/ 
		Label lblID = new Label(shlAddCustomer, SWT.NONE);
		// TODO: label method calls
		lblID.setBounds(10, 71, 91, 15);
		lblID.setText(Messages.getString("lblID.text")); //$NON-NLS-1$
		
		/***************/
		/* ID FIELD */
		/***************/ 
		idField = new Text(shlAddCustomer, SWT.BORDER);
		// TODO: label method calls
		idField.setBounds(140, 73, 103, 24);
		
		/*******************/
		/* CUSTOMERS LABEL */
		/*******************/ 
		Label lblCustomers = new Label(shlAddCustomer, SWT.CENTER);
		// TODO: label method calls
		lblCustomers.setBounds(258, 13, 416, 15);
		lblCustomers.setText(Messages.getString("lblCustomers.text")); //$NON-NLS-1$
		
		/*******************/
		/* CUSTOMERS TABLE */
		/*******************/ 
		customersTable = new Table(shlAddCustomer, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText(Messages.getString("AddCustomer.customersTable.toolTipText")); //$NON-NLS-1$
		customersTable.setBounds(258, 43, 516, 252);
		customersTable.setLinesVisible(true);	
		customersTable.setHeaderVisible(true);
		
		TableColumn tblclmnID = new TableColumn(customersTable, SWT.NONE);
		tblclmnID.setWidth(60);
		tblclmnID.setText(Messages.getString("AddCustomer.tblclmnID.text")); //$NON-NLS-1$

		TableColumn tblclmmName = new TableColumn(customersTable, SWT.NONE);
		tblclmmName.setWidth(160);
		tblclmmName.setText(Messages.getString("AddCustomer.tblclmnName.text")); //$NON-NLS-1$

		TableColumn tblclmnSize = new TableColumn(customersTable, SWT.NONE);
		tblclmnSize.setWidth(100);
		tblclmnSize.setText(Messages.getString("AddCustomer.tblclmnDate.text")); //$NON-NLS-1$
		
		TableColumn tblclmnDate = new TableColumn(customersTable, SWT.NONE);
		tblclmnDate.setWidth(100);
		tblclmnDate.setText(Messages.getString("AddCustomer.tblclmnSize.text")); //$NON-NLS-1$
		
		TableColumn tblclmnRefunds = new TableColumn(customersTable, SWT.NONE);
		tblclmnRefunds.setWidth(80);
		tblclmnRefunds.setText(Messages.getString("AddCustomer.tblclmnRefunds.text")); //$NON-NLS-1$
			
		shlAddCustomer.setTabList(new Control[]{nameField, sizeField, idField, dateTime, btnAdd, customersTable, btnExit});
		// TODO: label if
		if (customers.size() > 0)
		{
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
				int index = customers.size(); 	// INDEX TO ADD CUSTOMER AT 
				int id; 						// CUSTOMER ID
				
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
						Text[] fields = {nameField, sizeField, idField};	
						
						id = Integer.parseInt(idField.getText()); 
						setCustomerDetails(customer, nameField, sizeField, index, id, dateTime); 
						clearInput(fields); 
						addCustomer(customers, customer, index, customersTable); 	
						
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
	private boolean legalCustomerAddition() 
	{
		if (nameField.getText().trim().equals("") || sizeField.getText().equals("") || Integer.parseInt(idField.getText()) < 0 || !vaildDate(dateTime))
		{
			return false; 
		}
		else 
		{
			return true; 
		} 
	}
	
	
}
