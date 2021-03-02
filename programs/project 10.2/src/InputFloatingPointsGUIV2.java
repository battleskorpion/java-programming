package project_10_2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @see https://docs.oracle.com/javase/tutorial/displayCode.html?
 * code=https://docs.oracle.com/javase/tutorial/uiswing/examples/
 * layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java
*/
@SuppressWarnings("serial")
public class InputFloatingPointsGUIV2 extends JFrame {
	JPanel cards; // a panel that uses CardLayout
	private JButton nextButton = new JButton("Next"); 
	private JLabel numbersLabel = new JLabel("Numbers: "); 
	private JLabel averageLabel = new JLabel("Average: "); 
	private JLabel numbersGreaterLabel = new JLabel("Numbers > Average: "); 
	private JTextArea averageText = new JTextArea(); 
	private JLabel[] numbersLabels; 
	private JTextField[] numbersFields; 
	private JLabel[] numbersText; 
	private JTextArea[] numbersGreaterText; 				// NUMBERS > AVERAGE NUMBER TEXT AREA 
	//private JPanel rootPanel;  								// STORES ALL PANELS	// replaced by cards
	int nm_nmbrs = 10; 
	
	private double[] numbers; 
	private String current_panel; 
	private Thread calling_thread; 		// THREAD WHICH INSTANTIATED THE GUI 
	private Thread this_thread; 		// THREAD EXECUTING THIS CLASS
	
	static final private String INPUT_PANEL_NAME = "Input Panel"; 
	static final private String OUTPUT_PANEL_NAME = "Outpu8t Panel";
	
	// CONSTRUCTOR 
	public InputFloatingPointsGUIV2(double[] nmbrs, Thread cllng_thrd) 
	{
		this_thread = Thread.currentThread(); 
		calling_thread = cllng_thrd; 
		setupArrays(nmbrs.length); 
		setupActionListeners(); 	 
		
		// SET SPECIAL PROPERTIES
		averageText.setEditable(false);
	}
		
	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
	public void addComponentToPane() {
		Container container = getContentPane(); 	// "pane"
        // NORTH PANEL
		JPanel northPanel = new JPanel();
		northPanel.add(numbersLabel); 
		// "NEXT" PANEL (SOUTH PANEL) 
		JPanel nextPanel = new JPanel(); 
		nextPanel.add(nextButton); 
		//nextPanel.add(new JLabel("")); 					// EMPTY CELL
		
        // Create the cards
		/**************/
        /* INPUT CARD */
        /**************/ 
        JPanel inputCard = new JPanel();		// card1
        // SET UP PANELS AND ADD TO WINDOW 
         
		JPanel centerInputPanel = new JPanel (new GridLayout (nm_nmbrs, 2, 10, 5)); 
		//JPanel southInputPanel = new JPanel(); 
		// NORTH PANEL 
		
		// CENTER PANEL
		for (int i = 0; i < nm_nmbrs; i++) {
			centerInputPanel.add(numbersLabels[i]); 
			centerInputPanel.add(numbersFields[i]); 
		}
				
		
		// ADD INPUT PANELS TO INPUT CARD
        inputCard.add(centerInputPanel, BorderLayout.CENTER);
        
        /***************/
        /* OUTPUT CARD */
        /***************/ 
        JPanel outputCard = new JPanel();		// card2
		// SET UP PANELS AND ADD TO WINDOW 
		//JPanel northOutputPanel = new JPanel(new GridLayout(nm_nmbrs + 1, 2, 10, 40)); 
		JPanel centerOutputPanel = new JPanel(new GridLayout ((nm_nmbrs * 2) + 4, 1, 10, 5)); 
		//JPanel southOutputPanel = new JPanel(new GridLayout(nm_nmbrs + 1, 1, 10, 5)); 
		
		for (int i = 0; i < nm_nmbrs; i++) {
			if (i == 0) 
			{
				centerOutputPanel.add(new JLabel("Numbers: ")); 
			}
			else 
			{
				centerOutputPanel.add(new JLabel(""));  
			}
			centerOutputPanel.add(numbersText[i]); 
		}
		
		// AVERAGE
		centerOutputPanel.add(averageLabel); 
		centerOutputPanel.add(averageText); 
		
		// NUMBERS > AVERAGE
		for (int i = 0; i < nm_nmbrs; i++) {
			if (i == 0) 
			{
				centerOutputPanel.add(numbersGreaterLabel); 
			}
			else 
			{
				centerOutputPanel.add(new JLabel(""));  
			}
			//southOutputPanel.add(new JLabel(""));       				// EMPTY CELL
			centerOutputPanel.add(numbersGreaterText[i]); 
		}
		
		// ADD OUTPUT PANELS TO OUTPUT CARD
		//outputCard.add(northOutputPanel, BorderLayout.NORTH);		// should be fine
		outputCard.add(centerOutputPanel, BorderLayout.CENTER);
		//outputCard.add(southOutputPanel, BorderLayout.SOUTH);
		
        // Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(inputCard, INPUT_PANEL_NAME);
        cards.add(outputCard, OUTPUT_PANEL_NAME);
        
        container.add(northPanel, BorderLayout.PAGE_START); 
        container.add(cards, BorderLayout.CENTER);
        container.add(nextPanel, BorderLayout.PAGE_END); 
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
		numbersText = new JLabel[nm_nmbrs]; 
		numbersGreaterText = new JTextArea[nm_nmbrs]; 
		numbers = new double[nm_nmbrs]; 
			
		for (int i = 0; i < nm_nmbrs; i++) 
		{
			numbersLabels[i] = new JLabel("Number " + (i + 1) + ": "); 
			numbersFields[i] = new JTextField(""); 
			numbersText[i] = new JLabel(""); 
			numbersGreaterText[i] = new JTextArea("");
			
			// SET SPECIAL PROPERTIES
			numbersGreaterText[i].setEditable(false); 
		}
	}
	
	/******************************************************************************************************/
	/*										 ACTION LISTENER SECTION									  */
	/******************************************************************************************************/
	
	private void getInputFromScreen()
	{
		for (int i = 0; i < numbers.length; i++) 
		{
			numbers[i] = Double.parseDouble(numbersFields[i].getText()); 
			numbersText[i].setText(numbersFields[i].getText()); 
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
		addComponentToPane(); 
		pack(); 
		current_panel = INPUT_PANEL_NAME; 
		setVisible(true); 
	}
	
	public void setAverageText(double avg) 
	{
		averageText.setText(Double.toString(avg));
	}
	
	public void setNumbersGreaterText(double[] nms_grtr)
	{
		if (nms_grtr.length == 0) {
			numbersGreaterText[0].setText("None");
		}
		else {
			for (int i = 0; i < nms_grtr.length; i++) 
			{
				numbersGreaterText[i].setText(Double.toString(nms_grtr[i]));
			}
		}
	}
	
	private class nextButtonPressedListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			CardLayout cardsLayout = (CardLayout)  cards.getLayout();
			
			switch (current_panel)
			{
			case INPUT_PANEL_NAME : 
				getInputFromScreen(); 
				System.out.println("f0"); 
				
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
			
				current_panel = OUTPUT_PANEL_NAME; 
				pack(); 
				cardsLayout.show(cards, OUTPUT_PANEL_NAME);
				//setVisible(true); 
				
				// testing cause program 
				System.out.println("(output) a greater number: " + numbersGreaterText[0].getText()); 
				System.out.println("f1"); 
				break; 
				
			case OUTPUT_PANEL_NAME : 
				setVisible(false); 
				dispose(); 						// WINDOW NO LONGER NECESSARY
				System.out.println("f2"); 
				
				synchronized (calling_thread)
				{
					calling_thread.notify(); 
				}
				
				//System.exit(0); 
				break; 
				
			default : 
				System.out.println("error error error rrrrr"); 
				break; 
			}
				
		}
	}
}
