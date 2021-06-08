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
		int columnCount = tbl.getColumnCount(); 		// NUMBER OF COLUMN OBJECTS IN TABLE 
														// (TABLE CAN HAVE ONE PHYSICAL COLUMN 
														// BUT 0 COLUMN OBJECTS)
		
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
		int columnCount = tbl.getColumnCount(); 		// NUMBER OF COLUMN OBJECTS IN TABLE 
														// (TABLE CAN HAVE ONE PHYSICAL COLUMN 
														// BUT 0 COLUMN OBJECTS)
		
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
		tripDate = LocalDate.parse(dateTimeToLocalDateStringFormat(day, month, year)); 
		
		/*************************************************/
		/* METHOD CALL TO SET CUSTOMER DATE TO TRIP DATE */
		/*************************************************/
		cstmr.setDate(tripDate); 
	}
	
	protected void addCustomer(ArrayList<Customer> cstmrs, Customer cstmr, int indx, Table tbl) 
	/************************************************************************************************/
	/* PRECONDITION:  A CUSTOMER NEEDS TO BE ADDED TO THE ARRAY LIST OF CUSTOMERS					*/
	/* POSTCONDITION: ADDS THE CUSTOMER TO THE ARRAY LIST OF CUSTOMERS, SCHEDULES THE CUSTOMER'S 	*/
	/*				  TRIP, CALCULATES PROFIT FROM CUSTOMER											*/
	/************************************************************************************************/
	{
		/*************************************************/
		/* METHOD CALL TO ADD CUSTOMER TO CUSTOMERS LIST */
		/*************************************************/
		cstmrs.add(indx, cstmr);
		
		/********************************************/
		/* METHOD CALL TO SORT CUSTOMERS LIST BY ID */
		/********************************************/
		cstmrs.sort(new Customer.CompareId()); 
		
		/***************************************************************************/
		/* METHOD CALL TO SCHEDULE CUSTOMER TRIP ON DATE STORED IN CUSTOMER OBJECT */
		/***************************************************************************/
		BusCalculation.scheduleTrip(cstmr);
		
		/***************************************************************************/
		/* METHOD CALL TO UPDATE PROFIT IN CUSTOMER OBJECT AND UPDATE TOTAL PROFIT */
		/***************************************************************************/
		BusFinances.updateCustomerProfit(cstmr); 
		
		/************************************************/
		/* METHOD CALL UPDATE TABLE (LIST OF CUSTOMERS) */
		/************************************************/
		updateTable(tbl, cstmrs); 
	}
	
	protected void addCustomer(ArrayList<Customer> cstmrs, Customer cstmr, Table tbl) 
	/************************************************************************************************/
	/* PRECONDITION:  A CUSTOMER NEEDS TO BE ADDED AT THE END OF THE ARRAY LIST OF CUSTOMERS		*/
	/* POSTCONDITION: ADDS THE CUSTOMER TO THE ARRAY LIST OF CUSTOMERS, SCHEDULES THE CUSTOMER'S 	*/
	/*				  TRIP, CALCULATES PROFIT FROM CUSTOMER											*/
	/************************************************************************************************/
	{
		/*************************************************/
		/* METHOD CALL TO ADD CUSTOMER TO CUSTOMERS LIST */
		/*************************************************/
		cstmrs.add(cstmr);
		
		/********************************************/
		/* METHOD CALL TO SORT CUSTOMERS LIST BY ID */
		/********************************************/
		cstmrs.sort(new Customer.CompareId());
		
		/***************************************************************************/
		/* METHOD CALL TO SCHEDULE CUSTOMER TRIP ON DATE STORED IN CUSTOMER OBJECT */
		/***************************************************************************/
		BusCalculation.scheduleTrip(cstmr);
		
		/***************************************************************************/
		/* METHOD CALL TO UPDATE PROFIT IN CUSTOMER OBJECT AND UPDATE TOTAL PROFIT */
		/***************************************************************************/
		BusFinances.updateCustomerProfit(cstmr); 
		
		/************************************************/
		/* METHOD CALL UPDATE TABLE (LIST OF CUSTOMERS) */
		/************************************************/
		updateTable(tbl, cstmrs); 
	}
	
	protected Customer removeCustomer(ArrayList<Customer> cstmrs, int cstmrIndx)
	/************************************************************************************************/
	/* PRECONDITION:  A CUSTOMER AT THE SPECIFIED INDEX NEEDS TO BE REMOVED FROM 					*/
	/*				  THE LIST OF CUSTOMERS 														*/
	/* POSTCONDITION: UNSCHEDULES THE CUSTOMER'S TRIP, AND REMOVES THE CUSTOMER FROM THE ARRAY LIST */
	/*				  OF CUSTOMERS																	*/
	/************************************************************************************************/
	{
		/*********************************************/
		/* METHOD CALL TO UNSCHEDULE CUSTOMER'S TRIP */
		/*********************************************/
		BusCalculation.unscheduleTrip(cstmrs.get(cstmrIndx)); 
		
		/****************************************************************/
		/* REMOVE CUSTOMER FROM CUSTOMERS LIST, RETURN REMOVED CUSTOMER */
		/****************************************************************/
		return cstmrs.remove(cstmrIndx); 
	}

	protected void clearInput(Text[] flds) 
	/************************************************************************************************/
	/* PRECONDITION:  TEXT FIELDS NEED TO BE CLEARED 												*/
	/* POSTCONDITION: CLEARS ALL INPUT IN TEXT FIELDS 												*/
	/************************************************************************************************/
	{
		/***********************************************************/
		/* FOR LOOP TO CLEAR TEXT FIELDS (SET TEXT TO EMPTY STRING */
		/***********************************************************/
		for (int i = 0; i < flds.length; i++) 
		{
			flds[i].setText("");
		}
	}
	
	protected void updateIndex(ArrayList<Customer> cstmrs)
	/************************************************************************************************/
	/* PRECONDITION:  CUSTOMERS IN CUSTOMER ARRAY NEED THEIR INDEX PROPERTY UPDATED					*/
	/* POSTCONDITION: UPDATES THE INDEX LOCAL VARIABLE OF EACH CUSTOMER IN CUSTOMER ARRAY 			*/
	/*				  TO THE CORRECT VALUE															*/
	/************************************************************************************************/
	{
		/************************************************************/
		/* FOR LOOP TO UPDATE INDEX OF CUSTOMER IN CUSTOMER OBJECTS */
		/* TO THEIR CURRENT INDEX IN CUSTOMER LIST 					*/
		/************************************************************/
		for (int i = 0; i < cstmrs.size(); i++) 
		{
			cstmrs.get(i).setIndex(i);
		}
	}

	protected String dateTimeToLocalDateStringFormat(int dy, int mnth, int yr)
	/************************************************************************************************/
	/* PRECONDITION:  A DAY, MONTH, AND YEAR IN LocalDate FORMAT NEED TO BE CONVERTED TO A STRING 	*/
	/*				  IN DateTime FORMAT															*/
	/* POSTCONDITION: CONVERTS A DAY, MONTH, AND YEAR IN LocalDate FORMAT TO A STRING IN DateTime 	*/
	/*				  PARSEABLE FORMAT																*/																
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		String dateString; 
		
		/****************************************************/
		/* START DATE STRING AS THE YEAR + A DASH SEPARATOR */
		/****************************************************/ 
		dateString = yr + "-"; 
		
		/****************************************************************/
		/* INCREMENT MONTH (DateTime FORMAT IS 0-11, LocalDate is 1-12) */
		/****************************************************************/ 
		mnth++; 
		
		/*****************************************************/
		/* IF MONTH IS LESS THAN 10 ADD A 0 TO DATE STRING   */
		/* (LocalDate REQUIRES A 0 FOR MONTHS AND DAYS < 10) */
		/*****************************************************/ 
		if (mnth < 10) 
		{
			dateString += "0";  
		}
		
		/***********************************************/
		/* ADD MONTH AND DASH SEPARATOR TO DATE STRING */
		/***********************************************/ 
		dateString += mnth + "-";
		
		/*****************************************************/
		/* IF DAY IS LESS THAN 10 ADD A 0 TO DATE STRING     */
		/* (LocalDate REQUIRES A 0 FOR MONTHS AND DAYS < 10) */
		/*****************************************************/ 
		if (dy < 10) 
		{
			dateString += "0"; 
		}
		
		/**************************/
		/* ADD DAY TO DATE STRING */
		/**************************/
		dateString += dy; 

		/**********************/
		/* RETURN DATE STRING */
		/**********************/
		return dateString; 
	}
	
	protected boolean vaildDate(DateTime dateTime) 		
	/************************************************************************************************/
	/* PRECONDITION:  A DATE NEEDS TO BE DETERMINED TO BE A VALID DATE 								*/
	/* POSTCONDITION: DETERMINES IF THE DATE IS A VALID DATE (DATE IS AFTER TODAY) 					*/															
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		LocalDate date = LocalDate.parse(dateTimeToLocalDateStringFormat		// DATE REPRESENTED 
				(dateTime.getDay(), dateTime.getMonth(), dateTime.getYear()));	// BY dateTime OBJECT
																				// (CONVERTED TO 
																				// LocalDate FORMAT) 
		
		/***********************************************************/
		/* IF STATEMENT TO RETURN TRUE IF THE DATE IS AFTER TODAY, */
		/* FALSE IF THE DATE IS TODAY'S DATE OR BEFORE TODAY 	   */
		/***********************************************************/ 
		if (date.isAfter(LocalDate.now()))	
		{			
			return true;
		}
		else 
		{
			return false; 
		}
	}
	
	protected void openSubWindow(AbstractProgramWindow wndw, Shell shl) 
	/************************************************************************************************/
	/* PRECONDITION:  A SUB WINDOW OF THE WINDOW REPRESENTED BY THE SHELL OBJECT NEEDS TO BE OPENED */
	/*				  (shl SHOULD BE A SHELL REPRESENTING AN EXISTING WINDOW)						*/
	/* POSTCONDITION: A SPECIFIED WINDOW (wndw) IS OPENED USING shl AS ITS' SHELL					*/													
	/************************************************************************************************/
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
