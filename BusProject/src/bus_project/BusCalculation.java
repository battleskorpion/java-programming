package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;

public class BusCalculation 
{
	// constants
	public static final int MAX_BUSES = 20; 						// maximum buses available per day
	public static final int MAX_CAPACITY = 20; 						// maximum capacity of a bus
	public static final int MIN_CAPACITY = 10;						// minimum capacity a bus should be operating with
	
	// combined constants 
	public static final int MAX_PAX = MAX_BUSES * MAX_CAPACITY; 	// maximum passengers per day
	
	// static variables
	private static ArrayList<LocalDate> dates; 
	//private static ArrayList<Integer> busesPerDate;				// number of buses used per each date  
	private static ArrayList<ArrayList<Customer>> customers; 	 	// customers each date 
	
	/* constructors */
	
	public BusCalculation () 
	{
		
	}
	
	/* method section */ 
	
	// schedule customer on date selected
	public static int scheduleTrip (Customer cstmr) 
	{
		LocalDate date = cstmr.getDate(); 
		//int merge; 			// whether or not to merge	// 0 = yes, 1 = no
		int dateIndex; 			// index of current date in dates array
		int numPax; 			// number of other individual passengers (based on combined group size from all other groups that day) 
		
		
		// if another booking from any customer exists on same date
		if (dates.contains(date)) 
		{
			//
			//for (int i = 0; i < customers.get(dates.indexOf(date)).size(); i++) {
			//	// if another booking from identical customer exists on same date
			//	if (customers.get(dates.indexOf(date)).get(i).getName() == cstmr.getName()) {
			//		// ask to merge customers? 
			//		merge = JOptionPane.showConfirmDialog(null, "A customer with the same name was found on the same date. Merge the customers?", "Merge customers?" , JOptionPane.YES_NO_OPTION); 
			//		
			//		// if merge
			//		if (merge == 0) {
			//			
			//		}
			//		// if not merge
			//		else {
			//			cstmr.setName(cstmr.getName() + "");
			//		}
			//		break; 
			//	}
            //
			//	
			//}
			// another booking from another customer(s) exists on same date
			//else {
				dateIndex = dates.indexOf(date); 
				
				// calculate numPax
				numPax = getNumPaxOnDay(date); 
				
				if (numPax + cstmr.getNumPersons() <= MAX_PAX) 
				{
					customers.get(dateIndex).add(cstmr); 
					
					return 0; 	// no error
				}
				else 
				{
					return -2; 	// not enough buses 
				}
			//}
			
		}
		// else (no booking on date)
		else 
		{	
			if (cstmr.getNumPersons() <= MAX_PAX) 
			{
				dates.add(cstmr.getDate()); 						// add new date 
				customers.add(new ArrayList<Customer>());			// create new ArrayList aligning with new date
				customers.get(customers.size() - 1).add(cstmr);		// add customer to new ArrayList
				
				return 0; 		// no error
			}
			else 
			{
				return -2; 		// not enough buses 
			}
		}
	}
	
	// calculates number of buses which will be necessary for the day
	public static int getNumBuses(LocalDate dt) {	
		return (int)Math.ceil(getNumPaxOnDay(dt) / ((double)MAX_CAPACITY)); 
	}
	
	public static int getNumPaxOnDay(LocalDate dt) 
	{
		int numPax = 0; 		// number of passengers on the day
		
		if (dates.contains(dt))
		{
			for (Customer cstmr : customers.get(dates.indexOf(dt)))
			{
				numPax += cstmr.getNumPersons(); 
			}
			return numPax; 
		}
		else 
		{
			return 0; 			// date not found in booked dates so no bookings are on the date, means no pax on the date
		}
	}
}
