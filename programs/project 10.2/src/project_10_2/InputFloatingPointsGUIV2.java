package project_10_2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @see https://docs.oracle.com/javase/tutorial/displayCode.html?
 * code=https://docs.oracle.com/javase/tutorial/uiswing/examples/
 * layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java
 * 
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
	private JTextArea[] numbersText; 
	private JTextArea[] numbersGreaterText; 				// NUMBERS > AVERAGE NUMBER TEXT AREA 
	//private JPanel rootPanel;  								// STORES ALL PANELS	// replaced by cards
	int nm_nmbrs = 10; 
	
	private double[] numbers; 
	private String current_panel; 
	private Thread calling_thread; 		// THREAD WHICH INSTANTIATED THE GUI 
	private Thread this_thread; 		// THREAD EXECUTING THIS CLASS
	
	static final private String INPUT_PANEL_NAME = "Input Panel"; 
	static final private String OUTPUT_PANEL_NAME = "Output Panel";
	
	// CONSTRUCTOR 
	public InputFloatingPointsGUIV2(double[] nmbrs, Thread cllng_thrd) 
	{
		//rootPanel.add(inputPanelCard, "Input Panel"); 
		//rootPanel.add(outputPanelCard, "Output Panel"); 
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
	
	public void addComponentToPane() {
		Container container = getContentPane(); 	// "pane"
        // NORTH PANEL
		JPanel northPanel = new JPanel();
		northPanel.add(numbersLabel); 
		// NEXT PANEL (SOUTH) 
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
	//	JPanel northOutputPanel = new JPanel(new GridLayout (2, 2, 10, 5)); 
	//	JPanel centerOutputPanel = new JPanel (new GridLayout (nm_nmbrs + 2, 2, 10, 5)); 
	//	JPanel southOutputPanel = new JPanel(); 
        //outputCard.add(new JTextField("TextField", 20));	// temp test
		// NORTH PANEL
	//	northOutputPanel.add(numbersLabel); 
	//	northOutputPanel.add(new JLabel(""));						// EMPTY CELL
	//	for (int i = 0; i < nm_nmbrs; i++) {
	//		northOutputPanel.add(numbersLabels[i]); 
	//		northOutputPanel.add(numbersText[i]); 
	//	}
		// CENTER PANEL
	//	for (int i = 0; i < nm_nmbrs; i++) {
	//		centerOutputPanel.add(averageLabel); 
	//		centerOutputPanel.add(averageText); 
	//		centerOutputPanel.add(numbersGreaterLabel); 
	//		centerOutputPanel.add(new JLabel("")); 				// EMPTY CELL
	//		for (int j = 0; j < nm_nmbrs; j++) 
	//		{
	//			centerOutputPanel.add(new JLabel("")); 			// EMPTY CELL
	//			centerOutputPanel.add(numbersGreaterText[j]); 
	//		}
	//	}
	//	// SOUTH PANEL
	//	southOutputPanel.add(nextButton); 
	//	// ADD OUTPUT PANELS TO OUTPUT CARD
	//	outputCard.add(northOutputPanel, BorderLayout.PAGE_START);
	//	outputCard.add(centerOutputPanel, BorderLayout.CENTER);
	//	outputCard.add(southOutputPanel, BorderLayout.PAGE_END);
		
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
	
	/******************************************************************************************************/
	/*										 ACTION LISTENER SECTION									  */
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
		for (int i = 0; i < nms_grtr.length; i++) 
		{
			numbersGreaterText[i].setText(Double.toString(nms_grtr[i]));
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
			
			//	setupOutputWindow(numbers.length); // not need? 
				//System.out.println(cards.toString());
				cardsLayout.show(cards, OUTPUT_PANEL_NAME);
				//setVisible(true); 
				
				// testing cause program 
				System.out.println("(output) a greater number: " + numbersGreaterText[0].getText()); 
				//try {
				//	Thread.sleep(5000); 
				//} 
				//catch (InterruptedException e1) {
				//	e1.printStackTrace();
				//}
				//cards.show(rootPanel,"Output Panel");
				System.out.println("f1"); 
				break; 
				
			case OUTPUT_PANEL_NAME : 
				setVisible(false); 
				//dispose(); 
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
