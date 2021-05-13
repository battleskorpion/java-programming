package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BusCalculation 
{
	// TODO: make these proper labels 
	/*********************/
	/* CONSTANTS SECTION */
	/*********************/ 
	public static final int MAX_BUSES = 20; 						// maximum buses available per day
	public static final int MAX_CAPACITY = 20; 						// maximum capacity of a bus
	public static final int MIN_CAPACITY = 10;						// minimum capacity a bus should be operating with
	public static final int MAX_PAX = MAX_BUSES * MAX_CAPACITY; 	// maximum passengers per day
	
	/********************/
	/* VARIABLE SECTION */
	/********************/
	// static variables
	private static ArrayList<LocalDate> dates = new ArrayList<LocalDate>(); 								//TODO: issues because this isnt in order i think 
	//private static ArrayList<Integer> busesPerDate;				// number of buses used per each date  
	private static ArrayList<ArrayList<Customer>> customers = new ArrayList<ArrayList<Customer>>(); 	 	// customers each date 
	
	///* constructors */
	//
	//public BusCalculation () 
	//{
	//	
	//}
	
	/******************/
	/* METHOD SECTION */
	/******************/
	
	// TODO: fix bus reservation logic, notify of refund/potential refund, etc etc etc etc etc etc etc etc etce tc e tc er cetc etc etc etc etc et etc etc etc etc etc etc etce tetc etc. 
	
	// schedule customer on date selected
	//public static int scheduleTrip (Customer cstmr) 
	//{
	//	/********************/
	//	/* VARIABLE SECTION */
	//	/********************/
	//	LocalDate date = cstmr.getDate(); 
	//	int dateIndex; 			// index of current date in dates array
	//	int numPax; 			// number of other individual passengers (based on combined group size from all other groups that day) 
	//	
	//	
	//	// TODO: clean up code (really this entire file is a mess) 
	//	// if another booking from any customer exists on same date
	//	if (dates.contains(date)) 
	//	{
	//		//
	//		//for (int i = 0; i < customers.get(dates.indexOf(date)).size(); i++) {
	//		//	// if another booking from identical customer exists on same date
	//		//	if (customers.get(dates.indexOf(date)).get(i).getName() == cstmr.getName()) {
	//		//		// ask to merge customers? 
	//		//		merge = JOptionPane.showConfirmDialog(null, "A customer with the same name was found on the same date. Merge the customers?", "Merge customers?" , JOptionPane.YES_NO_OPTION); 
	//		//		
	//		//		// if merge
	//		//		if (merge == 0) {
	//		//			
	//		//		}
	//		//		// if not merge
	//		//		else {
	//		//			cstmr.setName(cstmr.getName() + "");
	//		//		}
	//		//		break; 
	//		//	}
    //        //
	//		//	
	//		//}
	//		// another booking from another customer(s) exists on same date
	//		//else {
	//		
	//		// TODO: label method calls 
	//			dateIndex = dates.indexOf(date); 
	//			
	//			// calculate numPax
	//			numPax = getNumPaxOnDate(date); 
	//			
	//			// TODO: label if 
	//			if (numPax + cstmr.getNumPersons() <= MAX_PAX) 
	//			{
	//				// TODO: label method call
	//				customers.get(dateIndex).add(cstmr); 
	//				
	//				return 0; 	// no error
	//			}
	//			else 
	//			{
	//				return -2; 	// not enough buses 
	//			}
	//		//}
	//		
	//	}
	//	// else (no booking on date)
	//	else 
	//	{	
	//		if (cstmr.getNumPersons() <= MAX_PAX) 
	//		{
	//			// TODO: label method calls
	//			if (getFirstDateAfterDate(cstmr.getDate()) == null)
	//			{
	//				dates.add(cstmr.getDate()); 						// add new date 
	//			}
	//			else 
	//			{
	//				dates.add(dates.indexOf(getFirstDateAfterDate(cstmr.getDate())), cstmr.getDate());
	//			}
	//			customers.add(new ArrayList<Customer>());			// create new ArrayList aligning with new date
	//			customers.get(customers.size() - 1).add(cstmr);		// add customer to new ArrayList
	//			
	//			return 0; 		// no error
	//		}
	//		else 
	//		{
	//			return -2; 		// not enough buses 
	//		}
	//	}
	//}
	
	// TODO: 
	// new version
	// prioritize filling buses to max 
	// codes: 
	// 0 	- no error
	// -x 	- number to refund 
	////// -1 	- some patrons will have to be refunded (a bus is not filled to min capacity) 
	////// -2	- passengers > MAX_PAX, not enough buses, some patrons will have to be refunded
	////// -3   - passengers > MAX_PAX, not enough buses for any patrons
	//TODO: LABEL CODE BETTER 
	public static int scheduleTrip(Customer cstmr)   
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		LocalDate date = cstmr.getDate(); 
		int dateIndex; 			// index of current date in dates array
		int numPax; 			// number of other individual passengers (based on combined group size from all other groups that day) 
		int numToRefund; // number of persons to refund
		
		// another trip already on date 
		if (dates.contains(date)) 
		{
			//TODO: label method calls 
			// get index of date in dates 
			dateIndex = dates.indexOf(date); 
						
			// calculate numPax
			numPax = getNumPaxOnDate(date);
						
			// TODO: label if 
			//if (cstmr.getNumPersons() >= MAX_PAX) {
			//	return -3; 
			//}
			if (numPax + cstmr.getNumPersons() > MAX_PAX) 
			{
				numToRefund = MAX_PAX - numPax + cstmr.getNumPersons();
				cstmr.refundPersons(numToRefund);
				customers.get(dateIndex).add(cstmr); 
				return -1 * numToRefund;  	// number to refund 
			}
			// if prexisting customers + new customer fill a bus at least to minimum capacity, or fill a bus entirely 
			else if ((numPax + cstmr.getNumPersons()) % MAX_CAPACITY >= MIN_CAPACITY || (numPax + cstmr.getNumPersons()) % MAX_CAPACITY == 0) 
			{
				// TODO: label method call
				customers.get(dateIndex).add(cstmr); 
							
				return 0; 	
			}
			// else (some customers will have to be refunded because a bus won't be filled to MIN_CAPACITY
			else 
			{
				numToRefund = numPax + cstmr.getNumPersons() % MAX_CAPACITY; 
				cstmr.refundPersons(numToRefund); 
				customers.get(dateIndex).add(cstmr); 
				return -1 * numToRefund; 
			}
		}
		else 
		{	
			if (cstmr.getNumPersons() >= MIN_CAPACITY) 
			{
				// TODO: label method calls
				// if/else to add date to dates array
				if (getFirstDateAfterDate(cstmr.getDate()) == null)
				{
					dates.add(cstmr.getDate()); 						// add new date 
					dateIndex = dates.size() - 1; 
				}
				else 
				{
					dateIndex = dates.indexOf(getFirstDateAfterDate(cstmr.getDate())); 
					dates.add(dateIndex, cstmr.getDate());
				}
				customers.add(dateIndex, new ArrayList<Customer>());			// create new ArrayList aligning with new date
				
				// if prexisting customers + new customer fill a bus at least to minimum capacity, or fill a bus entirely 
				if (cstmr.getNumPersons() % MAX_CAPACITY >= MIN_CAPACITY || cstmr.getNumPersons() % MAX_CAPACITY == 0) 
				{
					customers.get(dateIndex).add(cstmr);		// add customer to new ArrayList
				}
				// else (some customers will have to be refunded because a bus won't be filled to MIN_CAPACITY
				else 
				{
					numToRefund = cstmr.getNumPersons() % MAX_CAPACITY; 
					cstmr.refundPersons(numToRefund); 
					customers.get(dateIndex).add(cstmr); 
					return -1 * numToRefund; 
				}
						
				return 0; 		// no error
			}
			else 
			{
				numToRefund = cstmr.getNumPersons(); 
				cstmr.refundPersons(numToRefund);
				return -1 * numToRefund; 
			}
		}
	}
	
	// TODO: label method
	//TODO: modifications potential maybe 
	public static boolean unscheduleTrip (Customer cstmr) 
	{
		//customers.get(customers.indexOf(cstmr.getDate())).remove(customers.get(customers.indexOf(cstmr.getDate())).indexOf(cstmr));
		LocalDate date = cstmr.getDate(); 
		int dayIndex = dates.indexOf(date); 
		int cstmrLocation = customers.get(dayIndex).indexOf(cstmr); 
		BusFinances.setCustomerProfit(customers.get(dayIndex).get(cstmrLocation)); 
		customers.get(dayIndex).remove(cstmrLocation); 
		if (customers.get(dayIndex).size() == 0) 
		{
			customers.remove(dayIndex);
			dates.remove(dayIndex);
		}
		
		return true; 
	}
	
	// TODO: label method properly 
	// calculates number of buses which will be necessary for the day
	public static int getNumBuses(LocalDate dt) {	
		return (int)Math.ceil(getNumPaxOnDate(dt) / ((double)MAX_CAPACITY)); 
	}
	
	// TODO: label method
	public static int getNumPaxOnDate(LocalDate dt) 
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
	
	public static ArrayList<Customer> getCustomersOnDate(LocalDate dt) 
	{
		if (dates.contains(dt)) 
		{
			return customers.get(dates.indexOf(dt)); 
		}
		else 
		{
			return null;
		} 
	}
	
	public static List<ArrayList<Customer>> getCustomersToDate(LocalDate dt) 
	{
		
		if (dates.contains(dt)) 
		{
			return customers.subList(0, dates.indexOf(dt)); 	
		}
		else 
		{
			LocalDate priorDate = null; 
			for (int i = dates.size() - 1; i >= 0; i--) 
			{
				if (dates.get(i).isBefore(dt)) 
				{
					priorDate = dates.get(i); 
				}
			}
			if (priorDate == null)
			{
				return null;
			}
			else 
			{
				return customers.subList(0, dates.indexOf(priorDate) + 1); 		//.sublist is exclusive requiring the +1
			}
		}
	}

	// TODO: label method
	public static ArrayList<LocalDate> getDates() 
	{
		return dates;
	}
	
	//public static ArrayList<LocalDate> getDatesToDate(LocalDate dt) 
	//{
	//	if (dates.contains(dt)) 
	//	{
	//		return (ArrayList<LocalDate>)dates.subList(0, dates.indexOf(dt)); 	// convert from List to ArrayList for consistency
	//	}
	//	else 
	//	{
	//		return null; 
	//	}
	//}
	
	public static LocalDate getFirstDateAfterDate(LocalDate dt) 
	{
		LocalDate firstDateAfterDate = null; 
		for (LocalDate date : dates)  
		{
			if (date.compareTo(dt) > 0) 
			{
				if (firstDateAfterDate == null) 
				{
					firstDateAfterDate = date; 
				}
				else if (date.compareTo(firstDateAfterDate) < 0)
				{
					firstDateAfterDate = date; 
				}
			}
		}
		return firstDateAfterDate; 
	}
}
