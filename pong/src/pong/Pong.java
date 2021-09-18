package pong;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import server.PongServerDaemon;

public class Pong {
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected Shell shell;
	PongServerDaemon serverDaemon;
	
	public static void main(String[] args) {
		/********************/
		/* VARIABLE SECTION */
		/********************/
		Pong window = new Pong(); 
		
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
		shell.setSize(400, 317);
		shell.setText("Pong");
		
		/* START SERVER BUTTON */ 
		Button btnStartServer = new Button(shell, SWT.NONE); 
		btnStartServer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Main program initializing server daemon"); 
				
				serverDaemon = new PongServerDaemon(); 
			}
		});
		btnStartServer.setBounds(10, 10, 142, 98);
		btnStartServer.setText("Start server");
		
		/* END SERVER BUTTON */ 
		Button btnEndServer = new Button(shell, SWT.NONE);
		btnEndServer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("process ending..."); 
				//serverDaemon.terminate(); 
				//try {
					serverDaemon.interrupt();
					//serverDaemon.join();
				//} 
				//catch (InterruptedException e1) {
					// TODO Auto-generated catch block
				//	e1.printStackTrace();
				//}
				System.out.println("process ended."); 
			}
		});
		btnEndServer.setBounds(158, 10, 142, 98);
		btnEndServer.setText("End server");
		
		Button btnSoloMatch = new Button(shell, SWT.NONE);
		btnSoloMatch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PongBoardFrame pongBoardFrame = new PongBoardFrame(); 
				
			}
		});
		btnSoloMatch.setBounds(10, 114, 142, 98);
		btnSoloMatch.setText("Start solo match");
	}
}
