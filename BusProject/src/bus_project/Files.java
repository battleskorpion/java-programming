package bus_project;

import fileIO.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent; 

public class Files extends AbstractProgramWindow
{
	private ArrayList<Customer> customers; 				// LIST OF CUSTOMERS
	protected Shell shlFiles;

	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/ 
	public Files(ArrayList<Customer> customers) 
	{
		this.customers = customers; 
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
		shlFiles.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlFiles.forceActive();					// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlFiles.layout();
		
		/*******************************/
		/* WHILE SHELL IS NOT DISPOSED */
		/*******************************/
		while (!shlFiles.isDisposed()) 
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

	protected void createContents(Shell rootShell) 
	{
		shlFiles = new Shell();
		shlFiles.setSize(450, 300);
		shlFiles.setText("SWT Application");
		
		Menu menu = new Menu(shlFiles, SWT.BAR);
		shlFiles.setMenuBar(menu);
		
		MenuItem mntmHelp = new MenuItem(menu, SWT.NONE);
		mntmHelp.setText("Help");
		
		Label lblFile = new Label(shlFiles, SWT.NONE);
		lblFile.setBounds(10, 10, 78, 15);
		lblFile.setText("File: ");
		
		Label lblFileName = new Label(shlFiles, SWT.NONE);
		lblFileName.setBounds(94, 10, 330, 15);
		
		Button btnFileAction = new Button(shlFiles, SWT.NONE);
		btnFileAction.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		btnFileAction.setBounds(10, 31, 75, 25);
		btnFileAction.setText("New Button");

	}
}
