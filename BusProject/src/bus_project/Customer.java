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
	private int id;				// INDEX CUSTOMER IS/WAS AT IF BEING STORED IN AN ARRAY
	private double totalPrice; 		// total price of all tickets for group 
	
	//TODO: constructor 
	public Customer() 
	{
		date = LocalDate.now(); 
		name = ""; 
		numPersons = 0;
		numPersonsRefunded = 0; 
		id = 0; 
	}
	
	//TODO: label section
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	protected double addTotalPrice(double amt) 
	{
		return totalPrice += amt; 
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
	public int getId () 
	{
		return id; 
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
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public void setDate (LocalDate dt)
	{
		date = dt; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public void setName (String nm) 
	{
		name = nm; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	protected void setTotalPrice(double ttlPrc) 
	{
		totalPrice = ttlPrc; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public void setId (int indx) 
	{
		id = indx; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public void setNumPersons(int sz) 
	{
		numPersons = sz; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public int refundPersons(int prsns) 
	{
		numPersons -= prsns; 
		numPersonsRefunded += prsns; 
		BusFinances.setCustomerProfit(this); 
		return prsns; 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public int refundPersons() 
	{
		return refundPersons(numPersons); 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public int unrefundPersons() 
	{
		int numUnrefunded = unrefundPersons(numPersonsRefunded);  
		BusFinances.setCustomerProfit(this); 
		return numUnrefunded; 
	}

	/***************************************************************/
	/* PRECONDITION: // precondition: prsns <= numPersonsRefunded
	/* POSTCONDITION: 
	/***************************************************************/
	public int unrefundPersons(int prsns) 
	{
		numPersons += prsns; 
		numPersonsRefunded -= prsns; 
		BusFinances.setCustomerProfit(this);
		return (prsns); 
	}
	
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public int getNumPersonsRefunded() 
	{
		return numPersonsRefunded; 
	}
	
	//TODO: modifications perhaps (does not print well in table (etc etc)) 
	/***************************************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/***************************************************************/
	public String toString() 
	{
		return "Group: " + name + "\n\tGroup Size: " + numPersons + "\n\tRefunded Customers" + numPersonsRefunded + "\n\tDate: " + date.toString(); 
	}
	
	/********************/
	/* SUBCLASS SECTION */
	/********************/
	//TODO: label subclass
	public static class CompareDate implements Comparator<Customer> 
	{
		/***************************************************************/
		/* PRECONDITION: 
		/* POSTCONDITION: 
		/***************************************************************/
		public int compare(Customer a, Customer b)
		{
			return a.getDate().compareTo(b.getDate()); 			// IF A > B WILL BE POSITIVE, SAME NUMBER WILL BE 0, A < B WILL RETURN NEGATIVE 
		}
	}
	
	//TODO: label subclass
	public static class CompareName implements Comparator<Customer> 
	{
		/***************************************************************/
		/* PRECONDITION: 
		/* POSTCONDITION: 
		/***************************************************************/
		public int compare(Customer a, Customer b) 
		{
			return a.getName().compareTo(b.getName()); 			// IF A > B WILL BE POSITIVE, SAME NUMBER WILL BE 0, A < B WILL RETURN NEGATIVE 
		}
	}
	
	//TODO: label subclass
	public static class CompareSize implements Comparator<Customer> 
	{
		/***************************************************************/
		/* PRECONDITION: 
		/* POSTCONDITION: 
		/***************************************************************/
		public int compare(Customer a, Customer b) 
		{
			return a.getNumPersons() - b.getNumPersons(); 		// IF A > B WILL BE POSITIVE, SAME NUMBER WILL BE 0, A < B WILL RETURN NEGATIVE (number of persons always >= 0 so this logic is fine) 
		}
	}
	
	//TODO: label subclass
	public static class CompareTotalPrice implements Comparator<Customer> 
	{
		/***************************************************************/
		/* PRECONDITION: 
		/* POSTCONDITION: 
		/***************************************************************/
		public int compare(Customer a, Customer b) 
		{
			return Double.compare(a.getTotalPrice(), b.getTotalPrice()); 
		}
	}
}


