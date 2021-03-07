package bus_project;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BusProgramMenu {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BusProgramMenu window = new BusProgramMenu();
			window.open();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(382, 209);
		shell.setText("Bus Program");
		
		Button btnAddCustomer = new Button(shell, SWT.NONE);
		btnAddCustomer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				
			}
		});
		btnAddCustomer.setBounds(10, 10, 170, 25);
		btnAddCustomer.setText("Add new customer");
		
		Button btnModifyCustomer = new Button(shell, SWT.NONE);
		btnModifyCustomer.setBounds(10, 41, 170, 25);
		btnModifyCustomer.setText("Modify Customer");
		
		Button btnListCustomersBySize = new Button(shell, SWT.NONE);
		btnListCustomersBySize.setBounds(10, 72, 170, 25);
		btnListCustomersBySize.setText("List Customers by Group Size");
		
		Button btnProfitByDate = new Button(shell, SWT.NONE);
		btnProfitByDate.setBounds(10, 103, 170, 25);
		btnProfitByDate.setText("Profit by Date");
		
		Button btnQuit = new Button(shell, SWT.NONE);
		btnQuit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				shell.dispose(); 		
			}
		});
		btnQuit.setBounds(124, 134, 120, 25);
		btnQuit.setText("Quit");
		
		Button btnRemoveCustomer = new Button(shell, SWT.NONE);
		btnRemoveCustomer.setBounds(186, 10, 170, 25);
		btnRemoveCustomer.setText("Remove customer");
		
		Button btnListCustomersByName = new Button(shell, SWT.NONE);
		btnListCustomersByName.setBounds(186, 41, 170, 25);
		btnListCustomersByName.setText("List customers by Name");
		
		Button btnBusesNeededByDate = new Button(shell, SWT.NONE);
		btnBusesNeededByDate.setBounds(186, 72, 170, 25);
		btnBusesNeededByDate.setText("Buses needed by Date");
		
		Button btnProfitTotal = new Button(shell, SWT.NONE);
		btnProfitTotal.setBounds(186, 103, 170, 25);
		btnProfitTotal.setText("Total profit details");

	}
}
