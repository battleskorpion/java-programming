package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		
		try 
		{
			List<ArrayList<Customer>> customersToDate = BusCalculation.getCustomersToDate(dt); 
			//for (ArrayList<Customer> customers : BusCalculation.getCustomersToDate(dt)) 
			//for (ArrayList<Customer> customers : customersToDate)
			//{
				//System.out.println("loop 1"); 
				//for (Customer customer : customers) {
				//	System.out.println("loop 2"); 
				//	profit += customer.getTotalPrice(); 
				//}
				//for (int i = 0; i < customers.size(); i++) 
				//{
					//profit += customers.get(i).getTotalPrice();  
				//}
			//}
			
			for (int i = 0; i < customersToDate.size(); i++) 
			{
				for (int j = 0; j < customersToDate.get(i).size(); j++) 
				{
					profit += customersToDate.get(i).get(j).getTotalPrice(); 
					System.out.println(customersToDate.get(i).get(j).getTotalPrice()); // still not working, no print ahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
				}
			}
			
			//System.out.println("good return"); 
			System.out.println(nf.format(profit)); 
			return nf.format(profit); 
		}
		catch (Exception exc)
		{
			//System.out.println("exception: "); 
			//exc.printStackTrace(); 
			
			profit = 0; 
			return nf.format(profit); 
		}
	}
}
