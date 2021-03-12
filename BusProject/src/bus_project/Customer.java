package bus_project;

import java.time.LocalDate;
import java.util.Calendar;

public class Customer 
{
	private String name; 		// NAME OF GROUP
	private int numPersons;		// NUMBER OF PEOPLE IN GROUP
	private LocalDate date; 
	
	public Customer() 
	{
		//date = new Calendar(); 
		
	}
	
	public void setDate (LocalDate dt)
	{
		date = dt; 
	}
	
	public void setName (String nm) 
	{
		name = nm; 
	}
	
	public void setNumPersons(int sz) {
		numPersons = sz; 
	}
	
	public String toString() {
		
	}
}
