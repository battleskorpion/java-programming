package trayprogmulti;

import java.util.*; 
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;

public class TrayProgMulti {
	
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected Shell shell;
	TrayProgServerDaemon serverDaemon;
	
	public static void main(String[] args) {
		
		/********************/
		/* VARIABLE SECTION */
		/********************/
		TrayProgMulti window = new TrayProgMulti();
		
		/*****************************/
		/* METHOD TO OPEN GUI WINDOW */
		/*****************************/
		// TODO: also label try/catch? 
		try 
		{
			window.open();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
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
	{
		shell = new Shell();
		shell.setSize(700, 500);
		shell.setText("program"); 
		shell.setLayout(new GridLayout(2, false));
		
		Button btnStartServer = new Button(shell, SWT.NONE);
		GridData gd_btnStartServer = new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1);
		gd_btnStartServer.widthHint = 336;
		gd_btnStartServer.heightHint = 50;
		btnStartServer.setLayoutData(gd_btnStartServer);
		btnStartServer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Main program starting server daemon"); 
				
				serverDaemon = new TrayProgServerDaemon(); 
			}
		});
		btnStartServer.setText("Start server");
		
		Button btnEndServer = new Button(shell, SWT.NONE);
		GridData gd_btnEndServer = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_btnEndServer.widthHint = 332;
		gd_btnEndServer.heightHint = 67;
		btnEndServer.setLayoutData(gd_btnEndServer);
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
		btnEndServer.setText("End server");
		
		List list = new List(shell, SWT.BORDER);
		GridData gd_list = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_list.heightHint = 160;
		list.setLayoutData(gd_list);
	}
}
