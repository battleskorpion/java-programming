/****************************************************/ 
/* INFORMATION SECTION 								*/
/* DARIAN SIEMBAB 									*/
/* FEBURARY 11, 2021								*/
/* AverageDetailsOfNumbersGUI.java					*/
/****************************************************/

package project_10_2;

/******************/
/* IMPORT SECTION */
/******************/
import java.text.NumberFormat;						// FOR NUMBER OUTPUT FORMATTING
import javax.swing.JOptionPane;						// FOR SIMPLE SWING WINDOWS
import org.eclipse.swt.events.KeyEvent;				// FOR KEY PRESS DETECTION
import org.eclipse.swt.events.KeyListener;			// FOR KEY PRESS DETECTION
import org.eclipse.swt.SWT;							// FOR SWT WINDOWS
import org.eclipse.swt.widgets.Display;				// FOR SWT WINDOWS
import org.eclipse.swt.widgets.Shell;				// FOR SWT WINDOWS
import org.eclipse.swt.widgets.Label;				// FOR USE IN SWT WINDOWs
import org.eclipse.swt.widgets.Table;				// FOR USE IN SWT WINDOWS
import org.eclipse.swt.widgets.Text;				// FOR USE IN SWT WINDOWS
import org.eclipse.swt.widgets.TableItem;			// FOR USE IN SWT WINDOWS
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;		// FOR SWT WINDOWS

public class AverageDetailsOfNumbersGUI 
{
	public static final String AVG_LABEL_DEF_TEXT = "Average: "; 
	protected Shell shell;
	private Table numbersTable;
	private Text numberField;
	private double[] numbers;						// array of entered numbers
	private int numbers_count; 						// logic size of numbers[]
	private double[] numbersGreater; 				// numbers > average, will always be at least
													// numbers.length - 1 or less
	private int numbersGreater_count; 				// logic size of numbersGreater[]			
	private Table numbersGreaterTable;
	private NumberFormat nf = NumberFormat.getInstance(); 

	public static void main(String[] args) 
	{
		try 
		{
			AverageDetailsOfNumbersGUI window = new AverageDetailsOfNumbersGUI();
			window.open();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/
	public AverageDetailsOfNumbersGUI()
	{
		this(10); 	
	}
	public AverageDetailsOfNumbersGUI(int nm_nmbrs) 
	{
		numbers = new double[nm_nmbrs]; 
		numbers_count = 0; 
		numbersGreater = new double[nm_nmbrs - 1]; 	// numbers > average, will always be at least 
													// numbers.length - 1 or less
		numbersGreater_count = 0; 
		nf.setMaximumFractionDigits(4);
	}
	
	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
	/****************************************************************/
	/* PRECONDITION:  GUI INSTANCE NEEDS TO BE DISPLAYED            */
	/* POSTCONDITION: CREATES THE GUI DISPLAY AND OPENS THE DISPLAY	*/
	/****************************************************************/
	public void open()
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Display display = Display.getDefault();		// MANAGES THEC ONNECTION BETWEEN SWT 
													// AND THE UNDERLYING OPERATING SYSTEM 
		
		/**********************************************/
		/* METHOD TO CREATE CONTENTS OF SHELL/DISPLAY */
		/**********************************************/
		createContents();
		
		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shell.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shell.forceActive();		// SO WINDOW WILL BE FOCUSED WHEN CREATED
		shell.layout();
		while (!shell.isDisposed()) 
		{
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}

	/*****************************************************************************************/
	/* PRECONDITION:  CONTENTS NEED TO BE ADDED TO THE WINDOW SO THE WINDOW CAN BE DISPLAYED */
	/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW		  								 */
	/*****************************************************************************************/
	protected void createContents() 
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Label lblAverage; 							// Average of numbers label
		Label lblNumbersAverage; 					// Average of numbers output 
		Label lblNumbers;							// List of entered numbers label
		Label lblxMax;								// Max amt. of enterable numbers label
		Label lblInputNumber;						// Number entry label	 
		
		/***********************/
		/* CREATE WINDOW SHELL */
		/***********************/
		shell = new Shell();
		shell.setSize(406, 304);
		shell.setText("Numbers Details");
		
		/************************/
		/* CREATE AVERAGE LABEL */
		/************************/
		lblAverage = new Label(shell, SWT.NONE);
		lblAverage.setBounds(241, 10, 141, 15);
		lblAverage.setText(AVG_LABEL_DEF_TEXT);
		
		/*********************************************/
		/* CREATE NUMBERS GREATER THAN AVERAGE LABEL */
		/*********************************************/
		lblNumbersAverage = new Label(shell, SWT.NONE);
		lblNumbersAverage.setBounds(241, 31, 141, 15);
		lblNumbersAverage.setText("Numbers > Average: ");
		
		/*****************************/
		/* CREATE ENTER NUMBER LABEL */
		/*****************************/
		lblNumbers = new Label(shell, SWT.NONE);
		lblNumbers.setBounds(131, 10, 73, 15);
		lblNumbers.setText("Numbers: ");
		
		/**************************************/
		/* CREATE MAX AMOUNT OF NUMBERS LABEL */
		/**************************************/
		lblxMax = new Label(shell, SWT.NONE);
		lblxMax.setBounds(10, 59, 103, 15);
		lblxMax.setText("(" + numbers.length + " numbers max)");	
		
		/*****************************/
		/* CREATE ENTER NUMBER LABEL */
		/*****************************/
		lblInputNumber = new Label(shell, SWT.NONE);
		lblInputNumber.setBounds(10, 10, 103, 15);
		lblInputNumber.setText("Input Number: ");
		
		/********************************/
		/* CREATE NUMBERS ENTERED TABLE */
		/********************************/
		numbersTable = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		numbersTable.setBounds(131, 31, 93, 212);
		numbersTable.setLinesVisible(true);
		
		/*********************************************/
		/* CREATE NUMBERS GREATER THAN AVERAGE TABLE */
		/*********************************************/
		numbersGreaterTable = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		numbersGreaterTable.setLinesVisible(true);
		numbersGreaterTable.setBounds(241, 52, 93, 191);

		/*****************************/
		/* CREATE NUMBER ENTRY FIELD */
		/*****************************/
		numberField = new Text(shell, SWT.BORDER);
		numberField.setTouchEnabled(true);
		numberField.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		numberField.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		numberField.setBounds(10, 31, 103, 21);
		numberField.addKeyListener(new KeyListener()
		{ 
			/***************************************************************/
			/* PRECONDITION:  A KEY HAS BEEN PRESSED					   */
			/* POSTCONDITION: NOTHING (ABSTRACT METHOD HAS TO BE DECLARED) */
			/***************************************************************/
			public void keyPressed(KeyEvent e) 
			{
				// DO NOTHING  
			}
			
			/********************************************************************************/
			/* PRECONDITION:  A KEY HAS BEEN RELEASED					   				    */
			/* POSTCONDITION: IF KEY PRESSED WAS enter (CARRAIGE RETURN), TRY TO ADD NUMBER */ 
			/*  			  WHICH WAS ENTERED AND UPDATE STATISTICS					    */
			/********************************************************************************/
		    public void keyReleased(KeyEvent e) 
		    {  
		    	/************************/
		    	/* IF ENTER KEY PRESSED */
		    	/************************/
		    	if (e.keyCode == SWT.CR) 
		    	{
		    		/*****************************************/
		    		/* IF NUMBERS LOGICAL SIZE < ITS' LENGTH */
		    		/*****************************************/
		    		if (numbers_count < numbers.length) 
		    		{
		    			/**********************************************************************/
		    			/* TRY TO READ IN A NUMBER FROM THE ENTER FIELD AND UPDATE STATISTICS */
		    			/**********************************************************************/
		    			try 
		    			{
		    				numbers[numbers_count] = Double.parseDouble(numberField.getText());
		    				numbers_count++; 										// ARRAY LOGIC SIZE IS NOW INCREASED BY ONE 
		    																		// AFTER ADDING A NUMBER TO THE ARRAY
		    				updateTable(numbersTable, numbers[numbers_count-1]);	// NUMBERS_COUNT - 1 SINCE ARRAY COUNTING STARTS AT 0 
		    				updateAverage(numbers, numbers_count, numbersGreater, numbersGreater_count, lblAverage, numbersGreaterTable); 
		    			}
		    			/**********************************************/
		    			/* CATCH ANY EXCEPTION, DISPLAY ERROR MESSAGE */
		    			/**********************************************/
		    			catch (Exception exc) 
		    			{
		    				JOptionPane.showMessageDialog(null, "Improper numerical input.");
		    			}
		    		}
		    		/******************************/
		    		/* ELSE DISPLAY ERROR MESSAGE */
		    		/******************************/
		    		else 
		    		{
		    			JOptionPane.showMessageDialog(null, "Already entered maximum amount of numbers.");
		    		}
		    		numberField.setText(""); 
		    	}
		    }  
		});
		
		/**************************/
		/* CREATE CONTINUE BUTTON */
		/**************************/
		Button btnContinue = new Button(shell, SWT.NONE);
		btnContinue.setBounds(10, 218, 75, 25);
		btnContinue.setText("Continue");

	}
	
	/**************************************************************************/
	/* PRECONDITION:  NEW AVERAGE NEEDS TO BE CALCULATED FROM LIST OF NUMBERS */
	/* POSTCONDITION: CALCULATES AVERAGE AND RETURNS RESULT 				  */
	/**************************************************************************/
	private double calcAverage(double[] nmbrs, int nmbrs_ct) {
		/*****************/
		/* CALCULATE SUM */
		/*****************/
		double sum = 0; 
		for (int i = 0; i < nmbrs_ct; i++) 
		{
			sum += nmbrs[i];  
		}
		return sum /= nmbrs_ct;		// RETURN SUM / NUMBERS SUMMED TO CALCULATE AVERAGE = AVERAGE
	}
	
	/*****************************************************************************/
	/* PRECONDITION:  AVERAGE AND NUMBERS > AVERAGE NEED TO BE UPDATED 			 */
	/* POSTCONDITION: CALCULATES AVERAGE, NUMBERS > AVERAGE, AND UPDATES DISPLAY */
	/*****************************************************************************/
	private void updateAverage(double[] nmbrs, int nmbrs_ct, 
			double[] nmbrsGrtr, int nmbrsGrtr_ct, Label avg_lbl, Table nmbrsGrtrTbl)  
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		double avg; 		 					// average of each number in numbers
		
		/*******************************/
		/* METHOD TO CALCULATE AVERAGE */
		/*******************************/
		avg = calcAverage(numbers, numbers_count);
		
		/**************************/
		/* UPDATE AVERAGE DISPLAY */
		/**************************/
		avg_lbl.setText(AVG_LABEL_DEF_TEXT + nf.format(avg));
		
		/**************************************************/
		/* CALCULATE NUMBERS > AVERAGE AND UPDATE DISPLAY */
		/**************************************************/
		nmbrsGrtr_ct = 0; 						// RESETTING NUMBERS > AVERAGE LOGIC SIZE
		for (int i = 0; i < nmbrs_ct; i++) {
			if (nmbrs[i] > avg) 
			{
				nmbrsGrtr[nmbrsGrtr_ct] = nmbrs[i]; 
				nmbrsGrtr_ct++; 
			}
		}
		for (int i = 0; i < nmbrsGrtr_ct; i++) {
			updateTable(nmbrsGrtrTbl, nmbrsGrtr, nmbrsGrtr_ct); 
		}
	}
	
	private void updateTable(Table tbl, double nmbr) 
	{
		/*********************/
		/* ADD ITEM TO TABLE */
		/*********************/
		TableItem item = new TableItem(tbl, SWT.NULL); 
		item.setText(nf.format(nmbr));
	}
	
	private void updateTable(Table tbl, double nmbrs[], double nmbrs_ct) 
	{
		/***************/
		/* RESET TABLE */
		/***************/ 
		tbl.clearAll();							
		tbl.setItemCount(0);
		
		/****************/
		/* UPDATE TABLE */
		/****************/ 
		for (int i = 0; i < nmbrs_ct; i++) 
		{
			updateTable(tbl, nmbrs[i]); 
		}
	}
}
