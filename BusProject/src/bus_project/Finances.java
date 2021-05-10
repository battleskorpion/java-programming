package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import java.text.NumberFormat;
import java.util.Currency;

public abstract class Finances
{
	/*********************/
	/* CONSTANTS SECTION */
	/*********************/
	public static final NumberFormat nf = NumberFormat.getNumberInstance();
	public static final Currency USD = Currency.getInstance("USD");
	
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected static double profit = 0; 		
	
	/******************/
	/* METHOD SECTION */
	/******************/ 
	// add to profit
	protected static double addProfit(double prft) 
	{
		profit += prft; 
		return profit; 
	}
	
	// subtract from profit
	protected static double subtractProfit(double prft) 
	{
		profit -= prft; 
		return profit; 
	}
	
	// get the profit
	protected static String getProfit() 
	{
		return nf.format(profit); 
	}
	
}

