/********************************************/
/* INFORMATION SECTION 						*/
/* RemoveCustomer.java						*/
/* Darian Siembab 							*/
/* 											*/
/* CLASS TO ALLOW THE USER TO DELETE A 		*/
/* SPECIFIED CUSTOMER OR DELETE ALL 		*/
/* CUSTOMERS 								*/
/********************************************/ 
package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import javax.swing.JOptionPane;							// JOPTIONPANE MAKES IT EASY TO POP UP A 
														// STANDARD DIALOG BOX THAT PROMPTS USERS
														// FOR A VALUE OR INFORMS THEM OF SOMETHING.
import org.eclipse.swt.SWT;								// THIS CLASS PROVIDES ACCESS TO A SMALL 
														// NUMBER OF SWT SYSTEM-WIDE METHODS, AND
														// IN ADDITION DEFINES THE PUBLIC CONSTANTS 
														// PROVIDED BY SWT. 
import org.eclipse.swt.events.SelectionAdapter;			// THIS ADAPTER CLASS PROVIDES DEFAULT 
														// IMPLEMENTATIONS FOR THE METHODS DESCRIBED
														// BY THE SELECTIONLISTENER INTERFACE. 
import org.eclipse.swt.events.SelectionEvent;			// SELECTION EVENTS ARE SENT AS A RESULT OF 
														// WIDGETS BEING SELECTED. 
import org.eclipse.swt.widgets.Button;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Display;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Label;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Shell;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Table;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.TableColumn;				// FOR SWT WINDOWS/WIDGETS	

import bus_project.localization.Messages;

public class RemoveCustomer extends AbstractBusProgramWindow
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected Shell shlRemoveCustomer;					// SHELL WHICH REPRESENTS THIS WINDOW
	private ArrayList<Customer> customers; 				// LIST OF CUSTOMERS
	private ArrayList<Customer> customersRemoved = new ArrayList<Customer>(); 
	private Table customersTable;
	private Table remCustomersTable;

	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	public RemoveCustomer (ArrayList<Customer> cstmrs)
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
		shlRemoveCustomer.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlRemoveCustomer.forceActive();				// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlRemoveCustomer.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlRemoveCustomer.isDisposed()) 
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
		shlRemoveCustomer = new Shell();
		shlRemoveCustomer.setSize(1075, 370);
		shlRemoveCustomer.setText(messages.getString
				("RemoveCustomer.shlRemoveCustomer.text")); 							//$NON-NLS-1$
		
		Button btnExit = new Button(shlRemoveCustomer, SWT.NONE);
		btnExit.setText(messages.getString("btnExit.text")); 							//$NON-NLS-1$
		btnExit.setBounds(973, 295, 75, 25);
		
		Label lblCustomers = new Label(shlRemoveCustomer, SWT.CENTER);
		lblCustomers.setText(messages.getString("lblCustomers.text")); 					//$NON-NLS-1$
		lblCustomers.setBounds(10, 10, 516, 15);
		
		Label lblRemovedCustomers = new Label(shlRemoveCustomer, SWT.CENTER);
		lblRemovedCustomers.setText(messages.getString
				("RemoveCustomer.lblRemovedCustomers.text")); 							//$NON-NLS-1$
		lblRemovedCustomers.setBounds(532, 10, 516, 15);
		
		Button btnDelete = new Button(shlRemoveCustomer, SWT.NONE);
		
		btnDelete.setBounds(10, 295, 255, 25);
		btnDelete.setText(messages.getString("btnDelete.text")); 						//$NON-NLS-1$
		
		Button btnUndoDelete = new Button(shlRemoveCustomer, SWT.NONE);
		
		btnUndoDelete.setBounds(532, 295, 255, 25);
		btnUndoDelete.setText(messages.getString("btnUndoDelete.text")); 				//$NON-NLS-1$
		
		Button btnDeleteAll = new Button(shlRemoveCustomer, SWT.NONE);
		btnDeleteAll.setText(messages.getString("RemoveCustomer.btnDeleteAll.text")); 	//$NON-NLS-1$
		btnDeleteAll.setBounds(271, 295, 255, 25);
		
		/*******************/
		/* CUSTOMERS TABLE */
		/*******************/
		customersTable = new Table(shlRemoveCustomer, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText(messages.getString
				("RemoveCustomer.customersTable.toolTipText"));							//$NON-NLS-1$
		customersTable.setLinesVisible(true);
		customersTable.setHeaderVisible(true);
		customersTable.setBounds(10, 31, 516, 252);
		
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
		
		updateTable(customersTable, customers); 
		
		/***************************/
		/* REMOVED CUSTOMERS TABLE */
		/***************************/
		remCustomersTable = new Table(shlRemoveCustomer, SWT.BORDER | SWT.FULL_SELECTION);
		remCustomersTable.setToolTipText(messages.getString("RemoveCustomer.remCustomersTable.toolTipText")); //$NON-NLS-1$
		remCustomersTable.setLinesVisible(true);
		remCustomersTable.setHeaderVisible(true);
		remCustomersTable.setBounds(532, 31, 516, 252);
		
		TableColumn removedtblclmnID = new TableColumn(remCustomersTable, SWT.NONE);
		removedtblclmnID.setWidth(60);
		removedtblclmnID.setText("ID");
		
		TableColumn removedtblclmnName = new TableColumn(remCustomersTable, SWT.NONE);
		removedtblclmnName.setWidth(160);
		removedtblclmnName.setText("Name");
		
		TableColumn removedtblclmnDate = new TableColumn(remCustomersTable, SWT.NONE);
		removedtblclmnDate.setWidth(100);
		removedtblclmnDate.setText("Date");
		
		TableColumn removedtblclmnSize = new TableColumn(remCustomersTable, SWT.NONE);
		removedtblclmnSize.setWidth(100);
		removedtblclmnSize.setText("Group Size");
		
		TableColumn removedtblclmnRefunds = new TableColumn(remCustomersTable, SWT.NONE);
		removedtblclmnRefunds.setWidth(80);
		removedtblclmnRefunds.setText("Refunds");
		
		btnDelete.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				/********************/
				/* VARIABLE SECTION */
				/********************/ 
				int customerToRemoveIndex 				// INDEX OF CUSTOMER TO REMOVe
					= customersTable.getSelectionIndex(); 
				
				if (customerToRemoveIndex >= 0) 
				{
					customersRemoved.add(customers.remove(customerToRemoveIndex));

					updateIndex(customers); 
					updateTable(customersTable, customers); 
					updateTable(remCustomersTable, customersRemoved); 
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Error: no selection"); 
				}
			}
		});
		
		btnUndoDelete.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				/********************/
				/* VARIABLE SECTION */
				/********************/ 
				Customer removed; 						// CUSTOMER REMOVED TO UNREMOVE
				
				if (remCustomersTable.getSelectionIndex() >= 0) 
				{
					removed = customersRemoved.get(remCustomersTable.getSelectionIndex()); 
					
					if (removed.getIndex() <= customers.size()) 
					{
						reAddCustomer(customers, removed, removed.getIndex(), customersTable); 
					}
					else 
					{
						reAddCustomer(customers, removed, customersTable); 
					}
					customersRemoved.remove(remCustomersTable.getSelectionIndex()); 
					updateIndex(customers); 
					updateTable(remCustomersTable, customersRemoved); 
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Error: no selection"); 
				}
			}
		}); 
		
		btnDeleteAll.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				int confirm; 
				confirm = JOptionPane.showConfirmDialog(null, "Warning: deleting all customers is irreversable. Continue?"); 
				if (confirm == 0) 
				{
					customers.clear(); 					// CLEAR ALL CUSTOMERS
					BusCalculation.unscheduleAll(); 
					updateTable(customersTable, customers); 
				}
			}
		});
				
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				int confirmDelete; 
				if (customersRemoved.size() > 0) 
				{
					confirmDelete = JOptionPane.showConfirmDialog(null, "Confirm", "Are you sure you want to delete these customers?", JOptionPane.YES_NO_OPTION); 
					if (confirmDelete == 1) 			// 1: NO
					{
						return; 
					}
					
					/***********************************/
					/* ELSE PERMA DELETE ALL CUSTOMERS */
					/***********************************/
					else 								// 0: YES
					{
						/***********************************************************/
						/* FOR LOOP TO ITERATE THROUGH TEMP REMOVED CUSTOMERS LIST */
						/***********************************************************/
						for (Customer c: customersRemoved)
						{
							/*********************************************/
							/* METHOD CALL TO UNSCHEDULE CUSTOMER'S TRIP */
							/*********************************************/
							BusCalculation.unscheduleTrip(c); 
						}
						customersRemoved.clear(); 
					}
				}
				
				closeSubWindow(rootShell, shlRemoveCustomer); 	
			}
		});
	}
}
