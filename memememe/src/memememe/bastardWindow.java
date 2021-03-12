package memememe;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

public class bastardWindow {

	protected Shell shell;
	private Text enterField;
	private Table table;
	private Label lblNewLabel;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private Label lblNewLabel_3;
	private Label lblNewLabel_4;
	private Label lblNewLabel_5;
	private Label lblNewLabel_6;
	private Label lblNewLabel_7;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			bastardWindow window = new bastardWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		enterField = new Text(shell, SWT.BORDER);
		enterField.setBounds(63, 39, 76, 21);
		enterField.addKeyListener(new KeyListener()
				/**************************************************************************/
				/* KEY LISTENER TO PROCESS INPUT AND UPDATE SCREEN WHEN ENTER KEY PRESSED */
				/**************************************************************************/
				{ 
					public int numInputs = 0; 
					public int numEntries = 8; 
					
					public void keyPressed(KeyEvent e) 
					/***************************************************************/
					/* PRECONDITION:  A KEY HAS BEEN PRESSED					   */
					/* POSTCONDITION: NOTHING (ABSTRACT METHOD HAS TO BE DECLARED) */
					/***************************************************************/
					{
						// DO NOTHING  
					}
					
				    public void keyReleased(KeyEvent e) 
				    /********************************************************************************/
					/* PRECONDITION:  A KEY HAS BEEN RELEASED					   				    */
					/* POSTCONDITION: IF KEY PRESSED WAS enter (CARRAIGE RETURN), TRY TO ADD NUMBER */ 
					/*  			  WHICH WAS ENTERED AND UPDATE STATISTICS					    */
					/********************************************************************************/
				    {  
				    	/************************/
				    	/* IF ENTER KEY PRESSED */
				    	/************************/
				    	if (e.keyCode == SWT.CR) 
				    	{

				    		if (numInputs < numEntries) {
				    			/**********************************************************************/
				    			/* TRY TO READ IN A NUMBER FROM THE ENTER FIELD AND UPDATE STATISTICS */
				    			/**********************************************************************/
				    			try 
				    			{
				    				
				    			}
				    			/**********************************************/
				    			/* CATCH ANY EXCEPTION, DISPLAY ERROR MESSAGE */
				    			/**********************************************/
				    			catch (Exception exc) 
				    			{
				    				JOptionPane.showMessageDialog(null, "Improper numerical input.");
				    			}
				    			/****************************/
				    			/* RESET NUMBER ENTRY FIELD */
				    			/****************************/
				    			enterField.setText(""); 
				    		}
				    	}
				    	else {
				    		// print error; 
				    		//JOptionPane.showMessageDialog(null, "All numbers inputted");
				    		
				    		shell.dispose(); 	// all entered, now done. 
				    	}
				    }  
				});
		
		Label lblEnter = new Label(shell, SWT.NONE);
		lblEnter.setBounds(10, 42, 55, 15);
		lblEnter.setText("Enter: ");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(290, 39, 85, 154);
		table.setLinesVisible(true);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(208, 39, 55, 12);
		lblNewLabel.setText("New Label");
		
		lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(208, 60, 55, 12);
		lblNewLabel_1.setText("New Label");
		
		lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(208, 78, 55, 12);
		lblNewLabel_2.setText("New Label");
		
		lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(208, 96, 55, 12);
		lblNewLabel_3.setText("New Label");
		
		lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setText("New Label");
		lblNewLabel_4.setBounds(208, 114, 55, 12);
		
		lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setText("New Label");
		lblNewLabel_5.setBounds(208, 132, 55, 12);
		
		lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setText("New Label");
		lblNewLabel_6.setBounds(208, 150, 55, 12);
		
		lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setText("New Label");
		lblNewLabel_7.setBounds(208, 168, 55, 12);

	}
}
