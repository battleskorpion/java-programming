// Example 4.5: CircleARea with dialog I/O

package f; 

import javax.swing.JOptionPane;

public class CircleArea{

   public static void main(String[] args){
      String inputStr = JOptionPane.showInputDialog("Enter the radius",
                                                    "0");
      if (inputStr == null)
         return;
      double radius = Double.parseDouble(inputStr);
      if (radius < 0)
         JOptionPane.showMessageDialog(null, "Error: Radius must be >= 0");
      else{
         double area = Math.PI * Math.pow(radius, 2);
         JOptionPane.showMessageDialog(null, "The area is " + area);
      }
   }
}

// Darian Siembab
// 4.5
// Program prints the area of a circle when given a radius, 
// if radius is < 0 the program displays an error, 
// if the input is null the program ends. 