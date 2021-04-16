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
	public static double addCustomerProfit(Customer cstmr) 
	{
		return addProfit(cstmr.addTotalPrice(cstmr.getNumPersons() * TICKET_PRICE));
	}
	
	//TODO: method comment
	public static double removeCustomerProfit(Customer cstmr)
	{
		return subtractProfit(cstmr.addTotalPrice(cstmr.getNumPersons() * TICKET_PRICE)); 
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
