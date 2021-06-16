/********************************************/
/* INFORMATION SECTION 						*/
/* TotalProfitDetails.java					*/
/* Darian Siembab 							*/
/* 											*/
/* WINDOW CLASS TO PRINT TOTAL COMPANY		*/
/* PROFIT UP TO A SPECIFIED DATE			*/
/********************************************/ 
package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import java.time.LocalDate;								// FOR STORING DATES
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import org.eclipse.swt.SWT;								// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.events.SelectionAdapter;			// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.events.SelectionEvent;			// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Button;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Display;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Label;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Shell;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.wb.swt.SWTResourceManager;			// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.DateTime;				// FOR SWT WINDOWS/WIDGETS 
														// (CALENDAR AND DATE SELECTION)

public class TotalProfitDetails extends AbstractBusProgramWindow 
{

	/********************/
	/* VARIABLE SECTION */
	/********************/ 
	protected Shell shlTotalProfitDetails;				// SHELL WHICH REPRESENTS THIS WINDOW 
	
	public TotalProfitDetails (ArrayList<Customer> cstmrs) 
	{
		
	}
	
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
		Display display = Display.getDefault();				// MANAGES THE CONNECTION BETWEEN SWT 
															// AND THE UNDERLYING OPERATING SYSTEM 

		/**********************************************/
		/* METHOD TO CREATE CONTENTS OF SHELL/DISPLAY */
		/**********************************************/
		createContents(rootShell);
		
		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shlTotalProfitDetails.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlTotalProfitDetails.forceActive();						// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlTotalProfitDetails.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shlTotalProfitDetails.isDisposed()) 
		{
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}
	
	protected void createContents(Shell rootShell) 
	/*************************************************/
	/* PRECONDITION:  WINDOW NEEDS ELEMENTS 		 */
	/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW */
	/*************************************************/
	{
		shlTotalProfitDetails = new Shell();
		shlTotalProfitDetails.setSize(352, 300);
		shlTotalProfitDetails.setText("List Customers");
		
		Button btnExit = new Button(shlTotalProfitDetails, SWT.NONE);
		btnExit.setText("Exit");
		btnExit.setBounds(128, 226, 75, 25);
		
		Label lblProfitInfo = new Label(shlTotalProfitDetails, SWT.NONE);
		lblProfitInfo.setFont(SWTResourceManager.getFont("Segoe MDL2 Assets", 12, SWT.NORMAL));
		lblProfitInfo.setAlignment(SWT.CENTER);
		lblProfitInfo.setText("Profit Information:");
		lblProfitInfo.setBounds(10, 10, 316, 25);
		
		Label lblTotalProfit = new Label(shlTotalProfitDetails, SWT.NONE);
		lblTotalProfit.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblTotalProfit.setBounds(10, 71, 116, 25);
		lblTotalProfit.setText("Total Profit to Date: ");
		
		Label lblNewLabel = new Label(shlTotalProfitDetails, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(128, 71, 198, 25);
		
		
		DateTime dateTime = new DateTime(shlTotalProfitDetails, SWT.BORDER);
		dateTime.setBounds(239, 41, 87, 24);
		dateTime.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e) 
			{
				lblNewLabel.setText(BusFinances.currency.getSymbol(Messages.getLocale())
						+ BusFinances.getProfitToDate(LocalDate.parse(dateTimeToLocalDateStringFormat
						(dateTime.getDay(), dateTime.getMonth(), dateTime.getYear()))));
			}
		}); 
		// by default dateTime is today's date
		lblNewLabel.setText("$" + BusFinances.getProfitToDate
				(LocalDate.parse(dateTimeToLocalDateStringFormat
				(dateTime.getDay(), dateTime.getMonth(), dateTime.getYear()))));
		
		Label lblDate = new Label(shlTotalProfitDetails, SWT.NONE);
		lblDate.setText("Date:");
		lblDate.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblDate.setBounds(10, 41, 116, 25);
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				rootShell.forceActive(); 
				shlTotalProfitDetails.close(); 
			}
		});
	}
}
