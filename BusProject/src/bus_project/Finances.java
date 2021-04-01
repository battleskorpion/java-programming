package bus_project;

//TODO: lots
public class Finances {
	public static float profit = 0; 		// profit from trips	// only trips after today should be counted
	
	// add to profit
	public static float addProfit (float prft) {
		profit += prft; 
		return profit; 
	}
	
	// subtract from profit
	public static float subtractProfit (float prft) {
		profit -= prft; 
		return profit; 
	}
	
	// get the profit
	public static float getProfit (float prft) {
		return profit; 
	}
	
}

