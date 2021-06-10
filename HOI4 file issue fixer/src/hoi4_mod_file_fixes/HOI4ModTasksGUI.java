package hoi4_mod_file_fixes;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class HOI4ModTasksGUI {
	File hoi4_dir; 
	File states_dir; 
	File strat_region_dir; 
	
	//String focus_file_name; 
	
	public HOI4ModTasksGUI(File hoi4_dir, File states_dir, File strat_region_dir) {
		this.hoi4_dir = hoi4_dir; 
		this.states_dir = states_dir; 
		this.strat_region_dir = strat_region_dir; 
	}
	
	// runs the application
	public void run() {
		Display.getDefault().syncExec(new Runnable() {
		    public void run() {
		    	Shell shell = new Shell(Display.getDefault());
		    	shell.setText("HOI4 Utilities");
		    	createContents(shell, Display.getDefault());
		    	shell.pack();
		    	shell.open();
		    	while (!shell.isDisposed()) {
					if (!Display.getDefault().readAndDispatch()) {
						Display.getDefault().sleep();
					}
				}
		    	Display.getDefault().dispose(); 
		    }
		});
	}
	
	private void createContents(final Shell shell, Display display) {
		//File focus_file; 
		
		shell.setLayout(new GridLayout(2, false));
		
		//// in progress tasks (or none)
		//new Label(shell, SWT.NONE).setText("In Progress: ");
		//new Label(shell, SWT.NONE).setText(""); 					// EMPTY SHELL
		//Label tasks_in_progress = new Label(shell, SWT.NONE); 
		//GridData tasks_data = new GridData(GridData.FILL_HORIZONTAL); 
		//tasks_in_progress.setLayoutData(tasks_data); 
		//new Label(shell, SWT.NONE).setText(""); 					// EMPTY SHELL
		//new Label(shell, SWT.NONE).setText(""); 					// EMPTY SHELL
		//new Label(shell, SWT.NONE).setText(""); 					// EMPTY SHELL
		//tasks_in_progress.setText("N/A");
		////tasks_in_progress.
				
		// state files
		new Label(shell, SWT.NONE).setText("Fix Duplicate Provinces in State Files");
		Button FixProvDupStateButton = new Button(shell, SWT.PUSH); 
		FixProvDupStateButton.setText("Fix");
		FixProvDupStateButton.addSelectionListener(new SelectionAdapter() {
		    public void widgetSelected(SelectionEvent event) {
		    	try {
		    		boolean fixedDuplicates; 
		    		fixedDuplicates = FixDupProvinces.RemoveDuplicates(states_dir);
		    		if (fixedDuplicates) {
		    			JOptionPane.showMessageDialog(null, "States fixed.");
		    		}
				} 
		    	catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "States fix failed.");
				} 
		    }
		});
		// strat files
		new Label(shell, SWT.NONE).setText("Fix Duplicate Provinces in Strat Region Files");
		Button FixProvDupStratButton = new Button(shell, SWT.PUSH); 
		FixProvDupStratButton.setText("Fix");
		FixProvDupStratButton.addSelectionListener(new SelectionAdapter() {
		    public void widgetSelected(SelectionEvent event) {
		    	try {
		    		boolean fixedDuplicates; 
		    		fixedDuplicates = FixDupProvinces.RemoveDuplicates(strat_region_dir);
		    		if (fixedDuplicates) {
		    			JOptionPane.showMessageDialog(null, "Strategic regions fixed.");
		    		}
				} 
		    	catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Strategic regions fix failed.");
				} 
		    }
		});
		
		// rudimentary loc for focuses which dont have loc
		new Label(shell, SWT.NONE).setText("Add rudimentary localization for lacking focuses");
		Button addFocusLocButton = new Button(shell, SWT.PUSH); 
		addFocusLocButton.setText("Add");
		addFocusLocButton.addSelectionListener(new SelectionAdapter() {
		    public void widgetSelected(SelectionEvent event) {
		    	ShowFileDialog selectFocusFile = new ShowFileDialog("Select focus:");
		    	selectFocusFile.run(); 
		    	String focus_file_name = selectFocusFile.getFile();
		    	ShowFileDialog selectLocFile = new ShowFileDialog("Select focus loc. file:"); 
		    	selectLocFile.run(); 
		    	String loc_file_name = selectLocFile.getFile(); 
		    	try {
					FixFocus.addFocusLoc(hoi4_dir, new File(focus_file_name), new File(loc_file_name));
				} 	
		    	catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Focus localization failed.");
				} 
		    }
		});
		
		//selectFocusFile.run(); 
		
		// "OK/Done" button
		Button okButton = new Button(shell, SWT.PUSH);
		okButton.setText("Ok...");
		okButton.addSelectionListener(new SelectionAdapter() {
		    public void widgetSelected(SelectionEvent event) {
		    	shell.close(); 
		    }
		});
	}
	
	// add = false, then remove
	//private void editTasksInProgressInfo(Shell shell, Label tsks_prgs, String task, boolean add) {
	//	if (add) {
	//		if (tsks_prgs.getText().equals("N/A")) {
	//			tsks_prgs.setText(task);
	//		}
	//		else {
	//			tsks_prgs.setText(tsks_prgs.getText() + ", " + task); 
	//		}
	//	}
	//	else {
	//		if (tsks_prgs.getText().contains(task)) {
	//			String new_prgs_text = ""; 
	//			// first half (before task)
	//			new_prgs_text += tsks_prgs.getText().substring(0, tsks_prgs.getText().indexOf(task));
	//			// second half (after task)
	//			new_prgs_text += tsks_prgs.getText().substring
	//					(tsks_prgs.getText().indexOf(task) + task.length(), tsks_prgs.getText().length()); 
	//			tsks_prgs.setText(new_prgs_text);
	//			tsks_prgs.requestLayout(); 
	//			shell.redraw(); 
	//		}
	//	}
	//}
}
