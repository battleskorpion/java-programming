package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;

public class BusCalculation 
{
	// TODO: make these proper labels 
	// constants
	public static final int MAX_BUSES = 20; 						// maximum buses available per day
	public static final int MAX_CAPACITY = 20; 						// maximum capacity of a bus
	public static final int MIN_CAPACITY = 10;						// minimum capacity a bus should be operating with
	
	// combined constants 
	public static final int MAX_PAX = MAX_BUSES * MAX_CAPACITY; 	// maximum passengers per day
	
	// static variables
	private static ArrayList<LocalDate> dates = new ArrayList<LocalDate>(); 
	//private static ArrayList<Integer> busesPerDate;				// number of buses used per each date  
	private static ArrayList<ArrayList<Customer>> customers = new ArrayList<ArrayList<Customer>>(); 	 	// customers each date 
	
	///* constructors */
	//
	//public BusCalculation () 
	//{
	//	
	//}
	
	/* method section */ 
	
	// TODO: consider minimum bus capacity somehow in logic idk specifically how? (besides definitely min of 10 per day)
	
	// schedule customer on date selected
	public static int scheduleTrip (Customer cstmr) 
	{
		// TODO: label var section etc
		LocalDate date = cstmr.getDate(); 
		//int merge; 			// whether or not to merge	// 0 = yes, 1 = no
		int dateIndex; 			// index of current date in dates array
		int numPax; 			// number of other individual passengers (based on combined group size from all other groups that day) 
		
		
		// TODO: clean up code (really this entire file is a mess) 
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
			
			// TODO: label method calls 
				dateIndex = dates.indexOf(date); 
				
				// calculate numPax
				numPax = getNumPaxOnDay(date); 
				
				// TODO: label if 
				if (numPax + cstmr.getNumPersons() <= MAX_PAX) 
				{
					// TODO: label method call
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
				// TODO: label method calls
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
	
	// TODO: label method
	// TODO: TODO: TODO: remove date if no customers left on date
	public static boolean unscheduleTrip (Customer cstmr) {
		//customers.get(customers.indexOf(cstmr.getDate())).remove(customers.get(customers.indexOf(cstmr.getDate())).indexOf(cstmr));
		LocalDate date = cstmr.getDate(); 
		int dayIndex = dates.indexOf(date); 
		int cstmrLocation = customers.get(dayIndex).indexOf(cstmr); 
		customers.get(dayIndex).remove(cstmrLocation); 
		
		return true; 
	}
	
	// TODO: label method properly 
	// calculates number of buses which will be necessary for the day
	public static int getNumBuses(LocalDate dt) {	
		return (int)Math.ceil(getNumPaxOnDay(dt) / ((double)MAX_CAPACITY)); 
	}
	
	// TODO: label method
	public static int getNumPaxOnDay(LocalDate dt) 
	{
		int numPax = 0; 		// number of passengers on the day
		
		// TODO: label if
		if (dates.contains(dt))
		{
			// TODO: label for loop
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

	// TODO: label method
	public static ArrayList<LocalDate> getDates() {
		return dates;
	}
}
