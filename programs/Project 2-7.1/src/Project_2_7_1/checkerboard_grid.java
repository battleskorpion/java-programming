/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* SEPTEMBER 29, 2020								*/
/* PROGRAM 2										*/
/* PROJECT 2-7										*/
/* THIS PROGRAM WILL DISPLAY A 3X3 GRID COLORED 	*/
/* IN A BLACK-AND-WHITE CHECKERED PATTERN			*/
/****************************************************/ 

package Project_2_7_1;

/******************/
/* IMPORT SECTION */
/******************/
import java.awt.*; 									// FOR ADDING/MODIFYING CERTAIN QUALITIES OF JFRAME/JPANEL
import javax.swing.*;    							// FOR JFRAME AND JPANEL, AND JOPTIONPANE (WINDOWS)

public class checkerboard_grid 
{
	public static void main(String[] args)
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Container pane1; 							// OUTPUT WINDOW PANE
		JPanel panel1 = new JPanel(); 				// PANEL TO BE ADDED TO PANE1
		JPanel panel2 = new JPanel(); 				// PANEL TO BE ADDED TO PANE1
		JPanel panel3 = new JPanel(); 				// PANEL TO BE ADDED TO PANE1
		JPanel panel4 = new JPanel(); 				// PANEL TO BE ADDED TO PANE1
		JPanel panel5 = new JPanel(); 				// PANEL TO BE ADDED TO PANE1
		JPanel panel6 = new JPanel(); 				// PANEL TO BE ADDED TO PANE1
		JPanel panel7 = new JPanel(); 				// PANEL TO BE ADDED TO PANE1
		JPanel panel8 = new JPanel(); 				// PANEL TO BE ADDED TO PANE1
		JPanel panel9 = new JPanel(); 				// PANEL TO BE ADDED TO PANE1
		JFrame window1 = new JFrame(); 				// OUTPUT WINDOW FRAME
		
	    /****************************/
	    /* USER DESCRIPTION SECTION */ 
	    /****************************/
	    JOptionPane.showMessageDialog(null, "This program will display a 3x3 grid "
	    		+ "colored in a black-and-white checkered pattern", 
	    		"Program Description", JOptionPane.PLAIN_MESSAGE);
	    
	    /******************/ 
	    /* OUTPUT SECTION */
	    /******************/ 
	    window1.setTitle("Checkered Grid Program"); 
		window1.setSize (600, 600);
		window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    panel1.setBackground(Color.black);
	    panel2.setBackground(Color.white);
	    panel3.setBackground(Color.black);
	    panel4.setBackground(Color.white);
	    panel5.setBackground(Color.black);		
	    panel6.setBackground(Color.white);
	    panel7.setBackground(Color.black);
	    panel8.setBackground(Color.white);
	    panel9.setBackground(Color.black);	
	    
	    pane1 = window1.getContentPane(); 			// SETS PANE1 AS OUTPUT WINDOW CONTENT PANE
	    pane1.setLayout(new GridLayout (3, 3));		// SETS CONTENT PANE LAYOUT AS 3x3 GRID  
	    pane1.add(panel1); 
	    pane1.add(panel2); 
	    pane1.add(panel3);
	    pane1.add(panel4); 
	    pane1.add(panel5);
	    pane1.add(panel6); 
	    pane1.add(panel7);
	    pane1.add(panel8); 
	    pane1.add(panel9);
	    window1.setVisible(true);
	}
}
