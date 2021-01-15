/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* JANUARY 11, 2021									*/
/* PROGRAM 9 - Fraction.java class					*/
/* PROJECT 7-3 										*/
/*    	*/
/* 	*/
/****************************************************/

/******************/
/* IMPORT SECTION */
/******************/
package program_9;

import javax.swing.JOptionPane;

public class Fraction 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	private int numerator; 
	private int denominator; 
	
	/****************/
	/* CONSTRUCTORS */
	/****************/
	public Fraction () 
	{
		this (1, 1); 
	}
	
	public Fraction (int nmtr, int dnmtr) 
	{ 
		numerator = nmtr; 
		denominator = dnmtr; 
	}
	
	public Fraction (int dnmtr) 
	{
		this (0, dnmtr); 
	}
	
	public Fraction(Fraction frctn) 
	// COPY CONSTRUCTOR 
	{
		this(frctn.getNumerator(), frctn.getDenominator()); 
	}
	
	public void setNumerator(int nmtr) 
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		numerator = nmtr; 
	}
	
	public void setDenominator(int dnmtr) 
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		denominator = dnmtr; 
	}
	
	public void setFraction (int nmtr, int dnmtr)
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		numerator = nmtr; 
		denominator = dnmtr;
	}
	
	public int getNumerator() 
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		return numerator; 
	}
	
	public int getDenominator() 
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		return denominator; 
	}
	
	public String toString() 
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		if (denominator == 1) 
		{
			return numerator + ""; 
		}
		else if (numerator == 0) {
			return "0"; 
		}
		else 
		{
			return numerator + "/" + denominator;
		}
	}
	
	public Fraction add(Fraction f) 
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{ 
		Fraction result = new Fraction(); 
		
		int n1 = numerator; 
		int d1 = denominator; 
		int n2 = f.getNumerator(); 
		int d2 = f.getDenominator(); 
		
		result.setNumerator((n1 * d2) + (n2 * d1));
		result.setDenominator(d1 * d2); 
		result.simplify(); 
		return result; 
	}
	
	public Fraction subtract(Fraction f) 
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		Fraction result = new Fraction(); 
		
		int n1 = numerator; 
		int d1 = denominator; 
		int n2 = f.getNumerator(); 
		int d2 = f.getDenominator(); 
		
		result.setNumerator((n1 * d2) - (n2 * d1));
		result.setDenominator(d1 * d2); 
		result.simplify(); 
		return result; 
	} 
	
	public Fraction multiply(Fraction f) 
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		Fraction result = new Fraction(); 
		
		int n1 = numerator; 
		int d1 = denominator; 
		int n2 = f.getNumerator(); 
		int d2 = f.getDenominator(); 
		
		result.setFraction(n1 * n2, d1 * d2); 
		result.simplify(); 
		return result; 
	}
	
	public Fraction divide(Fraction f) 
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		Fraction result = new Fraction(); 
		
		int n1 = numerator; 
		int d1 = denominator; 
		int n2 = f.getNumerator(); 
		int d2 = f.getDenominator(); 
		
		result.setFraction(n1 * d2, d1 * n2);
		result.simplify(); 
		return result; 
	}
	
	static int calculateGCD(int n1, int n2)  
	/******************************************************************************************************/ 
	/* PRECONDITION:  PROGRAM REQURIES THE GREATEST COMMON DIVISOR TO BE CALCULATED OF TWO POSITIVE 	  */ 
	/* 				  INTEGERS  												   						  */					  
	/* POSTCONDITION: GREATEST COMMON DIVISOR OF TWO POSITIVE INTEGERS IS CALCULATED USING ECLUIDEAN 	  */
	/* 				  ALGORITHM												   				  		  	  */
	/******************************************************************************************************/ 
	{
		/********************************/
		/* DIVIDE BY 0 ERROR PREVENTION */
		/********************************/
		if (n1 == 0 || n2 == 0) 
		{
			return 0; 
		}
		
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int remainder; 						// remainder of larger / smaller (larger % smaller) 
		int larger = Math.max(n1, n2); 		// larger value of n1 and n2 
		int smaller = Math.min(n1, n2); 	// smaller value of n1 and n2
		
		/****************************/ 
		/* EUCLIDEAN ALGORITHM LOOP */
		/****************************/ 
		do 
		{ 
			/***************************************************************************************/ 
			/* remainder IS SET EQUAL TO THE REMAINDER OF THE LARGER VALUE DIVIDED BY THE SMALLER, */
			/* THEN THE larger VALUE IS REPLACED BY THE PREVIOUS smaller VALUE, 				   */
			/* THE smaller VALUE IS THEN REPLACED BY THE PREVIOUS CALCULATED remainder VALUE,      */
			/* AND THE STEPS ARE REPEATED UNTIL remainder IS EQUAL TO 0.						   */	
			/* THE FINAL VALUE OF larger WILL THEN BE THE GCD OF n1 and n2, 					   */
			/***************************************************************************************/
			remainder = larger % smaller; 
			
			larger = smaller; 
			smaller = remainder; 
		}
		while (remainder != 0); 
		
		/********************************************************************************************/ 
		/* THE VALUE OF larger WHEN THE ALGORITHM IS COMPLETED IS THE GCD, SO ITS VALUE IS RETURNED */
		/********************************************************************************************/ 
		return larger; 
	}
	
	static public Fraction inputFraction() 
	/******************************************************************************************************/
	/* PRECONDITION: A NEW FRACTION NEEDS TO BE INITIALIZED BASED ON USER INPUT							  */					  
	/* POSTCONDITION: A NEW FRACTION IS INITIALIZED BASED ON THE USER INPUT AND RETURNED 				  */
	/******************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int dnmtr; 		// DENOMINATOR
		Fraction f; 	// FRACTION
		int nmtr; 		// NUMERATOR
		String prompt1 = "Input the numerator of the fraction, must be an integer: "; 
		String prompt2 = "Input the denominator of the fraction, must be an integer: "; 
		
		/*****************/
		/* INPUT SECTION */
		/*****************/
		nmtr = inputInt(prompt1); 
		dnmtr = inputInt(prompt2); 
		
		/***********************/
		/* CALCULATION SECTION */
		/***********************/
		f = new Fraction(nmtr, dnmtr); 
		
		return f; 
		
	}
	
	static public Fraction inputFraction(String inpt_nm) 
	/******************************************************************************************************/
	/* PRECONDITION: A NEW FRACTION NEEDS TO BE INITIALIZED BASED ON USER INPUT							  */					  
	/* POSTCONDITION: A NEW FRACTION IS INITIALIZED BASED ON THE USER INPUT AND RETURNED 				  */
	/*																									  */
	/* inpt_nm PARAMETER: IF INPUTTING MORE THAN ONE FRACTION SEQUENTIALLY, 							  */
	/* 					  inpt_nm CAN DISTINGUISH BETWEEN INPUTS										  */
	/******************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int dnmtr; 		// DENOMINATOR
		Fraction f; 	// FRACTION
		int nmtr; 		// NUMERATOR
		String prompt1 = "Input the numerator of the " + inpt_nm + " fraction, must be an integer: "; 
		String prompt2 = "Input the denominator of the " + inpt_nm + " fraction, must be an integer: "; 
		
		/*****************/
		/* INPUT SECTION */
		/*****************/
		nmtr = inputInt(prompt1); 
		dnmtr = inputInt(prompt2); 
		
		/***********************/
		/* CALCULATION SECTION */
		/***********************/
		f = new Fraction(nmtr, dnmtr); 
		
		return f; 
		
	}
	
	static private int inputInt(String prmpt)
	/******************************************************************************************************/
	/* PRECONDITION: NUMERICAL INPUT OF AN INTEGER IS REQUIRED    										  */
	/* POSTCONDITION: THE VALUE INPUTTED BY THE USER IS RETURNED 				  						  */
	/******************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int val; // VALUE INPUTTED BY USER

		/****************/
		/* INPUT NUMBER */
		/****************/
		val = Integer.parseInt(JOptionPane.showInputDialog(prmpt));

		return val;
	}
	
	public void simplify() 
	/******************************************************************************************************/
	/* PRECONDITION:   *					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		int gcd = calculateGCD(+numerator, +denominator); 
		System.out.println(gcd);  
		
		if (gcd != 0) {
			numerator /= gcd; 
			denominator /= gcd; 
		}
		
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1; 
		}
	}
}
