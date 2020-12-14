package input_functions;

import javax.swing.JOptionPane;

public class input_functions {
	
	static double inputDouble(String prmpt, String err_prmpt, String op)
	{
		// op PARAMETER: SIGNIFIES WHETHER THE VALUE THE USER INPUTS HAS TO BE CHECKED TO BE LESS THAN 0 (<),
		// LESS THAN OR EQUAL (<=), GREATER THAN (>), GREATER THAN OR EQUAL TO 0 (>=), OR NOT EQUAL TO 0 (!=)
		
		if (op != ">=" && op != ">" && op != "<=" && op != "<" && op != "!=")
		{
			return -1; 			// signifies error 
		}
		
		/********************/
		/* VARIABLE SECTION */
		/********************/
		double val; 			// VALUE INPUTTED BY USER
		
		do 
		{
			val = Double.parseDouble(prmpt); 
			/*****************/
	    	/* ERROR MESSAGE */
	    	/*****************/ 
			if ((val < 0 && op == ">=") || (val <= 0 && op == ">") || (val > 0 && op == "<=") || (val >= 0 && op == "<"))
			{
				JOptionPane.showMessageDialog(null, "err_prmpt", "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		} 
		while ((val < 0 && op == ">=") || (val <= 0 && op == ">") || (val > 0 && op == "<=") || (val >= 0 && op == "<")); 

		return val; 
	}
	
	static int inputInteger(String prmpt)
	{
		int intgr; 		// VALUE ENTERED BY USER
		intgr = Integer.parseInt(JOptionPane.showInputDialog(prmpt)); 
		return intgr; 
	}
}


