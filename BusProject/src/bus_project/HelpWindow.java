/********************************************/
2/* INFORMATION SECTION 						*/
/* HelpWindow.java							*/
/* Darian Siembab 							*/
/* 											*/
/* WINDOW CLASS FOR DISPLAYING A STRING		*/
/* OF INFORMATION							*/
/********************************************/ 
package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import org.eclipse.swt.widgets.Display;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Shell;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.widgets.Button;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.SWT;								// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.layout.FormLayout;				// FOR SWT WINDOWS/WIDGETS		
import org.eclipse.swt.layout.FormData;					// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.layout.FormAttachment;			// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.custom.StyledText;				// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.events.SelectionAdapter;			// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.events.SelectionEvent;			// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.wb.swt.SWTResourceManager;			// FOR SWT WINDOWS/WIDGETS	
import org.eclipse.swt.graphics.Point;					// FOR SWT WINDOWS/WIDGETS	

public class HelpWindow extends AbstractBusProgramWindow 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected Shell shlHelpWindow;						// SHELL WHICH REPRESENTS THIS WINDOW
	private String helpWindowText; 

	public HelpWindow (String hlpWndwTxt)
	{
		helpWindowText = hlpWndwTxt; 
	}
	
	/************************************************************************************************/
	/* 										 METHOD SECTION 										*/
	/************************************************************************************************/
	
	/**************************
	 * @wbp.parser.entryPoint *
	 **************************/
	public void open(Shell rootShell)
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
		createContents(rootShell);
		
		/****************************************/
		/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
		/****************************************/
		shlHelpWindow.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlHelpWindow.forceActive();					// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlHelpWindow.layout();
		
		/*******************************/
		/* WHILE SHELL IS NOT DISPOSED */
		/*******************************/
		while (!shlHelpWindow.isDisposed()) 
		{
			/*******************************************/
			/* SLEEP DISPLAY IF THERE IS NOTHING TO DO */
			/*******************************************/
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Shell rootShell) 
	/********************************************************************************/
	/* PRECONDITION:  WINDOW IS TO BE OPENED, ELEMENTS NEED TO BE ADDED TO WINDOW	*/
	/* POSTCONDITION: ADDS ELEMENTS TO WINDOW										*/
	/********************************************************************************/
	{
		/*********/
		/* SHELL */
		/*********/
		shlHelpWindow = new Shell();
		shlHelpWindow.setMinimumSize(new Point(80, 80));
		shlHelpWindow.setSize(400, 275);
		shlHelpWindow.setLayout(new FormLayout());
		
		/***************/
		/* EXIT BUTTON */
		/***************/
		Button btnExit = new Button(shlHelpWindow, SWT.NONE);
		btnExit.setText("Exit");
		
		FormData fd_btnExit = new FormData();
		fd_btnExit.left = new FormAttachment(100, -85);
		fd_btnExit.bottom = new FormAttachment(100, -10);
		fd_btnExit.right = new FormAttachment(100, -10);
		btnExit.setLayoutData(fd_btnExit);
		
		/*********************/
		/* DISPLAY TEXT AREA */
		/*********************/
		StyledText styledText = new StyledText(shlHelpWindow, SWT.WRAP);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText(helpWindowText); 
		
		FormData fd_styledText = new FormData();
		fd_styledText.bottom = new FormAttachment(btnExit, -6);
		fd_styledText.right = new FormAttachment(btnExit, 0, SWT.RIGHT);
		fd_styledText.top = new FormAttachment(0, 10);
		fd_styledText.left = new FormAttachment(0, 10);
		styledText.setLayoutData(fd_styledText);
		
		/***************/
		/* EXIT BUTTON */
		/***************/ 
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			/****************************************************************************************/
			/* PRECONDITION:  SENT WHEN CONTROL IS SELECTED								  			*/
			/* POSTCONDITION: CLOSES THIS WINDOW					 								*/
			/****************************************************************************************/
			{
				closeSubWindow(rootShell, shlHelpWindow); 
			}
		});
	}
}
