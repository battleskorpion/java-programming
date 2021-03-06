
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
import org.eclipse.wb.swt.SWTResourceManager;		// FOR SWT WINDOWS

public class AverageDetailsOfNumbersGUI 
{
	public static final String AVG_LABEL_DEF_TEXT = "Average: "; 
	protected Shell shell;
	private Table numbersTable;
	private Text numberField;
	private double[] numbers;						// array of entered numbers
	private int numbers_count; 						// logic size of numbers[]
	private double[] numbersGreater; 				// numbers > average, will always be at least numbers.length - 1 or less
	private int numbersGreater_count; 				// logic size of numbersGreater[]
	private double average; 						// average of each number in numbers 
	private Table numbersGreaterTable;
	private NumberFormat nf = NumberFormat.getInstance(); 

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
		numbersGreater = new double[nm_nmbrs - 1]; 	// numbers > average, will always be at least numbers.length - 1 or less
		numbersGreater_count = 0; 
		nf.setMaximumFractionDigits(4);
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
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

	/**
	 * Open the window.
	 */
	public void open()
	{
		Display display = Display.getDefault();
		createContents();
		shell.open();
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

	/**
	 * Create contents of the window.
	 */
	protected void createContents() 
	{
		shell = new Shell();
		shell.setSize(406, 304);
		shell.setText("Numbers Details");
		
		Label lblAverage = new Label(shell, SWT.NONE);
		lblAverage.setBounds(241, 10, 141, 15);
		lblAverage.setText(AVG_LABEL_DEF_TEXT);
		
		Label lblNumbersAverage = new Label(shell, SWT.NONE);
		lblNumbersAverage.setBounds(241, 31, 141, 15);
		lblNumbersAverage.setText("Numbers > Average: ");
		
		Label lblNumbers = new Label(shell, SWT.NONE);
		lblNumbers.setBounds(131, 10, 73, 15);
		lblNumbers.setText("Numbers: ");
		
		numbersTable = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		numbersTable.setBounds(131, 31, 93, 212);
		numbersTable.setLinesVisible(true);
		
		numbersGreaterTable = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		numbersGreaterTable.setLinesVisible(true);
		numbersGreaterTable.setBounds(241, 52, 93, 191);
		
		Label lblInputNumber = new Label(shell, SWT.NONE);
		lblInputNumber.setBounds(10, 10, 103, 15);
		lblInputNumber.setText("Input Number: ");
		
		numberField = new Text(shell, SWT.BORDER);
		numberField.setTouchEnabled(true);
		numberField.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		numberField.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		numberField.setBounds(10, 31, 103, 21);
		
		Label lblxMax = new Label(shell, SWT.NONE);
		lblxMax.setBounds(10, 59, 103, 15);
		lblxMax.setText("(" + numbers.length + " numbers max)");	
		numberField.addKeyListener(new KeyListener()
		{ 
			public void keyPressed(KeyEvent e) 
			{
				// DO NOTHING  
			}
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
		    			try 
		    			{
		    				numbers[numbers_count] = Double.parseDouble(numberField.getText());
		    				numbers_count++; 										// Array logic size is now increased by one after adding a number to the array
		    				updateTable(numbersTable, numbers[numbers_count-1]);	// numbers_count - 1 since array counting starts at 0 
		    				average = calcAverage(numbers, numbers_count); 
		    				updateAverage(average, numbers, numbers_count, numbersGreater, numbersGreater_count, lblAverage, numbersGreaterTable); 
		    			}
		    			catch (Exception exc) 
		    			{
		    				JOptionPane.showMessageDialog(null, "Improper numerical input.");
		    			}
		    		}
		    		else 
		    		{
		    			JOptionPane.showMessageDialog(null, "Already entered maximum amount of numbers.");
		    			// print error
		    		}
		    		numberField.setText(""); 
		    	}
		    }  
		});

	}
	
	private double calcAverage(double[] nmbrs, int nmbrs_ct) {
		double avg = 0; 
		for (int i = 0; i < nmbrs_ct; i++) 
		{
			avg += nmbrs[i];  
		}
		return avg /= nmbrs_ct;
	}
	
	// updates average and numbers > average display
	private void updateAverage(double avg, double[] nmbrs, int nmbrs_ct, 
			double[] nmbrsGrtr, int nmbrsGrtr_ct, Label avg_lbl, Table nmbrsGrtrTbl)  
	{
		/**************************/
		/* UPDATE AVERAGE DISPLAY */
		/**************************/
		avg_lbl.setText(AVG_LABEL_DEF_TEXT + nf.format(avg));
		
		/**************************************************/
		/* CALCULATE NUMBERS > AVERAGE AND UPDATE DISPLAY */
		/**************************************************/
		nmbrsGrtr_ct = 0; 						// RESETTING NUMBERS > AVERAGE LOGIC SIZE
		for (double num : nmbrs) 
		{
			if (num > avg) 
			{
				nmbrsGrtr[nmbrsGrtr_ct] = num; 
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
