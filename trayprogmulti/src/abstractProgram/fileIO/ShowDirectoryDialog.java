/********************************************/
/* INFORMATION SECTION 						*/
/* ShowDirectoryDialog.java					*/
/* Darian Siembab 							*/
/* 											*/
/* WINDOW CLASS FOR SHOWING DIALOG			*/
/* FOR SELECTING A DIRECTORY/FILE			*/
/********************************************/ 

package abstractProgram.fileIO;

/******************/
/* IMPORT SECTION */
/******************/ 
import org.eclipse.swt.*;								// FOR SWT WINDOWS
import org.eclipse.swt.events.*;						// FOR SWT WINDOWS
import org.eclipse.swt.layout.*;						// FOR SWT WINDOWS
import org.eclipse.swt.widgets.*;						// FOR SWT WINDOWS

import abstractProgram.abstractProgram.*;

public class ShowDirectoryDialog extends AbstractProgramWindow
{
	protected Shell shlDirectoryDialog;					// SHELL WHICH REPRESENTS THIS WINDOW
	private String dir_to_select_text; 
	private String dir; 								// the selected directory or file
	private int selectionType; 							// directory or file
	
	/**
	 * Constructor
	 * @param selectionType 0: Directory selection 1: File selection
	 */
	public ShowDirectoryDialog(int selectionType) 
	{
		this.selectionType = selectionType; 
		
		switch (selectionType)
		{
		case 0: 
			dir_to_select_text = "Directory:";
		case 1: 
			dir_to_select_text = "File:";
		}
			
	}
	
	public ShowDirectoryDialog(String dir_to_select_text) 
	{
		this.dir_to_select_text = dir_to_select_text; 
	}
	  
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
		shlDirectoryDialog.open();
		
		/******************************************************************/
		/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
		/******************************************************************/
		shlDirectoryDialog.forceActive();					// SO WINDOW WILL BE FOCUSED WHEN CREATED
		
		/*************************************************/
		/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
		/*************************************************/
		shlDirectoryDialog.layout();
		
		/*******************************/
		/* WHILE SHELL IS NOT DISPOSED */
		/*******************************/
		while (!shlDirectoryDialog.isDisposed()) 
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
	 * Creates the window contents
	 * 
	 * @param shell the parent shell
	 */
	private void createContents(Shell rootShell) 
	/*************************************************/
	/* PRECONDITION:  WINDOW NEEDS ELEMENTS 		 */
	/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW */
	/*************************************************/
	{
		shlDirectoryDialog = new Shell();
		shlDirectoryDialog.setText("File Explorer");
		shlDirectoryDialog.setLayout(new GridLayout(7, false));
		new Label(shlDirectoryDialog, SWT.NONE).setText(dir_to_select_text);
	
		// dir text box 
		final Text text = new Text(shlDirectoryDialog, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		data.minimumWidth = 250; 	// so its long
		text.setLayoutData(data);
	
		// Button to open select directory menu
		Button selectDirButton = new Button(shlDirectoryDialog, SWT.PUSH);
		selectDirButton.setText("Browse...");
		selectDirButton.addSelectionListener(new SelectionAdapter() 
		{
		    public void widgetSelected(SelectionEvent event) 
		    {
		    	if (selectionType == 0) 
		    	{
		    		DirectoryDialog dlg = new DirectoryDialog(shlDirectoryDialog);
		    		
		    		// Set the initial filter path according
			    	// to anything they've selected or typed in
		    		dlg.setFilterPath(text.getText());
		    		
		    		// Change the title bar text
		    		dlg.setText("Select Directory");
		    		
		    		// Customizable message displayed in the dialog
		    		dlg.setMessage("Select a directory");
		    		
		    		// Calling open() will open and run the dialog.
			    	// It will return the selected directory, or
			    	// null if user cancels
		    		dir = dlg.open();
		    	}
		    	else if (selectionType == 1)
		    	{
		    		FileDialog dlg = new FileDialog(shlDirectoryDialog);
		    		
		    		// Set the initial filter path according
			    	// to anything they've selected or typed in
		    		dlg.setFilterPath(text.getText());
		    		
		    		// Change the title bar text
		    		dlg.setText("Select File");
		    		
		    		// Calling open() will open and run the dialog.
			    	// It will return the selected directory, or
			    	// null if user cancels
		    		dir = dlg.open();
		    	}
	
		      	if (dir != null) 
		      	{
		      		// Set the text box to the new selection
		      		text.setText(dir);
		      	}
		    }
		});
	  
		// "Ok" button
		Button okButton = new Button(shlDirectoryDialog, SWT.PUSH);
		okButton.setText("Ok...");
		okButton.addSelectionListener(new SelectionAdapter() 
		{
		    public void widgetSelected(SelectionEvent event) 
		    {
		    	shlDirectoryDialog.dispose(); 
		    }
		});
		
		shlDirectoryDialog.pack();
	  
	}
	
	public String getDir() 
	/************************************************************************************************/
	/* PRECONDITION:  DIRECTORY IS NEEDED						  									*/
	/* POSTCONDITION: RETURNS DIRECTORY										  						*/
	/************************************************************************************************/
	{
		return dir; 
	}

	
	public String getName()
	/************************************************************************************************/
	/* PRECONDITION:  NAME OF DIRECTORY/FILE IS NEEDED						  						*/
	/* POSTCONDITION: RETURNS NAME OF SPECIFIC DIRECTORY/FILE				  						*/
	/************************************************************************************************/
	{
		// last section of directory/file path string
		try 
		{
			return dir.substring(dir.lastIndexOf("\\") + 1, dir.length()); 
		}
		catch (Exception exc)
		{
			return null; 
		}
	}

}

