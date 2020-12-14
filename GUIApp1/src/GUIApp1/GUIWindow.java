package GUIApp1;

// Example 2.3: an empty frame

import javax.swing.*;     // Access JFrame

public class GUIWindow{
	
   public static void main(String[] args){
      JFrame theGUI = new JFrame();
      theGUI.setTitle("First GUI Program");
      theGUI.setSize(300, 200);
      theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      theGUI.setVisible(true);
   }
   
}

// Lab 2.3
// Darian Siembab
// Purpose: Example 2.3 GUIWindow 
