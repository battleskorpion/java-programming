/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/ 
/* OCTOBER 1, 2020									*/
/* PROGRAM 5										*/
/* PROJECT 4-1										*/
/* THIS PROGRAM WILL ACCEPT TWO INTEGERS, DIVIDE	*/
/* THE LARGER BY THE SMALLER, AND PROVIDE THE 		*/
/* QUOTIENT AND REMAINDER							*/
/****************************************************/ 

package program_5;

/******************/
/* IMPORT SECTION */
/******************/
import javax.swing.*;    				// FOR JFRAME, JPANEL, AND JOPTIONPANE (WINDOWS) 
import java.awt.*;						// TO CHANGE FONT AND COLORS

public class Divide_QuotientAndRemainder 
{
	/********************/
	/* CONSTANT SECTION */
	/********************/
	public static final String prompt1 = "Input an integer";
	public static final String prompt2 = "Input another integer"; 
	public static final String run_prompt = "Would you like to run the program again?"; 
			
	public static void main(String[] args)
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int int1; 				// USER'S FIRST INTEGER
		int int2;				// USER'S SECOND INTEGER
		int quotient; 			// QUOTIENT OF DIVISION OPERATION
		int remainder; 			// REMAINDER OF DIVISION OPERATION
		String results_op = ""; // STORES WHAT DIVISION OPERATION OCCURED AS A STRING
		int runProgram; 		// RUN PROGRAM AGAIN: 0 = YES, 1 = NO 

		/**************************/
		/* RUN PROGRAM AGAIN LOOP */
		/**************************/
		do 
		{
			/****************************/
		    /* USER DESCRIPTION SECTION */ 
		    /****************************/
			JOptionPane.showMessageDialog(null, "This program will accept two integers, divide " 
					+ "the larger integer by the smaller, \nand provide the quotient and the remainder "
					+ "from such operation.", "Program Description", JOptionPane.PLAIN_MESSAGE);
			
			/*****************/
		    /* INPUT SECTION */ 
		    /*****************/
			int1 = inputInteger(prompt1); 
			int2 = inputInteger(prompt2); 
			
			/***********************/
		    /* CALCULATION SECTION */ 
		    /***********************/
			if (int1 < int2)
			{
				remainder = int2 % int1; 
				quotient = (int2 - remainder) / int1;
				results_op = int2 + " / " + int1;  
			} 
			else if (int1 > int2)
			{
				remainder = int1 % int2; 
				quotient = (int1 - remainder) / int2; 
				results_op = int1 + " / " + int2; 
			}
			else 						// WHEN INTEGERS ARE EQUAL
			{
				remainder = 0; 			// A NUMBER DIVIDED BY ITSELF HAS NO REMAINDER
				quotient = 1; 			// A NUMBER DIVIDED BY ITSELF EQUALS 1
				results_op = int1 + " / " + int2; 
			}
			
			/******************/ 
		    /* OUTPUT SECTION */
		    /******************/
			JTextArea outputTextArea = new JTextArea(); 
			outputTextArea.setFont (new Font ("Monospaced", Font.PLAIN, 14)); 
			outputTextArea.setBackground(Color.WHITE); 
			outputTextArea.append("Integers:" + "\n" + int1 + "\n" + int2 + "\n\n"); 
			outputTextArea.append("Results of " + results_op + ":\n"); 
			outputTextArea.append("Quotient: " + quotient + "\n" + "Remainder:" + remainder);
			
			JOptionPane.showMessageDialog (null, outputTextArea,
					"Division with Quotient and Remainder",
					JOptionPane.QUESTION_MESSAGE, null);
			
			/*********************/
			/* RUN PROGRAM AGAIN */
			/*********************/
			runProgram = JOptionPane.showConfirmDialog(null, run_prompt, "t", JOptionPane.YES_NO_OPTION); 
			// USER SELECTS YES: runProgram = 0
			// USER SELECTS NO:  runProgram = 1
		}
		while (runProgram == 0);	 
	} 
	
	/********************/ 
	/* FUNCTION SECTION */
	/********************/ 
	
	static int inputInteger(String prmpt)
	/************************************************************************/
	/* PRECONDITION: DIALOG BOX ASKING USER TO INPUT AN INTEGER IS REQUIRED */  
	/* POSTCONDITION: THE INTEGER VALUE INPUTTED BY THE USER IS RETURNED 	*/
	/************************************************************************/
	{
		int intgr; 		// VALUE ENTERED BY USER
		intgr = Integer.parseInt(JOptionPane.showInputDialog(prmpt)); 
		return intgr; 
	}
	
}


