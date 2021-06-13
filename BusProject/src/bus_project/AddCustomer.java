/********************************************/
/* INFORMATION SECTION 						*/
/* AddCustomer.java							*/
/* Darian Siembab 							*/
/* 											*/
/* WINDOW TO ADD CUSTOMERS AND CUSTOMER		*/
/* INFORMATION, INCLUDING TRIP DATE			*/
/********************************************/ 

package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import org.eclipse.swt.events.SelectionAdapter;			// THIS ADAPTER CLASS PROVIDES DEFAULT 
														// IMPLEMENTATIONS FOR THE METHODS DESCRIBED
														// BY THE SELECTIONLISTENER INTERFACE. 
import org.eclipse.swt.events.SelectionEvent;			// SELECTION EVENTS ARE SENT AS A RESULT OF 
														// WIDGETS BEING SELECTED. 
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import javax.swing.JOptionPane;							// JOPTIONPANE MAKES IT EASY TO POP UP A 
														// STANDARD DIALOG BOX THAT PROMPTS USERS
														// FOR A VALUE OR INFORMS THEM OF SOMETHING.
import org.eclipse.swt.SWT;								// THIS CLASS PROVIDES ACCESS TO A SMALL 
														// NUMBER OF SWT SYSTEM-WIDE METHODS, AND
														// IN ADDITION DEFINES THE PUBLIC CONSTANTS 
														// PROVIDED BY SWT. 
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class AddCustomer extends AbstractBusProgramWindow
{
	/*********************/
	/* CONSTANTS SECTION */
	/*********************/
	private static final String helpWindowText = "To enter a customer, input the customer's name, "
			+ "group size, unique ID number, and trip date. For further clarification, "
			+ "hover over the labels for each input. To select the date of the trip, "
			+ "select a date in the calendar. When ready, select the \"Add\" button."; 
	
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected Shell shlAddCustomer;						// SHELL WHICH REPRESENTS THIS WINDOW
	private ArrayList<Customer> customers; 				// LIST OF CUSTOMERS
	private Text nameField;
	private Text sizeField;
	private Table customersTable;
	private Text idField;
	private DateTime dateTime; 
	private HelpWindow helpWindow; 

	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	public AddCustomer (ArrayList<Customer> cstmrs) 
	{
		customers = cstmrs; 
		helpWindow = new HelpWindow(helpWindowText); 
	}
	
	/************************************************************************************************/
	/* 										 METHOD SECTION 										*/
	/************************************************************************************************/
			
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
		/*********************/ 
		/* INSTANTIATE SHELL */
		/*********************/ 
		shlAddCustomer = new Shell();
		shlAddCustomer.setSize(820, 410);
		shlAddCustomer.setText(Messages.getString
				("AddCustomer.shlAddCustomer.text")); 			//$NON-NLS-1$
		
		/**********/
		/* LABELS */
		/**********/
		Label lblName = new Label(shlAddCustomer, SWT.NONE);
		lblName.setBounds(10, 13, 109, 24);
		lblName.setText(Messages.getString("lblName.text")); 	//$NON-NLS-1$
		lblName.setToolTipText(Messages.getString
				("lblName.tooltipText")); 						//$NON-NLS-1$
		
		Label lblSize = new Label(shlAddCustomer, SWT.NONE);
		lblSize.setText(Messages.getString("lblSize.text")); 	//$NON-NLS-1$
		lblSize.setToolTipText(Messages.getCompoundString
				("lblSize.toolTipText", new Object[] 
				{Integer.valueOf(BusCalculation.MIN_CAPACITY), 
				Integer.valueOf(BusCalculation.MAX_PAX)})); 	//$NON-NLS-1$
		lblSize.setBounds(10, 43, 109, 24);
		
		Label lblTripDate = new Label(shlAddCustomer, SWT.NONE);
		lblTripDate.setBounds(10, 103, 109, 15);
		lblTripDate.setText(Messages.getString
				("lblTripDate.text")); 							//$NON-NLS-1$
		
		Label lblID = new Label(shlAddCustomer, SWT.NONE);
		lblID.setBounds(10, 71, 91, 26);
		lblID.setText(Messages.getString("lblID.text")); 		//$NON-NLS-1$
		lblID.setToolTipText(Messages.getString
				("lblID.tooltipText")); 						//$NON-NLS-1$
		
		Label lblCustomers = new Label(shlAddCustomer, SWT.CENTER);
		lblCustomers.setBounds(279, 13, 516, 15);
		lblCustomers.setText(Messages.getString
				("lblCustomers.text")); 						//$NON-NLS-1$
		
		Label lblNameWarningIcon = new Label(shlAddCustomer, SWT.NONE);
		lblNameWarningIcon.setBackground(SWTResourceManager.getColor
				(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNameWarningIcon.setForeground(SWTResourceManager.getColor
				(255, 255, 0));
		lblNameWarningIcon.setFont(SWTResourceManager.getFont
				("Segoe UI", 11, SWT.BOLD));
		lblNameWarningIcon.setBounds(249, 13, 24, 24);
		lblNameWarningIcon.setText(Messages.getString
				("lblNameWarningIcon.text")); 					//$NON-NLS-1$
		lblNameWarningIcon.setVisible(false);
		
		/***********/
		/* BUTTONS */
		/***********/
		Button btnExit = new Button(shlAddCustomer, SWT.NONE);
		btnExit.setBounds(719, 316, 75, 25);
		btnExit.setText(Messages.getString("btnExit.text")); 	//$NON-NLS-1$
		
		Button btnAdd = new Button(shlAddCustomer, SWT.NONE);
		btnAdd.setBounds(10, 281, 233, 25);
		btnAdd.setText(Messages.getString("btnAdd.text")); 		//$NON-NLS-1$
		
		/**********/
		/* FIELDS */
		/**********/
		nameField = new Text(shlAddCustomer, SWT.BORDER);
		nameField.setText("");
		nameField.setBounds(140, 13, 103, 24);
		
		sizeField = new Text(shlAddCustomer, SWT.BORDER);
		sizeField.setBounds(140, 43, 103, 24);
	
		idField = new Text(shlAddCustomer, SWT.BORDER);
		idField.setBounds(140, 73, 103, 24);
		
		/************/
		/* CALENDAR */
		/************/
		dateTime = new DateTime(shlAddCustomer, SWT.BORDER | SWT.CALENDAR);
		dateTime.setBounds(10, 124, 233, 151);
		
		/*********/
		/* TABLE */
		/*********/ 
		customersTable = new Table(shlAddCustomer, 
				SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText(Messages.getString
				("AddCustomer.customersTable.toolTipText")); 	//$NON-NLS-1$
		customersTable.setBounds(279, 43, 516, 263);
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

		/************/
		/* MENU BAR */
		/************/
		Menu menu = new Menu(shlAddCustomer, SWT.BAR);
		shlAddCustomer.setMenuBar(menu);
		
		MenuItem mntmHelp = new MenuItem(menu, SWT.NONE);
		
		mntmHelp.setText(Messages.getString("mntmHelp.text")); //$NON-NLS-1$
		
		/*****************/
		/* SET TAB ORDER */
		/*****************/
		shlAddCustomer.setTabList(new Control[]{nameField, sizeField, idField, 
				dateTime, btnAdd, customersTable, btnExit});
		
		/************************************************/
		/* METHOD CALL UPDATE TABLE (LIST OF CUSTOMERS) */
		/************************************************/
		updateTable(customersTable, customers);
		
		/**************************/
		/* EVENT LISTENER SECTION */
		/**************************/
		
		/***********************/
		/* ADD CUSTOMER BUTTON */
		/***********************/ 
		btnAdd.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			/****************************************************************************************/
			/* PRECONDITION:  SENT WHEN CONTROL IS SELECTED				  							*/
			/* POSTCONDITION: ATTEMPTS TO ADD A NEW CUSTOMER USING SPECIFIED DETAILS				*/
			/****************************************************************************************/
			{
				/********************/
				/* VARIABLE SECTION */
				/********************/ 
				Customer customer; 
				int index = customers.size(); 						// INDEX TO ADD CUSTOMER AT 
				int id; 											// CUSTOMER ID
				Text[] fields = {nameField, sizeField, idField};	// INPUT FIELDS
				
				/*******************/
				/* TRY TO PARSE ID */
				/*******************/
				try 
				{
					id = Integer.parseInt(idField.getText()); 

					/**********************************************************************/
					/* DISPLAY ERROR MESSAGE AND CANCEL IF ID WAS INVALID (IS NOT UNIQUE) */
					/**********************************************************************/
					if (!uniqueID(id, customers))
					{
						JOptionPane.showMessageDialog(null, 
								"Invalid ID (Already taken)!"); 
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
					return; 
				}
				
				/***********************************************************/
				/* DISPLAY ERROR MESSAGE AND CANCEL IF NAME FIELD IS EMPTY */
				/***********************************************************/
				if (nameField.getText().trim().equals(""))
				{
					JOptionPane.showMessageDialog(null, 
							"Error: Name is required!");
					return; 
				}
				
				/***********************************************************/
				/* DISPLAY ERROR MESSAGE AND CANCEL IF SIZE FIELD IS EMPTY */
				/***********************************************************/
				if (sizeField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, 
							"Error: Size is required!");
					return;
				}
				
				/*******************************************************/
				/* DISPLAY ERROR MESSAGE AND CANCEL IF DATE IS INVALID */
				/*******************************************************/
				if(!vaildDate(dateTime))
				{
					JOptionPane.showMessageDialog(null, 
							"Error: Invalid date! (trip date must be in the future)");
					return; 
				}
				
				/****************/ 
				/* ADD CUSTOMER */
				/****************/ 
				customer = new Customer(); 							// CREATE NEW CUSTOMER
				setCustomerDetails(customer, nameField, 			// SET DETAILS OF NEW CUSTOMER
						sizeField, index, id, dateTime); 			
				clearInput(fields); 								// CLEAR CUSTOMER DETAILS FIELDS
				addCustomer(customers, customer, 					// ADD NEW CUSTOMER TO 
						index, customersTable); 					// CUSTOMER LIST
			}
		});
		
		/***********************/
		/* NAME FIELD MODIFIED */
		/***********************/ 
		nameField.addModifyListener(new ModifyListener() 
		{
			public void modifyText(ModifyEvent e) 
			/****************************************************************************************/
			/* PRECONDITION:  SENT WHEN RECIEVER'S TEXT IS MODIFIED						  			*/
			/* POSTCONDITION: DISPLAYS WARNING IF NAME IS TOO LONG AND WILL BE TRUNCATED			*/
			/****************************************************************************************/
			{
				/*********************************************************************/
				/* IF WARNING ICON IS NOT VISIBLE AND SHOULD BE (INPUT IS TOO LONG), */
				/* SET IT VISIBLE 													 */
				/*********************************************************************/
				if (!lblNameWarningIcon.isVisible())
				{
					if (nameField.getText().length() > Customer.MAX_NAME_LENGTH)
					{
						lblNameWarningIcon.setVisible(true);
					}
				}
				/*************************************************************************/
				/* IF WARNING ICON IS VISIBLE AND SHOULD NOT BE (INPUT IS NOT TOO LONG), */
				/* SET IT NOT VISIBLE 													 */
				/*************************************************************************/
				else if (nameField.getText().length() <= Customer.MAX_NAME_LENGTH)
				{
					lblNameWarningIcon.setVisible(false);
				}
			}
		});
		
		/**********************/
		/* MENU BAR HELP MENU */
		/**********************/ 
		mntmHelp.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e) 
			/****************************************************************************************/
			/* PRECONDITION:  SENT WHEN CONTROL IS SELECTED								  			*/
			/* POSTCONDITION: OPENS HELP WINDOW					 									*/
			/****************************************************************************************/
			{
				openSubWindow(helpWindow, shlAddCustomer);
			}
		});
		
		/***************/
		/* EXIT BUTTON */
		/***************/ 
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			/****************************************************************************************/
			/* PRECONDITION:  SENT WHEN CONTROL IS SELECTED								  			*/
			/* POSTCONDITION: CLOSES THIS WINDOW					 								*/
			/****************************************************************************************/
			{
				closeSubWindow(rootShell, shlAddCustomer); 
			}
		});
	}

}
