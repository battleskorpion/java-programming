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
import java.time.LocalDate;								// A DATE WITHOUT A TIME-ZONE IN THE ISO-8601 
														// CALENDAR SYSTEM
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE.
import java.util.List;									// AN ORDERED COLLECTION (ALSO KNOWN AS A
														// SEQUENCE). 
import javax.swing.JOptionPane;							// JOPTIONPANE MAKES IT EASY TO POP UP A 
														// STANDARD DIALOG BOX THAT PROMPTS USERS
														// FOR A VALUE OR INFORMS THEM OF SOMETHING.

public class BusCalculation 
{
	/*********************/
	/* CONSTANTS SECTION */
	/*********************/ 
	public static final int MAX_BUSES = 20; 						// maximum buses available per day
	public static final int MAX_CAPACITY = 20; 						// maximum capacity of a bus
	public static final int MIN_CAPACITY = 10;						// minimum capacity a bus should 
																	// be operating with
	public static final int MAX_PAX = MAX_BUSES * MAX_CAPACITY; 	// maximum passengers per day
	
	/********************/
	/* VARIABLE SECTION */
	/********************/					 
	private static ArrayList<ArrayList<Customer>> customers; 		// ARRAY LIST OF ARRAY LISTS OF
																	// CUSTOMERS (EACH ARRAY LIST OF
																	// CUSTOMERS IS FOR ONE DATE)
	private static ArrayList<LocalDate> dates; 						// ARRAY LIST OF TRIP DATES
			
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	public BusCalculation()
	{
		/****************************************************/
		/* METHOD TO INSTANTIATE CUSTOMERS AND DATES ARRAYS */
		/****************************************************/
		instantiateLists();
	}
	
	/************************************************************************************************/
	/* 										  METHOD SECTION 										*/
	/************************************************************************************************/
	
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
		LocalDate date = cstmr.getDate(); 		// TRIP DATE 
		int dateIndex; 							// INDEX OF CURRENT DATE IN DATES ARRAY
		int numPax; 							// NUMBER OF OTHER INDIVIDUAL PASSENGERS 
												// (BASED ON COMBINED GROUP SIZE FROM ALL
												// OTHER GROUPS THAT DAY) 
		int numPaxRefunded; 					// NUMBER OF PERSONS REFUNDED
		int numToRefund; 						// NUMBER OF PERSONS TO REFUND
		int numToUnrefund; 						// NUMBER OF PERSONS TO UNREFUND 
		
		
		cstmr.unrefundPersons(); 				// TAKE PREVIOUSLY REFUNDED PERSONS INTO ACCOUNT 
		
		/********************************************/
		/* IF ANOTHER TRIP ALREADY EXISTS ON DATE   */
		/* PERFORM SPECIAL CALCULATIONS CONSIDERING */
		/* THE OTHER TRIP(S) AS WELL			    */
		/********************************************/
		if (dates.contains(date)) 
		{
			/******************************/
			/* GET INDEX OF DATE IN DATES */
			/******************************/
			dateIndex = dates.indexOf(date); 		
						
			/******************************************************/
			/* CALCULATE NUMBER OF PASSENGERS AND NUMBER REFUNDED */
			/******************************************************/
			numPax = getNumPaxOnDate(date);
			numPaxRefunded = getNumPaxRefundedOnDate(date); 
						
			/**************************************************************************************/
			/* IF NUMBER OF PERSONS ON DATE AND NUMBER OF PERSONS OF CUSTOMER TO SCHEDULE ON DATE */
			/* EXCEED MAXIMUM NUMBER OF PASSENGERS ON DATE, REFUND SOME PERSONS OF CUSTOMER 	  */
			/**************************************************************************************/
			if (numPax + cstmr.getNumPersons() > MAX_PAX) 
			{
				numToRefund = numPax + cstmr.getNumPersons() - MAX_PAX;
				cstmr.refundPersons(numToRefund);
				
				customers.get(dateIndex).add(cstmr); 
				
				/**************************/
				/* RETURN NUMBER REFUNDED */
				/**************************/
				return -1 * numToRefund;  	
			}

			/**************************************************************************************/
			/* IF PREXISTING CUSTOMERS PASSENGERS + REFUND SOME PASSENGERS + NEW PASSENGERS FILL  */
			/* A BUS AT LEAST TO MINIMUM CAPACITY, OR FILL A BUS ENTIRELY, UNREFUND SOME 	  	  */
			/* CUSTOMERS IF POSSIBLE															  */
			/**************************************************************************************/
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
				return 0; 	// none refunded from this customer, only other customers unrefunded
			}

			/**************************************************************************************/
			/* ELSE (SOME CUSTOMERS WILL HAVE TO BE REFUNDED BECAUSE A BUS WON'T BE FILLED TO     */
			/* MIN_CAPACITY)  	  															  	  */
			/**************************************************************************************/
			else 
			{
				numToRefund = (numPax + cstmr.getNumPersons()) % MAX_CAPACITY; 
				cstmr.refundPersons(numToRefund); 
				
				customers.get(dateIndex).add(cstmr); 
				
				/**************************/
				/* RETURN NUMBER REFUNDED */
				/**************************/
				return -1 * numToRefund; 		
			}
		}
		
		/********************************************/
		/* ELSE NO TRIP EXISTS ON DATE   		    */
		/* PERFORM CALCULATIONS ONLY 	 			*/
		/* CONSIDERING POTENTIAL NECESSARY REFUNDS	*/
		/********************************************/
		else 
		{	
			/****************************************************/
			/* IF CUSTOMER'S NUMBER OF PERSONS MEETS OR EXCEEDS */
			/* MINIMUM CAPACITY									*/
			/****************************************************/
			if (cstmr.getNumPersons() >= MIN_CAPACITY) 
			{
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
				customers.add(dateIndex, new ArrayList<Customer>());	// create new ArrayList 
																		// aligning with new date
				
				// if prexisting customers + new customer fill a bus at least to minimum capacity, 
				// or fill a bus entirely 
				if (cstmr.getNumPersons() % MAX_CAPACITY >= MIN_CAPACITY 
						|| cstmr.getNumPersons() % MAX_CAPACITY == 0) 
				{
					customers.get(dateIndex).add(cstmr);		// add customer to new ArrayList
				}
				// else (some customers will have to be refunded because a bus won't be filled 
				// to MIN_CAPACITY
				else 
				{
					numToRefund = cstmr.getNumPersons() % MAX_CAPACITY; 
					cstmr.refundPersons(numToRefund); 
					
					customers.get(dateIndex).add(cstmr); 
					
					/**************************/
					/* RETURN NUMBER REFUNDED */
					/**************************/
					return -1 * numToRefund; 			
				}
				
				/*********************************/
				/* RETURN NUMBER REFUNDED (NONE) */
				/*********************************/
				return 0; 		
			}
			else 
			{
				numToRefund = cstmr.getNumPersons(); 
				
				cstmr.refundPersons(numToRefund);
				
				/**************************/
				/* RETURN NUMBER REFUNDED */
				/**************************/
				return -1 * numToRefund; 				
			}
		}
	}
	
	public static void unscheduleTrip (Customer cstmr) 
	/************************************************************************************************/
	/* PRECONDITION:  A CUSTOMER NEEDS TO BE UNSCHEDULED											*/
	/* POSTCONDITION: UNSCHEDULES THE CUSTOMER'S TRIP AND PERFORMS NECESSARY ACTIONS				*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int cstmrLocation; 						// INDEX OF CUSTOMER IN CUSTOMERS 2D ARRAYLIST 
		LocalDate date = cstmr.getDate(); 		// TRIP DATE 
		int dateIndex = dates.indexOf(date); 	// INDEX OF DATE IN DATES LIST (ALSO INDEX OF 
												// CUSTOMERS LIST FOR THAT DATE IN CUSTOMERS 
												// 2D ARRAYLIST)
		int numPax; 							// NUMBER OF PASSENGERS ON DATE
		int numToRefund; 						// NUMBER OF PERSONS TO REFUND
		int numToUnrefund; 						// NUMBER OF PERSONS TO UNREFUND
		
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
	
	public static int rescheduleTrip(Customer cstmr) 
	/************************************************************************************************/
	/* PRECONDITION:  A CUSTOMER NEEDS TO BE RESCHEDULED											*/
	/* POSTCONDITION: UNSCHEDULES THE CUSTOMER'S TRIP, THEN SCHEDULES A NEW TRIP					*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/ 
		int numToRefund; 
		
		unscheduleTrip(cstmr);
		numToRefund = scheduleTrip(cstmr); 
		displayRefundDialog(numToRefund);
		
		return numToRefund; 
	}
	

	public static void unscheduleAll() 
	/************************************************************************************************/
	/* PRECONDITION:  ALL CUSTOMERS NEED TO BE UNSCHEDULED											*/
	/* POSTCONDITION: UNSCHEDULES ALL CUSTOMER TRIPS (RESETS LISTS) 								*/
	/************************************************************************************************/
	{
		/****************************************************/
		/* METHOD TO INSTANTIATE CUSTOMERS AND DATES ARRAYS */
		/****************************************************/
		instantiateLists(); 
	}
	
	public static void instantiateLists()
	/************************************************************************************************/
	/* PRECONDITION:  CLASS LISTS NEED TO BE INSTANTIATED											*/
	/* POSTCONDITION: INSTANTIATES/CREATES NEW LISTS 												*/
	/************************************************************************************************/
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
	
	public static int getNumPaxOnDate(LocalDate dt) 
	/************************************************************************************************/
	/* PRECONDITION:  NUNBER OF PASSENGERS SCHEDULED ON A PARTICULAR DATE IS NEEDED					*/
	/* POSTCONDITION: RETURNS NUMBER OF PASSENGERS FOR A PARTICULAR DATE (DOES NOT INCLUDE REFUNDS)	*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/ 
		int numPax = 0; 		// NUMBER OF PASSENGERS ON THE DAY
		
		/*****************************************************************/
		/* IF DATES CONTAINS DATE CALCULATE NUMBER OF PASSENGERS ON DATE */
		/*****************************************************************/ 
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
			return 0; 			// DATE NOT FOUND IN BOOKED DATES SO NO BOOKINGS ARE ON THE DATE, MEANS NO PAX ON THE DATE
		}
	}
	
	public static int getNumPaxRefundedOnDate(LocalDate dt) 
	/************************************************************************************************/
	/* PRECONDITION:  NUNBER OF PASSENGERS WHICH WERE REFUNDED ON A PARTICULAR DATE IS NEEDED		*/			
	/* POSTCONDITION: RETURNS NUMBER OF PASSENGERS REFUNDED ON A PARTICULAR DATE 					*/
	/*				  (NOT INCLUDING UNSCHEDULED TRIPS/DELETED CUSTOMERS)							*/
	/************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int numPaxRefunded = 0;
		
		/***********************************************************************/
		/* IF DATES CONTAINS DATE CALCULATE NUMBER OF PERSONS REFUNDED ON DATE */
		/***********************************************************************/ 
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
	/************************************************************************************************/
	/* PRECONDITION:  NUNBER OF CUSTOMERS SCHEDULED ON A PARTICULAR DATE IS NEEDED					*/
	/* POSTCONDITION: RETURNS NUMBER OF CUSTOMERS FOR A PARTICULAR DATE 							*/
	/************************************************************************************************/
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
	/************************************************************************************************/
	/* PRECONDITION:  NUNBER OF CUSTOMERS SCHEDULED ON ALL DATES PRIOR AND INCLUDING THE 			*/
	/* 				  SPECIFIED DATE IS NEEDED														*/
	/* POSTCONDITION: RETURNS NUMBER OF CUSTOMERS SCHEDULED UP TO AND INCLUDING THE SPECIFIED DATE	*/
	/************************************************************************************************/
	{
		/**************************************************************/
		/* IF DATES CONTAINS DATE RETURN SUBLIST OF CUSTOMERS ON DATE */
		/**************************************************************/
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

	public static ArrayList<LocalDate> getDates() 
	/************************************************************************************************/
	/* PRECONDITION:  THE LIST OF TRIP DATES IS NEEDED												*/		
	/* POSTCONDITION: RETURNS THE LIST OF SCHEDULED TRIP DATES										*/
	/************************************************************************************************/
	{
		return dates;
	}
	
	public static LocalDate getFirstDateAfterDate(LocalDate dt) 
	/************************************************************************************************/
	/* PRECONDITION:  THE FIRST SCHEDULED TRIP DATE AFTER THE SPECIFIED DATE IS NEEDED 				*/
	/* POSTCONDITION: IF THERE IS A TRIP DATE AFTER THE SPECIFIED DATE, RETURNS THE DATE, OTHERWISE */
	/*			      RETURNS NULL																	*/ 
	/************************************************************************************************/
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
	/************************************************************************************************/
	/* PRECONDITION:  NUNBER OF PERSONS REFUNDED NEEDS TO BE DISPLAYED								*/
	/* POSTCONDITION: DISPLAYS A MESSAGE DIALOG INCLUDING THE NUMBER OF PERSONS REFUNDED			*/
	/************************************************************************************************/
	{
		if (numRefunded > 0) 
		{
			JOptionPane.showMessageDialog(null, numRefunded + "customers were refunded, due to not "
					+ "meeting minimum capacity");
		}
	}
}
