/********************************************/
/* INFORMATION SECTION 						*/
/* FileInfo.java							*/
/* Darian Siembab 							*/
/* 											*/
/* WINDOW CLASS FOR DISPLAYING INFO ABOUT	*/
/* A LOADED CUSTOMER DATA FILE				*/
/********************************************/ 

package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import fileIO.*;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class FileInfo extends AbstractBusProgramWindow
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	private ArrayList<Customer> customers; 				// LIST OF CUSTOMERS
	private Label lblFileName;							// LABEL TO DISPLAY FILE NAME
	protected Shell shlFileInfo;						// SHELL WHICH REPRESENTS THIS WINDOW
	private String fileNameText = "";					// FILE NAME
	private String filePath; 		 					// FILE PATH
	private Table customersTable;						// CUSTOMERS DISPLAY TABLE
	
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
		shlFileInfo.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlFileInfo.forceActive();					// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlFileInfo.layout();
		
		/*******************************/
		/* WHILE SHELL IS NOT DISPOSED */
		/*******************************/
		while (!shlFileInfo.isDisposed()) 
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
	/*************************************************/
	/* PRECONDITION:  WINDOW NEEDS ELEMENTS 		 */
	/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW */
	/*************************************************/
	{ 
		/*********/ 
		/* SHELL */
		/*********/ 
		shlFileInfo = new Shell();
		shlFileInfo.setSize(550, 420);
		shlFileInfo.setText("SWT Application");
		
		Menu menu = new Menu(shlFileInfo, SWT.BAR);
		shlFileInfo.setMenuBar(menu);
		
		MenuItem mntmHelp = new MenuItem(menu, SWT.NONE);
		mntmHelp.setText("Help");
		
		Label lblFile = new Label(shlFileInfo, SWT.NONE);
		lblFile.setBounds(10, 10, 78, 15);
		lblFile.setText("File: ");
		
		Label lblCustomers = new Label(shlFileInfo, SWT.CENTER);
		lblCustomers.setText("Customers: ");
		lblCustomers.setBounds(10, 31, 516, 15);
		
		lblFileName = new Label(shlFileInfo, SWT.NONE);
		lblFileName.setBounds(94, 10, 432, 15);
		lblFileName.setText(fileNameText);
		
		customersTable = new Table(shlFileInfo, SWT.BORDER | SWT.FULL_SELECTION);
		customersTable.setToolTipText("!AddCustomer.customersTable.toolTipText!");
		customersTable.setLinesVisible(true);
		customersTable.setHeaderVisible(true);
		customersTable.setBounds(10, 56, 516, 264); 
		
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
		
		Button btnExit = new Button(shlFileInfo, SWT.NONE);
		btnExit.setText("Exit");
		btnExit.setBounds(451, 326, 75, 25);
		
		try 
		{
			readCustomers(); 
			
			/************************************************/
			/* METHOD CALL UPDATE TABLE (LIST OF CUSTOMERS) */
			/************************************************/
			updateTable(customersTable, customers);
		}
		catch (Exception exc)
		{
			// DO NOTHING
		}
		
		/**************************/
		/* EVENT LISTENER SECTION */
		/**************************/
		
		/***************/
		/* EXIT BUTTON */
		/***************/ 
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			// TODO: label method
			public void widgetSelected(SelectionEvent e) 
			{
				closeSubWindow(rootShell, shlFileInfo); 
			}
		});
	}
	
	protected void readCustomers()
	/***********************************************************************/
	/* PRECONDITION:  CUSTOMERS NEED TO BE READ FROM A CUSTOMER DATA FILE, */
	/*				  CUSTOMERS LIST IS EMPTY (OR WILL BE OVERWRITTEN)	   */
	/* POSTCONDITION: READS CUSTOMER DETAILS FROM CUSTOMER DATA FILE,	   */
	/*   			  ADDS CUSTOMERS TO CUSTOMERS LIST 					   */
	/***********************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int index = 0; 
		Customer customer;
		List<String> dataList = new ArrayList<String>(); 
		
		/************************/
		/* CLEAR CUSTOMERS LIST */
		/************************/
		customers.clear(); 
		
		/***********************************/
		/* ADD DATA FROM FILE TO DATA LIST */
		/***********************************/
		Collections.addAll(dataList, FileRead.readFile(new File(filePath))
				.split(System.lineSeparator())); 

		/*****************************************************/
		/* FOR LOOP TO ADD CUSTOMERS USING DETAILS FROM FILE */
		/* (SKIP FIRST LINE, 4 LINES OF DATA PER CUSTOMER)   */
		/*****************************************************/
		for (int i = 1; i < dataList.size(); i+=4, index++)
		{
			/***********************/
			/* CREATE NEW CUSTOMER */
			/***********************/
			customer = new Customer(); 
			
			/**************************************************************/
			/* TRY TO SET DETAILS OF NEW CUSTOMER USING DETAILS FROM FILE */
			/**************************************************************/
			try 
			{
				setCustomerDetails(customer, dataList.get(i + 1), 
						Integer.parseInt(dataList.get(i + 2)), index, 
						Integer.parseInt(dataList.get(i)), dataList.get(i + 3));  
			}
			
			/**********************************************************************/
			/* DISPLAY ERROR MESSAGE IF DETAILS WERE CORRUPTED AND CLEAR ALL DATA */
			/**********************************************************************/
			catch (Exception exc)
			{
				JOptionPane.showMessageDialog
					(null, "Error: issue loading in customer data.");
				customers.clear();  
				return; 
			}
			
			/**************************************/
			/* ADD NEW CUSTOMER TO CUSTOMERS LIST */
			/**************************************/
			customers.add(customer); 
			updateIndex(customers); 
		}
		
		/*****************************/
		/* SORT CUSTOMERS LIST BY ID */
		/*****************************/
		customers.sort(new Customer.CompareId()); 
	}
	
	protected void setFileNameText(String fileName)
	/***********************************************************************/
	/* PRECONDITION:  SELECTED FILE NAME NEEDS TO BE UPDATED			   */
	/* POSTCONDITION: SETS FILE NAME TEXT TO THE FILE NAME  			   */
	/***********************************************************************/
	{
		fileNameText = fileName; 
	}
	
	protected void setFilePath(String filePath)
	/***********************************************************************/
	/* PRECONDITION:  SELECTED FILE PATH NEEDS TO BE UPDATED			   */
	/* POSTCONDITION: SETS FILE PATH TEXT TO THE FILE PATH  			   */
	/***********************************************************************/
	{
		this.filePath  = filePath; 
	}
}
