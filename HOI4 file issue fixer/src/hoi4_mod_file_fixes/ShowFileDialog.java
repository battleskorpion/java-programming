package hoi4_mod_file_fixes;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class ShowFileDialog {
	
	private String file_to_select_text; 
	private String file; // the selected directory 
	
	// constructors
	public ShowFileDialog() {
		file_to_select_text = "Directory:";
	}
	
	public ShowFileDialog(String file_to_select_text) {
		this.file_to_select_text = file_to_select_text; 
	}
	
	/**
	 * Runs the application
	 */
	public void run() {
		Display.getDefault().syncExec(new Runnable() {
		    public void run() {
		    	Shell shell = new Shell(Display.getDefault());
				shell.setText("File Explorer");
				createContents(shell);
				shell.pack();
				shell.open();
				while (!shell.isDisposed()) {
					if (!Display.getDefault().readAndDispatch()) {
						Display.getDefault().sleep();
					}
				}
				// unnecessary ig
				//shell.close(); 
		    }
		});
	}
	
	/**
	 * Runs the application (using current Display)
	 * @deprecated
	 */
	public void run(Display display) {
		//this.display = display; 
		Shell shell = new Shell(display);
		shell.setText("File Explorer");
		createContents(shell);
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose(); 
	}

	/**
	 * Creates the window contents
	 * 
	 * @param shell the parent shell
	 */
	private void createContents(final Shell shell) {
		shell.setLayout(new GridLayout(7, false));
		new Label(shell, SWT.NONE).setText(file_to_select_text);
	
		// dir text box 
		final Text text = new Text(shell, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		data.minimumWidth = 250; // so its long
		text.setLayoutData(data);
	
		// Button to open select file menu
		Button selectFileButton = new Button(shell, SWT.PUSH);
		selectFileButton.setText("Browse...");
		selectFileButton.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent event) {
	    	FileDialog dlg = new FileDialog(shell);
	
	    	// Set the initial filter path according
	    	// to anything they've selected or typed in
	    	dlg.setFilterPath(text.getText());
	    	
	    	// Change the title bar text
	    	dlg.setText("Select File");
	
	    	// Customizable message displayed in the dialog
	    	//dlg.setMessage("Select a file");
	
	    	// Calling open() will open and run the dialog.
	    	// It will return the selected directory, or
	    	// null if user cancels
	    	file = dlg.open();
	      		if (file != null) {
	      			// Set the text box to the new selection
	      			text.setText(file);
	      		}
	    	}
		});
	  
		// "Ok" button
		Button okButton = new Button(shell, SWT.PUSH);
		okButton.setText("Ok...");
		okButton.addSelectionListener(new SelectionAdapter() {
		    public void widgetSelected(SelectionEvent event) {
		    	shell.dispose(); 
		    }
		});
	  
	  
	}
	
	// returns selected directory; 
	public String getFile() {
		return file; 
	}

	/**
	 * The application entry point
	 * 
	 * for testing purposes
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new ShowFileDialog().run();
	}
}