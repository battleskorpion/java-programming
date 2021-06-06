package bus_project;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

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
	
	/************************************************************************************************/
	/* 										 METHOD SECTION 										*/
	/************************************************************************************************/
			
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
		shlAddCustomer.forceActive();					// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlAddCustomer.layout();
		
		/*******************************/
		/* WHILE SHELL IS NOT DISPOSED */
		/*******************************/
		while (!shlAddCustomer.isDisposed()) 
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

	/********************************************************************************/
	/* PRECONDITION:  WINDOW IS TO BE OPENED, ELEMENTS NEED TO BE ADDED TO WINDOW	*/
	/* POSTCONDITION: ADDS ELEMENTS TO WINDOW										*/
	/********************************************************************************/
	protected void createContents(Shell rootShell) 
	{
		shlAddCustomer = new Shell();
		shlAddCustomer.setSize(820, 400);
		shlAddCustomer.setText(Messages.getString
				("AddCustomer.shlAddCustomer.text")); 			//$NON-NLS-1$
		
		Label lblName = new Label(shlAddCustomer, SWT.NONE);
		lblName.setBounds(10, 13, 109, 15);
		lblName.setText(Messages.getString("lblName.text")); 	//$NON-NLS-1$
		lblName.setToolTipText(Messages.getString
				("lblName.tooltipText")); 						//$NON-NLS-1$
		
		nameField = new Text(shlAddCustomer, SWT.BORDER);
		nameField.setText("");
		nameField.setBounds(140, 13, 103, 24);
		
		Label lblSize = new Label(shlAddCustomer, SWT.NONE);
		lblSize.setText(Messages.getString("lblSize.text")); 	//$NON-NLS-1$
		lblSize.setToolTipText(Messages.getCompoundString
				("lblSize.toolTipText", new Object[] 
				{Integer.valueOf(BusCalculation.MIN_CAPACITY), 
				Integer.valueOf(BusCalculation.MAX_PAX)})); 	//$NON-NLS-1$
		lblSize.setBounds(10, 43, 109, 15);
		
		sizeField = new Text(shlAddCustomer, SWT.BORDER);
		sizeField.setBounds(140, 43, 103, 24);
		
		Label lblTripDate = new Label(shlAddCustomer, SWT.NONE);
		lblTripDate.setBounds(10, 92, 109, 15);
		lblTripDate.setText(Messages.getString
				("lblTripDate.text")); 							//$NON-NLS-1$
		
		dateTime = new DateTime(shlAddCustomer, SWT.BORDER | SWT.CALENDAR);
		dateTime.setBounds(10, 113, 233, 151);
		
		Button btnExit = new Button(shlAddCustomer, SWT.NONE);
		btnExit.setBounds(720, 326, 75, 25);
		btnExit.setText(Messages.getString("btnExit.text")); 	//$NON-NLS-1$
		
		Button btnAdd = new Button(shlAddCustomer, SWT.NONE);
		btnAdd.setBounds(10, 270, 233, 25);
		btnAdd.setText(Messages.getString("btnAdd.text")); 		//$NON-NLS-1$
		
		/************/
		/* ID LABEL */
		/************/ 
		Label lblID = new Label(shlAddCustomer, SWT.NONE);
		lblID.setBounds(10, 71, 91, 15);
		lblID.setText(Messages.getString("lblID.text")); 		//$NON-NLS-1$
		lblID.setToolTipText(Messages.getString
				("lblID.tooltipText")); 						//$NON-NLS-1$
		
		/************/
		/* ID FIELD */
		/************/ 
		idField = new Text(shlAddCustomer, SWT.BORDER);
		idField.setBounds(140, 73, 103, 24);
		
		/*******************/
		/* CUSTOMERS LABEL */
		/*******************/ 
		Label lblCustomers = new Label(shlAddCustomer, SWT.CENTER);
		lblCustomers.setBounds(279, 13, 516, 15);
		lblCustomers.setText(Messages.getString
				("lblCustomers.text")); 						//$NON-NLS-1$
		
		/*******************/
		/* CUSTOMERS TABLE */
		/*******************/ 
		customersTable = new Table(shlAddCustomer, 
				SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText(Messages.getString
				("AddCustomer.customersTable.toolTipText")); 	//$NON-NLS-1$
		customersTable.setBounds(279, 43, 516, 252);
		customersTable.setLinesVisible(true);	
		customersTable.setHeaderVisible(true);
		
		TableColumn tblclmnID = new TableColumn(customersTable, SWT.NONE);
		tblclmnID.setWidth(60);
		tblclmnID.setText(Messages.getString
				("AddCustomer.tblclmnID.text")); 				//$NON-NLS-1$

		TableColumn tblclmnName = new TableColumn(customersTable, SWT.NONE);
		tblclmnName.setWidth(160);
		tblclmnName.setText(Messages.getString
				("AddCustomer.tblclmnName.text")); 				//$NON-NLS-1$

		TableColumn tblclmnDate = new TableColumn(customersTable, SWT.NONE);
		tblclmnDate.setWidth(100);
		tblclmnDate.setText(Messages.getString
				("AddCustomer.tblclmnDate.text")); 				//$NON-NLS-1$
		
		TableColumn tblclmnSize = new TableColumn(customersTable, SWT.NONE);
		tblclmnSize.setWidth(100);
		tblclmnSize.setText(Messages.getString
				("AddCustomer.tblclmnSize.text")); 				//$NON-NLS-1$
		
		TableColumn tblclmnRefunds = new TableColumn(customersTable, SWT.NONE);
		tblclmnRefunds.setWidth(80);
		tblclmnRefunds.setText(Messages.getString
				("AddCustomer.tblclmnRefunds.text")); 			//$NON-NLS-1$
			
		shlAddCustomer.setTabList(new Control[]{nameField, sizeField, idField, 
				dateTime, btnAdd, customersTable, btnExit});
		
		Label lblNameWarningIcon = new Label(shlAddCustomer, SWT.NONE);
		lblNameWarningIcon.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblNameWarningIcon.setForeground(SWTResourceManager.getColor(255, 255, 0));
		lblNameWarningIcon.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblNameWarningIcon.setBounds(249, 13, 24, 24);
		lblNameWarningIcon.setText(Messages.getString("lblNameWarningIcon.text")); //$NON-NLS-1$
		lblNameWarningIcon.setVisible(false);
		
		updateTable(customersTable, customers); 
		
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
					try 
					{
						id = Integer.parseInt(idField.getText()); 
						if (!uniqueID(id))
						{
							JOptionPane.showMessageDialog(null, 
									"Invalid ID (Already taken)!"); 
							return; 
						}
					}
					catch (Exception exc) 
					{
						JOptionPane.showMessageDialog(null, 
								"Invalid ID!"); 
						return; 
					}
					
					// TODO: label if
					if (!legalCustomerAddition())
					{
						/***************/
						/* PRINT ERROR */
						/***************/
						JOptionPane.showMessageDialog(null, 
								"Error: Invalid customer detail(s)!"); 
					}
					else
					{
						// TODO: label method calls
						// TODO: label vars etc. 
						customer = new Customer(); 
						Text[] fields = {nameField, sizeField, idField};	
						setCustomerDetails(customer, nameField, sizeField, 
								index, id, dateTime); 
						clearInput(fields); 
						addCustomer(customers, customer, index, customersTable); 	
					}
				}
				catch (Exception exc) 
				{
					//switch(exc.)
					/***************/
					/* PRINT ERROR */
					/***************/
					JOptionPane.showMessageDialog(null, 
							"Error: Improper input or other error.");  
				}
			}
		});
		
		nameField.addModifyListener(new ModifyListener() 
		{
			public void modifyText(ModifyEvent e) 
			{
				if (!lblNameWarningIcon.isVisible())
				{
					if (nameField.getText().length() > 20)
					{
						lblNameWarningIcon.setVisible(true);
					}
				}
				else if (nameField.getText().length() <= 20)
				{
					lblNameWarningIcon.setVisible(false);
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
	
	// TODO: label method
	private boolean legalCustomerAddition() 
	{
		int id = Integer.parseInt(idField.getText()); 
		if (nameField.getText().trim().equals("") 
				|| sizeField.getText().equals("") 
				|| !vaildDate(dateTime))
		{
			return false; 
		}
		return true; 
	}
	private boolean uniqueID(int id)
	{
		for (int i = 0; i < customers.size(); i++) 
		{
			 if (customers.get(i).getId() == id) 
			 {
				 return false; 
			 }
		}
		return true; 
	}
}
