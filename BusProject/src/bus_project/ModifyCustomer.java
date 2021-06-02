package bus_project;

import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import javax.swing.JOptionPane;							// FOR JOPTIONPANE DIALOG WINDOWS
import org.eclipse.swt.SWT;								// THIS CLASS PROVIDES ACCESS TO A SMALL 
														//NUMBER OF SWT SYSTEM-WIDE METHODS, AND
														//IN ADDITION DEFINES THE PUBLIC CONSTANTS 
														//PROVIDED BY SWT. 
import org.eclipse.swt.events.SelectionAdapter;			// THIS ADAPTER CLASS PROVIDES DEFAULT 
														// IMPLEMENTATIONS FOR THE METHODS DESCRIBED
														// BY THE SELECTIONLISTENER INTERFACE. 
import org.eclipse.swt.events.SelectionEvent;			// SELECTION EVENTS ARE SENT AS A RESULT OF 
														// WIDGETS BEING SELECTED. 
import org.eclipse.swt.widgets.Display;					// RESPONSIBLE FOR MANAGING THE CONNECTION 
														// BETWEEN SWT AND THE UNDERLYING OPERATING
														// SYSTEM.
import org.eclipse.swt.widgets.Shell;					// REPRESENTS THE "WINDOWS" WHICH THE DESKTOP
														// OR "WINDOW MANAGER" IS MANAGING. 
import org.eclipse.swt.widgets.Button;					// REPRESENTS A SELECTABLE USER INTERFACE 
														// OBJECT THAT ISSUES NOTIFICATION WHEN 
														// PRESSED AND RELEASED. 
import org.eclipse.swt.widgets.Control;					// CONTROL IS THE ABSTRACT SUPERCLASS OF 
														// ALL WINDOWED USER INTERFACE CLASSES. 
														// (REPRESENTS CONTROL EVENTS).
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;


public class ModifyCustomer extends AbstractProgramWindow 
{

	//TODO: label vars
	/********************/
	/* VARIABLE SECTION */
	/********************/ 
	protected Shell shlModifyCustomers;			// REPRESENTS THIS WINDOW
	private ArrayList<Customer> customers; 		// ArrayList of all customers
	private Table customersTable;				
	private DateTime dateTime;
	private Text idField;
	private Text nameField;
	private Text sizeField;
	
	//TODO: label constructor section
	public ModifyCustomer (ArrayList<Customer> cstmrs)
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
		Display display = Display.getDefault();						// MANAGES THE CONNECTION BETWEEN SWT 
																	// AND THE UNDERLYING OPERATING SYSTEM 
		
		/**********************************************/
		/* METHOD TO CREATE CONTENTS OF SHELL/DISPLAY */
		/**********************************************/
		createContents(rootShell);
		
		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shlModifyCustomers.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlModifyCustomers.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlModifyCustomers.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlModifyCustomers.isDisposed()) 
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
	protected void createContents(Shell rootShell) 
	{
		//TODO: labels
		
		shlModifyCustomers = new Shell();
		shlModifyCustomers.setSize(600, 400);
		shlModifyCustomers.setText(Messages.getString("ModifyCustomer.shlModifyCustomers.text")); //$NON-NLS-1$
		
		Button btnExit = new Button(shlModifyCustomers, SWT.NONE);
		btnExit.setText(Messages.getString("btnExit.text")); //$NON-NLS-1$
		btnExit.setBounds(499, 326, 75, 25);
		
		customersTable = new Table(shlModifyCustomers, SWT.BORDER | SWT.FULL_SELECTION);
		
		customersTable.setToolTipText(Messages.getString("ModifyCustomer.customersTable.toolTipText")); //$NON-NLS-1$
		customersTable.setLinesVisible(true);
		customersTable.setBounds(10, 40, 316, 310);
		if (customers.size() > 0) {
			updateTable(customersTable, customers); 
		}
		
		Label lblCustomers = new Label(shlModifyCustomers, SWT.CENTER);
		lblCustomers.setText(Messages.getString("lblCustomers.text")); //$NON-NLS-1$
		lblCustomers.setBounds(10, 10, 316, 15);
		
		Label lblName = new Label(shlModifyCustomers, SWT.NONE);
		lblName.setText(Messages.getString("lblName.text")); //$NON-NLS-1$
		lblName.setBounds(341, 13, 120, 15);
		
		nameField = new Text(shlModifyCustomers, SWT.BORDER);
		nameField.setText("");
		nameField.setBounds(474, 10, 100, 24);
		
		sizeField = new Text(shlModifyCustomers, SWT.BORDER);
		sizeField.setBounds(474, 40, 100, 24);
		
		Label lblSize = new Label(shlModifyCustomers, SWT.NONE);
		lblSize.setText(Messages.getString("lblSize.text")); //$NON-NLS-1$
		lblSize.setBounds(341, 43, 120, 15);
		
		Label lblNumber = new Label(shlModifyCustomers, SWT.NONE);
		lblNumber.setText(Messages.getString("lblID.text")); //$NON-NLS-1$
		lblNumber.setBounds(341, 71, 106, 15);
		
		idField = new Text(shlModifyCustomers, SWT.BORDER);
		idField.setBounds(474, 70, 100, 24);
		
		Label lblTripDate = new Label(shlModifyCustomers, SWT.NONE);
		lblTripDate.setText(Messages.getString("lblTripDate.text")); //$NON-NLS-1$
		lblTripDate.setBounds(341, 92, 100, 15);
		
		dateTime = new DateTime(shlModifyCustomers, SWT.BORDER | SWT.CALENDAR);
		dateTime.setBounds(341, 113, 233, 151);

		Button btnModify = new Button(shlModifyCustomers, SWT.NONE);
		btnModify.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				int confirmModify; 
				Text[] fields = {nameField, sizeField, idField}; 
				Customer selectedCustomer; 
				
				selectedCustomer = customers.get(customersTable.getSelectionIndex()); 
				
				if (legalCustomerModification(customers)) 
				{
					confirmModify = JOptionPane.showConfirmDialog(null, "Confirm", "Are you sure you want to modify these customers?", JOptionPane.YES_NO_OPTION); 
					if (confirmModify == 1) 								// no option
					{
						//updateTable(customersTable, customers); 			// reset table
						updateCustomerInfoDisplay(selectedCustomer);
						return; 
					}
					else 													// yes option
					{
						modifyCustomer(selectedCustomer);
						clearInput(fields); 								// customer now modified 
						updateTable(customersTable, customers); 
					}
				}
				else {
					updateCustomerInfoDisplay(selectedCustomer);			// reset modification if invalid modification 
					JOptionPane.showMessageDialog(null, "Invalid modification."); 
				}
			}
		});
		btnModify.setText(Messages.getString("btnModify.text")); //$NON-NLS-1$
		btnModify.setBounds(341, 267, 233, 25);
		shlModifyCustomers.setTabList(new Control[]{nameField, sizeField, idField, dateTime, btnModify, customersTable, btnExit});
		
		customersTable.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				Customer selectedCustomer = customers.get(customersTable.getSelectionIndex()); 
				
				updateCustomerInfoDisplay(selectedCustomer); 
			}
		});
		
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				rootShell.forceActive(); 
				shlModifyCustomers.close(); 	
			}
		});
	}
	
	/***************************************************************/
	/* PRECONDITION:  MODIFICATIONS ARE LEGAL 
	/* POSTCONDITION: 
	/***************************************************************/
	private void modifyCustomer(Customer slctdCstmr) 
	{
		int id = Integer.parseInt(idField.getText()); 		// NEW ID OF SELECTED CUSTOMER
		
		setCustomerDetails(slctdCstmr, nameField, sizeField, id, 
				slctdCstmr.getId(), dateTime); 
		BusCalculation.rescheduleTrip(slctdCstmr); 
		BusFinances.setCustomerProfit(slctdCstmr); 
		
		// SORT ARRAY CONSIDERING MODIFICATIONS TO ID
		customers.sort(new Customer.CompareId());
	}
	
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: returns year in format acceptable for DateTime
	/***************************************************************/
	private int getDateTimeYear(Customer cstmr)
	{	
		return cstmr.getDate().getYear(); 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: returns customer date in DateTime format
	/***************************************************************/
	private int getDateTimeMonth(Customer cstmr) 
	{
		return cstmr.getDate().getMonthValue() - 1; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: returns customer date in DateTime format
	/***************************************************************/
	private int getDateTimeDay(Customer cstmr) 
	{
		return cstmr.getDate().getDayOfMonth(); 
	}
	
	/***************************************************************/
	/* PRECONDITION:  
	/* POSTCONDITION: 
	/***************************************************************/
	private void updateCustomerInfoDisplay(Customer cstmr) 
	{
		nameField.setText(cstmr.getName());
		sizeField.setText(Integer.toString(cstmr.getNumPersons()));
		idField.setText(Integer.toString(customersTable.getSelectionIndex()));
		dateTime.setDate(getDateTimeYear(cstmr), getDateTimeMonth(cstmr), 
				getDateTimeDay(cstmr));
	}
	
	/***************************************************************/
	/* PRECONDITION:  
	/* POSTCONDITION: DECIDES IF POTENTIAL MODIFICATIONS ARE ALLOWED,
	/* 				  RETURNS FALSE OR TRUE
	/***************************************************************/
	private boolean legalCustomerModification(ArrayList<Customer> cstmrs)
	{
		
		if (nameField.getText().equals("") || sizeField.getText().equals("") 
				|| Integer.parseInt(idField.getText()) < 0 
				|| Integer.parseInt(idField.getText()) >= cstmrs.size() 
				|| !vaildDate(dateTime))
		{
			return false; 
		}
		else 
		{
			return true; 
		} 
	}
}
