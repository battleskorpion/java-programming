package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;

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
	
	// TODO: finish of fix bus reservation logic, notify of refund/potential refund, etc etc etc etc etc etc etc etc etce tc e tc er cetc etc etc etc etc et etc etc etc etc etc etc etce tetc etc. 
	
	// TODO: 
	// new version
	// prioritize filling buses to max 
	// codes: 
	// 0 	- no error/issue
	// -x 	- number to refund 
	
	//TODO: LABEL CODE BETTER 
	public static int scheduleTrip(Customer cstmr)   
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		LocalDate date = cstmr.getDate(); 
		int dateIndex; 			// index of current date in dates array
		int numPax; 			// number of other individual passengers (based on combined group size from all other groups that day) 
		int numPaxRefunded; 
		int numToRefund; // number of persons to refund
		int numToUnrefund; 
		
		// take previously refunded persons into account 
		cstmr.unrefundPersons(); 
		
		// another trip already on date 
		if (dates.contains(date)) 
		{
			//TODO: label method calls 
			// get index of date in dates 
			dateIndex = dates.indexOf(date); 
						
			// calculate numPax
			numPax = getNumPaxOnDate(date);
			numPaxRefunded = getNumPaxRefundedOnDate(date); 
						
			// TODO: label if 
			if (numPax + cstmr.getNumPersons() > MAX_PAX) 
			{
				numToRefund = MAX_PAX - numPax + cstmr.getNumPersons();
				cstmr.refundPersons(numToRefund);
				
				customers.get(dateIndex).add(cstmr); 
				displayRefundDialog(numToRefund); 
				return -1 * numToRefund;  	// number refunded
			}
			// if prexisting customers + new customer fill a bus at least to minimum capacity, 
			// or fill a bus entirely 
			else if ((numPax + cstmr.getNumPersons()) % MAX_CAPACITY >= MIN_CAPACITY 
					|| (numPax + cstmr.getNumPersons()) % MAX_CAPACITY == 0) 
			{
				// TODO: label method call
				customers.get(dateIndex).add(cstmr); 
				return 0; 	
			}
			// if prexisting customers + refund some customers + new customer fill a bus at least to minimum capacity, 
			// or fill a bus entirely 
			else if (MIN_CAPACITY - ((numPax + cstmr.getNumPersons()) % MAX_CAPACITY) - numPaxRefunded <= MIN_CAPACITY) 
			{
				
				if (numPax + cstmr.getNumPersons() + numPaxRefunded > MAX_PAX) {
					numToUnrefund = MAX_PAX - numPax - cstmr.getNumPersons(); 
				}
				else {
					numToUnrefund = numPaxRefunded; 
					if ((numPax + cstmr.getNumPersons() + numToUnrefund) % MAX_CAPACITY < MIN_CAPACITY)
					{
						numToUnrefund -= (numPax + cstmr.getNumPersons() + numToUnrefund) % MAX_CAPACITY; 
					}
				}
				
				unrefundLoop: 
				for (int i = 0; i < customers.get(dateIndex).size(); i++)
				{
					if (customers.get(dateIndex).get(i).getNumPersonsRefunded() - numToUnrefund >= 0) {
						numToUnrefund -= customers.get(dateIndex).get(i).unrefundPersons(); 
					}
					// else refund some (or 0) customers and break
					else {
						customers.get(dateIndex).get(i).unrefundPersons(numToUnrefund); 
						break unrefundLoop; 
					}
				}
				
				customers.get(dateIndex).add(cstmr); 
				// TODO: customers were unrefunded window 
				return 0; // none refunded from this customer, only other customers unrefunded
			}
			// else (some customers will have to be refunded because a bus won't be filled to MIN_CAPACITY
			else 
			{
				numToRefund = numPax + cstmr.getNumPersons() % MAX_CAPACITY; 
				cstmr.refundPersons(numToRefund); 
				
				customers.get(dateIndex).add(cstmr); 
				displayRefundDialog(numToRefund);
				return -1 * numToRefund; 		// number refunded
			}
		}
		// no trip on date 
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
					displayRefundDialog(numToRefund);
					return -1 * numToRefund; 
				}
						
				return 0; 		// no error
			}
			else 
			{
				numToRefund = cstmr.getNumPersons(); 
				
				cstmr.refundPersons(numToRefund);
				displayRefundDialog(numToRefund);
				return -1 * numToRefund; 
			}
		}
	}
	
	// TODO: label method
	// TODO: modifications potential maybe
	// TODO: refund, unrefund in the necessary case 
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
	public static int getNumBuses(LocalDate dt) 
	{	
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
	
	public static int getNumPaxRefundedOnDate(LocalDate dt) 
	{
		int numPaxRefunded = 0;
		
		//TODO: label le if
		if (dates.contains(dt)) 
		{
			for (Customer cstmr: customers.get(dates.indexOf(dt))) 
			{
				numPaxRefunded += cstmr.getNumPersonsRefunded(); 
			}
			return numPaxRefunded; 
		}
		else 
		{
			return 0; 
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
	
	protected static void displayRefundDialog(int numRefunded) 
	{
		JOptionPane.showMessageDialog(null, numRefunded + "customers were refunded, due to not meeting minimum capacity");
	}
}
