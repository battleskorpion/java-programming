package bus_project;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BusCalculation {
	private static ArrayList<LocalDate> dates; 
	private static ArrayList<Integer> busesPerDate;					// number of buses used per each date  
	private static ArrayList<ArrayList<Customer>> customers; 	 	// customers each date 
	
	/* constructors */
	
	public BusCalculation () {
		
	}
	
	/* method section */ 
	
	// schedule customer on date selected
	public static void scheduleTrip (Customer cstmr) {
		LocalDate date = cstmr.getDate(); 
		int merge; 		// whether or not to merge	// 0 = yes, 1 = no
		
		// if another booking from any customer exists on same date
		if (dates.contains(date)) {
			//
			for (int i = 0; i < customers.get(dates.indexOf(date)).size(); i++) {
				// if another booking from identical customer exists on same date
				if (customers.get(dates.indexOf(date)).get(i).getName() == cstmr.getName()) {
					// ask to merge customers? 
					merge = JOptionPane.showConfirmDialog(null, "A customer with the same name was found on the same date. Merge the customers?", "Merge customers?" , JOptionPane.YES_NO_OPTION); 
					
					// if merge
					if (merge == 0) {
						
					}
					// if not merge
					else {
						
					}
					break; 
				}

				
			}
			// another booking from another customer(s) exists on same date
			//else {
			//	customers.get(dates.indexOf(date)).add(cstmr); 
			//}
			
		}
		// else (no booking on date)
		else {	
			
		}
	}
}
