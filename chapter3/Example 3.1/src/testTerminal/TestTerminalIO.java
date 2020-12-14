package testTerminal;

// Example 3.1: tests 3 types of input data
import java.util.Scanner;
public class TestTerminalIO {
   public static void main (String [] args) {
      Scanner reader = new Scanner(System.in);
      String name;
      int age;
      double weight;
      
      System.out.print ("Enter your name (a string): ");
      name = reader.nextLine();
      System.out.print ("Enter your age (an integer): ");
      age = reader.nextInt();
            
      System.out.print ("Enter your weight (a double): ");
      weight = reader.nextDouble();
      System.out.println ("Greetings " + name + ". You are " + age + 
          " years old and you weigh " + weight + " pounds.");
   }
}

// Lab 3.1
// Darian Siembab
// Purpose: Example 3.1 Test Terminal IO
