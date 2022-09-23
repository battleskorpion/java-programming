/********************************************/
/* INFORMATION SECTION 						*/
/* Finances.java							*/
/* Darian Siembab 							*/
/* 											*/
/* ABSTRACT CLASS FOR PERFORMING BASIC		*/
/* FINANCE CALCULATIONS, AND STORING 		*/
/* FORMATTING INFORMATION					*/
/********************************************/ 

package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import java.text.NumberFormat;							// NUMBER FORMAT IS THE ABSTRACT BASE CLASS 
														// FOR ALL NUMBER FORMATS. THIS CLASS PROVIDES
														// THE INTERFACE FOR FORMATTING AND PARSING 
														// NUMBERS.
import java.util.Currency;								// REPRESENTS A CURRENCY

public abstract class Finances
{
	/*********************/
	/* CONSTANTS SECTION */
	/*********************/
	protected static NumberFormat nf = NumberFormat.getNumberInstance();
	protected static Currency currency; 
	
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected static double profit = 0; 		
	
	/******************/
	/* METHOD SECTION */
	/******************/
	protected static double addProfit(double prft) 
	/************************************************************************************************/
	/* PRECONDITION:  AN AMOUNT NEEDS TO BE ADDED TO PROFIT			  								*/
	/* POSTCONDITION: ADDS TO PROFIT																*/
	/************************************************************************************************/
	{
		profit += prft; 
		return profit; 
	}
	
	protected static double subtractProfit(double prft) 
	/************************************************************************************************/
	/* PRECONDITION:  AN AMOUNT NEEDS TO BE SUBTRACTED FROM THE PROFIT			  					*/
	/* POSTCONDITION: SUBTRACT FROM PROFIT 															*/
	/************************************************************************************************/
	{
		profit -= prft; 
		return profit; 
	}
	
	protected static String getProfit() 
	/************************************************************************************************/
	/* PRECONDITION:  THE TOTAL PROFIT IS NEEDED		  											*/
	/* POSTCONDITION: RETURNS THE PROFIT															*/
	/************************************************************************************************/
	{
		return nf.format(profit); 
	}
	
}

