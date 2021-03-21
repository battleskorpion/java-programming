package bus_project;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BusProgramMenu extends AbstractProgramWindow {

	protected Shell shell;
	private ArrayList<Customer> customers = new ArrayList<Customer>(); 
	//private Calendar date; 	// current date

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		BusProgramMenu window = new BusProgramMenu();
		
		/*****************************/
		/* METHOD TO OPEN GUI WINDOW */
		/*****************************/
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

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		/********************/
		/* VARIABLE SECTION */
		/********************/
		AddCustomer addCustomerWindow = new AddCustomer(customers);
		RemoveCustomer removeCustomerWindow = new RemoveCustomer(customers); 
		ModifyCustomer modifyCustomerWindow = new ModifyCustomer(customers); 
		
		shell = new Shell();
		shell.setSize(382, 209);
		shell.setText("Bus Program");
		
		Button btnAddCustomer = new Button(shell, SWT.NONE);
		btnAddCustomer.setBounds(10, 10, 170, 25);
		btnAddCustomer.setText("Add new customer");
		btnAddCustomer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				/****************************************/
				/* DISABLE MENU WHILE PERFORMING ACTION */
				/****************************************/
				shell.setEnabled(false);
				
				/****************************/
				/* OPEN ADD CUSTOMER WINDOW */
				/****************************/
				addCustomerWindow.open(shell);
					
				/****************************************/
				/* ENABLE  MENU AFTER PERFORMING ACTION */
				/****************************************/
				shell.setEnabled(true);
				
				/******************************************************************/
				/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
				/******************************************************************/
				shell.forceActive();	
			}
		});

		Button btnModifyCustomer = new Button(shell, SWT.NONE);
		btnModifyCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/****************************************/
				/* DISABLE MENU WHILE PERFORMING ACTION */
				/****************************************/
				shell.setEnabled(false);
				
				/****************************/
				/* OPEN ADD CUSTOMER WINDOW */
				/****************************/
				modifyCustomerWindow.open(shell);
					
				/****************************************/
				/* ENABLE  MENU AFTER PERFORMING ACTION */
				/****************************************/
				shell.setEnabled(true);
				
				/******************************************************************/
				/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
				/******************************************************************/
				shell.forceActive();	
			}
		});
		btnModifyCustomer.setBounds(10, 41, 170, 25);
		btnModifyCustomer.setText("Modify Customer");
		
		Button btnListCustomersBySize = new Button(shell, SWT.NONE);
		btnListCustomersBySize.setBounds(10, 72, 170, 25);
		btnListCustomersBySize.setText("List Customers by Group Size");
		
		Button btnProfitByDate = new Button(shell, SWT.NONE);
		btnProfitByDate.setBounds(10, 103, 170, 25);
		btnProfitByDate.setText("Profit by Date");
		
		Button btnRemoveCustomer = new Button(shell, SWT.NONE);
		btnRemoveCustomer.setBounds(186, 10, 170, 25);
		btnRemoveCustomer.setText("Remove customer");
		btnRemoveCustomer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				/****************************************/
				/* DISABLE MENU WHILE PERFORMING ACTION */
				/****************************************/
				shell.setEnabled(false);
				
				/****************************/
				/* OPEN ADD CUSTOMER WINDOW */
				/****************************/
				removeCustomerWindow.open(shell);
					
				/***************************************/
				/* ENABLE MENU AFTER PERFORMING ACTION */
				/***************************************/
				shell.setEnabled(true);
				
				/******************************************************************/
				/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
				/******************************************************************/
				shell.forceActive();	
			}
		});
		
		Button btnListCustomersByName = new Button(shell, SWT.NONE);
		btnListCustomersByName.setBounds(186, 41, 170, 25);
		btnListCustomersByName.setText("List customers by Name");
		
		Button btnBusesNeededByDate = new Button(shell, SWT.NONE);
		btnBusesNeededByDate.setBounds(186, 72, 170, 25);
		btnBusesNeededByDate.setText("Buses needed by Date");
		
		Button btnProfitTotal = new Button(shell, SWT.NONE);
		btnProfitTotal.setBounds(186, 103, 170, 25);
		btnProfitTotal.setText("Total profit details");
		
		/***************/
		/* QUIT BUTTON */
		/***************/
		Button btnQuit = new Button(shell, SWT.NONE);
		btnQuit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				shell.dispose(); 		// QUIT 
			}
		});
		btnQuit.setBounds(124, 134, 120, 25);
		btnQuit.setText("Quit");
	}
}
