package testingstuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author darian.siembab 
 * @author Sam Schumacher 
 *
 */
public class Test {

	public static void main (String args[]) {
		 
		Scanner in;		
		
		// format: ArrayList<data_type> var_name = new ArrayList<data_type>(); 
		ArrayList<String> inputs = new ArrayList<String>(); 
		
		// this is for file input (try/catch is mandatory or the program will not run likely)
		// if standard input can be used replace entire block with 
		// in = new Scanner(System.in) (to recieve input from console using Scanner) 
		try {	// start block
			in = new Scanner(new File("test.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return; 
		}		// end block
		
		// Add line to ArrayList while there is more input to read
		while (in.hasNextLine()) 
		{
			inputs.add(in.nextLine());
		}
		
		// If one of the lines consists of multiple inputs separated by a character:
		String[] lineParts = inputs.get(0).split(" ");
		// If this doesn't work, try using \ to escape the character inside split
		
		// Say that lineParts is actually supposed to be a sequence of integers:
		int[] intInputs = new int[lineParts.length];
		for (int i = 0; i < lineParts.length; i++)
		{
			intInputs[i] = Integer.parseInt(lineParts[i]);
			// or Double.parseDouble(lineParts[i]);
		}
		
		System.out.println(inputs);
		
		for (String part : lineParts)
		{
			System.out.println(part);
		}
		
		for (int num : intInputs)
		{
			System.out.println(num);
		}
		
		in.close();
	}
}
