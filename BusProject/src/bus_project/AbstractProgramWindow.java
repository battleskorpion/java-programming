package bus_project;

import org.eclipse.swt.widgets.Shell;

public abstract class AbstractProgramWindow {
	
	protected Shell shell;

	/**
	 * Open the window.
	 */
	public abstract void open(); 

	/**
	* Create contents of the window.
	*/
	protected abstract void createContents(); 
	 
}
