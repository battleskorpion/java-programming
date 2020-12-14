// Example 4.3: Computes and displays the average of
// a file of floating-point numbers

package f; 

import java.io.*;
import java.util.Scanner;

 public class ComputeAverage{
   public static void main(String[] args) throws IOException {
      Scanner reader = new Scanner(new File("C:\\Users\\Skorpion\\eclipse-workspace\\numbers.txt"));
      double number, sum = 0;
      int count = 0;

      while (reader.hasNext()){
         number = reader.nextDouble(); 
         sum += number;
         count++;
      }
      if (count == 0)
         System.out.println("The file had no numbers");
      else
         System.out.println("The average of " + count + " numbers is " +
                            sum / count);
   }
}
 
// Darian Siembab
// 4.3
// Program computes average of numbers read from a text file, 
// or prints an error if the file had no numbers