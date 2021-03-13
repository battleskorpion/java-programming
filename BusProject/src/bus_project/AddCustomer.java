package bus_project;

import java.time.*; 
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;

public class AddCustomer extends AbstractProgramWindow {

	protected Shell shlAddCustomer;
	private ArrayList<Customer> customers = new ArrayList<Customer>(); 
	private Text nameField;
	private Text sizeField;
	private Table customersTable;
	private Text indexField;

	public AddCustomer (ArrayList<Customer> cstmrs) {
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
		shlAddCustomer.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlAddCustomer.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlAddCustomer.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlAddCustomer.isDisposed()) 
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
		
		shlAddCustomer = new Shell();
		shlAddCustomer.setSize(600, 400);
		shlAddCustomer.setText("Add Customer");
		
		Label lblName = new Label(shlAddCustomer, SWT.NONE);
		lblName.setBounds(10, 13, 73, 15);
		lblName.setText("Name:");
		
		nameField = new Text(shlAddCustomer, SWT.BORDER);
		nameField.setText("");
		nameField.setBounds(89, 10, 80, 24);
		
		Label lblSize = new Label(shlAddCustomer, SWT.NONE);
		lblSize.setText("Group size: ");
		lblSize.setBounds(10, 43, 73, 15);
		
		sizeField = new Text(shlAddCustomer, SWT.BORDER);
		sizeField.setBounds(89, 40, 80, 24);
		
		Label lblTripDate = new Label(shlAddCustomer, SWT.NONE);
		lblTripDate.setBounds(10, 92, 73, 15);
		lblTripDate.setText("Trip Date: ");
		
		DateTime dateTime = new DateTime(shlAddCustomer, SWT.BORDER | SWT.CALENDAR);
		dateTime.setBounds(10, 113, 233, 151);
		
		Button btnExit = new Button(shlAddCustomer, SWT.NONE);
		btnExit.setBounds(499, 326, 75, 25);
		btnExit.setText("Exit");
		
		Button btnAdd = new Button(shlAddCustomer, SWT.NONE);
		btnAdd.setBounds(89, 270, 75, 25);
		btnAdd.setText("Add");
		
		customersTable = new Table(shlAddCustomer, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText("");
		customersTable.setBounds(264, 43, 213, 252);
		customersTable.setLinesVisible(true);
		updateTable(customersTable, customers); 
		
		Label lblCustomers = new Label(shlAddCustomer, SWT.NONE);
		lblCustomers.setBounds(335, 13, 63, 15);
		lblCustomers.setText("Customers: ");
		
		indexField = new Text(shlAddCustomer, SWT.BORDER);
		indexField.setBounds(89, 70, 80, 24);
		
		Label lblNumber = new Label(shlAddCustomer, SWT.NONE);
		lblNumber.setBounds(10, 71, 55, 15);
		lblNumber.setText("Index: ");
		
		btnAdd.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				Customer customer; 
				int index; 				// INDEX TO ADD CUSTOMER AT 
				String tripDate; 
				
				try
				{
					//if (nameField.getText().equals(""))
					//{
						/***************/
						/* PRINT ERROR */
						/***************/
					//	JOptionPane.showMessageDialog(null, "Error: No group name specified!"); 
					//}
					//else if (Integer.parseInt(sizeField.getText()) < 0) 
					//{
						/***************/
						/* PRINT ERROR */
						/***************/
					//	JOptionPane.showMessageDialog(null, "Error: Number of persons can not be negative!"); 
					//}
					//else
					//{
						customer = new Customer(); 
						
						customer.setName(nameField.getText()); 
						customer.setNumPersons(Integer.parseInt(sizeField.getText())); 
						tripDate = dateTime.getYear() + "-" + dateTime.getMonth() + "-" + dateTime.getDay(); 
						customer.setDate(LocalDate.parse(tripDate)); 
						index = Integer.parseInt(indexField.getText()); 
								
						addCustomer(customer, index, customersTable); 	// has issue
					//}
				}
				catch (Exception exc) 
				{
					//switch(exc.)
					/***************/
					/* PRINT ERROR */
					/***************/
					JOptionPane.showMessageDialog(null, "Error: Improper numerical input!"); 
				}
			}
		});
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				rootShell.forceActive(); 
				shlAddCustomer.close(); 
			}
		});
	}
	
	//private void inputCustomer() {
	//	
	//}
	
	private void addCustomer(Customer customer, int index, Table tbl) {
		customers.add(index, customer);
		updateTable(tbl, customers); 
	}
}
