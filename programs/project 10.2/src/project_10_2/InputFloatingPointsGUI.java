package project_10_2;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

@SuppressWarnings("serial") // Same-version serialization only
public class InputFloatingPointsGUI extends JFrame 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	private JButton nextButton = new JButton("Next"); 
	private JLabel numbersLabel = new JLabel("Numbers: "); 
	private JLabel averageLabel = new JLabel("Average: "); 
	private JLabel numbersGreaterLabel = new JLabel("Numbers > Average: "); 
	private JTextArea averageText = new JTextArea(); 
	private JLabel[] numbersLabels; 
	private JTextField[] numbersFields; 
	private JTextArea[] numbersText; 
	private JTextArea[] numbersGreaterText; 				// NUMBERS > AVERAGE NUMBER TEXT AREA 
	
	public double[] numbers; 
	
	// CONSTRUCTOR 
	public InputFloatingPointsGUI(double[] nmbrs) 
	{
		setupArrays(nmbrs.length); 
		setupInputWindow(nmbrs.length); 
		setupOutputWindow(nmbrs.length); 
	}
	
	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
	private void getInputFromScreen()
	{
		for (int i = 0; i < numbers.length; i++) 
		{
			numbers[i] = Double.parseDouble(numbersFields[i].getText()); 
		}
	}

	private void setupInputWindow(int nm_nmbrs) 
	{
		// SET UP PANELS AND ADD TO WINDOW 
		JPanel northPanel = new JPanel(); 
		JPanel centerPanel = new JPanel (new GridLayout (nm_nmbrs, 2, 10, 5)); 
		JPanel southPanel = new JPanel(); 
				
		Container container = getContentPane(); 
		container.add(centerPanel,BorderLayout.CENTER);
		container.add(southPanel,BorderLayout.SOUTH);
				
		// ADD CONTENTS TO PANELS 
		// NORTH PANEL 
		northPanel.add(numbersLabel); 
		
		// CENTER PANEL
		for (int i = 0; i < nm_nmbrs; i++) {
			centerPanel.add(numbersLabels[i]); 
			centerPanel.add(numbersFields[i]); 
		}
		
		// SOUTH PANEL
		southPanel.add(nextButton); 
		southPanel.add(new JLabel("")); 					// EMPTY CELL
		
		// ATTACH LISTENERS TO BUTTONS
		nextButton.addActionListener(new inputNextButtonPressedListener());
		
		// SET WINDOW ATTRIBUTES
		setTitle("Input Numbers"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		pack(); 	// RESIZES WINDOW TO SIZE NECESSARY 
	}
	
	private void setupOutputWindow(int nm_nmbrs) 
	{
		// SET UP PANELS AND ADD TO WINDOW 
		JPanel northPanel = new JPanel(new GridLayout (nm_nmbrs + 1, 2, 10, 5)); 
		JPanel centerPanel = new JPanel (new GridLayout (nm_nmbrs + 2, 2, 10, 5)); 
		JPanel southPanel = new JPanel(); 
		
		Container container = getContentPane(); 
		container.add(centerPanel,BorderLayout.CENTER);
		container.add(southPanel,BorderLayout.SOUTH);
		
		// ADD CONTENTS TO PANELS 
		// NORTH PANEL 
		northPanel.add(numbersLabel); 
		northPanel.add(new JLabel(""));						// EMPTY CELL
		for (int i = 0; i < nm_nmbrs; i++) {
			northPanel.add(numbersLabels[i]); 
			northPanel.add(numbersText[i]); 
		}
				
		// CENTER PANEL
		for (int i = 0; i < nm_nmbrs; i++) {
			centerPanel.add(averageLabel); 
			centerPanel.add(averageText); 
			centerPanel.add(numbersGreaterLabel); 
			centerPanel.add(new JLabel("")); 				// EMPTY CELL
			for (int j = 0; j < nm_nmbrs; j++) 
			{
				centerPanel.add(new JLabel("")); 			// EMPTY CELL
				centerPanel.add(numbersGreaterText[j]); 
			}
		}
				
		// SOUTH PANEL
		southPanel.add(nextButton); 
	}
	
	private void setupArrays(int nm_nmbrs) 
	{
		// ARRAYS 
		numbersLabels = new JLabel[nm_nmbrs]; 		
		numbersFields = new JTextField[nm_nmbrs]; 
		numbersText = new JTextArea[nm_nmbrs]; 
		numbersGreaterText = new JTextArea[nm_nmbrs]; 
		numbers = new double[nm_nmbrs]; 
		
		
				
		for (int i = 0; i < nm_nmbrs; i++) 
		{
			numbersLabels[i] = new JLabel("Number " + (i + 1) + ": "); 
			numbersFields[i] = new JTextField("0"); 
			numbersText[i] = new JTextArea(""); 
			numbersGreaterText[i] = new JTextArea(""); 
		}
	}
	
	public void setVisible(boolean b) 
	{
		setVisible(b); 
	}
	
	/******************************************************************************************************/
	/*										 PRIVATE CLASS SECTION										  */
	/******************************************************************************************************/
	
	private class inputNextButtonPressedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) 
		{
			getInputFromScreen(); 
			dispose(); 
			//Window[] windows = Window.getWindows();
	        //for (Window window : windows) {
	        //	if (window instanceof JDialog && window.isActive()) {
	        //		getInputFromScreen(); 
	        //        window.dispose();
	        //    }
			//}
		}
	}
	
}


