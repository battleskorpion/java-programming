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
	/********************/
	/* VARIABLE SECTION */
	/********************/ 
	protected Shell shell;								// SHELL WHICH REPRESENTS THIS WINDOW

	/************************************************************************************************/
	/*										METHOD SECTION 			  				  				*/
	/************************************************************************************************/

	public void open(Shell shell)
	/************************************************************************************************/
	/* PRECONDITION:  WINDOW NEEDS TO BE DISPLAYED ON SCREEN 				  						*/
	/* POSTCONDITION: SHELL OF WINDOW IS OPENED (WINDOW DISPLAYED ON SCREEN)  						*/
	/************************************************************************************************/
	{
		/***********************************************/
		/* METHOD TO OPEN SHELL 					   */
		/* (OPEN WINDOW REPRESENTED BY SHELL ON SCREEN */
		/***********************************************/
		shell.open(); 
	}
	
	public void close(Shell shell)
	/************************************************************************************************/
	/* PRECONDITION:  WINDOW NEEDS TO BE CLOSED 				  			  						*/
	/* POSTCONDITION: SHELL OF WINDOW IS CLOSED (WINDOW IS NO LONGER DISPLAYED ON SCREEN)  			*/
	/************************************************************************************************/
	{
		/***************************************/
		/* METHOD TO CLOSE SHELL 			   */
		/* (CLOSE WINDOW REPRESENTED BY SHELL) */
		/***************************************/
		shell.close(); 
	}
	
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
		
		/*************************/
		/* SET ITEM TEXT TO DATA */
		/*************************/
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
		
		/*************************/
		/* SET ITEM TEXT TO DATA */
		/*************************/
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
		
		/************************************************/
		/* METHOD CALL TO CLEAR ALL ELEMENTS FROM TABLE */
		/************************************************/
		tbl.clearAll();							
		
		/********************************************/
		/* METHOD CALL TO SET TABLE ITEM COUNT TO 0 */
		/* (SO NEW DATA WILL BE ADDED AT THE TOP)   */
		/********************************************/
		tbl.setItemCount(0);
		
		/****************/
		/* UPDATE TABLE */
		/****************/ 

		/****************************************************/
		/* FOR LOOP TO UPDATE TABLE BY ROW WITH DATA IN dta */
		/****************************************************/
		for (int i = 0; i < dta.size(); i++) 
		{
			/************************************************************/
			/* IF STATEMENT TO UPDATE TABLE DIFFERENTLY DEPENDING ON IF */
			/* IT IS MADE UP OF MUTIPLE COLUMNS 						*/
			/************************************************************/ 
			if (columnCount <= 1) 
			{
				/*************************************/
				/* METHOD CALL TO UPDATE TABLE WITH  */
				/* DATA AT i IN dta					 */
				/*************************************/
				updateTable(tbl, dta.get(i)); 
			}
			else 
			{
				/************************************/
				/* METHOD CALL TO UPDATE TABLE WITH */
				/* ARRAY OF DATA AT i IN dta		*/
				/************************************/
				updateTable(tbl, dta.get(i).toString().split("\n")); 
			}
		}
	}
	
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
		
		/***************/
		/* RESET TABLE */
		/***************/ 
		
		/************************************************/
		/* METHOD CALL TO CLEAR ALL ELEMENTS FROM TABLE */
		/************************************************/
		tbl.clearAll();							
		
		/********************************************/
		/* METHOD CALL TO SET TABLE ITEM COUNT TO 0 */
		/* (SO NEW DATA WILL BE ADDED AT THE TOP)   */
		/********************************************/
		tbl.setItemCount(0);
		
		/****************/
		/* UPDATE TABLE */
		/****************/ 

		/****************************************************/
		/* FOR LOOP TO UPDATE TABLE BY ROW WITH DATA IN dta */
		/****************************************************/
		for (int i = 0; i < dta.size(); i++) 
		{
			if (columnCount <= 1) 
			{
				/*************************************/
				/* METHOD CALL TO UPDATE TABLE WITH  */
				/* DATA AT i IN dta					 */
				/* AS WELL AS ADDITIONAL DATA 		 */
				/*************************************/
				updateTable(tbl, dta.get(i) + ", " 
						+ addtlDataFunction.apply(dta.get(i))); 
			}
			else 
			{ 
				/************************************/
				/* METHOD CALL TO UPDATE TABLE WITH */
				/* ARRAY OF DATA AT i IN dta		*/
				/* AS WELL AS ADDITIONAL DATA		*/
				/************************************/
				updateTable(tbl, (dta.get(i).toString() + "\n" 
						+ addtlDataFunction.apply(dta.get(i))).split("\n")); 
			}
		}
	}
	
	protected <E> void updateComboBox(Combo bx, ArrayList<E> dta) 
	/************************************************************************************************/
	/* PRECONDITION:  A COMBO BOX NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 					*/
	/* POSTCONDITION: CLEARS PREVIOUS VALUES AND ADDS NEW VALUES TO COMBO BOX	  					*/
	/************************************************************************************************/
	{
		/************************************************************/
		/* IF STATEMENT TO UPDATE COMBO BOX IF THERE IS DATA IN dta */
		/************************************************************/ 
		if (dta.size() > 0) 
		{	
			/**************************************/
			/* METHOD CALL TO FILL COMBO BOX WITH */
			/* DATA IN dta (SPLITS DATA IN dta BY */
			/* COMMA AND REMOVES [] FROM toString */
			/**************************************/
			bx.setItems(dta.toString().substring(1, dta.toString().length() - 1).split(", ")); 	
		}
	}
	
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
		LocalDate tripDate; 				// DATE OF CUSTOMER TRIP
		int year;							// YEAR SELECTED IN CALENDAR
		int month; 							// MONTH SELECTED IN CALENDAR
		int day; 							// DAY SELECTED IN CALENDAR
		
		/******************************************************************/
		/* IF STATEMENT TO SET CUSTOMER NAME DEPENDING ON LENGTH OF INPUT */
		/******************************************************************/ 
		if (nmField.getText().length() > Customer.MAX_NAME_LENGTH) 
		{
			/***************************************/
			/* METHOD CALL TO SET CUSTOMER NAME TO */
			/* FIRST 20 CHARACTERS IN NAME FIELD   */
			/***************************************/
			cstmr.setName(nmField.getText().substring					
					(0, Customer.MAX_NAME_LENGTH)); 					
		}
		else 
		{
			/***************************************/
			/* METHOD CALL TO SET CUSTOMER NAME TO */
			/* TEXT IN NAME FIELD   			   */
			/***************************************/
			cstmr.setName(nmField.getText());							
																		
		}
		
		/***************************************************/
		/* METHOD CALL TO SET CUSTOMER'S NUMBER OF PERSONS */
		/* TO NUMBER ENTERED IN SIZE FIELD				   */
		/***************************************************/
		cstmr.setNumPersons(Integer.parseInt(szField.getText())); 
		
		/********************************************************/
		/* METHOD CALL TO SET CUSTOMER'S INDEX PROPERTY TO indx */
		/********************************************************/
		cstmr.setIndex(indx);			
		
		/****************************************/
		/* METHOD CALL TO SET CUSTOMER ID TO id */
		/****************************************/
		cstmr.setId(id);							
		
		/********************************************/
		/* METHOD CALL TO SET YEAR TO CALENDAR YEAR */
		/********************************************/
		year = dtTime.getYear();
		
		/*********************************************/
		/* METHOD CALL TO SET MONTH TO CALENDAR MONTH */
		/*********************************************/
		month = dtTime.getMonth(); 
		
		/******************************************/
		/* METHOD CALL TO SET DAY TO CALENDAR DAY */
		/******************************************/
		day = dtTime.getDay(); 
		
		/******************************************************/
		/* METHOD CALL TO SET TRIP DATE TO LOCAL DATE VERSION */
		/* OF CALENDAR YEAR, MONTH, AND DATE 				  */
		/******************************************************/
		tripDate = LocalDate.parse(StringToLocalDateFormat(day, month, year)); 
		
		/*************************************************/
		/* METHOD CALL TO SET CUSTOMER DATE TO TRIP DATE */
		/*************************************************/
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
	
	//TODO: comment
	protected void addCustomer(ArrayList<Customer> cstmrs, Customer cstmr, Table tbl) 
	{
		cstmrs.add(cstmr);
		BusCalculation.scheduleTrip(cstmr);
		BusFinances.setCustomerProfit(cstmr); 
		updateTable(tbl, cstmrs); 
	}
	
	//TODO: comment
	protected Customer removeCustomer(ArrayList<Customer> cstmrs, int cstmrIndx)
	{
		BusCalculation.unscheduleTrip(cstmrs.get(cstmrIndx)); 
		return cstmrs.remove(cstmrIndx); 
	}
	
	//TODO: comment
	protected String getCustomerProfitString(Customer cstmr) 
	{
		return cstmr.getTotalPriceFormatted(); 
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
