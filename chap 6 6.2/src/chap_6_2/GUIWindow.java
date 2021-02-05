// Example 6.2: Loading an image from a file

package chap_6_2; 

import javax.swing.*;
import java.awt.*;

public class GUIWindow{

   public static void main(String[] args){
      JFrame theGUI = new JFrame();
      theGUI.setTitle("GUI Program");
      theGUI.setSize(300, 300);
      theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ImageIcon image = new ImageIcon("smokey.jpg");
      ColorPanel panel = new ColorPanel(Color.black, image);
      Container pane = theGUI.getContentPane();
      pane.add(panel);
      theGUI.setVisible(true);
   }
}

// Darian Siembab
// 6.2 
// Program uses name of an image to display the image 
// in a panel with a black background.
// (the image used in this program is a cat). 
