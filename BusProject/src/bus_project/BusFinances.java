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
import java.time.LocalDate;
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import java.util.Currency;
import java.util.List;

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
		double profit = 0; 
		
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
		double profit = 0;
		
		try 
		{
			List<ArrayList<Customer>> customersToDate = BusCalculation.getCustomersToDate(dt); 
			
			for (int i = 0; i < customersToDate.size(); i++) 
			{
				for (int j = 0; j < customersToDate.get(i).size(); j++) 
				{
					profit += customersToDate.get(i).get(j).getTotalPrice(); 
				}
			}
			return nf.format(profit); 
		}
		catch (Exception exc)
		{
			profit = 0; 
			return nf.format(profit); 
		}
	}
}
