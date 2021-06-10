/********************************************/
/* INFORMATION SECTION 						*/
/* BusCalculation.java						*/
/* Darian Siembab 							*/
/* 											*/
/* CLASS WITH STATIC METHODS AND VARIABLES	*/
/* FOR SCHEDULING AND UNSCHEDULING CUSTOMER	*/
/* TRIPS, AND PERFORMING NECESSARY LOGIC	*/
/********************************************/ 
package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

//TODO: number of refunds refunded not always correct if pax of another customer are refunded
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
	private static ArrayList<ArrayList<Customer>> customers; 
	private static ArrayList<LocalDate> dates; 		
			
	//TODO: constructor
	public BusCalculation()
	{
		instantiateLists();
	}
	
	/************************************************************************************************/
	/* 										  METHOD SECTION 										*/
	/************************************************************************************************/
	
	//TODO: LABEL CODE BETTER 
	public static int scheduleTrip(Customer cstmr)   
	/************************************************************************************************/
	/* PRECONDITION:  CUSTOMER TRIP NEEDS TO BE SCHEDULED ON DATE AFTER TODAY						*/
	/* POSTCONDITION: CUSTOMER IS SCHEDULED ON THE CORRECT DATE, REFUNDS OR UNREFUNDS ARE MADE TO   */
	/* 				  MATCH MINIMUM ANDMAXIMUM CAPACITIES OF BUSES, NUMBER OF REFUNDS RETURNED IF 	*/
	/*				  APPLICABLE																	*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		LocalDate date = cstmr.getDate(); 		// 
		int dateIndex; 							// index of current date in dates array
		int numPax; 							// number of other individual passengers 
												// (based on combined group size from all other groups that day) 
		int numPaxRefunded; 
		int numToRefund; 						// number of persons to refund
		int numToUnrefund; 
		
		// take previously refunded persons into account 
		cstmr.unrefundPersons(); 
		
		// another trip already on date 
		if (dates.contains(date)) 
		{
			System.out.println("date exists"); 
			//TODO: label method calls 
			// get index of date in dates 
			dateIndex = dates.indexOf(date); 
						
			// calculate numPax
			numPax = getNumPaxOnDate(date);
			numPaxRefunded = getNumPaxRefundedOnDate(date); 
						
			// TODO: label if 
			if (numPax + cstmr.getNumPersons() > MAX_PAX) 
			{
				numToRefund = numPax + cstmr.getNumPersons() - MAX_PAX;
				cstmr.refundPersons(numToRefund);
				
				customers.get(dateIndex).add(cstmr); 
				displayRefundDialog(numToRefund); 
				System.out.println("number refunded (too many pax): " + numToRefund); 	// test
				return -1 * numToRefund;  	// number refunded
			}
			// if prexisting customers + refund some customers + new customer fill a bus at least to minimum capacity, 
			// or fill a bus entirely 
			// TODO: LOGIC FIX
			else if ((numPax + cstmr.getNumPersons() + numPaxRefunded) % MAX_CAPACITY >= MIN_CAPACITY) 
			{
				numToUnrefund = numPaxRefunded; 
				if (numPax + cstmr.getNumPersons() + numToUnrefund > MAX_PAX) 
				{
					numToUnrefund = MAX_PAX - numPax - cstmr.getNumPersons(); 
				}
				else
				{
					if ((numPax + cstmr.getNumPersons() + numToUnrefund) % MAX_CAPACITY == 0) 
					{
						//numToUnrefund = 
					}
					else if ((numPax + cstmr.getNumPersons() + numToUnrefund) % MAX_CAPACITY < MIN_CAPACITY)
					{
						numToUnrefund -= (numPax + cstmr.getNumPersons() + numToUnrefund) % MAX_CAPACITY; 
					}	
				}
				
				// debug 
				System.out.println(numToUnrefund + "(unrefund)"); 
				
				unrefundLoop: 
				for (int i = 0; i < customers.get(dateIndex).size(); i++)
				{
					// if unrefunds > passengers refunded of customer, unrefund as many as possible and continue. 
					if (customers.get(dateIndex).get(i).getNumPersonsRefunded() - numToUnrefund <= 0) 
					{
						numToUnrefund -= customers.get(dateIndex).get(i).unrefundPersons(); 
					}
					// else refund some (or 0) customers and break
					else 
					{
						customers.get(dateIndex).get(i).unrefundPersons(numToUnrefund); 
						break unrefundLoop; 
					}
				}
				
				customers.get(dateIndex).add(cstmr); 
				// TODO: customers were unrefunded window 
				System.out.println("no refund"); 	// test
				return 0; // none refunded from this customer, only other customers unrefunded
			}
			// else (some customers will have to be refunded because a bus won't be filled to MIN_CAPACITY
			else 
			{
				numToRefund = (numPax + cstmr.getNumPersons()) % MAX_CAPACITY; 
				cstmr.refundPersons(numToRefund); 
				
				customers.get(dateIndex).add(cstmr); 
				displayRefundDialog(numToRefund);
				System.out.println("number refunded (< min capacity): " + numToRefund); 	// test
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
				if (cstmr.getNumPersons() % MAX_CAPACITY >= MIN_CAPACITY 
						|| cstmr.getNumPersons() % MAX_CAPACITY == 0) 
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
					return -1 * numToRefund; 			// number refunded
				}
				
				System.out.println("no refund"); 	// test
				return 0; 		// no error
			}
			// TODO: ISSUES MAY BE HERE: 
			else 
			{
				numToRefund = cstmr.getNumPersons(); 
				
				cstmr.refundPersons(numToRefund);
				displayRefundDialog(numToRefund);
				return -1 * numToRefund; 				// number refunded
			}
		}
	}
	
	// TODO: label method
	// TODO: modifications potential maybe
	// TODO: make comment boxes out of some comments etc. 
	public static void unscheduleTrip (Customer cstmr) 
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int cstmrLocation; 
		LocalDate date = cstmr.getDate(); 
		int dateIndex = dates.indexOf(date); 
		int numPax; 
		int numToUnrefund; 
		int numToRefund; 
		
		cstmrLocation = customers.get(dateIndex).indexOf(cstmr); 
		
		BusFinances.updateCustomerProfit(customers.get(dateIndex).get(cstmrLocation)); 
		customers.get(dateIndex).remove(cstmrLocation).refundPersons();	// removes customer from list and refunds the entire group 
		
		// get number of pax left after customer has been removed 
		numPax = getNumPaxOnDate(date); 
		if (numPax > 0) 
		{
			// if no refunds are required now that a customer has been deleted, see if customers can be unrefunded at all
			if (numPax % MAX_CAPACITY >= MIN_CAPACITY || numPax % MAX_CAPACITY == 0) 
			{
				// unrefund if possible (can possibly fill a bus even more) 
				if (numPax % MAX_CAPACITY != 0) 
				{
					numToUnrefund = MIN_CAPACITY - (numPax % MAX_CAPACITY); 
					
					// TODO: put into its own function? (appears before) 
					unrefundLoop: 
					for (int i = 0; i < customers.get(dateIndex).size(); i++)
					{
						if (customers.get(dateIndex).get(i).getNumPersonsRefunded() - numToUnrefund >= 0) 
						{
							numToUnrefund -= customers.get(dateIndex).get(i).unrefundPersons(); 
						}
						// else refund some (or 0) customers and break (no more customers can be refunded) 
						else
						{
							customers.get(dateIndex).get(i).unrefundPersons(numToUnrefund); 
							break unrefundLoop; 
						}
					}
				}
			}
			// else some refunds are required
			else  
			{
				numToRefund = numPax % MAX_CAPACITY; // will be between 1 and MIN_CAPACITY - 1 AT THIS POINT IN EXECUTION
				
				refundLoop: 
				for (int i = 0; i < customers.get(dateIndex).size(); i++) 
				{
					// if there is more persons in customer at index i than there needs to be refunded, 
					// refund some and break (no more refunds required)
					if (customers.get(dateIndex).get(i).getNumPersons() - numToRefund >= 0) 
					{
						numToRefund -= customers.get(dateIndex).get(i).refundPersons(numToRefund); 
						break refundLoop;  
					}
					// else more customers will have to be refunded, refund as many as possible and continue
					else 
					{
						numToRefund -= customers.get(dateIndex).get(i).refundPersons(); 
					}
				}
			}
		}
		
		// remove customers ArrayList for date if it is now empty 
		if (customers.get(dateIndex).size() == 0) 
		{
			customers.remove(dateIndex);
			dates.remove(dateIndex);
		}
	}
	
	//TODO: 
	public static int rescheduleTrip(Customer cstmr) 
	{
		unscheduleTrip(cstmr);
		return scheduleTrip(cstmr); 
	}
	
	// TODO: 
	public static void unscheduleAll() 
	{
		instantiateLists(); 
	}
	
	public static void instantiateLists()
	{
		customers = new ArrayList<ArrayList<Customer>>(); 	 	// CUSTOMERS EACH DATE 
		dates = new ArrayList<LocalDate>(); 
	}
	
	public static int getNumBuses(LocalDate dt) 
	/************************************************************************************************/
	/* PRECONDITION:  CUSTOMERS MAY HAVE BEEN SCHEDULED ON DATE										*/
	/* POSTCONDITION: RETURNS NUMBER OF BUSES WHICH WILL BE NECESSARY FOR THE DAY					*/
	/************************************************************************************************/
	{	
		return (int)Math.ceil(getNumPaxOnDate(dt) / ((double)MAX_CAPACITY)); 
	}
	
	// TODO: label method
	public static int getNumPaxOnDate(LocalDate dt) 
	{
		int numPax = 0; 		// NUMBER OF PASSENGERS ON THE DAY
		
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
			return 0; 			// DATE NOT FOUND IN BOOKED DATES SO NO BOOKINGS ARE ON THE DATE, MEANS NO PAX ON THE DATE
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
				return customers.subList(0, dates.indexOf(priorDate) + 1); 		//.SUBLIST IS EXCLUSIVE REQUIRING THE +1
			}
		}
	}

	// TODO: label method
	public static ArrayList<LocalDate> getDates() 
	{
		return dates;
	}
	
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
