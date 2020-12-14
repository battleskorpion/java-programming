// Case Study 7.2: Display the nth Fibonacci number
package chap_7; 
import java.util.Scanner;

public class Fibonacci {

   public static void main (String [] args) {
      Scanner reader = new Scanner(System.in);
      int n;          //The number entered by the user
      int fib;        //The nth Fibonacci number
      int a,b,count;  //Variables that facilitate the computation

      while (true){

         // Ask the user for the next input
         System.out.print("Enter a positive integer or -1 to quit: ");
         n = reader.nextInt();
         if (n == -1) break;
         else if (n >= 1){ 

            //Calculate the nth Fibonacci number
            fib = 1;             //Takes care of case n = 1 or 2
            a = 1;
            b = 1;
            count = 3;
            while (count <= n){  //Takes care of case n >= 3
               fib = a + b;      //Point p. Referred to later.
               a = b;
               b = fib;
               count = count + 1;
            }
        
            //Print the nth Fibonacci number 
            System.out.println ("Fibonacci number " + n + " is " + fib);
         }  // end else
      }     // end while
   }        // end main
}

// Darian Siembab
// case study 7.2
// Program asks the user to input a positive number n (or -1 to end), 
// and calculates and displays the nth fibonacci number