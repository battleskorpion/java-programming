package bus_project;

import java.time.LocalDate;

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
	public static String getProfitOnDate(LocalDate date) {
		double profit = 0; 
		
		for (Customer cstmr: BusCalculation.getCustomersOnDate(date))
		{
			profit += cstmr.getTotalPrice();
		}
		return nf.format(profit);
	}
}
