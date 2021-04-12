package bus_project;

import java.text.NumberFormat;
import java.util.Currency;

//TODO: lots
public class Finances {
	/***************/
	/* CONSTANTS SECTION */
	/*********************/
	public static NumberFormat nf = NumberFormat.getNumberInstance();
	public static Currency USD = Currency.getInstance("USD");
	
	// TODO:variable section
	protected static double profit = 0; 		
	
	// add to profit
	protected static double addProfit (double prft) 
	{
		profit += prft; 
		return profit; 
	}
	
	// subtract from profit
	protected static double subtractProfit (double prft) 
	{
		profit -= prft; 
		return profit; 
	}
	
	// get the profit
	protected static double getProfit () 
	{
		return profit; 
	}
	
}

