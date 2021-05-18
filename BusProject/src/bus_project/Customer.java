package bus_project;

import java.time.LocalDate;
import java.util.Comparator;

public class Customer 
{
	//TODO: label section
	private String name; 			// NAME OF GROUP
	private int numPersons;			// NUMBER OF PEOPLE IN GROUP
	private int numPersonsRefunded;	// number refunded
	private LocalDate date; 		// date of trip
	private int index;				// INDEX CUSTOMER IS/WAS AT IF BEING STORED IN AN ARRAY
	private double totalPrice; 		// total price of all tickets for group 
	
	//TODO: label section
	
	//TODO: label method
	public Customer() 
	{
		date = LocalDate.now(); 
		name = ""; 
		numPersons = 0;
		numPersonsRefunded = 0; 
		index = 0; 
	}
	
	/****************************************/
	/* PRECONDITION: 
	/* POSTCONDITION: 
	/****************************************/ 
	protected double addTotalPrice(double amt) 
	{
		return totalPrice += amt; 
	}
	
	//TODO: label method 
	protected double subtractTotalPrice(double amt) 
	{
		return totalPrice -= amt; 
	}
	
	//TODO: label method
	public String getName () 
	{
		return name; 
	}
	
	//TODO: label method
	public int getNumPersons () 
	{
		return numPersons; 
	}
	
	//TODO: label method
	public LocalDate getDate ()
	{
		return date; 
	}
	
	//TODO: label method
	public int getIndex () 
	{
		return index; 
	}
	
	//TODO: label method
	public double getTotalPrice() 
	{
		return totalPrice; 
	}
	
	public String getTotalPriceFormatted() 
	{
		return Finances.nf.format(totalPrice); 
	}
	
	//TODO: label method
	public void setDate (LocalDate dt)
	{
		date = dt; 
	}
	
	//TODO: label method
	public void setName (String nm) 
	{
		name = nm; 
	}
	
	//TODO: label method
	protected void setTotalPrice(double ttlPrc) 
	{
		totalPrice = ttlPrc; 
	}
	
	//TODO: label method
	public void setIndex (int indx) 
	{
		index = indx; 
	}
	
	public void setNumPersons(int sz) 
	{
		numPersons = sz; 
	}
	
	public void refundPersons(int prsns) 
	{
		numPersons -= prsns; 
		numPersonsRefunded += prsns; 
		BusFinances.setCustomerProfit(this); 
	}
	
	public int unrefundPersons() 
	{
		int numUnrefunded = unrefundPersons(numPersonsRefunded);  
		BusFinances.setCustomerProfit(this); 
		return numUnrefunded; 
	}
	
	// precondition: prsns <= numPersonsRefunded
	public int unrefundPersons(int prsns) 
	{
		numPersons += prsns; 
		numPersonsRefunded -= prsns; 
		BusFinances.setCustomerProfit(this);
		return (prsns); 
	}
	
	public int getNumPersonsRefunded() 
	{
		return numPersonsRefunded; 
	}
	
	//TODO: label method
	//TODO: modifications perhaps (does not print well in table (etc etc)) 
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
		//TODO: label method
		public int compare(Customer a, Customer b)
		{
			return a.getDate().compareTo(b.getDate()); 			// IF A > B WILL BE POSITIVE, SAME NUMBER WILL BE 0, A < B WILL RETURN NEGATIVE 
		}
	}
	
	//TODO: label subclass
	public static class CompareName implements Comparator<Customer> 
	{
		//TODO: label method
		public int compare(Customer a, Customer b) 
		{
			return a.getName().compareTo(b.getName()); 			// IF A > B WILL BE POSITIVE, SAME NUMBER WILL BE 0, A < B WILL RETURN NEGATIVE 
		}
	}
	
	//TODO: label subclass
	public static class CompareSize implements Comparator<Customer> 
	{
		//TODO: label method
		public int compare(Customer a, Customer b) 
		{
			return a.getNumPersons() - b.getNumPersons(); 		// IF A > B WILL BE POSITIVE, SAME NUMBER WILL BE 0, A < B WILL RETURN NEGATIVE (number of persons always >= 0 so this logic is fine) 
		}
	}
	
	//TODO: label subclass
	public static class CompareTotalPrice implements Comparator<Customer> 
	{
		//TODO: label method
		public int compare(Customer a, Customer b) 
		{
			return Double.compare(a.getTotalPrice(), b.getTotalPrice()); 
		}
	}
	
	
}


