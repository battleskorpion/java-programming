/********************************************/
/* INFORMATION SECTION 						*/
/* AbstractProgramWindow.java				*/
/* Darian Siembab 							*/
/* 											*/
/* ABSTRACT BUS PROGRAM WINDOW CLASS TO BE 	*/
/* INHERITED BY WINDOW CLASSES, INCLUDES 	*/
/* COMMON METHODS AND VARIABLES FOR BUS		*/
/* PROGRAM WINDOWS							*/
/********************************************/ 

package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import java.time.LocalDate;								// FOR STORING DATES
import java.time.format.DateTimeFormatter;				// FOR FORMATTING DATES TO STORE AS LocalDate
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.							
import org.eclipse.swt.widgets.DateTime;				// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Table;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Text;					// FOR SWT WINDOWS/WIDGETS		
import abstractProgramWindow.*; 						// FOR PROGRAM WINDOW SUPERCLASS FUNCTIONS 

public class AbstractBusProgramWindow extends AbstractProgramWindow
{
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
	
	public void setCustomerDetails(Customer customer, String name, int groupSize, 
			int index, int id, String tripDateString) 
	/************************************************************************************************/
	/* PRECONDITION:  DETAILS OF CUSTOMER NEED TO BE SET											*/
	/* POSTCONDITION: SETS CUSTOMER DETAILS TO NEW VALUES 						  					*/
	/************************************************************************************************/
	{
		/**************************************/
		/* METHOD CALL TO SET CUSTOMER'S NAME */
		/**************************************/
		customer.setName(name);
		
		/***************************************************/
		/* METHOD CALL TO SET CUSTOMER'S NUMBER OF PERSONS */
		/***************************************************/
		customer.setNumPersons(groupSize); 
		
		/********************************************************/
		/* METHOD CALL TO SET CUSTOMER'S INDEX PROPERTY TO indx */
		/********************************************************/
		customer.setIndex(index);			
		
		/****************************************/
		/* METHOD CALL TO SET CUSTOMER ID TO id */
		/****************************************/
		customer.setId(id);							
		
		/*************************************************/
		/* METHOD CALL TO SET CUSTOMER DATE TO TRIP DATE */
		/*************************************************/
		customer.setDate(LocalDate.parse(tripDateString, DateTimeFormatter.ofPattern("M[M]/dd/yyyy"))); 
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
		
		/*****************************************************************************/
		/* METHOD CALLS TO SCHEDULE CUSTOMER TRIP ON DATE STORED IN CUSTOMER OBJECT, */
		/* AND DISPLAY NUMBER OF PERSONS REFUNDED IF ANY 					  		 */ 
		/*****************************************************************************/
		BusCalculation.displayRefundDialog(BusCalculation.scheduleTrip(cstmr));
		
		/***************************************************************************/
		/* METHOD CALL TO UPDATE PROFIT IN CUSTOMER OBJECT AND UPDATE TOTAL PROFIT */
		/***************************************************************************/
		BusFinances.updateCustomerProfit(cstmr); 
		
		/************************************************/
		/* METHOD CALL UPDATE TABLE (LIST OF CUSTOMERS) */
		/************************************************/
		updateTable(tbl, cstmrs); 
	}
	
	protected void reAddCustomer(ArrayList<Customer> cstmrs, Customer cstmr, int indx, Table tbl) 
	/************************************************************************************************/
	/* PRECONDITION:  A CUSTOMER NEEDS TO BE ADDED BACK TO THE ARRAY LIST OF CUSTOMERS				*/
	/* POSTCONDITION: ADDS THE CUSTOMER TO THE ARRAY LIST OF CUSTOMERS, 							*/
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
		
		/************************************************/
		/* METHOD CALL UPDATE TABLE (LIST OF CUSTOMERS) */
		/************************************************/
		updateTable(tbl, cstmrs); 
	}
	
	protected void reAddCustomer(ArrayList<Customer> cstmrs, Customer cstmr, Table tbl) 
	/************************************************************************************************/
	/* PRECONDITION:  A CUSTOMER NEEDS TO BE RE-ADDED AT THE END OF THE ARRAY LIST OF CUSTOMERS		*/
	/* POSTCONDITION: ADDS THE CUSTOMER TO THE ARRAY LIST OF CUSTOMERS, 							*
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
		
		/*****************************************************************************/
		/* METHOD CALLS TO SCHEDULE CUSTOMER TRIP ON DATE STORED IN CUSTOMER OBJECT, */
		/* AND DISPLAY NUMBER OF PERSONS REFUNDED IF ANY 					  		 */ 
		/*****************************************************************************/
		BusCalculation.displayRefundDialog(BusCalculation.scheduleTrip(cstmr));
		
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
	
	public void updateIndex(ArrayList<Customer> cstmrs)
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
	
	/********************************************************************************/
	/* PRECONDITION:  AN ID IS ENTERED												*/
	/* POSTCONDITION: RETURNS TRUE IF THE ID IS UNIQUE, FALSE IF NOT				*/
	/********************************************************************************/
	public boolean uniqueID(int id, ArrayList<Customer> customers)
	{
		/*******************************************/
		/* FOR LOOP ITERATE THROUGH CUSTOMERS LIST */
		/*******************************************/
		for (int i = 0; i < customers.size(); i++) 
		{
			/********************************************************/
			/* IF A CUSTOMER'S ID IS IDENTICAL TO THE ID SUBMITTED, */
			/* RETURN FALSE 										*/
			/********************************************************/ 
			if (customers.get(i).getId() == id) 
			{
				return false; 
			}
		}
		return true; 
	}
}
