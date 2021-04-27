package bus_project;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.DateTime;

public class TotalProfitDetails extends AbstractProgramWindow 
{

	//TODO: label section, vars
		protected Shell shlTotalProfitDetails;
		private ArrayList<Customer> customers; 		 
		
		//TODO: label method
		public TotalProfitDetails (ArrayList<Customer> cstmrs) 
		{
			customers = cstmrs;  
		}
		
		/**
		 * Open the window.
		 * @wbp.parser.entryPoint
		 */
		//TODO: label method more
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
			
			//TODO: label method calls/shell
			shlTotalProfitDetails = new Shell();
			shlTotalProfitDetails.setSize(352, 300);
			shlTotalProfitDetails.setText("List Customers");
			
			//TODO: label button
			Button btnExit = new Button(shlTotalProfitDetails, SWT.NONE);
			//TODO: label method calls
			btnExit.setText("Exit");
			btnExit.setBounds(128, 226, 75, 25);
			
			Label lblProfitInfo = new Label(shlTotalProfitDetails, SWT.NONE);
			lblProfitInfo.setFont(SWTResourceManager.getFont("Segoe MDL2 Assets", 12, SWT.NORMAL));
			lblProfitInfo.setAlignment(SWT.CENTER);
			lblProfitInfo.setText("Profit Information:");
			lblProfitInfo.setBounds(10, 10, 316, 25);
			
			Label lblTotalProfit = new Label(shlTotalProfitDetails, SWT.NONE);
			lblTotalProfit.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
			lblTotalProfit.setBounds(10, 62, 116, 25);
			lblTotalProfit.setText("Total Profit to Date: ");
			
			Label lblNewLabel = new Label(shlTotalProfitDetails, SWT.NONE);
			lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
			lblNewLabel.setAlignment(SWT.RIGHT);
			lblNewLabel.setBounds(128, 62, 198, 25);
			lblNewLabel.setText("$" + BusFinances.getProfit());
			
			DateTime dateTime = new DateTime(shlTotalProfitDetails, SWT.BORDER);
			dateTime.setBounds(239, 32, 87, 24);
			dateTime.addSelectionListener(new SelectionAdapter()
			{
				public void widgetSelected(SelectionEvent e) 
				{
					
				}
			}); 
			// by default dateTime is today's date
			
			Label lblDate = new Label(shlTotalProfitDetails, SWT.NONE);
			lblDate.setText("Date:");
			lblDate.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
			lblDate.setBounds(10, 32, 116, 25);
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
