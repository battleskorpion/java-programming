/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* JANUARY 11, 2021									*/
/* PROGRAM 9										*/
/* PROJECT 7-3 										*/
/* CLASS Fraction.java  							*/
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
	private int denominator;
	private int numerator; 
	 
	/****************/
	/* CONSTRUCTORS */
	/****************/
	
	public Fraction () 
	/***********************/
	/* DEFAULT CONSTRUCTOR */
	/***********************/
	{
		this (1, 1); 
	}
	
	public Fraction (int nmtr, int dnmtr) 
	/****************************/
	/* INITIALIZING CONSTRUCTOR */
	/****************************/
	{ 
		numerator = nmtr; 
		denominator = dnmtr; 
	}
	
	public Fraction (int nmtr) 
	/****************************/
	/* INITIALIZING CONSTRUCTOR */
	/****************************/
	{
		this (nmtr, 1); 
	}
	
	public Fraction(Fraction frctn)
	/********************/
	/* COPY CONSTRUCTOR */
	/********************/
	{
		this(frctn.getNumerator(), frctn.getDenominator()); 
	}
	
	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
	public Fraction add(Fraction f) 
	/******************************************************************************************************/
	/* PRECONDITION:  FRACTION OBJECT f NEEDS TO BE ADDED TO THIS FRACTION OBJECT	 					  */					  
	/* POSTCONDITION: CALCULATES THE ADDITION OF f TO THIS FRACTION, 							  		  */
	/* 				  SIMPLIFIES AND RETURNS THE RESULTING FRACTION 									  */
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
	
	public Fraction divide(Fraction f) 
	/******************************************************************************************************/
	/* PRECONDITION:  THIS FRACTION OBJECT NEEDS TO BE DIVIDED BY FRACTION OBJECT f 					  */					  
	/* POSTCONDITION: CALCULATES THE DIVISION OF THIS FRACTION BY f, 							  		  */
	/* 				  SIMPLIFIES AND RETURNS THE RESULTING FRACTION 									  */
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
	
	public int getDenominator() 
	/******************************************************************************************************/
	/* PRECONDITION:  THE VALUE OF THIS FRACTION OBJECT'S DENOMINATOR IS REQUIRED						  */					  
	/* POSTCONDITION: RETURNS THIS FRACTION OBJECT'S DENOMINATOR 										  */	
	/******************************************************************************************************/
	{
		return denominator; 
	}
	
	public int getNumerator() 
	/******************************************************************************************************/
	/* PRECONDITION:  THE VALUE OF THIS FRACTION OBJECT'S NUMERATOR IS REQUIRED 						  */					  
	/* POSTCONDITION: RETURNS THIS FRACTION OBJECT'S NUMERATOR 											  */
	/******************************************************************************************************/
	{
		return numerator; 
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
		String prompt1 = "Enter the numerator of the fraction, must be an integer: "; 
		String prompt2 = "Enter the denominator of the fraction, must be an integer: "; 
		
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
		String prompt2 = "Input the denominator of the " + inpt_nm + " fraction, must be an integer and != 0: "; 
		String error_prompt2 = "Denominator can not be 0!"; 
		
		/*****************/
		/* INPUT SECTION */
		/*****************/
		nmtr = inputInt(prompt1); 
		dnmtr = inputInt(prompt2, error_prompt2, "!="); 
		
		/***********************/
		/* CALCULATION SECTION */
		/***********************/
		f = new Fraction(nmtr, dnmtr); 
		
		return f; 
		
	}
	
	static int inputInt(String prmpt)
	/******************************************************************************************************/
	/* PRECONDITION: NUMERICAL INPUT OF AN INTEGER IS REQUIRED    										  */
	/* POSTCONDITION: THE VALUE INPUTTED BY THE USER IS RETURNED 				  						  */
	/******************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int val; 	// VALUE INPUTTED BY USER

		/****************/
		/* INPUT NUMBER */
		/****************/
		val = Integer.parseInt(JOptionPane.showInputDialog(prmpt));

		return val;
	}
	
	static int inputInt(String prmpt, String err_prmpt, String op)
	/******************************************************************************************************/
	/* PRECONDITION: NUMERICAL INPUT OF AN INTEGER IS REQUIRED    										  */
	/* POSTCONDITION: THE VALUE INPUTTED BY THE USER IS RETURNED WHEN IT IS CORRECT						  */
	/* 																									  */
	/* op PARAMETER: SIGNIFIES WHETHER THE VALUE THE USER INPUTS HAS TO BE CHECKED TO BE LESS THAN 0 (<), */
	/* LESS THAN OR EQUAL (<=), GREATER THAN (>), GREATER THAN OR EQUAL TO 0 (>=), OR NOT EQUAL TO 0 (!=) */
	/******************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int val; 		// VALUE INPUTTED BY USER

		/****************/
		/* INPUT NUMBER */
		/****************/
		
		/********************************************/
		/* LOOP IN CASE INPUTTED NUMBER IS IMPROPER */
		/********************************************/
		do 
		{
			val = Integer.parseInt(JOptionPane.showInputDialog(prmpt));

			/************************************************/
			/* ERROR MESSAGE IF NUMBER INPUTTED IS IMPROPER */
			/************************************************/
			if ((val < 0 && op.equals(">=")) || (val <= 0 && op.equals(">")) || (val > 0 && op.equals("<="))
					|| (val >= 0 && op.equals("<")) || (val == 0 && op.equals("!="))) 
			{
				JOptionPane.showMessageDialog(null, err_prmpt, "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		} 
		while ((val < 0 && op.equals(">=")) || (val <= 0 && op.equals(">")) || (val > 0 && op.equals("<="))
				|| (val >= 0 && op.equals("<")) || (val == 0 && op.equals("!=")));
		
		return val;
	}
	
	public Fraction multiply(Fraction f) 
	/******************************************************************************************************/
	/* PRECONDITION:  THIS FRACTION OBJECT NEEDS TO BE MULTIPLIED BY FRACTION OBJECT f 					  */					  
	/* POSTCONDITION: CALCULATES THE MULTIPLICATION OF THIS FRACTION BY f, 							  	  */
	/* 				  SIMPLIFIES AND RETURNS THE RESULTING FRACTION 									  */
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
	
	public void setDenominator(int dnmtr) 
	/******************************************************************************************************/
	/* PRECONDITION:  denominator OF THIS FRACTION OBJECT NEEDS TO BE SET TO A NEW VALUE 				  */					  
	/* POSTCONDITION: SETS denominator TO dnmtr (NEW DENOMINATOR VALUE) 								  */
	/******************************************************************************************************/
	{
		denominator = dnmtr; 
	}
	
	public void setFraction (int nmtr, int dnmtr)
	/******************************************************************************************************/
	/* PRECONDITION:  BOTH THE numerator AND denominator OF THIS FRACTION OBJECT NEEDS TO BE SET TO NEW   */
	/* 				  VALUES 																			  */					  
	/* POSTCONDITION: SETS numerator TO nmtr (NEW NUMERATOR VALUE) 										  */
	/* 	              and denominator TO dnmtr (NEW DENOMINATOR VALUE) 									  */
	/******************************************************************************************************/
	{
		numerator = nmtr; 
		denominator = dnmtr;
	}
	
	public void setNumerator(int nmtr) 
	/******************************************************************************************************/
	/* PRECONDITION:  numerator OF THIS FRACTION OBJECT NEEDS TO BE SET TO A NEW VALUE  			      */					  
	/* POSTCONDITION: SETS numerator TO nmtr (NEW NUMERATOR VALUE) 										  */
	/******************************************************************************************************/
	{
		numerator = nmtr; 
	}
	
	public void simplify() 
	/******************************************************************************************************/
	/* PRECONDITION:  THIS FRACTION OBJECT MAY NEED TO BE SIMPLIFIED 									  */					  
	/* POSTCONDITION: SIMPLIFIES THE FRACTION IF POSSIBLE (AND/OR SIMPLIFIES SIGN IF BOTH ARE NEGATIVE)   */
	/******************************************************************************************************/
	{
		int gcd = calculateGCD(+numerator, +denominator); 
		
		if (gcd != 0) {
			numerator /= gcd; 
			denominator /= gcd; 
		}
		
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1; 
		}
	}
	
	public Fraction subtract(Fraction f) 
	/******************************************************************************************************/
	/* PRECONDITION:  FRACTION OBJECT f NEEDS TO BE SUBTRACTED FROM THIS FRACTION OBJECT 				  */					  
	/* POSTCONDITION: CALCULATES THE SUBTRACTION OF f FROM THIS FRACTION, 							  	  */
	/* 				  SIMPLIFIES AND RETURNS THE RESULTING FRACTION 									  */
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
	
	public String toString() 
	/******************************************************************************************************/
	/* PRECONDITION:  THIS FRACTION CLASS INSTANCE NEEDS TO BE REPRESENTED BY A STRING 					  */					  
	/* POSTCONDITION: RETURNS A SIMPLIFIED STRING REPRESENTATION OF THE FRACTION						  */ 
	/* 				  THE CLASS INSTANCE IS STORING        												  */
	/******************************************************************************************************/
	{
		if (denominator == 0) 
		{
			return "DIVIDE BY 0 ERROR"; 
		}
		else if (denominator == 1) 
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
}
