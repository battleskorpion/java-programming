package bus_project;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class BusProgramMenu extends AbstractProgramWindow {

	//TODO: test 
	// TODO: comment vars
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected Shell shell;
	private ArrayList<Customer> customers = new ArrayList<Customer>(); 
	//private Calendar date; 	// current date

	/**
	 * Launch the application.
	 * @param args
	 */
	// TODO: better comment, as to why this is here
	public static void main(String[] args) 
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		BusProgramMenu window = new BusProgramMenu();
		
		/*****************************/
		/* METHOD TO OPEN GUI WINDOW */
		/*****************************/
		// TODO: also label try/catch? 
		try 
		{
			window.open();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
	// TODO: why is there two open functions are they both necessary? 
	
	public void open()
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
		createContents();

		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shell.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shell.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shell.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shell.isDisposed()) 
		{
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}
	
	public void open(Shell shell)
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
		createContents();

		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shell.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shell.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shell.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shell.isDisposed()) 
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
	protected void createContents() 
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		
		/***********/
		/* WINDOWS */
		/***********/
		AddCustomer addCustomerWindow = new AddCustomer(customers);
		RemoveCustomer removeCustomerWindow = new RemoveCustomer(customers); 
		ModifyCustomer modifyCustomerWindow = new ModifyCustomer(customers); 
		ListCustomers listCustomersWindow = new ListCustomers(customers); 
		BusesByDate busesByDateWindow = new BusesByDate(customers); 
		ProfitByDate profitByDateWindow = new ProfitByDate(customers); 
		TotalProfitDetails totalProfitWindow = new TotalProfitDetails(customers); 
	
		/*********/
		/* SHELL */
		/*********/
		shell = new Shell();
		shell.setSize(358, 216);
		shell.setText("Bus Program");
		shell.setLayout(new GridLayout(2, true));
				
		/***********/
		/* BUTTONS */
		/***********/
		Button btnAddCustomer = new Button(shell, SWT.NONE);
		GridData gd_btnAddCustomer = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		
		Button btnRemoveCustomer = new Button(shell, SWT.NONE);
		GridData gd_btnRemoveCustomer = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		
		Button btnModifyCustomer = new Button(shell, SWT.NONE);
		GridData gd_btnModifyCustomer = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		
		Button btnListCustomersByName = new Button(shell, SWT.NONE);
		GridData gd_btnListCustomersByName = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		
		Button btnListCustomersBySize = new Button(shell, SWT.NONE);
		GridData gd_btnListCustomersBySize = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		
		Button btnBusesNeededByDate = new Button(shell, SWT.NONE);
		GridData gd_btnBusesNeededByDate = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		
		Button btnProfitByDate = new Button(shell, SWT.NONE);
		GridData gd_btnProfitByDate = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		
		Button btnProfitTotal = new Button(shell, SWT.NONE);
		GridData gd_btnProfitTotal = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		
		Button btnQuit = new Button(shell, SWT.NONE);
		GridData gd_btnQuit = new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1);
		
		// TODO: label add customer button
		
		gd_btnAddCustomer.widthHint = 167;
		btnAddCustomer.setLayoutData(gd_btnAddCustomer);
		btnAddCustomer.setText("Add new customer");
		// TODO: label selection listener method call whatever
		btnAddCustomer.addSelectionListener(new SelectionAdapter() {
			// TODO: label method
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(addCustomerWindow, shell);
			}
		});
		
		// TODO: label button
		
		gd_btnRemoveCustomer.widthHint = 148;
		btnRemoveCustomer.setLayoutData(gd_btnRemoveCustomer);
		btnRemoveCustomer.setText("Remove customer");
		// TODO: label selection listener method call whatever
		btnRemoveCustomer.addSelectionListener(new SelectionAdapter() 
		{
			// TODO: label method
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(removeCustomerWindow, shell);	
			}
		});
		
		// TODO: label modify customer button
				
		gd_btnModifyCustomer.widthHint = 167;
		btnModifyCustomer.setLayoutData(gd_btnModifyCustomer);
		btnModifyCustomer.setText("Modify Customer");
		// TODO: label selection listener method call whatever
		btnModifyCustomer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			// TODO: label method 
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(modifyCustomerWindow, shell);
			}
		});

		
		// TODO: label list customers by name button
		
		gd_btnListCustomersByName.widthHint = 141;
		btnListCustomersByName.setLayoutData(gd_btnListCustomersByName);
		btnListCustomersByName.setText("List customers by Name");
		// TODO: label selection listener method call whatever
		btnListCustomersByName.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				// TODO: label method
				listCustomersWindow.setSortByName();
				openSubWindow(listCustomersWindow, shell);
			}
		}); 
		
		// TODO: label button
		
		gd_btnListCustomersBySize.widthHint = 167;
		btnListCustomersBySize.setLayoutData(gd_btnListCustomersBySize);
		btnListCustomersBySize.setText("List Customers by Group Size");
		// TODO: label selection listener method call whatever
		btnListCustomersBySize.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			// TODO: label method
			public void widgetSelected(SelectionEvent e) 
			{
				listCustomersWindow.setSortBySize();
				openSubWindow(listCustomersWindow, shell);
			}
		}); 
		
		// TODO: label button
		
		gd_btnBusesNeededByDate.widthHint = 148;
		btnBusesNeededByDate.setLayoutData(gd_btnBusesNeededByDate);
		btnBusesNeededByDate.setText("Buses needed by Date");
		// TODO: label selection listener method call whatever
		btnBusesNeededByDate.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) {
				openSubWindow(busesByDateWindow, shell); 
			}
		});	
		
		// TODO: label button
		
		gd_btnProfitByDate.widthHint = 167;
		btnProfitByDate.setLayoutData(gd_btnProfitByDate);
		btnProfitByDate.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) {
				openSubWindow(profitByDateWindow, shell); 
			}
		});
		btnProfitByDate.setText("Profit by Date");
		
		// TODO: label button
		
		gd_btnProfitTotal.widthHint = 148;
		btnProfitTotal.setLayoutData(gd_btnProfitTotal);
		btnProfitTotal.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) {
				openSubWindow(totalProfitWindow, shell);
			}
		});
		btnProfitTotal.setText("Total profit details");

		/***************/
		/* QUIT BUTTON */
		/***************/
		
		gd_btnQuit.heightHint = 47;
		gd_btnQuit.widthHint = 332;
		btnQuit.setLayoutData(gd_btnQuit);
		// TODO: label method calls
		// TODO: label selection listener method call whatever
		btnQuit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				shell.dispose(); 		// QUIT 
			}
		});
		btnQuit.setText("Quit");
		
		/************/
		/* TAB LIST */
		/************/ 
		shell.setTabList(new Control[]{btnAddCustomer, btnRemoveCustomer, btnBusesNeededByDate, btnModifyCustomer, btnListCustomersByName, btnListCustomersBySize, btnProfitByDate, btnProfitTotal, btnQuit}); 
	}
}
