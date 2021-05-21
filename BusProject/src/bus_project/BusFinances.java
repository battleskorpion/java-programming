package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import java.time.LocalDate;
import java.util.ArrayList;
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
	// default constructor will be called upon static reference of class
	public BusFinances() 
	{
		nf.setCurrency(USD);
	}
	
	/******************/
	/* METHOD SECTION */
	/******************/
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	// returns new value of profit 
	public static double setCustomerProfit(Customer cstmr) 
	{
		double amt = cstmr.getNumPersons() * TICKET_PRICE; 
		cstmr.addTotalPrice(amt); 
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
			
			//System.out.println(BusCalculation.getCustomersToDate(dt)); 
			
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
				//    profit += customers.get(i).getTotalPrice();  
				//}
			//}
			
			for (int i = 0; i < customersToDate.size(); i++) 
			{
				for (int j = 0; j < customersToDate.get(i).size(); j++) 
				{
					profit += customersToDate.get(i).get(j).getTotalPrice(); 
					//System.out.println(customersToDate.get(i).get(j).getTotalPrice()); // still not working, no print ahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
				}
			}
			
			//System.out.println("good return"); 
			//System.out.println(nf.format(profit)); 
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
