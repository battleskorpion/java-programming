package hoi4_mod_file_fixes;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
* This class demonstrates the DirectoryDialog class
*/
public class ShowDirectoryDialog {
	
	private String dir_to_select_text; 
	
	private String dir; // the selected directory 
	
	// constructors
	public ShowDirectoryDialog() {
		dir_to_select_text = "Directory:";
	}
	
	public ShowDirectoryDialog(String dir_to_select_text) {
		this.dir_to_select_text = dir_to_select_text; 
	}
	
	/**
	 * Runs the application
	 */
	public void run() {
	  Display display = new Display();
	  Shell shell = new Shell(display);
	  shell.setText("Directory Browser");
	  createContents(shell);
	  shell.pack();
	  shell.open();
	  while (!shell.isDisposed()) {
	    if (!display.readAndDispatch()) {
	      display.sleep();
	    }
	  }
	}

	/**
	 * Creates the window contents
	 * 
	 * @param shell the parent shell
	 */
	private void createContents(final Shell shell) {
		shell.setLayout(new GridLayout(7, false));
		new Label(shell, SWT.NONE).setText(dir_to_select_text);
	
		// dir text box 
		final Text text = new Text(shell, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		data.minimumWidth = 250; // so its long
		text.setLayoutData(data);
	
		// Button to open select directory menu
		Button selectDirButton = new Button(shell, SWT.PUSH);
		selectDirButton.setText("Browse...");
		selectDirButton.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent event) {
	    	DirectoryDialog dlg = new DirectoryDialog(shell);
	
	    	// Set the initial filter path according
	    	// to anything they've selected or typed in
	    	// TODO: make this better (find mod folder maybe?) 
	    	dlg.setFilterPath(text.getText());
	
	    	// Change the title bar text
	    	dlg.setText("Select Directory");
	
	    	// Customizable message displayed in the dialog
	    	dlg.setMessage("Select a directory");
	
	    	// Calling open() will open and run the dialog.
	    	// It will return the selected directory, or
	    	// null if user cancels
	    	dir = dlg.open();
	      		if (dir != null) {
	      			// Set the text box to the new selection
	      			text.setText(dir);
	      		}
	    	}
		});
	  
		// "Ok" button
		Button okButton = new Button(shell, SWT.PUSH);
		okButton.setText("Ok...");
		okButton.addSelectionListener(new SelectionAdapter() {
		    public void widgetSelected(SelectionEvent event) {
		    	shell.close(); 
		    }
		});
	  
	  
	}
	
	// returns selected directory; 
	public String getDir() {
		return dir; 
	}

	/**
	 * The application entry point
	 * 
	 * for testing purposes
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new ShowDirectoryDialog().run();
	}
}

