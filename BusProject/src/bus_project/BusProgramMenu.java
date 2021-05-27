package bus_project;

import java.io.File;
import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class BusProgramMenu extends AbstractProgramWindow 
{
	//TODO: comment vars
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected Shell shell;
	private ArrayList<Customer> customers = new ArrayList<Customer>(); 

	/**
	 * Launch the application.
	 * @param args
	 */
	// TODO: better comment, as to why this is here
	public static void main(String[] args) 
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		BusProgramMenu window = new BusProgramMenu();
		
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

	/******************************************************************************************************/
	/*											 METHOD SECTION 										  */
	/******************************************************************************************************/
	
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
	
	public void open(Shell shell)
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

	/*************************************************/
	/* PRECONDITION:  WINDOW NEEDS ELEMENTS 		 */
	/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW */
	/*************************************************/
	protected void createContents() 
	{
		/********************/
		/* VARIABLE SECTION */
		/********************/
		
		/***********/
		/* WINDOWS */
		/***********/
		AddCustomer addCustomerWindow = new AddCustomer(customers);
		RemoveCustomer removeCustomerWindow = new RemoveCustomer(customers); 
		ModifyCustomer modifyCustomerWindow = new ModifyCustomer(customers); 
		ListCustomers listCustomersWindow = new ListCustomers(customers); 
		BusesByDate busesByDateWindow = new BusesByDate(customers); 
		ProfitByDate profitByDateWindow = new ProfitByDate(customers); 
		TotalProfitDetails totalProfitWindow = new TotalProfitDetails(customers); 
	
		/*********/
		/* SHELL */
		/*********/
		shell = new Shell();
		shell.addControlListener(new ControlAdapter() 
		{
			// TODO: 
			@Override
			public void controlResized(ControlEvent e)
			{
				
			}
		});
		shell.setSize(700, 540);
		shell.setText(Messages.getString("title.text")); //$NON-NLS-1$
		GridLayout gl_shell = new GridLayout(4, true);
		shell.setLayout(gl_shell);
				
		/***********/
		/* BUTTONS */
		/***********/
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		
		ToolItem tltmLanguage = new ToolItem(toolBar, SWT.DROP_DOWN);
		
		tltmLanguage.setText(Messages.getString("BusProgramMenu.Language.text")); //$NON-NLS-1$
		
		Menu languageMenu = new Menu(toolBar);
		toolBar.setMenu(languageMenu);
		
		MenuItem mntmEnus = new MenuItem(languageMenu, SWT.NONE);
		mntmEnus.setText(Messages.getString("BusProgramMenu.Language.en_US.text")); //$NON-NLS-1$
		mntmEnus.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				BusProgram.languageChanged(0); 
				shell.close(); 
			}
		});
		
		MenuItem mntmEses = new MenuItem(languageMenu, SWT.NONE);
		mntmEses.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				BusProgram.languageChanged(1); 
				shell.close(); 
			}
		});
		mntmEses.setText(Messages.getString("BusProgramMenu.Language.es_ES.text")); //$NON-NLS-1$
		
		MenuItem mntmPortuguesePortugal = new MenuItem(languageMenu, SWT.NONE);
		mntmPortuguesePortugal.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				BusProgram.languageChanged(2); 
				shell.close(); 
			}
		});
		mntmPortuguesePortugal.setText(Messages.getString("BusProgramMenu.Language.pt_PT.text")); //$NON-NLS-1$
		
		tltmLanguage.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				languageMenu.setVisible(true);
			}
		});
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnAddCustomer = new Button(shell, SWT.NONE);
		GridData gd_btnAddCustomer = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			
		Button btnRemoveCustomer = new Button(shell, SWT.NONE);
		GridData gd_btnRemoveCustomer = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		
		Button btnModifyCustomer = new Button(shell, SWT.NONE);
		GridData gd_btnModifyCustomer = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_btnModifyCustomer.widthHint = 122;
		btnModifyCustomer.setLayoutData(gd_btnModifyCustomer);
		btnModifyCustomer.setText(Messages.getString("BusProgramMenu.ModifyCustomer.text")); //$NON-NLS-1$
		btnModifyCustomer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(modifyCustomerWindow, shell);
			}
		});
		
		Button btnBusesNeededByDate = new Button(shell, SWT.NONE);
		GridData gd_btnBusesNeededByDate = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		
		gd_btnBusesNeededByDate.widthHint = 122;
		btnBusesNeededByDate.setLayoutData(gd_btnBusesNeededByDate);
		btnBusesNeededByDate.setText(Messages.getString("BusProgramMenu.BusesByDate.text")); //$NON-NLS-1$
		btnBusesNeededByDate.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) {
				openSubWindow(busesByDateWindow, shell); 
			}
		});
		
		Button btnListCustomersByName = new Button(shell, SWT.NONE);
		GridData gd_btnListCustomersByName = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		
				
		gd_btnListCustomersByName.widthHint = 140;
		btnListCustomersByName.setLayoutData(gd_btnListCustomersByName);
		btnListCustomersByName.setText(Messages.getString("BusProgramMenu.CustomersByName.text")); //$NON-NLS-1$
		btnListCustomersByName.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				listCustomersWindow.setSortByName();
				openSubWindow(listCustomersWindow, shell);
			}
		});
		
		Button btnListCustomersBySize = new Button(shell, SWT.NONE);
		GridData gd_btnListCustomersBySize = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_btnListCustomersBySize.widthHint = 167;
		btnListCustomersBySize.setLayoutData(gd_btnListCustomersBySize);
		btnListCustomersBySize.setText(Messages.getString("BusProgramMenu.CustomersByGroupSize.text")); //$NON-NLS-1$
		btnListCustomersBySize.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				listCustomersWindow.setSortBySize();
				openSubWindow(listCustomersWindow, shell);
			}
		});
		
		Button btnProfitByDate = new Button(shell, SWT.NONE);
		GridData gd_btnProfitByDate = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_btnProfitByDate.widthHint = 168;
		btnProfitByDate.setLayoutData(gd_btnProfitByDate);
		btnProfitByDate.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) {
				openSubWindow(profitByDateWindow, shell); 
			}
		});
		btnProfitByDate.setText(Messages.getString("BusProgramMenu.ProfitByDate.text")); //$NON-NLS-1$
		
		Button btnProfitTotal = new Button(shell, SWT.NONE);
		GridData gd_btnProfitTotal = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_btnProfitTotal.widthHint = 148;
		btnProfitTotal.setLayoutData(gd_btnProfitTotal);
		btnProfitTotal.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) {
				openSubWindow(totalProfitWindow, shell);
			}
		});
		btnProfitTotal.setText(Messages.getString("BusProgramMenu.TotalProfit.text")); //$NON-NLS-1$
		new Label(shell, SWT.NONE);
		
		Button btnQuit = new Button(shell, SWT.NONE);
		GridData gd_btnQuit = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_btnQuit.heightHint = 38;
		gd_btnQuit.widthHint = 335;
		btnQuit.setLayoutData(gd_btnQuit);
		btnQuit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				shell.dispose(); 		// QUIT 
			}
		});
		btnQuit.setText(Messages.getString("btnQuit.text")); //$NON-NLS-1$
		new Label(shell, SWT.NONE);
		
		Image mainImage = new Image(Display.getDefault(), "ski-lift.jpg");	
		Label lblMainImage = new Label(shell, SWT.CENTER);
		GridData gd_lblMainImage = new GridData(SWT.CENTER, SWT.CENTER, true, true, 4, 1);
		gd_lblMainImage.widthHint = 680;
		gd_lblMainImage.heightHint = 360;
		lblMainImage.setLayoutData(gd_lblMainImage);
		lblMainImage.setImage(mainImage);
		gd_btnAddCustomer.widthHint = 122;
		btnAddCustomer.setLayoutData(gd_btnAddCustomer);
		btnAddCustomer.setText(Messages.getString("BusProgramMenu.AddNewCustomer.text")); //$NON-NLS-1$
		btnAddCustomer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(addCustomerWindow, shell);
			}
		});
		gd_btnRemoveCustomer.widthHint = 122;
		btnRemoveCustomer.setLayoutData(gd_btnRemoveCustomer);
		btnRemoveCustomer.setText(Messages.getString("BusProgramMenu.RemoveCustomer.text"));
		shell.setTabList(new Control[]{btnAddCustomer, btnRemoveCustomer, btnModifyCustomer, btnBusesNeededByDate, btnListCustomersByName, btnListCustomersBySize, btnProfitByDate, btnProfitTotal, btnQuit, toolBar});
		btnRemoveCustomer.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(removeCustomerWindow, shell);	
			}
		});
	}
	
	public void close()  
	{
		super.close(shell);
	}
}
