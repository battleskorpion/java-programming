// Example 6.3: Displays a circle and a filed circle

package chap_6_3; 

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ColorPanel extends JPanel{

   private Circle c1, c2;
	
   public ColorPanel(Color backColor){
      setBackground(backColor);
      c1 = new Circle(200, 100, 25, Color.red);
      c2 = new Circle(100, 100, 50, Color.blue);   
   }
	
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      c1.fill(g);
      c2.draw(g);      
   }
}