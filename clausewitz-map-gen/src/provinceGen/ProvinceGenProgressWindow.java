package provinceGen;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import abstractProgramWindow.*;
import org.eclipse.swt.widgets.ProgressBar; 

public class ProvinceGenProgressWindow extends AbstractProgramWindow{

	protected Shell shell;
	private int mapHeight = 2048; 	// 2048 is default
	private ProgressBar progressBar;

	public ProvinceGenProgressWindow()
	{
		
	}
	
	public ProvinceGenProgressWindow(int mapHeight)
	{
		this.mapHeight = mapHeight; 
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProvinceGenProgressWindow window = new ProvinceGenProgressWindow();
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
	
	//public void open(Thread thread) {
	//	open(); 
	//	thread.notify();
	//}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		progressBar = new ProgressBar(shell, SWT.NONE); 
		progressBar.setMaximum(mapHeight);
		progressBar.setBounds(10, 232, 414, 19);
		progressBar.setSelection(0);
	}
	
	public void setMapHeight(int height)
	{
		mapHeight = height; 
		progressBar.setMaximum(height);
	}
	
	public void increaseProgress()
	{
		progressBar.setSelection(progressBar.getSelection() + 1);
	}
	
	public void increaseProgress(int progress)
	{
		progressBar.setSelection(progressBar.getSelection() + progress);
	}
	
	public void setProgress(int progress)
	{
		progressBar.setSelection(progress);
	}
}
