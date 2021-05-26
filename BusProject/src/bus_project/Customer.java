/********************************************/
/* INFORMATION SECTION 						*/
/* Customer.java							*/
/* Darian Siembab 							*/
/* 											*/
/* Customer object for storing 				*/
/* and obtaining details about customers,	*/
/* and sorting customers 					*/
/********************************************/ 

package bus_project;

import java.time.LocalDate;
import java.util.Comparator;

public class Customer 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/ 
	private String name; 			// NAME OF GROUP
	private int numPersons;			// NUMBER OF PEOPLE IN GROUP
	private int numPersonsRefunded;	// number refunded
	private LocalDate date; 		// date of trip
	private int index;				// INDEX CUSTOMER IS/WAS AT IF BEING STORED IN AN ARRAY
	private int id; 				// CUSTOMER ID NUMBER 
	private double totalPrice; 		// total price of all tickets for group 
	
	//TODO: constructor 
	public Customer() 
	{
		date = LocalDate.now(); 
		name = ""; 
		numPersons = 0;
		numPersonsRefunded = 0; 
		index = 0; 
	}
	
	/************************************************************************/
	/* 							METHOD SECTION								*/
	/************************************************************************/
	
	/************************************************************************/
	/* PRECONDITION: AN AMOUNT NEEDS TO BE ADDED TO TOTAL PRICE 			*/
	/*				 OF THIS CUSTOMER  										*/	
	/* POSTCONDITION: ADDS amt TO totalPrice AND RETURNS NEW totalPrice		*/
	/************************************************************************/
	protected double addTotalPrice(double amt) 
	{
		return totalPrice += amt; 
	}
	
	/************************************************************************/
	/* PRECONDITION:  TOTAL PRICE OF THIS CUSTOMER NEEDS TO BE SET 			*/
	/*				  TO A SPECIFIC AMOUNT									*/	
	/* POSTCONDITION: ADDS amt TO AND RETURNS totalPrice					*/
	/************************************************************************/
	protected double setTotalProfit(double amt)
	{
		return totalPrice = amt; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	protected double subtractTotalPrice(double amt) 
	{
		return totalPrice -= amt; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public String getName () 
	{
		return name; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public int getNumPersons () 
	{
		return numPersons; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public LocalDate getDate ()
	{
		return date; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public int getIndex () 
	{
		return index; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public double getTotalPrice() 
	{
		return totalPrice; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public String getTotalPriceFormatted() 
	{
		return Finances.nf.format(totalPrice); 
	}
	
	/************************************************************************/
	/* PRECONDITION:  DATE OF THIS CUSTOMER NEEDS TO BE SET TO A NEW VALUE	*/
	/* POSTCONDITION: date OF THIS CUSTOMER IS SET TO dt					*/
	/************************************************************************/
	public void setDate (LocalDate dt)
	{
		date = dt; 
	}
	
	/************************************************************************/
	/* PRECONDITION:  NAME OF THIS CUSTOMER NEEDS TO BE SET TO A NEW VALUE	*/
	/* POSTCONDITION: name OF THIS CUSTOMER IS SET TO nm					*/
	/************************************************************************/
	public void setName (String nm) 
	{
		name = nm; 
	}
	
	/************************************************************************/
	/* PRECONDITION:  TOTAL PRICE OF THIS CUSTOMER NEEDS TO BE SET TO 		*/
	/*				  A NEW VALUE (REPRESENTS AMOUNT OF PROFIT				*/
	/*				  WHICH THIS CUSTOMER WILL BRING)						*/	
	/* POSTCONDITION: totalPrice OF THIS CUSTOMER IS SET TO ttlPrc			*/
	/************************************************************************/
	protected void setTotalPrice(double ttlPrc) 
	{
		totalPrice = ttlPrc; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public void setIndex (int indx) 
	{
		index = indx; 
	}
	
	/************************************************************************/
	/* PRECONDITION:  NUMBER OF PERSONS OF THIS CUSTOMER NEEDS TO BE SET TO */
	/*				  A NEW VALUE											*/	
	/* POSTCONDITION: numPersons OF THIS CUSTOMER IS SET TO nmPrsns			*/
	/************************************************************************/
	public void setNumPersons(int nmPrsns) 
	{	
		numPersons = nmPrsns; 
	}
	
	/************************************************************************/
	/* PRECONDITION:  id OF THIS CUSTOMER IS NEEDED 						*/
	/* POSTCONDITION: RETURNS id OF THIS CUSTOMER							*/
	/************************************************************************/
	public int getId() 
	{
		return id;
	}

	/************************************************************************/
	/* PRECONDITION:  id OF THIS CUSTOMER NEEDS TO BE SET TO A NEW VALUE	*/
	/* POSTCONDITION: SETS id OF THIS CUSTOMER TO id						*/
	/************************************************************************/
	public void setId(int id) 
	{
		this.id = id;
	}

	/************************************************************************/
	/* PRECONDITION:  SOME PERSONS OF THIS CUSTOMER NEED TO BE REFUNDED;	*/	
	/*				  prsns <= numPersons									*/
	/* POSTCONDITION: RETURNS THE NUMBER OF PERSONS THAT WERE REFUNDED		*/
	/************************************************************************/
	public int refundPersons(int prsns) 
	{
		numPersons -= prsns; 
		numPersonsRefunded += prsns; 
		
		/********************/
		/* METHOD TO SET 
		/*******************/
		BusFinances.setCustomerProfit(this); 
		return prsns; 
	}
	
	/************************************************************************/
	/* PRECONDITION:  ALL PERSONS OF THIS CUSTOMER NEED TO BE REFUNDED;		*/	
	/* POSTCONDITION: RETURNS THE NUMBER OF PERSONS THAT WERE REFUNDED		*/
	/************************************************************************/
	public int refundPersons() 
	{
		return refundPersons(numPersons); 
	}
	
	/************************************************************************/
	/* PRECONDITION:  ALL PERSONS OF THIS CUSTOMER NEED TO BE UNREFUNDED	*/							
	/* POSTCONDITION: RETURNS THE NUMBER OF PERSONS THAT WERE UNREFUNDED	*/
	/************************************************************************/
	public int unrefundPersons() 
	{
		int numUnrefunded = unrefundPersons(numPersonsRefunded);  
		BusFinances.setCustomerProfit(this); 
		return numUnrefunded; 
	}

	/************************************************************************/
	/* PRECONDITION:  SOME PERSONS OF THIS CUSTOMER NEED TO BE UNREFUNDED;	*/	
	/*				  prsns <= numPersonsRefunded							*/
	/* POSTCONDITION: RETURNS THE NUMBER OF PERSONS THAT WERE UNREFUNDED	*/
	/************************************************************************/
	public int unrefundPersons(int prsns) 
	{
		numPersons += prsns; 
		numPersonsRefunded -= prsns; 
		BusFinances.setCustomerProfit(this);
		return (prsns); 
	}
	
	/************************************************************************/
	/* PRECONDITION:  THIS OBJECT'S NUMBER OF PERSONS REFUNDED IS NEEDED	*/
	/* POSTCONDITION: RETURNS NUMBER OF PERSONS REFUNDED					*/
	/************************************************************************/
	public int getNumPersonsRefunded() 
	{
		return numPersonsRefunded; 
	}
	
	/************************************************************************/
	/* PRECONDITION:  THIS CUSTOMER OBJECT NEEDS TO BE PRINTED				*/
	/* POSTCONDITION: RETURNS A STRING REPRESENTATION OF THIS 				*/
	/*				  CUSTOMER OBJECT										*/
	/************************************************************************/
	public String toString() 
	{
		return "Group: " + name + " \n\tID: " + id + " \n\tGroup Size: " + numPersons + " \n\tRefunded Customers: " + numPersonsRefunded + " \n\tDate: " + date.toString(); 
	}
	
	/************************************************************************/
	/* 							SUBCLASS SECTION 							*/
	/************************************************************************/
	
	/****************************************************/
	/* CompareId CLASS, IMPLEMENTS COMPARATOR INTERFACE */
	/* ASSISTS IN SORTING CUSTOMERS BY ID				*/
	/****************************************************/
	public static class CompareId implements Comparator<Customer>
	{
		/********************************************************************/
		/* PRECONDITION:  CUSTOMERS NEED TO BE COMPARED BY ID				*/
		/* POSTCONDITION: RETURNS THE VALUE 0 IF ID A IS NUMERICALLY		*/
		/*			      EQUAL TO ID B; A VALUE LESS THAN 0 IF	ID A 		*/
		/*				  IS NUMERICALLY LESS THAN ID B; 					*/
		/* 				  AND A VALUE GREATER THAN 0 IF 					*/
		/*				  ID A IS NUMERICALLY GREATER THAN ID B				*/ 				
		/********************************************************************/
		public int compare(Customer a, Customer b)
		{
			return Double.compare(a.getId(), b.getId()); 
		}
	}
	
	/********************************************************/
	/* CompareDate CLASS, IMPLEMENTS COMPARATOR INTERFACE 	*/
	/* ASSISTS IN SORTING CUSTOMERS BY DATE					*/
	/********************************************************/
	public static class CompareDate implements Comparator<Customer> 
	{
		/********************************************************************/
		/* PRECONDITION:  CUSTOMERS NEED TO BE COMPARED BY DATE				*/
		/* POSTCONDITION: RETURNS THE VALUE 0 IF DATE A IS 					*/
		/*			      LEXICOGRAPHICALLY EQUAL TO DATE B; A VALUE LESS  	*/
		/*				  THAN 0 IF DATE A IS LEXICOGRAPHICALLY LESS THAN  	*/
		/* 				  DATE B; AND A VALUE GREATER THAN 0 IF 			*/
		/*				  DATE A IS LEXICOGRAPHICALLY GREATER THAN DATE B	*/ 				
		/********************************************************************/
		public int compare(Customer a, Customer b)
		{
			return a.getDate().compareTo(b.getDate()); 			
		}
	}
	
	/********************************************************/
	/* CompareName CLASS, IMPLEMENTS COMPARATOR INTERFACE 	*/
	/* ASSISTS IN SORTING CUSTOMERS BY NAME					*/
	/********************************************************/
	public static class CompareName implements Comparator<Customer> 
	{
		/********************************************************************/
		/* PRECONDITION:  CUSTOMERS NEED TO BE COMPARED BY NAME				*/
		/* POSTCONDITION: RETURNS THE VALUE 0 IF NAME A IS 					*/
		/*			      LEXICOGRAPHICALLY EQUAL TO NAME B; A VALUE LESS 	*/
		/*				  THAN 0 IF NAME A IS LEXICOGRAPHICALLY  LESS THAN  */
		/* 				  NAME B; AND A VALUE GREATER THAN 0 IF 			*/
		/*				  NAME A IS LEXICOGRAPHICALLY  GREATER THAN NAME B	*/ 				
		/********************************************************************/
		public int compare(Customer a, Customer b) 
		{
			return a.getName().compareTo(b.getName()); 			 
		}
	}
	
	/********************************************************/
	/* CompareSize CLASS, IMPLEMENTS COMPARATOR INTERFACE 	*/
	/* ASSISTS IN SORTING CUSTOMERS BY NUMBER OF PERSONS	*/
	/********************************************************/
	public static class CompareSize implements Comparator<Customer> 
	{
		/********************************************************************/
		/* PRECONDITION:  CUSTOMERS NEED TO BE COMPARED BY SIZE, 			*/
		/* 				  Customer.getNumPersons() >= 0						*/
		/* POSTCONDITION: IF A > B WILL BE POSITIVE, SAME NUMBER WILL BE 0, */
		/* 				  A < B WILL RETURN NEGATIVE (number of persons 	*/
		/* 				  always >= 0)										*/ 				
		/********************************************************************/
		public int compare(Customer a, Customer b) 
		{
			return a.getNumPersons() - b.getNumPersons(); 		
		}
	}
	
	/************************************************************/
	/* CompareTotalPrice CLASS, IMPLEMENTS COMPARATOR INTERFACE */
	/* ASSISTS IN SORTING CUSTOMERS BY ID						*/
	/************************************************************/
	public static class CompareTotalPrice implements Comparator<Customer> 
	{
		/********************************************************************/
		/* PRECONDITION:  CUSTOMERS NEED TO BE COMPARED BY PRICE			*/
		/* POSTCONDITION: RETURNS THE VALUE 0 IF PRICE A IS NUMERICALLY		*/
		/*			      EQUAL TO PRICE B; A VALUE LESS THAN 0 IF PRICE A 	*/
		/*				  IS NUMERICALLY LESS THAN PRICE B; 				*/
		/* 				  AND A VALUE GREATER THAN 0 IF 					*/
		/*				  PRICE A IS NUMERICALLY GREATER THAN PRICE B		*/ 				
		/********************************************************************/
		public int compare(Customer a, Customer b) 
		{
			return Double.compare(a.getTotalPrice(), b.getTotalPrice()); 
		}
	}
}


