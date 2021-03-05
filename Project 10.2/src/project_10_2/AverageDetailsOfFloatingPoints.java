/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* FEBURARY 11, 2021								*/
/* PROGRAM 10										*/
/* PROJECT 10-2										*/
/* THIS PROGRAM WILL   		*/
/*  						*/
/* 		 					*/
/****************************************************/

/******************/
/* IMPORT SECTION */
/******************/
package project_10_2;

import javax.swing.JOptionPane;

public class AverageDetailsOfFloatingPoints {
	
	public static void main (String args[]) throws InterruptedException {
		
		/******************/
		/* PROMPT SECTION */
		/******************/
		//String prompt1 = "Input a number: "; 
		
		/********************/
		/* VARIABLE SECTION */
		/********************/
		double average; 										// AVERAGE OF numbers
		Thread process = Thread.currentThread(); 				// THREAD EXECUTING main
		
		/****************************/
		/* USER DESCRIPTION SECTION */
		/****************************/
		JOptionPane.showMessageDialog(null, "This program will stuff "
				+ "\n" + "and stuff");
		
		/**************************/
		/* RUN PROGRAM AGAIN LOOP */
		/**************************/
		do 
		{
			/*****************************/
			/* VARIABLE SECTION - ARRAYS */
			/*****************************/
			double[] numbers = new double[10]; 						// LIST OF NUMBERS
			double[] nums_greater = new double[numbers.length - 1]; // LIST OF NUMBERS > average (AT LEAST 1 NUMBER HAS TO BE < average)
			
			/*****************/
			/* INPUT SECTION */
			/*****************/
			//inputNumbersList(prompt1, numbers); 
			InputFloatingPointsGUI GUI = new InputFloatingPointsGUI(numbers, Thread.currentThread());  
			GUI.run(); 
			
			wait(process); 
			
			numbers = GUI.getNumbers(); 
			
			/***********************/
			/* CALCULATION SECTION */
			/***********************/
			average = calculateAverage(numbers); 
			GUI.setAverageText(average); 
			nums_greater = calculateNumsGreater(numbers, average, nums_greater); 
			GUI.setNumbersGreaterText(nums_greater); 
			
			/******************/
			/* OUTPUT SECTION */
			/******************/
			// WAKE UP GUI THREAD
			notify(GUI.getThread()); 
			wait(process); 
		}
		while (runProgramPrompt() == true); 
		
	}
	
	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
	static double calculateAverage(double[] nmbrs)
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		double avg = 0; 
		for (double n : nmbrs) {
			avg += n; 
		}
		return avg / nmbrs.length; 
	}
	
	static double[] calculateNumsGreater(double[] nmbrs, double avg, double[] nmbrs_grtr)  
	/******************************************************************************************************/
	/* PRECONDITION:   */					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		int grtr_index = 0; 	// INDEX FOR ADDING NUMBERS > avg TO nmbrs_grtr
		for (int i = 0; i < nmbrs.length; i++) {
			if (nmbrs[i] > avg) {
				nmbrs_grtr[grtr_index] = nmbrs[i]; 
				grtr_index++; 
			}
		}
		
		// SHORTEN LIST IF NECESSARY
		if (grtr_index < nmbrs_grtr.length - 1) {
			nmbrs_grtr = ListModification.shortenArray(nmbrs_grtr, grtr_index); 
		}
		
		return nmbrs_grtr; 		// weird stuff
	}
	
	static void notify(Thread prcss) 
	{
		synchronized (prcss)
		{
			prcss.notify(); 
		}
	}
	
	static boolean runProgramPrompt()  
	/******************************************************************************************************/ 
	/* PRECONDITION:  PROGRAM NEEDS TO PROMPT THE USER, IF THE PROGRAM SHOULD BE RUN AGAIN, OR NOT   	  */					  
	/* POSTCONDITION: USER IS ASKED IF THEY WOULD LIKE TO RUN THE PROGRAM AGAIN (YES OR NO);              */
	/* 				  USER'S INPUT IS RETURNED															  */
	/******************************************************************************************************/	
	{ 
		/********************/
		/* VARIABLE SECTION */
		/********************/
		int runProgram; 				// INDICATES WHETHER USER WISHES TO RUN THE PROGRAM AGAIN
		
		/*****************/
		/* INPUT SECTION */
		/*****************/
		runProgram = JOptionPane.showConfirmDialog(null, "Would you like to run the program again?", 
				"Run Program Again?", JOptionPane.YES_NO_OPTION); 
		// USER SELECTS YES: runProgram = 0
		// USER SELECTS NO:  runProgram = 1
		
		/******************************************/
		/* RETURN EITHER TRUE OR FALSE		      */
		/* DEPENDING ON VALUE OF runProgram	INPUT */
		/******************************************/
		return runProgram == 0;
	}
	
	static void inputNumbersList(String prmpt, double[] nmbrs)
	/******************************************************************************************************/
	/* PRECONDITION: NUMERICAL INPUT OF AN INTEGER IS REQUIRED    										  */
	/* POSTCONDITION: THE VALUE INPUTTED BY THE USER IS RETURNED 				  						  */
	/******************************************************************************************************/
	{
		/*****************/
		/* INPUT NUMBERS */
		/*****************/
		for (int i = 0; i < 10; i++) 
		{
			nmbrs[i] = inputDouble(prmpt); 
		}
	}
	
	static Double inputDouble(String prmpt)
	/******************************************************************************************************/
	/* PRECONDITION: NUMERICAL INPUT OF AN INTEGER IS REQUIRED    										  */
	/* POSTCONDITION: THE VALUE INPUTTED BY THE USER IS RETURNED 				  						  */
	/******************************************************************************************************/
	{
		/****************/
		/* INPUT NUMBER */
		/****************/
		return Double.parseDouble(JOptionPane.showInputDialog(prmpt));	
	}

	static void outputNumbersData(double[] nmbrs, double average, double[] nmbrs_grtr) 
	/******************************************************************************************************/
	/* PRECONDITION:   *					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		// CREATE OUTPUT
		String output = "";
		output += "Numbers: \n"; 
		for (double n : nmbrs) 
		{
			output += "\t" + n + "\n"; 
		}
		output += "\n" + "Average: " + average + "\n";
		output += "\n" + "Numbers > Average: \n"; 
		for (double n : nmbrs_grtr) 
		{
			output += "\t" + n + "\n"; 
		}
		
		JOptionPane.showMessageDialog(null, output);
	}
	
	static void wait(Thread process) throws InterruptedException {
		synchronized (process)
		{
			process.wait(); 
		}
	}
}

