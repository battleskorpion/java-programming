package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractProgramWindow 
{
	
	protected Shell shell;

	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
	/**
	 * Open the window.
	 */
	public void open(Shell shell) 
	{
		shell.open(); 
	}

	/**
	* Create contents of the window.
	*/
	//protected abstract void createContents(); 
	
	protected void updateTable(Table tbl, Object dta) 
	/********************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ADDITIONAL VALUE 			*/
	/* POSTCONDITION: ADDS A VALUE TO THE TABLE 						   			*/
	/********************************************************************************/
	{
		/*********************/
		/* ADD ITEM TO TABLE */
		/*********************/
		TableItem item = new TableItem(tbl, SWT.NULL); 
		item.setText(dta.toString()); 
	}
	
	protected <E> void updateTable(Table tbl, ArrayList<E> dta) 
	/********************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 	  	*/
	/* POSTCONDITION: CLEARS PREVIOUS TABLE AND ADDS NEW VALUES TO TABLE	  		*/
	/********************************************************************************/
	{
		/***************/
		/* RESET TABLE */
		/***************/ 
		tbl.clearAll();							
		tbl.setItemCount(0);
		
		/****************/
		/* UPDATE TABLE */
		/****************/ 
		for (int i = 0; i < dta.size(); i++) 
		{
			updateTable(tbl, dta.get(i)); 
		}
	}
	
	protected <E> void updateTable(Table tbl, ArrayList<E> dta, Function<E, String> addtlDataFunction) 
	/********************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 	  	*/
	/* POSTCONDITION: CLEARS PREVIOUS TABLE AND ADDS NEW VALUES TO TABLE	  		*/
	/********************************************************************************/
	{
		/***************/
		/* RESET TABLE */
		/***************/ 
		tbl.clearAll();							
		tbl.setItemCount(0);
		
		/****************/
		/* UPDATE TABLE */
		/****************/ 
		for (int i = 0; i < dta.size(); i++) 
		{
			updateTable(tbl, dta.get(i) + ", " + addtlDataFunction.apply(dta.get(i))); 
		}
	}
	
	/********************************************************************************/
	/* PRECONDITION:  A COMBO BOX NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 	*/
	/* POSTCONDITION: CLEARS PREVIOUS VALUES AND ADDS NEW VALUES TO COMBO BOX	  	*/
	/********************************************************************************/
	protected <E> void updateComboBox(Combo bx, ArrayList<E> dta) 
	{
		if (dta.size() > 0) 
		{	
			bx.setItems(dta.toString().substring(0, dta.toString().length() - 1).substring(1, dta.toString().length() - 1).split(", ")); 	// TODO: FIX THIS BEAUTFUL MESS
		}
		else 
		{
			
		}
	}
	
	// TODO: COMMENT
	protected void setCustomerDetails(Customer customer, Text nameField, Text sizeField, Text indexField, int index, DateTime dateTime) 
	{
		LocalDate tripDate; 
		
		int year;
		int month; 
		int day; 

		customer.setName(nameField.getText()); 
		customer.setNumPersons(Integer.parseInt(sizeField.getText())); 
		customer.setIndex(index);
		year = dateTime.getYear(); 
		month = dateTime.getMonth(); 
		day = dateTime.getDay(); 
		
		tripDate = LocalDate.parse(StringToLocalDateFormat(day, month, year)); 
		customer.setDate(tripDate); 
	}
	
	protected void addCustomer(ArrayList<Customer> cstmrs, Customer cstmr, int indx, Table tbl) 
	{
		cstmrs.add(indx, cstmr);
		BusCalculation.scheduleTrip(cstmr);
		BusFinances.setCustomerProfit(cstmr); 
		updateTable(tbl, cstmrs); 
	}
	
	protected void addCustomer(ArrayList<Customer> cstmrs, Customer cstmr, Table tbl) 
	{
		cstmrs.add(cstmr);
		BusCalculation.scheduleTrip(cstmr);
		BusFinances.setCustomerProfit(cstmr); 
		updateTable(tbl, cstmrs); 
	}
	
	protected Customer removeCustomer(ArrayList<Customer> cstmrs, int cstmrIndx)
	{
		BusCalculation.unscheduleTrip(cstmrs.get(cstmrIndx)); 
		return cstmrs.remove(cstmrIndx); 
	}
	
	// TODO: comment
	protected void clearInput(Text[] fields) 
	{
		for (int i = 0; i < fields.length; i++) 
		{
			fields[i].setText("");
		}
	}

	// TODO: comment
	protected void updateIndex(ArrayList<Customer> cstmrs)
	{
		for (int i = 0; i < cstmrs.size(); i++) 
		{
			cstmrs.get(i).setIndex(i);
		}
	}
	
	// TODO: COMMENT
	protected String StringToLocalDateFormat(int day, int month, int year)
	{
		String dateString; 
		dateString = year + "-"; 
		if ((month + 1) < 10) 
		{
			dateString += "0";  
		}
		dateString += (month + 1) + "-";
		if (day < 10) 
		{
			dateString += "0"; 
		}
		dateString += day; 
		return dateString; 
	}
	
	// TODO: COMMENT
	protected boolean vaildDate(DateTime dateTime) 		
	{
		LocalDate date = LocalDate.parse(StringToLocalDateFormat(dateTime.getDay(), dateTime.getMonth(), dateTime.getYear()));
		
		if (date.isAfter(LocalDate.now()))	
		{			
			return true;
		}
		else 
		{
			return false; 
		}
	}
	
	// TODO: comment
	protected void openSubWindow(AbstractProgramWindow wndw, Shell shl) 
	{
		/**********************************************/
		/* DISABLE ROOT SHELL WHILE PERFORMING ACTION */
		/**********************************************/
		shl.setEnabled(false);
		
		/***************/
		/* OPEN WINDOW */
		/***************/
		wndw.open(shl);
			
		/*********************************************/
		/* ENABLE ROOT SHELL AFTER PERFORMING ACTION */
		/*********************************************/
		shl.setEnabled(true);
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shl.forceActive();	
	}	
	
}
