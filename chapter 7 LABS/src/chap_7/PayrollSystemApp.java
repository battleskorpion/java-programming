/* Case Study 7.1: PayrollSystemApp.java
1. Request employee name, type, pay rate, and hours.
2. Print employee name and pay.
3. Repeat until the name is blank.*/
package chap_7; 
import java.util.Scanner;

public class PayrollSystemApp {

   public static void main(String [] args) {
      Scanner reader = new Scanner(System.in);
      Employee emp;    // employee
      String name;     //   name     
      int    type;     //   type 
      double rate;     //   hourly pay rate
      int    hours;    //   hours worked    
      String prompt;   // user prompt;
      
      while (true){
         // Get the name and break if blank
         System.out.println("Enter employee data");
         System.out.print("  Name (or blank to quit): ");
         name = reader.nextLine();
         name = name.trim(); // Trim off leading and trailing spaces
         if (name.length() == 0) break;
         emp = new Employee();
         emp.setName(name);
         
         // Get the type until valid
         while (true){
            prompt = "  Type (" + emp.getTypeRules() + "): ";
            System.out.print(prompt);
            type = reader.nextInt();
            if (emp.setType(type)) break;
         }
         
         // Get the hourly pay rate until valid
         while (true){
            prompt = "  Hourly rate (" + emp.getRateRules() + "): ";
            System.out.print(prompt);
            rate = reader.nextDouble();
            if (emp.setRate(rate)) break;
         }
            
         // Get the hours worked until valid
         //   To illustrate the possibilities we compress this code 
         //   into a single hard-to-read statement. 
         System.out.print("Hours worked (" + 
                          emp.getHoursRules() + "): ");
         while (!emp.setHours(reader.nextInt()))
                System.out.print("Hours worked (" + 
                                 emp.getHoursRules() + "): ");

         // Consume the trailing newline
         reader.nextLine();

        // Print the name and pay
        System.out.println("  The weekly pay for " + emp.getName() + 
                           " is $" + emp.getPay());
      }
   }
}         

// Darian Siembab
// case study 7.1
// program accepts a name of an employee, 
// their employee type (must be 1 or 2), 
// how many hours they worked the week (must be 1-60), 
// and their hourly pay rate (must be 6.75-30.50), 
// and calculates the employees pay for the week. 

