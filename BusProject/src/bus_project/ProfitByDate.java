package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import java.util.function.Function;
import org.eclipse.swt.SWT;								// THIS CLASS PROVIDES ACCESS TO A SMALL 
														// NUMBER OF SWT SYSTEM-WIDE METHODS, AND
														// IN ADDITION DEFINES THE PUBLIC CONSTANTS 
														// PROVIDED BY SWT. 
import org.eclipse.swt.events.SelectionAdapter;			// THIS ADAPTER CLASS PROVIDES DEFAULT 
														// IMPLEMENTATIONS FOR THE METHODS DESCRIBED
														// BY THE SELECTIONLISTENER INTERFACE. 
import org.eclipse.swt.events.SelectionEvent;			// SELECTION EVENTS ARE SENT AS A RESULT OF 
														// WIDGETS BEING SELECTED. 
import org.eclipse.swt.widgets.Button;					// REPRESENTS A SELECTABLE USER INTERFACE 
														// OBJECT THAT ISSUES NOTIFICATION WHEN 
														// PRESSED AND RELEASED. 
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;					// RESPONSIBLE FOR MANAGING THE CONNECTION 
														// BETWEEN SWT AND THE UNDERLYING OPERATING
														// SYSTEM.
import org.eclipse.swt.widgets.Label;					// REPRESENTS A NON-SELECTABLE USER INTERFACE 
														// OBJECT THAT DISPLAYS A STRING OR IMAGE 
														// (OR HORIZONTAL/VERTICAL LINE).
import org.eclipse.swt.widgets.Shell;					// REPRESENTS THE "WINDOWS" WHICH THE DESKTOP
														// OR "WINDOW MANAGER" IS MANAGING. 
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class ProfitByDate extends AbstractProgramWindow
{

	// TODO: instance var section, label vars
	protected Shell shlProfitByDate;
	private ArrayList<Customer> customers; 
	private ArrayList<Customer> customersSorted;
	private ArrayList<LocalDate> dates = BusCalculation.getDates(); 		 
	private Table customersTable;
	private int sortBy = 0; 								// sort by filter
															// srtBy = 0, sort by date increasing
															// srtBy = 1, sort by date decreasing
															// srtBy = 2 sort by profit increasing
															// srtBy = 3 sort by profit decreasing
	
	// TODO: constructor section
	public ProfitByDate (ArrayList<Customer> cstmrs) 
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
		Display display = Display.getDefault();					// MANAGES THE CONNECTION BETWEEN SWT 
																// AND THE UNDERLYING OPERATING SYSTEM 

		/**********************************************/
		/* METHOD TO CREATE CONTENTS OF SHELL/DISPLAY */
		/**********************************************/
		createContents(rootShell);
		
		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shlProfitByDate.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlProfitByDate.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlProfitByDate.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlProfitByDate.isDisposed()) 
		{
			// TODO: 
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Shell rootShell) 
	{
		// TODO: var 
		Function<Customer, String> customerProfit; 
		
		// TODO: label method calls
		shlProfitByDate = new Shell();
		shlProfitByDate.setSize(880, 330);
		shlProfitByDate.setText(Messages.getString("ProfitByDate.shlProfitByDate.text")); //$NON-NLS-1$
		
		// TODO: label dateTime
		DateTime dateTime = new DateTime(shlProfitByDate, SWT.BORDER | SWT.CALENDAR);
		
		// TODO: label method calls
		dateTime.setBounds(10, 10, 233, 151);

		// TODO: label combo
		Combo comboDatesList = new Combo(shlProfitByDate, SWT.NONE);
		// TODO: label method calls
		comboDatesList.setBounds(10, 167, 91, 23);
		
		// TODO: label label
		Label lblProfit = new Label(shlProfitByDate, SWT.NONE);
		lblProfit.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 10, SWT.NORMAL));
		// TODO: label method calls
		lblProfit.setBounds(10, 206, 91, 25);
		lblProfit.setText(Messages.getString("lblProfit.text")); 						//$NON-NLS-1$
		
		// TODO: label label
		Label lblAmtProfit = new Label(shlProfitByDate, SWT.RIGHT);
		lblAmtProfit.setAlignment(SWT.LEFT);
		lblAmtProfit.setText(Messages.getString("ProfitByDate.lblAmtProfit.text")); 	//$NON-NLS-1$
		lblAmtProfit.setFont(SWTResourceManager.getFont("Segoe UI Light", 12, SWT.NORMAL));
		// TODO: label method calls
		lblAmtProfit.setBounds(107, 203, 136, 25);
		
		// TODO: label button
		Button btnExit = new Button(shlProfitByDate, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				//TODO: label method calls
				rootShell.forceActive(); 
				shlProfitByDate.close(); 
			}
		});
		// TODO: label method calls
		btnExit.setBounds(10, 258, 75, 25);
		btnExit.setText(Messages.getString("btnExit.text"));
		
		
		Label lblNewLabel = new Label(shlProfitByDate, SWT.CENTER);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(258, 10, 507, 15);
		lblNewLabel.setText(Messages.getString("ProfitByDate.lblTable.text"));			//$NON-NLS-1$
		
		Label label = new Label(shlProfitByDate, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 198, 233, 2);
		
		customersTable = new Table(shlProfitByDate, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText("!AddCustomer.customersTable.toolTipText!");
		customersTable.setLinesVisible(true);
		customersTable.setHeaderVisible(true);
		customersTable.setBounds(249, 31, 605, 252);
		
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
		
		TableColumn tblclmnProfit = new TableColumn(customersTable, SWT.NONE);
		tblclmnProfit.setWidth(100);
		tblclmnProfit.setText(Messages.getString("tblclmnProfit.text")); //$NON-NLS-1$

		//TODO: label method calls
		sortCustomers(); 
		customerProfit = this::getCustomerProfitString;
		updateComboBox(comboDatesList, dates); 
		updateTable(customersTable, customersSorted, customerProfit); 
		
		/* event handlers */ //TODO: fix
		dateTime.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				LocalDate date = LocalDate.parse(StringToLocalDateFormat(dateTime.getDay(), 
						dateTime.getMonth(), dateTime.getYear()));
				 
				//if (dates.contains(date)) 
				//{
				// update combo box maybe ?
				//	combo.set
					
					lblAmtProfit.setText(BusFinances.currency.getSymbol(Messages.getLocale())
							+ BusFinances.getProfitOnDate(date)); 
				//}
				//else 
				//{
				//	lblAmtProfit.setText("$" + "0"); 
				//}
			}
		});
		comboDatesList.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				int index = comboDatesList.getSelectionIndex(); 
				LocalDate date = dates.get(index); 
				
				// move calendar to date 
				dateTime.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
				
				// update number of buses text
				lblAmtProfit.setText(BusFinances.currency.getSymbol(Messages.getLocale())
						+ BusFinances.getProfitOnDate(date)); 
		
			}
		});
	}
	
	//TODO: label method
	@SuppressWarnings("unchecked")		// TO SUPRESS WARNING ABOUT "TYPE SAFETY: UNCHECKED CAST...."
	private void sortCustomers() {
		//TODO: label call
		customersSorted =  (ArrayList<Customer>)customers.clone(); 
			
		//TODO: label switch
		switch (sortBy) 
		{
			case 0: 
				//customersSorted.sort(new Customer.CompareName());
				customersSorted.sort(new Customer.CompareDate());
				break; 
			case 1: 
				//customersSorted.sort(new Customer.CompareSize());
				break; 
			case 2: 
				customersSorted.sort(new Customer.CompareTotalPrice());
				break; 
			case 3: 
				break; 
			default: 
				break; 
		}
	}
}
