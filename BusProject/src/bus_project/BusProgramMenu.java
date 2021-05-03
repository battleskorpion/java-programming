package bus_project;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;

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
	protected void createContents() {
		/********************/
		/* VARIABLE SECTION */
		/********************/
		AddCustomer addCustomerWindow = new AddCustomer(customers);
		RemoveCustomer removeCustomerWindow = new RemoveCustomer(customers); 
		ModifyCustomer modifyCustomerWindow = new ModifyCustomer(customers); 
		ListCustomers listCustomersWindow = new ListCustomers(customers); 
		BusesByDate busesByDateWindow = new BusesByDate(customers); 
		ProfitByDate profitByDateWindow = new ProfitByDate(customers); 
		TotalProfitDetails totalProfitWindow = new TotalProfitDetails(customers); 
		
		// TODO: label shell 
		shell = new Shell();
		// TODO: label method calls
		shell.setSize(382, 209);
		shell.setText("Bus Program");
		
		// TODO: label add customer button
		Button btnAddCustomer = new Button(shell, SWT.NONE);
		// TODO: label method calls
		btnAddCustomer.setBounds(10, 10, 170, 25);
		btnAddCustomer.setText("Add new customer");
		// TODO: label selection listener method call whatever
		btnAddCustomer.addSelectionListener(new SelectionAdapter() {
			// TODO: label method
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(addCustomerWindow, shell);
			}
		});

		// TODO: label modify customer button
		Button btnModifyCustomer = new Button(shell, SWT.NONE);
		// TODO: label method calls
		btnModifyCustomer.setBounds(10, 41, 170, 25);
		btnModifyCustomer.setText("Modify Customer");
		// TODO: label selection listener method call whatever
		btnModifyCustomer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			// TODO: label method 
			public void widgetSelected(SelectionEvent e) {
				openSubWindow(modifyCustomerWindow, shell);
			}
		});
		
		// TODO: label list customers by name button
		Button btnListCustomersByName = new Button(shell, SWT.NONE);
		// TODO: label method calls
		btnListCustomersByName.setBounds(186, 41, 170, 25);
		btnListCustomersByName.setText("List customers by Name");
		// TODO: label selection listener method call whatever
		btnListCustomersByName.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO: label method
				listCustomersWindow.setSortByName();
				openSubWindow(listCustomersWindow, shell);
			}
		}); 
		
		// TODO: label button
		Button btnListCustomersBySize = new Button(shell, SWT.NONE);
		// TODO: label method calls
		btnListCustomersBySize.setBounds(10, 72, 170, 25);
		btnListCustomersBySize.setText("List Customers by Group Size");
		// TODO: label selection listener method call whatever
		btnListCustomersBySize.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			// TODO: label method
			public void widgetSelected(SelectionEvent e) {
				listCustomersWindow.setSortBySize();
				openSubWindow(listCustomersWindow, shell);
			}
		}); 
		
		// TODO: label button
		Button btnProfitByDate = new Button(shell, SWT.NONE);
		btnProfitByDate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openSubWindow(profitByDateWindow, shell); 
			}
		});
		// TODO: label method calls
		btnProfitByDate.setBounds(10, 103, 170, 25);
		btnProfitByDate.setText("Profit by Date");
		
		// TODO: label button
		Button btnRemoveCustomer = new Button(shell, SWT.NONE);
		// TODO: label method calls
		btnRemoveCustomer.setBounds(186, 10, 170, 25);
		btnRemoveCustomer.setText("Remove customer");
		// TODO: label selection listener method call whatever
		btnRemoveCustomer.addSelectionListener(new SelectionAdapter() {
			// TODO: label method
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(removeCustomerWindow, shell);	
			}
		});
		
		// TODO: label button
		Button btnBusesNeededByDate = new Button(shell, SWT.NONE);
		// TODO: label method calls
		btnBusesNeededByDate.setBounds(186, 72, 170, 25);
		btnBusesNeededByDate.setText("Buses needed by Date");
		// TODO: label selection listener method call whatever
		btnBusesNeededByDate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openSubWindow(busesByDateWindow, shell); 
			}
		});	
		
		// TODO: label button
		Button btnProfitTotal = new Button(shell, SWT.NONE);
		btnProfitTotal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openSubWindow(totalProfitWindow, shell);
			}
		});
		// TODO: label method calls
		btnProfitTotal.setBounds(186, 103, 170, 25);
		btnProfitTotal.setText("Total profit details");
		
		/***************/
		/* QUIT BUTTON */
		/***************/
		Button btnQuit = new Button(shell, SWT.NONE);
		// TODO: label method calls
		// TODO: label selection listener method call whatever
		btnQuit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				shell.dispose(); 		// QUIT 
			}
		});
		btnQuit.setBounds(124, 134, 120, 25);
		btnQuit.setText("Quit");
		shell.setTabList(new Control[]{btnAddCustomer, btnRemoveCustomer, btnBusesNeededByDate, btnModifyCustomer, btnListCustomersByName, btnListCustomersBySize, btnProfitByDate, btnProfitTotal, btnQuit}); 
	}
}
