package shark_lesson;

public class sharkLesson {
	
	public static void main (String args[]) {
		
		String strat_provs = "222 222 333 4444 5555 5555 5555 9001 9001 9001 9001 9001";
		System.out.println("Before: " + strat_provs); 
		strat_provs = strat_provs.trim(); 
		
		String[] prov_array = strat_provs.split(" "); 
		String new_provs = prov_array[0]; 
	
		for (int i = 1; i < prov_array.length; i++) {
			if (!(prov_array[i].equals(prov_array[i - 1]))) {
				new_provs = new_provs + " " + prov_array[i]; 
			}
		}
		
		System.out.println("After: " + new_provs); 
	}
}
