package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;

public class BusFinances extends Finances 
{
	public static double TICKET_PRICE = 49.99; 
	
	// default constructor will be called
	public BusFinances() 
	{
		nf.setCurrency(USD);
	}
	
	//TODO: method section comment
	
	//TODO: method comment
	// returns new value of profit 
	public static double addCustomerProfit(Customer cstmr) 
	{
		double amt = cstmr.getNumPersons() * TICKET_PRICE; 
		cstmr.addTotalPrice(amt); 
		return addProfit(amt);
	}
	
	//TODO: method comment
	// returns new value of profit 
	public static double removeCustomerProfit(Customer cstmr)
	{
		double amt = cstmr.getNumPersons() * TICKET_PRICE;
		cstmr.subtractTotalPrice(amt); 
		return subtractProfit(amt); 
	}

	//TODO: method comment
	public static String getProfitOnDate(LocalDate dt) 
	{
		double profit = 0; 
		
		for (Customer cstmr: BusCalculation.getCustomersOnDate(dt))
		{
			profit += cstmr.getTotalPrice();
		}
		return nf.format(profit);
	}
	
	//TODO: comment
	public static String getProfitToDate(LocalDate dt) 
	{
		double profit = 0;
		
		for (ArrayList<Customer> customers : BusCalculation.getCustomersToDate(dt)) 
		{
			for (int i = 0; i < customers.size(); i++) 
			{
				profit += customers.get(i).getTotalPrice();  
			}
		}
		return nf.format(profit); 
	}
}
