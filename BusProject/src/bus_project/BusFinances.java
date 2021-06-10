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
	//TODO: 
	public BusFinances() 
	{
		System.out.println("called"); 
		currency = Currency.getInstance(Messages.getLocale());
		nf.setCurrency(currency);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
	}
	
	/******************/
	/* METHOD SECTION */
	/******************/
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	// returns new value of profit 
	public static double updateCustomerProfit(Customer cstmr) 
	{
		// subtract previous profit amount + add new profit amount = add difference in profit
		double amt = cstmr.getNumPersons() * TICKET_PRICE; 
		subtractProfit(cstmr.getTotalPrice()); 
		cstmr.setTotalPrice(amt); 
		return addProfit(amt);
	}
	
	//TODO: method comment
	// returns new value of profit 
	//public static double removeCustomerProfit(Customer cstmr)
	//{
	//	double amt = cstmr.getNumPersons() * TICKET_PRICE;
	//	cstmr.subtractTotalPrice(amt); 
	//	return subtractProfit(amt); 
	//}

	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public static String getProfitOnDate(LocalDate dt) 
	{
		double profit = 0; 
		
		if (BusCalculation.getDates().contains(dt)) {
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
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public static String getProfitToDate(LocalDate dt) 
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
