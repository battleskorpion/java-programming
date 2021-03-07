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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.SWT;							// FOR SWT WINDOWS
import org.eclipse.swt.widgets.Display;				// FOR SWT WINDOWS
import org.eclipse.swt.widgets.Shell;				// FOR SWT WINDOWS
import org.eclipse.swt.widgets.Label;				// FOR USE IN SWT WINDOWS
import org.eclipse.swt.widgets.Table;				// FOR USE IN SWT WINDOWS
import org.eclipse.swt.widgets.Text;				// FOR USE IN SWT WINDOWS
import org.eclipse.swt.widgets.TableItem;			// FOR USE IN SWT WINDOWS
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;		// FOR SWT WINDOWS

public class AverageDetailsOfNumbersGUI 
{
	/*********************/
	/* CONSTANTS SECTION */
	/*********************/
	private static final String AVG_LABEL_DEF_TEXT = "Average: "; 
	
	/********************/
	/* VARIABLE SECTION */
	/********************/
	private NumberFormat nf = NumberFormat.getInstance(); 
	
	private Text numberField;						// NUMBER ENTRY FIELD
	private double[] numbers;						// ARRAY OF ENTERED NUMBERS
	private int numbers_count; 						// LOGIC SIZE OF numbers[]
	private double[] numbersGreater; 				// NUMBERS > AVERAGE, WILL ALWAYS BE AT LEAST
													// numbers.length - 1 OR LESS
	private int numbersGreater_count; 				// LOGIC SIZE OF numbersGreater[]
	private Table numbersGreaterTable;				// TABLE DISPLAYING NUMBERS > AVERAGE
	private Table numbersTable;						// TABLE DISPLAYING NUMBERS ENTERED	
	protected Shell shell;							// REPRESENTS A DESKTOP WINDOW

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
		numbers_count = 0; 							// LOGICAL SIZE OF numbers (0 TO START) 
		numbersGreater = new double[nm_nmbrs - 1]; 	// NUMBERS > AVERAGE, WILL ALWAYS BE AT LEAST 
													// numbers.length - 1 OR LESS
		numbersGreater_count = 0; 
		nf.setMaximumFractionDigits(4);
	}
	
	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
	public void open()
	/****************************************************************/
	/* PRECONDITION:  GUI INSTANCE NEEDS TO BE DISPLAYED            */
	/* POSTCONDITION: CREATES THE GUI DISPLAY AND OPENS THE DISPLAY	*/
	/****************************************************************/
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

	
	protected void createContents() 
	/*****************************************************************************************/
	/* PRECONDITION:  CONTENTS NEED TO BE ADDED TO THE WINDOW SO THE WINDOW CAN BE DISPLAYED */
	/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW		  								 */
	/*****************************************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Label lblAverage; 							// AVERAGE OF NUMBERS LABEL
		Label lblNumbersAverage; 					// AVERAGE OF NUMBERS OUTPUT 
		Label lblNumbers;							// LIST OF ENTERED NUMBERS LABEL
		Label lblxMax;								// MAX AMT. OF ENTERABLE NUMBERS LABEL
		Label lblInputNumber;						// NUMBER ENTRY LABEL	 
		
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
		/**************************************************************************/
		/* KEY LISTENER TO PROCESS INPUT AND UPDATE SCREEN WHEN ENTER KEY PRESSED */
		/**************************************************************************/
		{ 
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
		    		
		    		/****************************/
		    		/* RESET NUMBER ENTRY FIELD */
		    		/****************************/
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
		btnContinue.addSelectionListener(new SelectionAdapter()  
		/*******************************************************************/
		/* SELECTION LISTENER TO CONTINUE WHEN CONTINUE BUTTON IS SELECTED */
		/*******************************************************************/
		{
			public void widgetSelected(SelectionEvent event) 
			/*********************************************************/
			/* PRECONDITION:  USER HAS PRESSED CONTINUE BUTTON       */
			/* POSTCONDITION: SHELL IS DISPOSED/GUI WINDOW IS CLOSED */
			/*********************************************************/
			{
				shell.dispose(); 	
			}
		});

	}
	
	
	private double calcAverage(double[] nmbrs, int nmbrs_ct) 
	/**************************************************************************/
	/* PRECONDITION:  NEW AVERAGE NEEDS TO BE CALCULATED FROM LIST OF NUMBERS */
	/* POSTCONDITION: CALCULATES AVERAGE AND RETURNS RESULT 				  */
	/**************************************************************************/
	{
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
	
	private void updateAverage(double[] nmbrs, int nmbrs_ct, 
			double[] nmbrsGrtr, int nmbrsGrtr_ct, Label avg_lbl, Table nmbrsGrtrTbl)  
	/*****************************************************************************/
	/* PRECONDITION:  AVERAGE AND NUMBERS > AVERAGE NEED TO BE UPDATED 			 */
	/* POSTCONDITION: CALCULATES AVERAGE, NUMBERS > AVERAGE, AND UPDATES DISPLAY */
	/*****************************************************************************/
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
			/********************************************************/
			/* IF THIS NUMBER IN nmbrs IS GREATER THAN THE AVERAGE, */
			/* ADD IT TO THE NUMBERS > AVERAGE ARRAY 				*/
			/********************************************************/
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
	/***********************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ADDITIONAL VALUE */
	/* POSTCONDITION: ADDS A VALUE TO THE TABLE 						   */
	/***********************************************************************/
	{
		/*********************/
		/* ADD ITEM TO TABLE */
		/*********************/
		TableItem item = new TableItem(tbl, SWT.NULL); 
		item.setText(nf.format(nmbr));
	}
	
	private void updateTable(Table tbl, double nmbrs[], double nmbrs_ct) 
	/*****************************************************************************/
	/* PRECONDITION:  A TABLE NEEDS TO BE UPDATED WITH AN ARRAY OF NEW VALUES 	 */
	/* POSTCONDITION: CLEARS PREVIOUS TABLE AND ADDS NEW VALUES TO TABLE		 */
	/*****************************************************************************/
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
