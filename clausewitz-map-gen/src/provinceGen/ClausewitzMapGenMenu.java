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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite; 

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
	private TabFolder tabFolder;
	private TabItem tbtmNewItem;
	private TabItem tbtmNewItem_1;
	private TabItem tbtmNewItem_2;
	private TabItem tbtmNewItem_3;
	private TabItem tbtmNewItem_4;
	private TabItem tbtmNewItem_5;
	private TabItem tbtmNewItem_6;
	private TabItem tbtmNewItem_7;
	private Composite composite;
	private Composite composite_1;
	
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
		
		tabFolder = new TabFolder(shell, SWT.NONE);
		GridData gd_tabFolder = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_tabFolder.heightHint = 275;
		gd_tabFolder.widthHint = 565;
		tabFolder.setLayoutData(gd_tabFolder);
		
		tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("New Item");
		
		composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_1);
		
		Button btnStart = new Button(composite_1, SWT.NONE);
		btnStart.setBounds(0, 250, 565, 25);
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				provinceGen = new ProvinceGeneration(menu); 
				provinceGen.provinceGeneration();
			}
		});
		btnStart.setText("Start");
		
		tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("New Item");
		
		composite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite);
		
		btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(0, 250, 565, 25);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setText("River Map");
		
		Menu menu_1 = new Menu(btnNewButton);
		btnNewButton.setMenu(menu_1);
		
		mntmCreateBlankRiver = new MenuItem(menu_1, SWT.NONE);
		mntmCreateBlankRiver.setText("Create Blank River Map");
		
		mntmGenerateNewRiver = new MenuItem(menu_1, SWT.NONE);
		mntmGenerateNewRiver.setText("Generate New River Map");
		
		mntmGenerateAdditionalRivers = new MenuItem(menu_1, SWT.NONE);
		mntmGenerateAdditionalRivers.setText("Generate Additional Rivers");
		
		tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("New Item");
		
		tbtmNewItem_3 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_3.setText("New Item");
		
		tbtmNewItem_4 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_4.setText("New Item");
		
		tbtmNewItem_5 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_5.setText("New Item");
		
		tbtmNewItem_6 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_6.setText("New Item");
		
		tbtmNewItem_7 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_7.setText("New Item");
		new Label(shell, SWT.NONE);
		
		progressText = new Label(shell, SWT.NONE);
		progressText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		progressText.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		progressText.setVisible(true);
		new Label(shell, SWT.NONE);
		
		progressBar = new ProgressBar(shell, SWT.NONE); 
		GridData gd_progressBar = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_progressBar.widthHint = 593;
		progressBar.setLayoutData(gd_progressBar);
		progressBar.setMaximum(mapHeight);
		progressBar.setSelection(0);
		new Label(shell, SWT.NONE);
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
