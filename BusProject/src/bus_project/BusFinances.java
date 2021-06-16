/********************************************/
/* INFORMATION SECTION 						*/
/* BusFinances.java							*/
/* Darian Siembab 							*/
/* 											*/
/* CLASS INHERITING Finances.java TO 		*/
/* PERFORM FINANCE CALCULATIONS SPECIFIC	*/
/* TO THE BUS PROGRAM						*/
/********************************************/ 

package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import java.time.LocalDate;								// FOR STORING DATES
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import java.util.Currency;								// REPRESENTS A CURRENCY
import java.util.List;									// AN ORDERED COLLECTION (ALSO KNOWN AS A 
														// SEQUENCE). 

public class BusFinances extends Finances 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	public static double TICKET_PRICE = 49.99; 	//TODO: label
	
	/***********************/ 
	/* CONSTRUCTOR SECTION */
	/***********************/ 
	public BusFinances() 
	{
		currency = Currency.getInstance(Messages.getLocale());
		nf.setCurrency(currency);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
	}
	
	/************************************************************************************************/
	/*										METHOD SECTION 			  				  				*/
	/************************************************************************************************/
	
	// 
	public static double updateCustomerProfit(Customer cstmr) 
	/************************************************************************************************/
	/* PRECONDITION:  CUSTOMER PROFIT NEEDS TO BE UPDATED 							  				*/
	/* POSTCONDITION: RETURNS NEW VALUE OF PROFIT  													*/
	/************************************************************************************************/
	{
		// subtract previous profit amount + add new profit amount = add difference in profit
		double amt = cstmr.getNumPersons() * TICKET_PRICE; 
		subtractProfit(cstmr.getTotalPrice()); 
		cstmr.setTotalPrice(amt); 
		return addProfit(amt);
	}
	

	public static String getProfitOnDate(LocalDate dt) 
	/************************************************************************************************/
	/* PRECONDITION:  PROFIT OF THE SPECIFIED DATE IS NEEDED 				  						*/
	/* POSTCONDITION: RETURNS TOTAL PROFIT FROM TRIPS SCHEDULED ON DATE								*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		double profit = 0; 								// PROFIT OF DATE
		
		/**************************************/
		/* IF A TRIP(S) IS BOOKED ON THE DATE */
		/**************************************/
		if (BusCalculation.getDates().contains(dt)) 
		{
			/*****************************************/
			/* ADD THE PROFIT FROM EACH TRIP ON DATE */
			/*****************************************/
			for (Customer cstmr: BusCalculation.getCustomersOnDate(dt))
			{
				profit += cstmr.getTotalPrice();
			}
			return nf.format(profit);
		}
		else
		{
			return nf.format(profit);
		}
	}
	
	public static String getProfitToDate(LocalDate dt) 
	/************************************************************************************************/
	/* PRECONDITION:  PROFIT FROM TRIPS UNTIL DATE IS NEEDED				  						*/
	/* POSTCONDITION: RETURNS SUM OF PROFIT FROM EACH DATE UNTIL SPECIFIED DATE						*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		double profit = 0;								// PROFIT TO DATE
		List<ArrayList<Customer>> customersToDate; 		// LIST OF CUSTOMERS ON EACH DAY TO DATE 
		
		/******************************************/
		/* TRY TO SUM PROFIT OF ALL DATES TO DATE */
		/******************************************/
		try 
		{
			customersToDate = BusCalculation.getCustomersToDate(dt); 
			
			/**************************************************************/
			/* LOOP THROUGH EACH LIST OF CUSTOMERS TO SUM PROFITS OF EACH */
			/**************************************************************/
			for (int i = 0; i < customersToDate.size(); i++) 
			{
				for (int j = 0; j < customersToDate.get(i).size(); j++) 
				{
					profit += customersToDate.get(i).get(j).getTotalPrice(); 
				}
			}
			return nf.format(profit); 
		}
		/**********************************/
		/* SET PROFIT TO 0 IF FAIL OCCURS */
		/**********************************/
		catch (Exception exc)
		{
			profit = 0; 
			return nf.format(profit); 
		}
	}
}
