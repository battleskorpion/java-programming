package bus_project;

import fileIO.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class FileInfo extends AbstractBusProgramWindow
{
	private ArrayList<Customer> customers; 				// LIST OF CUSTOMERS
	private Label lblFileName;
	protected Shell shlFiles;							// SHELL WHICH REPRESENTS THIS WINDOW
	private String fileNameText = "";
	private String filePath; 		 
	private Table table;
	
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/ 
	public FileInfo(ArrayList<Customer> customers) 
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
		// variable section
		ArrayList<String> tableData = new ArrayList<String>(); 
		
		shlFiles = new Shell();
		shlFiles.setSize(550, 420);
		shlFiles.setText("SWT Application");
		
		Menu menu = new Menu(shlFiles, SWT.BAR);
		shlFiles.setMenuBar(menu);
		
		MenuItem mntmHelp = new MenuItem(menu, SWT.NONE);
		mntmHelp.setText("Help");
		
		Label lblFile = new Label(shlFiles, SWT.NONE);
		lblFile.setBounds(10, 10, 78, 15);
		lblFile.setText("File: ");
		
		lblFileName = new Label(shlFiles, SWT.NONE);
		lblFileName.setBounds(94, 10, 432, 15);
		lblFileName.setText(fileNameText);
		
		Label lblCustomers = new Label(shlFiles, SWT.CENTER);
		lblCustomers.setText("Customers: ");
		lblCustomers.setBounds(10, 31, 516, 15);
		
		table = new Table(shlFiles, SWT.BORDER | SWT.FULL_SELECTION);
		table.setToolTipText("!AddCustomer.customersTable.toolTipText!");
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 56, 516, 264);
		try 
		{
			Collections.addAll(tableData, FileRead.readFile(new File(filePath)).split(System.lineSeparator())); 
			super.updateTable(table, tableData);
		}
		catch (Exception exc)
		{
			// DO NOTHING
		}
		
		TableColumn tblclmnID = new TableColumn(table, SWT.NONE);
		tblclmnID.setWidth(60);
		tblclmnID.setText("ID");
		
		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(160);
		tblclmnName.setText("Name");
		
		TableColumn tblclmnDate = new TableColumn(table, SWT.NONE);
		tblclmnDate.setWidth(100);
		tblclmnDate.setText("Date");
		
		TableColumn tblclmnSize = new TableColumn(table, SWT.NONE);
		tblclmnSize.setWidth(100);
		tblclmnSize.setText("Group Size");
		
		TableColumn tblclmnRefunds = new TableColumn(table, SWT.NONE);
		tblclmnRefunds.setWidth(80);
		tblclmnRefunds.setText("Refunds");
		
		Button btnExit = new Button(shlFiles, SWT.NONE);
		btnExit.setText("Exit");
		btnExit.setBounds(451, 326, 75, 25);
	}
	
	protected void setFileNameText(String fileName)
	{
		fileNameText = fileName; 
	}
	
	protected void setFilePath(String filePath)
	{
		this.filePath  = filePath; 
	}
}
