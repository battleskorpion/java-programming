package project_10_2;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;			// for KeyListener
import org.eclipse.swt.events.KeyListener;		// for KeyListener
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class AverageDetailsOfNumbersGUI {

	protected Shell shell;
	private Table table;
	private Text numberField;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AverageDetailsOfNumbersGUI window = new AverageDetailsOfNumbersGUI();
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
		shell.setSize(408, 304);
		shell.setText("SWT Application");
		
		Label lblAverage = new Label(shell, SWT.NONE);
		lblAverage.setBounds(230, 10, 111, 15);
		lblAverage.setText("Average: ");
		
		Label lblNumbersAverage = new Label(shell, SWT.NONE);
		lblNumbersAverage.setBounds(230, 31, 141, 15);
		lblNumbersAverage.setText("Numbers > Average: ");
		
		Label lblNumbers = new Label(shell, SWT.NONE);
		lblNumbers.setBounds(131, 10, 64, 15);
		lblNumbers.setText("Numbers: ");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(131, 31, 85, 191);
		table.setLinesVisible(true);
		
		Label lblInputNumber = new Label(shell, SWT.NONE);
		lblInputNumber.setBounds(10, 10, 103, 15);
		lblInputNumber.setText("Input Number: ");
		
		numberField = new Text(shell, SWT.BORDER);
		numberField.setTouchEnabled(true);
		numberField.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		numberField.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		numberField.setBounds(10, 31, 103, 21);
		numberField.addKeyListener(new KeyListener() { 
			public void keyPressed(KeyEvent e) {
				
			}
		    public void keyReleased(KeyEvent e) {  
		    	if (e.keyCode == SWT.CR) {
		    		Double.parseDouble(numberField.getText()); 
		    	}
		    }  
		});

	}
}
