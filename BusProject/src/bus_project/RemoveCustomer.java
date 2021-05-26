package bus_project;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import javax.swing.JOptionPane;

public class RemoveCustomer extends AbstractProgramWindow{

	protected Shell shlRemoveCustomer;
	private ArrayList<Customer> customers; 
	private ArrayList<Customer> customersRemoved = new ArrayList<Customer>(); 
	private Table customersTable;
	private Table remCustomersTable;

	public RemoveCustomer (ArrayList<Customer> cstmrs) {
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
		shlRemoveCustomer.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
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
	protected void createContents(Shell rootShell) {
		
		shlRemoveCustomer = new Shell();
		shlRemoveCustomer.setSize(674, 400);
		shlRemoveCustomer.setText(Messages.getString("RemoveCustomer.shlRemoveCustomer.text")); //$NON-NLS-1$
		
		Button btnExit = new Button(shlRemoveCustomer, SWT.NONE);
		btnExit.setText(Messages.getString("btnExit.text")); //$NON-NLS-1$
		btnExit.setBounds(573, 326, 75, 25);
		
		Label lblCustomers = new Label(shlRemoveCustomer, SWT.CENTER);
		lblCustomers.setText(Messages.getString("lblCustomers.text")); //$NON-NLS-1$
		lblCustomers.setBounds(10, 10, 316, 15);
		
		customersTable = new Table(shlRemoveCustomer, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText(Messages.getString("RemoveCustomer.customersTable.toolTipText")); //$NON-NLS-1$
		customersTable.setLinesVisible(true);
		customersTable.setBounds(10, 40, 316, 252);
		updateTable(customersTable, customers); 
		
		Label lblRemovedCustomers = new Label(shlRemoveCustomer, SWT.CENTER);
		lblRemovedCustomers.setText(Messages.getString("RemoveCustomer.lblRemovedCustomers.text")); //$NON-NLS-1$
		lblRemovedCustomers.setBounds(332, 10, 316, 15);
		
		remCustomersTable = new Table(shlRemoveCustomer, SWT.BORDER | SWT.FULL_SELECTION);
		remCustomersTable.setToolTipText(Messages.getString("RemoveCustomer.remCustomersTable.toolTipText")); //$NON-NLS-1$
		remCustomersTable.setLinesVisible(true);
		remCustomersTable.setBounds(332, 40, 316, 252);
		
		Button btnDelete = new Button(shlRemoveCustomer, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				int customerToRemoveIndex = customersTable.getSelectionIndex(); 
				
				if (customerToRemoveIndex >= 0) 
				{
					customersRemoved.add(removeCustomer(customers, customerToRemoveIndex));
					//BusCalculation.unscheduleTrip(customers.get(customerToRemoveIndex)); 
					//customersRemoved.add(customers.remove(customerToRemoveIndex)); 	// remove customer from customers and add to customersRemoved 
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
		btnDelete.setBounds(10, 295, 316, 25);
		btnDelete.setText(Messages.getString("btnDelete.text")); //$NON-NLS-1$
		
		Button btnUndoDelete = new Button(shlRemoveCustomer, SWT.NONE);
		//TODO: make sure everything good good now etc. 
		btnUndoDelete.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				if (remCustomersTable.getSelectionIndex() >= 0) {
					Customer removed = customersRemoved.get(remCustomersTable.getSelectionIndex()); 
					if (removed.getIndex() <= customers.size()) 
					{
						addCustomer(customers, removed, removed.getIndex(), customersTable); 
						//customers.add(removed.getIndex(), removed);
					}
					else 
					{
						addCustomer(customers, removed, customersTable); 
						//customers.add(removed); 
					}
					customersRemoved.remove(remCustomersTable.getSelectionIndex()); 
					updateIndex(customers); 
					//updateTable(customersTable, customers); 
					updateTable(remCustomersTable, customersRemoved); 
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Error: no selection"); 
				}
			}
		});
		btnUndoDelete.setBounds(332, 295, 316, 25);
		btnUndoDelete.setText(Messages.getString("btnUndoDelete.text")); //$NON-NLS-1$
		
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				int confirmDelete; 
				if (customersRemoved.size() > 0) 
				{
					confirmDelete = JOptionPane.showConfirmDialog(null, "Confirm", "Are you sure you want to delete these customers?", JOptionPane.YES_NO_OPTION); 
					if (confirmDelete == 1) //1 = NO
					{
						return; 
					}
				}
				rootShell.forceActive(); 
				shlRemoveCustomer.close(); 	
			}
		});
	}
}
