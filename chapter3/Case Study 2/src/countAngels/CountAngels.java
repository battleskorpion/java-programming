package countAngels;

/*Case study 2: count the angels
Count the number of angels that can dance on the head of a pin.
1. The user inputs are
      The radius of the pinhead
      The space occupied by an angel
      The allowed overlap between angels subject to the restriction
      that no space can simultaneously be occupied by more than two
2. The program computes
      The area of the pinhead based on its radius
      The amount of nonoverlapping space required by an angel
      The number of angels based on the preceding two values
3. The program ends by printing the number of angels.
*/

import java.util.Scanner;

public class CountAngels {
	
   public static void main(String [] args){
	   
      Scanner reader = new Scanner(System.in);
      
      double radius;           	//Radius of the pinhead in millimeters
      
      double angelSpace;      	//Space occupied by an angel
                      			//in square micrometers
      
      double overlapFactor;   	//Allowed overlap between angels from 0 to 1
      double area;        		//Area of the pinhead in square millimeters
      double nonOverlapSpace; 	//Nonoverlapping space required by an angel
      double numberAngels;    	//Number of angels that can dance on the
      							//pinhead
      //Get user inputs
      System.out.print ("Enter the radius in millimeters: ");
      radius = reader.nextDouble();
      System.out.print("Enter the space occupied by an angel in square micrometers: ");
      angelSpace = reader.nextDouble();
      System.out.print ("Enter the overlap factor: ");
      overlapFactor = reader.nextDouble();
      
      //Perform calculations
      area = 3.14 * radius * radius;
      nonOverlapSpace = angelSpace * (1.0 - overlapFactor);
      numberAngels = area / nonOverlapSpace;
      
      //Print results
      System.out.print ("The number of angels = " + numberAngels);
      reader.close(); 
   }
}

// Lab 3-6
// Darian Siembab
// Purpose: Case Study 3-2 - Counting Angels
