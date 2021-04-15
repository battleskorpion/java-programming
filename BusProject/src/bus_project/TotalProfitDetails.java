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

public class TotalProfitDetails extends AbstractProgramWindow 
{

	//TODO: label section, vars
		protected Shell shlTotalProfitDetails;
		private ArrayList<Customer> customers; 
		private ArrayList<Customer> customersSorted; 
		private int sortBy; 									// sort by filter		 
		
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

		/*************************************************/
		/* PRECONDITION:  WINDOW NEEDS ELEMENTS 		 */
		/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW */
		/*************************************************/
		protected void createContents(Shell rootShell) {
			
			//TODO: label method calls/shell
			shlTotalProfitDetails = new Shell();
			shlTotalProfitDetails.setSize(352, 500);
			shlTotalProfitDetails.setText("List Customers");
			
			//TODO: label button
			Button btnExit = new Button(shlTotalProfitDetails, SWT.NONE);
			//TODO: label method calls
			btnExit.setText("Exit");
			btnExit.setBounds(128, 426, 75, 25);
			
			Label lblProfitInfo = new Label(shlTotalProfitDetails, SWT.NONE);
			lblProfitInfo.setAlignment(SWT.CENTER);
			lblProfitInfo.setText("Profit Information:");
			lblProfitInfo.setBounds(10, 10, 316, 15);
			//TODO: label listener
			btnExit.addSelectionListener(new SelectionAdapter() 
			{
				//TODO: label method
				public void widgetSelected(SelectionEvent e) 
				{
					//TODO: label method calls
					rootShell.forceActive(); 
					shlTotalProfitDetails.close(); 
				}
			});
		}
		
		//TODO: label method
		public void setSortByName() {
			sortBy = 0;  	// set sort by filter to name
		}
		
		//TODO: label method
		public void setSortBySize() {
			sortBy = 1; 	// set sort by filter to size
		}
		
}
