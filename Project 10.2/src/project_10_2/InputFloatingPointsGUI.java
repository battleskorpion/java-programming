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
	private JPanel rootPanel;  								// STORES ALL PANELS
	private JPanel inputPanelCard; 							// INPUT WINDOW PANELS
	private JPanel outputPanelCard; 						// OUTPUT WINDOW PANELS

			
	private double[] numbers; 
	private String current_window; 
	private Thread calling_thread; 		// THREAD WHICH INSTANTIATED THE GUI 
	private Thread this_thread; 		// THREAD EXECUTING THIS CLASS
	
	// CONSTRUCTOR 
	public InputFloatingPointsGUI(double[] nmbrs, Thread cllng_thrd) 
	{
		rootPanel = new JPanel(new CardLayout());
		inputPanelCard = new JPanel();
		outputPanelCard = new JPanel(); 
		rootPanel.add(inputPanelCard, "Input Panel"); 
		rootPanel.add(outputPanelCard, "Output Panel"); 
		//CardLayout cards = (CardLayout)  rootPanel.getLayout();
		//cards.show(rootPanel, "Input Panel");
		
		this_thread = Thread.currentThread(); 
		calling_thread = cllng_thrd; 
		setupArrays(nmbrs.length); 
		setupActionListeners(); 	 
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
	
	public double[] getNumbers() 
	{
		return numbers; 
	}
	
	public Thread getThread()
	{
		return this_thread; 
	}
	
	public void run() 
	{
		setupInputWindow(numbers.length); 
		current_window = "Input"; 
		setVisible(true); 
	}
	
	public void setAverageText(double avg) 
	{
		averageText.setText(Double.toString(avg));
	}
	
	public void setNumbersGreaterText(double[] nms_grtr)
	{
		for (int i = 0; i < nms_grtr.length; i++) 
		{
			numbersGreaterText[i].setText(Double.toString(nms_grtr[i]));
		}
	}
	
	private void setupActionListeners() 
	{
		// ATTACH LISTENERS TO BUTTONS
		nextButton.addActionListener(new nextButtonPressedListener());
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
			numbersFields[i] = new JTextField(""); 
			numbersText[i] = new JTextArea(""); 
			numbersGreaterText[i] = new JTextArea(""); 
		}
	}

	private void setupInputWindow(int nm_nmbrs) 
	{
		// SET UP PANELS AND ADD TO WINDOW 
		JPanel northPanel = new JPanel(); 
		JPanel centerPanel = new JPanel (new GridLayout (nm_nmbrs, 2, 10, 5)); 
		JPanel southPanel = new JPanel(); 
		
		Container container = getContentPane();
		container.add(rootPanel); 
		
		//rootPanel.add(northPanel,BorderLayout.NORTH);
		//rootPanel.add(centerPanel,BorderLayout.CENTER);
		//rootPanel.add(southPanel,BorderLayout.SOUTH);
				
		// ADD CONTENTS TO PANELS 
		// NORTH PANEL 
		northPanel.add(numbersLabel); 
		inputPanelCard.add(northPanel, BorderLayout.NORTH); 
		
		// CENTER PANEL
		for (int i = 0; i < nm_nmbrs; i++) {
			centerPanel.add(numbersLabels[i]); 
			centerPanel.add(numbersFields[i]); 
		}
		inputPanelCard.add(centerPanel, BorderLayout.CENTER); 
		
		// SOUTH PANEL
		southPanel.add(nextButton); 
		southPanel.add(new JLabel("")); 					// EMPTY CELL
		inputPanelCard.add(southPanel, BorderLayout.SOUTH); 
		
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
		
		//getContentPane().removeAll();
		Container container = getContentPane(); 
		//getContentPane().list(); 
		container.add(rootPanel);
		//container.add(centerPanel,BorderLayout.CENTER);
		//container.add(southPanel,BorderLayout.SOUTH);
		//getContentPane().list(); 
		
		// ADD CONTENTS TO PANELS 
		// NORTH PANEL 
		northPanel.add(numbersLabel); 
		northPanel.add(new JLabel(""));						// EMPTY CELL
		for (int i = 0; i < nm_nmbrs; i++) {
			northPanel.add(numbersLabels[i]); 
			northPanel.add(numbersText[i]); 
		}
		outputPanelCard.add(northPanel, BorderLayout.NORTH); 
				
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
		outputPanelCard.add(centerPanel, BorderLayout.CENTER);
				
		// SOUTH PANEL
		southPanel.add(nextButton); 
		//southPanel.add(new JTextArea("test")); 
		outputPanelCard.add(southPanel, BorderLayout.SOUTH);
		
		// SET WINDOW ATTRIBUTES
		setTitle("Information Output"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		//getContentPane().list(); 
		pack(); 	// RESIZES WINDOW TO SIZE NECESSARY 
	}
	
	/******************************************************************************************************/
	/*										 ACTION LISTENER SECTION									  */
	/******************************************************************************************************/
	
	private class nextButtonPressedListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			CardLayout cards = (CardLayout)  rootPanel.getLayout();
			
			switch (current_window.toLowerCase())
			{
			case "input" : 
				getInputFromScreen(); 
				//setVisible(false); 
				//dispose(); 
				
				// WAKE UP THREAD WHERE PUBLIC CLASS WAS INSTANTIATED 
				synchronized (calling_thread)
				{
					calling_thread.notify(); 
				}
				// SLEEP THIS THREAD 
				synchronized (this_thread) 
				{
					try {
						this_thread.wait();
					} 
					catch (InterruptedException e1) {
						e1.printStackTrace();
					} 
				}
			
				setupOutputWindow(numbers.length);
				//System.out.println(cards.toString());
				cards.show(rootPanel,"Output Panel");
				//setVisible(true); 
				
				// testing cause program 
				System.out.println("(output) a greater number: " + numbersGreaterText[0].getText()); 
				try {
					Thread.sleep(5000); 
				} 
				catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				//cards.show(rootPanel,"Output Panel");
				System.out.println("f1"); 
				
			case "output" : 
				setVisible(false); 
				//dispose(); 
				System.out.println("f2"); 
				
				synchronized (calling_thread)
				{
					calling_thread.notify(); 
				}
				
				//System.exit(0); 
			}
				
		}
	}
	
}


