package bus_project;

import java.time.LocalDate;	
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import java.util.function.Function; 
import org.eclipse.swt.SWT;								// THIS CLASS PROVIDES ACCESS TO A SMALL 
														// NUMBER OF SWT SYSTEM-WIDE METHODS, AND
														// IN ADDITION DEFINES THE PUBLIC CONSTANTS 
														// PROVIDED BY SWT. 
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;					// REPRESENTS THE "WINDOWS" WHICH THE DESKTOP
														// OR "WINDOW MANAGER" IS MANAGING. 			
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public abstract class AbstractProgramWindow 
{
	
	protected Shell shell;

	/************************************************************************************************/
	/*										METHOD SECTION 			  				  				*/
	/************************************************************************************************/

	public void open(Shell shell)
	/************************************************************************************************/
	/* PRECONDITION:  WINDOW NEEDS TO BE DISPLAYED ON SCREEN 				  						*/
	/* POSTCONDITION: SHELL OF WINDOW IS OPENED (WINDOW DISPLAYED ON SCREEN)  						*/
	/************************************************************************************************/
	{
		shell.open(); 
	}
	
	public void close(Shell shell)
	/************************************************************************************************/
	/* PRECONDITION:  WINDOW NEEDS TO BE CLOSED 				  			  						*/
	/* POSTCONDITION: SHELL OF WINDOW IS CLOSED (WINDOW IS NO LONGER DISPLAYED ON SCREEN)  			*/
	/************************************************************************************************/
	{
		shell.close(); 
	}
	/**
	* Create contents of the window.
	*/
	//protected abstract void createContents(); 
	
	protected void updateTable(Table tbl, Object dta) 
	/************************************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ADDITIONAL VALUE	 						*/
	/* POSTCONDITION: ADDS A VALUE TO THE TABLE 						   	  						*/
	/************************************************************************************************/
	{
		/*********************/
		/* ADD ITEM TO TABLE */
		/*********************/
		TableItem item = new TableItem(tbl, SWT.NULL); 
		item.setText(dta.toString()); 
	}
	
	protected void updateTable(Table tbl, String[] dta) 
	/************************************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ADDITIONAL VALUE 	  						*/
	/* POSTCONDITION: ADDS A VALUE TO THE TABLE 						   	  						*/
	/************************************************************************************************/
	{
		/*********************/
		/* ADD ITEM TO TABLE */
		/*********************/
		TableItem item = new TableItem(tbl, SWT.NULL); 
		item.setText(dta); 
	}
	
	protected <E> void updateTable(Table tbl, ArrayList<E> dta) 
	/************************************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 						*/
	/* POSTCONDITION: CLEARS PREVIOUS TABLE AND ADDS NEW VALUES TO TABLE	  						*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int columnCount = tbl.getColumnCount(); 
		
		/***************/
		/* RESET TABLE */
		/***************/ 
		tbl.clearAll();							
		tbl.setItemCount(0);
		
		/****************/
		/* UPDATE TABLE */
		/****************/ 
		//TODO: COMMENTS
		for (int i = 0; i < dta.size(); i++) 
		{
			if (columnCount <= 1) 
			{
				updateTable(tbl, dta.get(i)); 
			}
			else 
			{
				updateTable(tbl, dta.get(i).toString().split("\n")); 
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <E> void updateTable(Table tbl, ArrayList<E> dta, 
			Function<E, String> addtlDataFunction) 
	/************************************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 						*/
	/* POSTCONDITION: CLEARS PREVIOUS TABLE AND ADDS NEW VALUES TO TABLE	  						*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/ 
		int columnCount = tbl.getColumnCount(); 
		//TODO: COMMENT
		if (columnCount <= 1) 
		{
			/***************/
			/* RESET TABLE */
			/***************/ 
			tbl.clearAll();							
			tbl.setItemCount(0);
			
			/****************/
			/* UPDATE TABLE */
			/****************/ 
			//TODO: COMMENT better
			for (int i = 0; i < dta.size(); i++) 
			{
				updateTable(tbl, dta.get(i) + ", " 
						+ addtlDataFunction.apply(dta.get(i))); 
			}
		}
		else 
		{
			/***************/
			/* RESET TABLE */
			/***************/ 
			tbl.clearAll();	
			
			//TODO: COMMENT
			for (int i = 0; i < dta.size(); i++) 
			{
				for(int col = 0; col < columnCount; col++) 
				{
					/*********************/
					/* ADD ITEM TO TABLE */
					/*********************/
					TableItem item = new TableItem(tbl, SWT.NULL); 
					item.setText(((ArrayList<ArrayList<E>>) dta.get(i))
							.get(col).toString());
				}
			}
		}
	}
	
	protected <E> void updateComboBox(Combo bx, ArrayList<E> dta) 
	/************************************************************************************************/
	/* PRECONDITION:  A COMBO BOX NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 					*/
	/* POSTCONDITION: CLEARS PREVIOUS VALUES AND ADDS NEW VALUES TO COMBO BOX	  					*/
	/************************************************************************************************/
	{
		//TODO: COMMENT
		if (dta.size() > 0) 
		{	
			// TODO: THIS WORKS BUT IS UNREADABLE
			bx.setItems(dta.toString().substring(0, dta.toString().length() - 1)
					.substring(1, dta.toString().length() - 1).split(", ")); 	
		}
		else 
		{
			
		}
	}
	
	// TODO: COMMENT
	protected void setCustomerDetails(Customer cstmr, Text nmField, Text szField, 
			int indx, int id, DateTime dtTime) 
	/************************************************************************************************/
	/* PRECONDITION:  DETAILS OF CUSTOMER NEED TO BE SET											*/
	/* POSTCONDITION: SETS CUSTOMER DETAILS TO NEW VALUES 						  					*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		LocalDate tripDate; 
		int year;
		int month; 
		int day; 

		cstmr.setName(nmField.getText()); 
		cstmr.setNumPersons(Integer.parseInt(szField.getText())); 
		cstmr.setIndex(indx);
		cstmr.setId(id);
		year = dtTime.getYear(); 
		month = dtTime.getMonth(); 
		day = dtTime.getDay(); 
		
		tripDate = LocalDate.parse(StringToLocalDateFormat(day, month, year)); 
		cstmr.setDate(tripDate); 
	}
	
	/************************************************************************************************/
	/* PRECONDITION:  A CUSTOMER NEEDS TO BE ADDED TO THE ARRAY LIST OF CUSTOMERS					*/
	/* POSTCONDITION: ADDS THE CUSTOMER TO THE ARRAY LIST OF CUSTOMERS, SCHEDULES THE CUSTOMER'S 	*/
	/*				  TRIP, CALCULATES PROFIT FROM CUSTOMER											*/
	/************************************************************************************************/
	protected void addCustomer(ArrayList<Customer> cstmrs, Customer cstmr, int indx, Table tbl) 
	{
		cstmrs.add(indx, cstmr);
		cstmrs.sort(new Customer.CompareId()); 
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
	protected void clearInput(Text[] flds) 
	{
		//TODO: COMMETNS
		for (int i = 0; i < flds.length; i++) 
		{
			flds[i].setText("");
		}
	}

	// TODO: comment
	protected void updateIndex(ArrayList<Customer> cstmrs)
	{
		//TODO: COMMENTS
		for (int i = 0; i < cstmrs.size(); i++) 
		{
			cstmrs.get(i).setIndex(i);
		}
	}
	
	// TODO: COMMENT
	protected String StringToLocalDateFormat(int dy, int mnth, int yr)
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		String dateString; 
		
		//TODO: COMMENTS
		dateString = yr + "-"; 
		if ((mnth + 1) < 10) 
		{
			dateString += "0";  
		}
		dateString += (mnth + 1) + "-";
		if (dy < 10) 
		{
			dateString += "0"; 
		}
		dateString += dy; 
		return dateString; 
	}
	
	// TODO: COMMENT
	protected boolean vaildDate(DateTime dateTime) 		
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		LocalDate date = LocalDate.parse(StringToLocalDateFormat
				(dateTime.getDay(), dateTime.getMonth(), dateTime.getYear()));
		
		//TODO: COMMENTS
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
