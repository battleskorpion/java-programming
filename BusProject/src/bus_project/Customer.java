package bus_project;

import java.time.LocalDate;

public class Customer 
{
	private String name; 		// NAME OF GROUP
	private int numPersons;		// NUMBER OF PEOPLE IN GROUP
	private LocalDate date; 
	private int index;				// INDEX CUSTOMER IS/WAS AT IF BEING STORED IN AN ARRAY
	
	public Customer() 
	{
		date = LocalDate.now(); 
		name = ""; 
		numPersons = 0;
		index = 0; 
	}
	
	public String getName () 
	{
		return name; 
	}
	
	public int getNumPersons () 
	{
		return numPersons; 
	}
	
	public LocalDate getDate ()
	{
		return date; 
	}
	
	public int getIndex () {
		return index; 
	}
	
	public void setDate (LocalDate dt)
	{
		date = dt; 
	}
	
	public void setName (String nm) 
	{
		name = nm; 
	}
	
	public void setIndex (int indx) {
		index = indx; 
	}
	
	public void setNumPersons(int sz) {
		numPersons = sz; 
	}
	
	public String toString() {
		return "Group: " + name + " \nGroup Size: " + numPersons + " \nDate: " + date.toString(); 
	}
}
