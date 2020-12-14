package GUIApp3;

// Example 2.5: a frame with 5 colored panels
// that show the border layout

import javax.swing.*;    // For JFrame and JPanel
import java.awt.*;       // For Color and Container
public class GUIWindow{
   public static void main(String[] args){   
      JFrame theGUI = new JFrame();
      theGUI.setTitle("Third GUI Program");
      theGUI.setSize(300, 200);
      theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel northPanel = new JPanel(); 
      northPanel.setBackground(Color.red);
      JPanel eastPanel = new JPanel(); 
      eastPanel.setBackground(Color.blue);
      JPanel southPanel = new JPanel(); 
      southPanel.setBackground(Color.red);
      JPanel westPanel = new JPanel(); 
      westPanel.setBackground(Color.blue);
      JPanel centerPanel = new JPanel(); 
      centerPanel.setBackground(Color.white);
      Container pane = theGUI.getContentPane();
      pane.add(northPanel, BorderLayout.NORTH);
      pane.add(eastPanel, BorderLayout.EAST);
      pane.add(southPanel, BorderLayout.SOUTH);
      pane.add(westPanel, BorderLayout.WEST);
      pane.add(centerPanel, BorderLayout.CENTER);
      theGUI.setVisible(true);
   }
}

// Lab 2.5
// Darian Siembab
// Purpose: Example 2.5 GUIWindow