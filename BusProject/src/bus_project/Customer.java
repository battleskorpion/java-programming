package bus_project;

import java.time.LocalDate;
import java.util.Comparator;

public class Customer 
{
	//TODO: label section
	private String name; 			// NAME OF GROUP
	private int numPersons;			// NUMBER OF PEOPLE IN GROUP
	private LocalDate date; 		// date of trip
	private int index;				// INDEX CUSTOMER IS/WAS AT IF BEING STORED IN AN ARRAY
	private int totalPrice; 		// total price of all tickets for group 
	
	//TODO: label section
	
	//TODO: label method
	public Customer() 
	{
		date = LocalDate.now(); 
		name = ""; 
		numPersons = 0;
		index = 0; 
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
	public int getIndex () {
		return index; 
	}
	
	//TODO: label method
	public int getTotalPrice() {
		return totalPrice; 
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
	public void setTotalPrice(int ttlPrc) {
		totalPrice = ttlPrc; 
	}
	
	//TODO: label method
	public void setIndex (int indx) {
		index = indx; 
	}
	
	//TODO: label method
	public void setNumPersons(int sz) {
		numPersons = sz; 
	}
	
	//TODO: label method
	public String toString() {
		return "Group: " + name + " \nGroup Size: " + numPersons + " \nDate: " + date.toString(); 
	}
	
	/********************/
	/* SUBCLASS SECTION */
	/********************/
	
	//TODO: label subclass
	public static class CompareDate implements Comparator<Customer> {
		//TODO: label method
		public int compare(Customer a, Customer b) {
			return a.getDate().compareTo(b.getDate()); 			// IF A > B WILL BE POSITIVE, SAME NUMBER WILL BE 0, A < B WILL RETURN NEGATIVE 
		}
	}
	
	//TODO: label subclass
	public static class CompareName implements Comparator<Customer> {
		//TODO: label method
		public int compare(Customer a, Customer b) {
			return a.getName().compareTo(b.getName()); 			// IF A > B WILL BE POSITIVE, SAME NUMBER WILL BE 0, A < B WILL RETURN NEGATIVE 
		}
	}
	
	//TODO: label subclass
	public static class CompareSize implements Comparator<Customer> {
		//TODO: label method
		public int compare(Customer a, Customer b) {
			return a.getNumPersons() - b.getNumPersons(); 		// IF A > B WILL BE POSITIVE, SAME NUMBER WILL BE 0, A < B WILL RETURN NEGATIVE 
		}
	}
	
	
}


