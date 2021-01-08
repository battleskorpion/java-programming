import java.text.NumberFormat;
import javax.swing.*;

/* 
 * bonus
 * 
 */

public class sentenceInfo 
{
	public static void main(String[] args) 
	{
		/******************/
		/* PROMPT SECTION */
		/******************/
		final String prompt1 = "Input a sentence: "; 
		
		/********************/
		/* VARIABLE SECTION */
		/********************/
		double avgWordLength; 
		int numWords; 
		boolean runProgram; 								// RUN PROGRAM AGAIN: false = NO, true = YES
		int sentenceLength; 
		String userSentence; 
		
		
		/****************************/
		/* USER DESCRIPTION SECTION */
		/****************************/
		JOptionPane.showMessageDialog(null, "This program will STUFF", 
				  "Program Description", JOptionPane.PLAIN_MESSAGE);
		
		/**************************/
		/* RUN PROGRAM AGAIN LOOP */
		/**************************/
		do 
		{
			/*****************/
			/* INPUT SECTION */
			/*****************/
			
			/****************************/
			/* METHOD TO INPUT SENTENCE */
			/****************************/
			userSentence = inputString(prompt1); 
			
			/***********************/
			/* CALCULATION SECTION */
			/***********************/
	
			/******************************************/
			/* METHOD TO CALCULATE SENTENCE  */
			/******************************************/
			numWords = calculateNumWords(userSentence); 
			avgWordLength = calculateAvgWordLength(userSentence, numWords); 
			sentenceLength = calculateSentenceLength(userSentence, numWords); 	// calculates not including spaces
			
			/******************/
			/* OUTPUT SECTION */
			/******************/
			
			/**************/
			/* METHOD TO 
			/**************/
			displaySentenceInfo(userSentence, numWords, avgWordLength, sentenceLength); 
			
			/***************************************/
			/* METHOD FOR RUN PROGRAM AGAIN PROMPT */
			/***************************************/
			runProgram = runProgramPrompt();
		} 
		while (runProgram == true);
	}

	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
	static int calculateNumWords(String sntnc)
	/******************************************************************************************************/
	/* PRECONDITION:   *					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		String tempString; 			// STRING FOR CALCULATION
		
		/***********************/
		/* CALCULATION SECTION */
		/***********************/
		tempString = sntnc; 
		
		tempString = tempString.trim(); // REMOVES LEADING AND TRAILING WHITESPACE
		
		// if no words return 0 
		if (tempString.equals("")) 	// 
		{
			return 0; 	// there is no words
		}
		
		int wordCtr = 1; // Every sentence now will have at least one word
		
		for (int i = tempString.indexOf(' '); i < tempString.length();)
		{
			if (i == -1) 
			{
				return wordCtr; 		// no more words to count
			}
			else if (tempString.charAt(i - 1) != ' ')
			{
				wordCtr++; 
				i = tempString.indexOf(' ', i + 1); 
			}
			else 
			{
				i = tempString.indexOf(' ', i + 1); 
			}
		}
	
		return wordCtr; 	// redundant return
	}
	
	static double calculateAvgWordLength(String sntnc, int nmWrds)
	/******************************************************************************************************/
	/* PRECONDITION:   *					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		String tempString; 					// STRING FOR CALCULATION
		
		/***********************/
		/* CALCULATION SECTION */
		/***********************/
		tempString = sntnc; 
		
		tempString = tempString.trim(); 	// REMOVES LEADING AND TRAILING WHITESPACE
		
		// if no words return 0 
		if (nmWrds == 0) 	 
		{
			return 0; 	// there is no words, so average word length is also 0
		}
		
		int totalChars = 0; 					// total number of non-space characters
		
		for (int i = 0; i < tempString.length(); i++)
		{
			if (tempString.charAt(i) != ' ') 
			{
				totalChars++; 
			}
		}
		
		return (double)totalChars / nmWrds; 	// redundant return
	}
	
	static int calculateSentenceLength(String sntnc, int nmWrds)
	/******************************************************************************************************/
	/* PRECONDITION:   *					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		String tempString; 					// STRING FOR CALCULATION
		
		/***********************/
		/* CALCULATION SECTION */
		/***********************/
		tempString = sntnc; 
		
		tempString = tempString.trim(); 	// REMOVES LEADING AND TRAILING WHITESPACE
		
		// if no words return 0 
		if (nmWrds == 0) 	 
		{
			return 0; 	// there is no words, so average word length is also 0
		}
		
		int totalChars = 0; 					// total number of non-space characters
		
		for (int i = 0; i < tempString.length(); i++)
		{
			if (tempString.charAt(i) != ' ') 
			{
				totalChars++; 
			}
		}
		
		return totalChars; 	// redundant return
	}
	
	static void displaySentenceInfo(String sntnc, int nmWrds, double avgWrdLngth, int sntncLngth)
	/******************************************************************************************************/
	/* PRECONDITION:   *					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		NumberFormat nf = NumberFormat.getInstance(); 		// FOR FORMATTING NUMBERS IN OUTPUT
		
		nf.setMaximumFractionDigits(2); 		// NUMBERS FORMATTED WITH nf TO HAVE MAX OF 2 DECIMAL DIGITS

		//// FOR LONG STRINGS THAT WOULD NOT FIT PROPERLY ON ONE LINE
		//if (sntnc.length() >= 128) {
		//	String temp = "";
		//	for (int i = 128; i < sntnc.length(); i += 128) {
		//		temp = sntnc.substring(i + 1, sntnc.length());
		//		sntnc = sntnc.substring(0, i);
		//		sntnc.concat("\n" + temp);
		//	}
		//}

		JOptionPane.showMessageDialog(null, "Sentence: " + sntnc + "\n" + "Number of words: " + nmWrds 
				+ "\n" + "Average word length: " + nf.format(avgWrdLngth)
				+ "\n" + "Sentence Length"
				+ "\n" + "    " + "Not incl. whitespace: " + sntncLngth
				+ "\n" + "    " + "Incl. whitespace: " + sntnc.length()
				,"Sentence Data", JOptionPane.PLAIN_MESSAGE); 
	}
	
	static String inputString(String prmpt)
	/******************************************************************************************************/
	/* PRECONDITION:   *					  
	/* POSTCONDITION:  */
	/******************************************************************************************************/
	{
		return JOptionPane.showInputDialog(prmpt); 
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
		
		/***********************************************/ 
		/* IF STATEMENT TO RETURN EITHER TRUE OR FALSE */
		/* DEPENDING ON VALUE OF runProgram	INPUT 	   */
		/***********************************************/
		if (runProgram == 0)
		{
			return true;

		}
		else
		{
			return false;
		}
	}
}
