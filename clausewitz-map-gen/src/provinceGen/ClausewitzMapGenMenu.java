package provinceGen;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import abstractProgram.abstractProgram.AbstractProgramWindow;

import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem; 

public class ClausewitzMapGenMenu extends AbstractProgramWindow{

	protected Shell shell;
	private int mapHeight = 2048; 		// 2048 is default
	private ProgressBar progressBar;
	@SuppressWarnings("unused")			// is fine for now 
	private ClausewitzMapGenProgram program; 
	private ProvinceGeneration provinceGen; 
	Label progressText; 
	private Button btnNewButton;
	private MenuItem mntmCreateBlankRiver;
	private MenuItem mntmGenerateNewRiver;
	private MenuItem mntmGenerateAdditionalRivers;
	
	public ClausewitzMapGenMenu(ClausewitzMapGenProgram program)
	{
		this.program = program; 
	}
	
	public ClausewitzMapGenMenu(ClausewitzMapGenProgram program, int mapHeight)
	{
		this.mapHeight = mapHeight; 
	}
	
	/****************************
	 * @wbp.parser.entryPoint	*
	 ****************************/
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
		
		/*******************************/
		/* WHILE SHELL IS NOT DISPOSED */
		/*******************************/
		while (!shell.isDisposed()) 
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
	 * Creates the GUI and opens the display. 
	 * @param shell {@link Shell} (window) to display until closed/disposed. 
	 */
	public void open(Shell shell) {
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
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep(); 
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(600, 400);
		shell.setText("SWT Application");
		ClausewitzMapGenMenu menu = this; 
		shell.setLayout(new GridLayout(2, true));
		
		Button btnStart = new Button(shell, SWT.NONE);
		GridData gd_btnStart = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
		gd_btnStart.widthHint = 576;
		btnStart.setLayoutData(gd_btnStart);
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				provinceGen = new ProvinceGeneration(menu); 
				provinceGen.provinceGeneration();
			}
		});
		btnStart.setText("Start");
		
		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 229;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("River Map");
		
		Menu menu_1 = new Menu(btnNewButton);
		btnNewButton.setMenu(menu_1);
		
		mntmCreateBlankRiver = new MenuItem(menu_1, SWT.NONE);
		mntmCreateBlankRiver.setText("Create Blank River Map");
		
		mntmGenerateNewRiver = new MenuItem(menu_1, SWT.NONE);
		mntmGenerateNewRiver.setText("Generate New River Map");
		
		mntmGenerateAdditionalRivers = new MenuItem(menu_1, SWT.NONE);
		mntmGenerateAdditionalRivers.setText("Generate Additional Rivers");
		new Label(shell, SWT.NONE);
		
		progressText = new Label(shell, SWT.NONE);
		progressText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		progressText.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		progressText.setVisible(true);
		new Label(shell, SWT.NONE);
		
		progressBar = new ProgressBar(shell, SWT.NONE); 
		GridData gd_progressBar = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
		gd_progressBar.widthHint = 593;
		progressBar.setLayoutData(gd_progressBar);
		progressBar.setMaximum(mapHeight);
		progressBar.setSelection(0);
	}
	
	public void setMapHeight(int height) {
		mapHeight = height; 
		progressBar.setMaximum(height);
	}
	
	public void increaseProgress() {
		progressBar.setSelection(progressBar.getSelection() + 1);
	}
	
	public void increaseProgress(int progress) {
		progressBar.setSelection(progressBar.getSelection() + progress);
	}
	
	public void setProgress(int progress) {
		progressBar.setSelection(progress);
	}
	
	public void resetProgress() {
		progressBar.setSelection(0);
	} 
	
	public void setProgressMessage(String message) {
		progressText.setText(message);
	}
	
	public void clearProgressMessage() {
		progressText.setText("");
	}
	
	public void setProgressStage(String message, int progressMaximum) {
		resetProgress(); 
		clearProgressMessage(); 
		progressBar.setMaximum(progressMaximum);
		setProgressMessage(message); 
	}
}
