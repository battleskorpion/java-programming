/********************************************/
/* INFORMATION SECTION 						*/
/* ModifyCustomer.java						*/
/* Darian Siembab 							*/
/* 											*/
/* WINDOW CLASS TO DISPLAY A LIST OF 		*/
/* CUSTOMERS AND ALLOW THE USER TO MODIFY	*/
/* A CUSTOMER BY SELECTING THE CUSTOMER IN 	*/
/* THE LIST AND EDITING AND SAVING THE 		*/
/* NEW DETAILS 								*/
/********************************************/ 
package bus_project;

//TODO: PRINT PROFIT? 
import java.time.LocalDate;
/******************/
/* IMPORT SECTION */
/******************/
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import javax.swing.JOptionPane;							// FOR JOPTIONPANE DIALOG WINDOWS
import org.eclipse.swt.SWT;								// THIS CLASS PROVIDES ACCESS TO A SMALL 
														// NUMBER OF SWT SYSTEM-WIDE METHODS, AND
														// IN ADDITION DEFINES THE PUBLIC CONSTANTS 
														// PROVIDED BY SWT. 
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
import org.eclipse.swt.widgets.DateTime;				// FOR SWT WINDOWS/WIDGETS (CALENDAR AND 
														// DATE/TIME SELECTION)
import org.eclipse.swt.widgets.Label;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Table;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Text;					// FOR SWT WINDOWS/WIDGETS	

import bus_project.localization.Messages;

import org.eclipse.swt.widgets.TableColumn;				// FOR SWT WINDOWS/WIDGETS	


public class ModifyCustomer extends AbstractBusProgramWindow 
{

	/********************/
	/* VARIABLE SECTION */
	/********************/ 
	protected Shell shlModifyCustomers;					// SHELL WHICH REPRESENTS THIS WINDOW
	private ArrayList<Customer> customers; 				// ArrayList of all customers
	private DateTime calendar;							// dateTime CALENDAR
	private Text idField;								// CUSTOMER ID FIELD
	private Text nameField;								// CUSTOMER NAME FIELD
	private Text sizeField;								// CUSTOMER SIZE FIELD	
	private Table customersTable;						// CUSTOMERS TABLE
	
	public ModifyCustomer (ArrayList<Customer> cstmrs)
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
	
	protected void createContents(Shell rootShell) 
	/*************************************************/
	/* PRECONDITION:  WINDOW NEEDS ELEMENTS 		 */
	/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW */
	/*************************************************/
	{
		shlModifyCustomers = new Shell();
		shlModifyCustomers.setSize(800, 340);
		shlModifyCustomers.setText(Messages.getString("ModifyCustomer.shlModifyCustomers.text")); //$NON-NLS-1$
		
		Button btnExit = new Button(shlModifyCustomers, SWT.NONE);
		btnExit.setText(Messages.getString("btnExit.text")); //$NON-NLS-1$
		btnExit.setBounds(699, 271, 75, 25);
		
		Label lblCustomers = new Label(shlModifyCustomers, SWT.CENTER);
		lblCustomers.setText(Messages.getString("lblCustomers.text")); //$NON-NLS-1$
		lblCustomers.setBounds(258, 9, 516, 15);
		
		Label lblName = new Label(shlModifyCustomers, SWT.NONE);
		lblName.setText(Messages.getString("lblName.text")); //$NON-NLS-1$
		lblName.setBounds(10, 9, 120, 21);
		
		nameField = new Text(shlModifyCustomers, SWT.BORDER);
		nameField.setText("");
		nameField.setBounds(143, 6, 100, 24);
		
		sizeField = new Text(shlModifyCustomers, SWT.BORDER);
		sizeField.setBounds(143, 36, 100, 24);
		
		Label lblSize = new Label(shlModifyCustomers, SWT.NONE);
		lblSize.setText(Messages.getString("lblSize.text")); //$NON-NLS-1$
		lblSize.setBounds(10, 39, 120, 21);
		
		Label lblID = new Label(shlModifyCustomers, SWT.NONE);
		lblID.setText(Messages.getString("lblID.text")); //$NON-NLS-1$
		lblID.setBounds(10, 67, 106, 23);
		
		idField = new Text(shlModifyCustomers, SWT.BORDER);
		idField.setBounds(143, 66, 100, 24);
		
		Label lblTripDate = new Label(shlModifyCustomers, SWT.NONE);
		lblTripDate.setText(Messages.getString("lblTripDate.text")); //$NON-NLS-1$
		lblTripDate.setBounds(10, 96, 100, 15);
		
		calendar = new DateTime(shlModifyCustomers, SWT.BORDER | SWT.CALENDAR);
		calendar.setBounds(10, 117, 233, 151);

		Button btnModify = new Button(shlModifyCustomers, SWT.NONE);
		btnModify.setText(Messages.getString("btnModify.text")); //$NON-NLS-1$
		btnModify.setBounds(10, 271, 233, 25);
		
		customersTable = new Table(shlModifyCustomers, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText("!AddCustomer.customersTable.toolTipText!");
		customersTable.setLinesVisible(true);
		customersTable.setHeaderVisible(true);
		customersTable.setBounds(258, 30, 516, 233);
		
		TableColumn tableColumn = new TableColumn(customersTable, SWT.NONE);
		tableColumn.setWidth(60);
		tableColumn.setText("ID");
		
		TableColumn tableColumn_1 = new TableColumn(customersTable, SWT.NONE);
		tableColumn_1.setWidth(160);
		tableColumn_1.setText("Name");
		
		TableColumn tableColumn_2 = new TableColumn(customersTable, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("Date");
		
		TableColumn tableColumn_3 = new TableColumn(customersTable, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("Group Size");
		
		TableColumn tableColumn_4 = new TableColumn(customersTable, SWT.NONE);
		tableColumn_4.setWidth(80);
		tableColumn_4.setText("Refunds");
		shlModifyCustomers.setTabList(new Control[]{nameField, sizeField, idField, calendar, btnModify, btnExit});
		
		updateTable(customersTable, customers); 
		
		customersTable.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				Customer selectedCustomer = customers.get(customersTable.getSelectionIndex()); 
				
				updateCustomerInfoDisplay(selectedCustomer); 
			}
		});
		
		btnModify.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				/********************/
				/* VARIABLE SECTION */
				/********************/
				int confirmModify; 									// CONFIRM MODIFICATIONS
				Text[] fields = {nameField, sizeField, idField}; 	// TEXT FIELDS
				Customer selectedCustomer; 							// SELECTED CUSTOMER TO MODIFY
				int id; 											// NEW CUSTOMER ID
				
				selectedCustomer = customers.get(customersTable.getSelectionIndex()); 
				
				/*******************/
				/* TRY TO PARSE ID */
				/*******************/
				try 
				{
					id = Integer.parseInt(idField.getText()); 

					/**********************************************************************/
					/* DISPLAY ERROR MESSAGE AND CANCEL IF ID WAS INVALID (IS NOT UNIQUE) */
					/**********************************************************************/
					if (!uniqueID(id, customers, selectedCustomer))
					{
						JOptionPane.showMessageDialog(null, 
								"Invalid ID (Already taken)!"); 
						updateCustomerInfoDisplay(selectedCustomer);		// RESET MODIFICATION IF 
																			// INVALID MODIFICATION 
						return; 
					}
					
					/*******************************************************/
					/* DISPLAY ERROR MESSAGE AND CANCEL IF ID WAS NEGATIVE */
					/*******************************************************/
					if (id < 0)
					{
						JOptionPane.showMessageDialog(null, 
								"Invalid ID (Negative value)!"); 
						return; 
					}
				}
				
				/******************************************************************************/
				/* DISPLAY ERROR MESSAGE AND CANCEL IF ID WAS INVALID (INCLUDED NON-INTEGERS) */
				/******************************************************************************/
				catch (Exception exc) 
				{
					JOptionPane.showMessageDialog(null, 
							"Invalid ID!"); 
					updateCustomerInfoDisplay(selectedCustomer);		// RESET MODIFICATION IF 
																		// INVALID MODIFICATION 
					return; 
				}
				
				/***********************************************************/
				/* DISPLAY ERROR MESSAGE AND CANCEL IF NAME FIELD IS EMPTY */
				/***********************************************************/
				if (nameField.getText().trim().equals(""))
				{
					JOptionPane.showMessageDialog(null, 
							"Invalid modification: Name field is blank.");
					updateCustomerInfoDisplay(selectedCustomer);		// RESET MODIFICATION IF 
																		// INVALID MODIFICATION 
					return; 
				}
				
				/***********************************************************/
				/* DISPLAY ERROR MESSAGE AND CANCEL IF SIZE FIELD IS EMPTY */
				/***********************************************************/
				if (sizeField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, 
							"Invalid modification: Size field is blank.");
					updateCustomerInfoDisplay(selectedCustomer);		// RESET MODIFICATION IF 
																		// INVALID MODIFICATION 
					return;
				}
				
				/*********************/
				/* TRY TO PARSE SIZE */
				/*********************/
				try 
				{
					/*******************************************************************/
					/* DISPLAY ERROR MESSAGE AND CANCEL IF GROUP SIZE IS INVALID (< 0) */
					/*******************************************************************/
					if (Integer.parseInt(sizeField.getText()) < 0) 
					{
						JOptionPane.showMessageDialog(null, 
								"Error: Size is negative!");
						updateCustomerInfoDisplay(selectedCustomer);		// RESET MODIFICATION IF 
																			// INVALID MODIFICATION 
						return;
					}
				}
				
				/*************************************************************/
				/* DISPLAY ERROR MESSAGE AND CANCEL IF GROUP SIZE IS INVALID */
				/*************************************************************/
				catch (Exception exc)
				{
					JOptionPane.showMessageDialog(null, 
							"Error: Size field is not an integer!");
					updateCustomerInfoDisplay(selectedCustomer);		// RESET MODIFICATION IF 
																		// INVALID MODIFICATION 
					return;
				}
				
				/*******************************************************/
				/* DISPLAY ERROR MESSAGE AND CANCEL IF DATE IS INVALID */
				/*******************************************************/
				if(!vaildDate(calendar))
				{
					JOptionPane.showMessageDialog(null, 
							"Error: Invalid date! (trip date must be in the future)");
					updateCustomerInfoDisplay(selectedCustomer);		// RESET MODIFICATION IF 
																		// INVALID MODIFICATION
					return; 
				}
				
				/******************/ 
				/* CONFIRM MODIFY */
				/******************/ 
				confirmModify = JOptionPane.showConfirmDialog
						(null, "Are you sure you want to modify these customers?", 
						"Confirm", JOptionPane.YES_NO_OPTION); 
				
				/*******************************************/ 
				/* CANCEL MODIFICATIONS OR MODIFY CUSTOMER */
				/*******************************************/ 
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
		});
		
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				closeSubWindow(rootShell, shlModifyCustomers); 	
			}
		});
	}
	
	/***************************************************************/
	/* PRECONDITION:  MODIFICATIONS ARE LEGAL 					   */
	/* POSTCONDITION: MODIFIES CUSTOMER (SETS CUSTOMER DETAILS TO  */
	/* 	 			  NEW ENTERED INFORMATION), RESCHEUDULES TRIP  */
	/*				  AND RECALCULATES PROFIT 					   */
	/***************************************************************/
	private void modifyCustomer(Customer slctdCstmr) 
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int id = Integer.parseInt(idField.getText()); 		// NEW ID OF SELECTED CUSTOMER
		LocalDate prevDate = slctdCstmr.getDate(); 			// CUSTOMER TRIP DATE PRIOR TO 
															// MODIFICATION (MAY NOT HAVE CHANGED)
		
		setCustomerDetails(slctdCstmr, nameField, 
				 sizeField, slctdCstmr.getIndex(), id, calendar); 		
		BusCalculation.rescheduleTrip(slctdCstmr, prevDate); 
		BusFinances.updateCustomerProfit(slctdCstmr); 
		
		// SORT ARRAY CONSIDERING MODIFICATIONS TO ID
		customers.sort(new Customer.CompareId());
	}
	
	
	/***************************************************************/
	/* PRECONDITION:  TRIP YEAR IN DateTime FORMAT IS NEEDED	   */
	/* POSTCONDITION: RETURNS YEAR IN FORMAT ACCEPTABLE FOR 	   */
	/* 			      DateTime 									   */
	/***************************************************************/
	private int getDateTimeYear(Customer cstmr)
	{	
		return cstmr.getDate().getYear(); 
	}
	
	/***************************************************************/
	/* PRECONDITION:  CUSTOMER DATE IN DateTime FORMAT IS NEEDED   */
	/* POSTCONDITION: RETURNS CUSTOMER DATE IN DateTime FORMAT	   */
	/***************************************************************/
	private int getDateTimeMonth(Customer cstmr) 
	{
		return cstmr.getDate().getMonthValue() - 1; 
	}
	
	/***************************************************************/
	/* PRECONDITION:  CUSTOMER DATE IN DateTime FORMAT IS NEEDED   */
	/* POSTCONDITION: RETURNS CUSTOMER DATE IN DateTime FORMAT	   */
	/***************************************************************/
	private int getDateTimeDay(Customer cstmr) 
	{
		return cstmr.getDate().getDayOfMonth(); 
	}
	
	/***************************************************************/
	/* PRECONDITION:  CUSTOMER INFO DISPLAY NEEDS TO BE UPDATED	   */
	/* POSTCONDITION: UPDATES CUSTOMER INFO DISPLAY				   */
	/***************************************************************/
	private void updateCustomerInfoDisplay(Customer cstmr) 
	{
		nameField.setText(cstmr.getName());
		sizeField.setText(Integer.toString(cstmr.getNumPersons()));
		idField.setText(Integer.toString(cstmr.getId()));
		calendar.setDate(getDateTimeYear(cstmr), getDateTimeMonth(cstmr), 
				getDateTimeDay(cstmr));
	}
}
