/********************************************/
/* INFORMATION SECTION 						*/
/* Customer.java							*/
/* Darian Siembab 							*/
/* 											*/
/* CUSTOMER OBJECT FOR STORING 				*/
/* AND OBTAINING DETAILS ABOUT CUSTOMERS,	*/
/* AND SORTING CUSTOMERS 					*/
/********************************************/ 

package bus_project;

import java.time.LocalDate;
import java.util.Comparator;

public class Customer 
{
	/*********************/
	/* CONSTANTS SECTION */
	/*********************/ 
	public static final int MAX_NAME_LENGTH = 20; 		// MAX CUSTOMER NAME LENGTH
	
	/********************/
	/* VARIABLE SECTION */
	/********************/ 
	private String name; 			                    // NAME OF GROUP
	private int numPersons;			                    // NUMBER OF PEOPLE IN GROUP
	private int numPersonsRefunded;	                    // number refunded
	private LocalDate date; 		                    // date of trip
	private int index;				                    // INDEX CUSTOMER IS/WAS AT IF BEING STORED IN AN ARRAY
	private int id; 				                    // CUSTOMER ID NUMBER
	private double totalPrice; 		                    // total price of all tickets for group 
	
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	public Customer() 
	{
		date = LocalDate.now(); 
		name = ""; 
		numPersons = 0;
		numPersonsRefunded = 0; 
		index = 0; 
		id = 0; 
	}
	
	public Customer(Customer cstmr)
	{
		date = cstmr.getDate();
		name = cstmr.getName();
		numPersons = cstmr.getNumPersons(); 
		numPersonsRefunded = cstmr.getNumPersonsRefunded();
		index = cstmr.getIndex(); 
		id = cstmr.getId(); 
	}
	
	/************************************************************************/
	/* 							METHOD SECTION								*/
	/************************************************************************/
	
	protected double addTotalPrice(double amt) 
	/************************************************************************/
	/* PRECONDITION: AN AMOUNT NEEDS TO BE ADDED TO TOTAL PRICE 			*/
	/*				 OF THIS CUSTOMER  										*/	
	/* POSTCONDITION: ADDS amt TO totalPrice AND RETURNS NEW totalPrice		*/
	/************************************************************************/
	{
		return totalPrice += amt; 
	}
	
	protected double setTotalProfit(double amt)
	/************************************************************************/
	/* PRECONDITION:  TOTAL PRICE OF THIS CUSTOMER NEEDS TO BE SET 			*/
	/*				  TO A SPECIFIC AMOUNT									*/	
	/* POSTCONDITION: ADDS amt TO AND RETURNS totalPrice					*/
	/************************************************************************/
	{
		return totalPrice = amt; 
	}
	
	protected double subtractTotalPrice(double amt) 
	/************************************************************************/
	/* PRECONDITION:  AN AMOUNT NEEDS TO BE SUBTRACTED FROM THE   			*/
	/* 				  TOTAL PRICE OF THIS CUSTOMER'S TRIP 		  			*/
	/* POSTCONDITION: RETURNS THE NEW VALUE OF TOTAL PRICE					*/
	/************************************************************************/
	{
		return totalPrice -= amt; 
	}
	
	public String getName() 
	/************************************************************************/
	/* PRECONDITION:  NAME OF CUSTOMER IS NEEDED							*/
	/* POSTCONDITION: RETURNS THE NAME OF THE CUSTOMER						*/
	/************************************************************************/
	{
		return name; 
	}
	
	public int getNumPersons() 
	/************************************************************************/
	/* PRECONDITION:  THE NUMBER OF PERSONS WITH THIS CUSTOMER IS NEEDED	*/
	/* POSTCONDITION: RETURNS THE NUMBER OF PERSONS OF THIS CUSTOMER		*/
	/************************************************************************/	
	{
		return numPersons; 
	}
	
	public LocalDate getDate()
	/************************************************************************/	
	/* PRECONDITION:  
	/* POSTCONDITION: 
	/************************************************************************/	
	{
		return date; 
	}
	
	public int getIndex() 
	/************************************************************************/	
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/************************************************************************/	
	{
		return index; 
	}
	
	public double getTotalPrice() 
	/************************************************************************/	
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/************************************************************************/	
	{
		return totalPrice; 
	}
	
	public String getTotalPriceFormatted() 
	/************************************************************************/
	/* PRECONDITION:  FORMATTED PROFIT OF CUSTOMER IS NEEDED 				*/
	/* POSTCONDITION: RETURNS THE FORMATTED PROFIT OF THE CUSTOMER TRIP 	*/
	/*				  AS A STRING OF CUSTOMERS								*/
	/************************************************************************/
	{
		return Finances.nf.format(totalPrice); 
	}

	public void setDate(LocalDate dt)
	/************************************************************************/
	/* PRECONDITION:  DATE OF THIS CUSTOMER NEEDS TO BE SET TO A NEW VALUE	*/
	/* POSTCONDITION: date OF THIS CUSTOMER IS SET TO dt					*/
	/************************************************************************/
	{
		date = dt; 
	}
	
	public void setName(String nm) 
	/************************************************************************/
	/* PRECONDITION:  NAME OF THIS CUSTOMER NEEDS TO BE SET TO A NEW VALUE	*/
	/* POSTCONDITION: name OF THIS CUSTOMER IS SET TO nm					*/
	/************************************************************************/
	{
		name = nm; 
	}
	
	protected void setTotalPrice(double ttlPrc) 
	/************************************************************************/
	/* PRECONDITION:  TOTAL PRICE OF THIS CUSTOMER NEEDS TO BE SET TO 		*/
	/*				  A NEW VALUE (REPRESENTS AMOUNT OF PROFIT				*/
	/*				  WHICH THIS CUSTOMER WILL BRING)						*/	
	/* POSTCONDITION: totalPrice OF THIS CUSTOMER IS SET TO ttlPrc			*/
	/************************************************************************/
	{
		totalPrice = ttlPrc; 
	}
	
	public void setIndex(int indx) 
	/************************************************************************/
	/* PRECONDITION:  THE INDEX LOCAL VARIABLE OF THIS CUSTOMER NEEDS TO BE */
	/*				  UPDATED												*/
	/* POSTCONDITION: UPDATES INDEX TO A NEW VALUE							*/
	/************************************************************************/
	{
		index = indx; 
	}
	
	public void setNumPersons(int nmPrsns) 
	/************************************************************************/
	/* PRECONDITION:  NUMBER OF PERSONS OF THIS CUSTOMER NEEDS TO BE SET TO */
	/*				  A NEW VALUE											*/	
	/* POSTCONDITION: numPersons OF THIS CUSTOMER IS SET TO nmPrsns			*/
	/************************************************************************/
	{	
		numPersons = nmPrsns; 
	}
	
	public int getId() 
	/************************************************************************/
	/* PRECONDITION:  id OF THIS CUSTOMER IS NEEDED 						*/
	/* POSTCONDITION: RETURNS id OF THIS CUSTOMER							*/
	/************************************************************************/
	{
		return id;
	}

	public void setId(int id)
	/************************************************************************/
	/* PRECONDITION:  id OF THIS CUSTOMER NEEDS TO BE SET TO A NEW VALUE	*/
	/* POSTCONDITION: SETS id OF THIS CUSTOMER TO id						*/
	/************************************************************************/
	{
		this.id = id;
	}

	public int refundPersons(int prsns) 
	/************************************************************************/
	/* PRECONDITION:  SOME PERSONS OF THIS CUSTOMER NEED TO BE REFUNDED;	*/	
	/*				  prsns <= numPersons									*/
	/* POSTCONDITION: RETURNS THE NUMBER OF PERSONS THAT WERE REFUNDED		*/
	/************************************************************************/
	{
		numPersons -= prsns; 
		numPersonsRefunded += prsns; 
		
		/********************/
		/* METHOD TO SET 
		/*******************/
		BusFinances.updateCustomerProfit(this); 
		return prsns; 
	}
	
	public int refundPersons() 
	/************************************************************************/
	/* PRECONDITION:  ALL PERSONS OF THIS CUSTOMER NEED TO BE REFUNDED;		*/	
	/* POSTCONDITION: RETURNS THE NUMBER OF PERSONS THAT WERE REFUNDED		*/
	/************************************************************************/
	{
		return refundPersons(numPersons); 
	}
	
	public int unrefundPersons() 
	/************************************************************************/
	/* PRECONDITION:  ALL PERSONS OF THIS CUSTOMER NEED TO BE UNREFUNDED	*/							
	/* POSTCONDITION: RETURNS THE NUMBER OF PERSONS THAT WERE UNREFUNDED	*/
	/************************************************************************/
	{
		int numUnrefunded = unrefundPersons(numPersonsRefunded);  
		BusFinances.updateCustomerProfit(this); 
		return numUnrefunded; 
	}
	
	public int unrefundPersons(int prsns) 
	/************************************************************************/
	/* PRECONDITION:  SOME PERSONS OF THIS CUSTOMER NEED TO BE UNREFUNDED;	*/	
	/*				  prsns <= numPersonsRefunded							*/
	/* POSTCONDITION: RETURNS THE NUMBER OF PERSONS THAT WERE UNREFUNDED	*/
	/************************************************************************/
	{
		numPersons += prsns; 
		numPersonsRefunded -= prsns; 
		BusFinances.updateCustomerProfit(this);
		return (prsns); 
	}
	
	public int getNumPersonsRefunded() 
	/************************************************************************/
	/* PRECONDITION:  THIS OBJECT'S NUMBER OF PERSONS REFUNDED IS NEEDED	*/
	/* POSTCONDITION: RETURNS NUMBER OF PERSONS REFUNDED					*/
	/************************************************************************/
	{
		return numPersonsRefunded; 
	}
	
	public String toString() 
	/************************************************************************/
	/* PRECONDITION:  THIS CUSTOMER OBJECT NEEDS TO BE PRINTED				*/
	/* POSTCONDITION: RETURNS A STRING REPRESENTATION OF THIS 				*/
	/*				  CUSTOMER OBJECT										*/
	/************************************************************************/
	{
		return id + "\n" + name + "\n" + date.toString() 
				+ "\n" + numPersons + "\n" + numPersonsRefunded; 
	}
	
	/************************************************************************/
	/* 							SUBCLASS SECTION 							*/
	/************************************************************************/
	
	public static class CompareId implements Comparator<Customer>
	/****************************************************/
	/* CompareId CLASS, IMPLEMENTS COMPARATOR INTERFACE */
	/* ASSISTS IN SORTING CUSTOMERS BY ID				*/
	/****************************************************/
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
	
	public static class CompareDate implements Comparator<Customer> 
	/********************************************************/
	/* CompareDate CLASS, IMPLEMENTS COMPARATOR INTERFACE 	*/
	/* ASSISTS IN SORTING CUSTOMERS BY DATE					*/
	/********************************************************/
	{
		
		public int compare(Customer a, Customer b)
		/********************************************************************/
		/* PRECONDITION:  CUSTOMERS NEED TO BE COMPARED BY DATE				*/
		/* POSTCONDITION: RETURNS THE VALUE 0 IF DATE A IS 					*/
		/*			      LEXICOGRAPHICALLY EQUAL TO DATE B; A VALUE LESS  	*/
		/*				  THAN 0 IF DATE A IS LEXICOGRAPHICALLY LESS THAN  	*/
		/* 				  DATE B; AND A VALUE GREATER THAN 0 IF 			*/
		/*				  DATE A IS LEXICOGRAPHICALLY GREATER THAN DATE B	*/ 				
		/********************************************************************/
		{
			return a.getDate().compareTo(b.getDate()); 			
		}
	}
	
	public static class CompareName implements Comparator<Customer> 
	/********************************************************/
	/* CompareName CLASS, IMPLEMENTS COMPARATOR INTERFACE 	*/
	/* ASSISTS IN SORTING CUSTOMERS BY NAME					*/
	/********************************************************/
	{
		public int compare(Customer a, Customer b) 
		/********************************************************************/
		/* PRECONDITION:  CUSTOMERS NEED TO BE COMPARED BY NAME				*/
		/* POSTCONDITION: RETURNS THE VALUE 0 IF NAME A IS 					*/
		/*			      LEXICOGRAPHICALLY EQUAL TO NAME B; A VALUE LESS 	*/
		/*				  THAN 0 IF NAME A IS LEXICOGRAPHICALLY  LESS THAN  */
		/* 				  NAME B; AND A VALUE GREATER THAN 0 IF 			*/
		/*				  NAME A IS LEXICOGRAPHICALLY  GREATER THAN NAME B	*/ 				
		/********************************************************************/
		{
			return a.getName().compareTo(b.getName()); 			 
		}
	}
	
	public static class CompareSize implements Comparator<Customer> 
	/********************************************************/
	/* CompareSize CLASS, IMPLEMENTS COMPARATOR INTERFACE 	*/
	/* ASSISTS IN SORTING CUSTOMERS BY NUMBER OF PERSONS	*/
	/********************************************************/
	{
		public int compare(Customer a, Customer b) 
		/********************************************************************/
		/* PRECONDITION:  CUSTOMERS NEED TO BE COMPARED BY SIZE, 			*/
		/* 				  Customer.getNumPersons() >= 0						*/
		/* POSTCONDITION: IF A > B WILL BE POSITIVE, SAME NUMBER WILL BE 0, */
		/* 				  A < B WILL RETURN NEGATIVE (number of persons 	*/
		/* 				  always >= 0)										*/ 				
		/********************************************************************/
		{
			return a.getNumPersons() - b.getNumPersons(); 		
		}
	}
	
	public static class CompareTotalPrice implements Comparator<Customer> 
	/************************************************************/
	/* CompareTotalPrice CLASS, IMPLEMENTS COMPARATOR INTERFACE */
	/* ASSISTS IN SORTING CUSTOMERS BY ID						*/
	/************************************************************/
	{
		
		public int compare(Customer a, Customer b) 
		/********************************************************************/
		/* PRECONDITION:  CUSTOMERS NEED TO BE COMPARED BY PRICE			*/
		/* POSTCONDITION: RETURNS THE VALUE 0 IF PRICE A IS NUMERICALLY		*/
		/*			      EQUAL TO PRICE B; A VALUE LESS THAN 0 IF PRICE A 	*/
		/*				  IS NUMERICALLY LESS THAN PRICE B; 				*/
		/* 				  AND A VALUE GREATER THAN 0 IF 					*/
		/*				  PRICE A IS NUMERICALLY GREATER THAN PRICE B		*/ 				
		/********************************************************************/
		{
			return Double.compare(a.getTotalPrice(), b.getTotalPrice()); 
		}
	}
}


