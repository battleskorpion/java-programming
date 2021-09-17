package pong;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.awt.Graphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.wb.swt.SWTResourceManager;

public class PongBoard implements KeyListener {	// implements Runnable

	protected Shell shell;
	private int KEY_MOVE_UP = SWT.ARROW_UP; 
	private int KEY_MOVE_DOWN = SWT.ARROW_DOWN; 
	private final int WIDTH = 900; 
	private final int HEIGHT = 750; 
	//private Thread thread; 

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		/********************/
		/* VARIABLE SECTION */
		/********************/
		PongBoard window = new PongBoard(); 
		
		/*******************/
		/* OPEN GUI WINDOW */
		/*******************/
		try {
			window.open(); 
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

	/********************************************/
	/* 				METHOD SECTION 				*/ 
	/********************************************/ 
	public void open()
	/****************************************************************/
	/* PRECONDITION:  GUI INSTANCE NEEDS TO BE DISPLAYED            */
	/* POSTCONDITION: CREATES THE GUI DISPLAY AND OPENS THE DISPLAY	*/
	/****************************************************************/
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Display display = Display.getDefault();			// MANAGES THE CONNECTION BETWEEN SWT 
														// AND THE UNsDERLYING OPERATING SYSTEM 
		
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
		shell.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shell.layout();
		
		/*********************************************************************************/
		/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
		/*********************************************************************************/
		while (!shell.isDisposed()) 
		{
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}
	
	protected void createContents()
	/****************************************************************/
	/* PRECONDITION:  CONTENTS OF WINDOW NEED TO BE CREATED         */
	/* POSTCONDITION: ADDS CONTENT TO WINDOW 						*/
	/****************************************************************/
	{
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		shell.setSize(WIDTH, HEIGHT);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		shell.addKeyListener(this);	// ?? 

	}
	
	public void paint(Graphics g) {
		//g.setColor(Color.black); 
		//g.fillRect(0, 0, WIDTH, HEIGHT);
	}
	
	public void update(Graphics g) {
		paint(g); 
	}
	
	public void run() {
		for(;;) {
			
			
			
			//repaint(); 
			try {
				Thread.sleep(10);	// ?? 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.keyCode == KEY_MOVE_UP) {
			
		}
		else if(e.keyCode == KEY_MOVE_DOWN) {
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//@Override
	//public void keyTyped(KeyEvent e) {
	//	// TODO Auto-generated method stub
	//	
	//}
}
