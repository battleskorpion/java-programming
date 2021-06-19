/********************************************/
/* INFORMATION SECTION 						*/
/* BusProgramMenu.java						*/
/* Darian Siembab 							*/
/* 											*/
/* WINDOW CLASS FOR DISPLAYING THE PROGRAM	*/
/* MAIN MENU								*/
/********************************************/ 

package bus_project;

/******************/
/* IMPORT SECTION */
/******************/
import fileIO.*; 										// FOR FILE READING/WRITING
import java.util.ArrayList;								// RESIZABLE-ARRAY IMPLEMENTATION OF THE LIST
														// INTERFACE
import javax.swing.JOptionPane;							// JOPTIONPANE WINDOW DIALOGS
import org.eclipse.swt.SWT;								// THIS CLASS PROVIDES ACCESS TO A SMALL 
														// NUMBER OF SWT SYSTEM-WIDE METHODS, AND
														// IN ADDITION DEFINES THE PUBLIC CONSTANTS 
														// PROVIDED BY SWT. 
import org.eclipse.swt.widgets.Display;					// RESPONSIBLE FOR MANAGING THE CONNECTION 
														// BETWEEN SWT AND THE UNDERLYING OPERATING
														// SYSTEM.
import org.eclipse.swt.widgets.Shell;					// REPRESENTS THE "WINDOWS" WHICH THE DESKTOP
														// OR "WINDOW MANAGER" IS MANAGING. 
import org.eclipse.swt.widgets.Button;					// REPRESENTS A SELECTABLE USER INTERFACE 
														// OBJECT THAT ISSUES NOTIFICATION WHEN 
														// PRESSED AND RELEASED. 
import org.eclipse.swt.widgets.Composite;				// CONTROL CAPABLE OF CONTAINING OTHER 
														// CONTROLS
import org.eclipse.swt.widgets.Control;					// CONTROL IS THE ABSTRACT SUPERCLASS OF 
														// ALL WINDOWED USER INTERFACE CLASSES. 
														// (REPRESENTS CONTROL EVENTS).
import org.eclipse.swt.widgets.Label;					// REPRESENTS A NON-SELECTABLE USER INTERFACE 
														// OBJECT THAT DISPLAYS A STRING OR IMAGE 
														// (OR HORIZONTAL/VERTICAL LINE).
import org.eclipse.swt.widgets.Menu;					// FOR MENU DROPDOWN WINDOW.
import org.eclipse.swt.widgets.MenuItem;				// SELECTABLE ITEM OF MENU WINDOW.
import org.eclipse.swt.widgets.ToolBar;					// SUPPORTS THE LAYOUT OF SELECTABLE 
														// TOOL BAR ITEMS. 
import org.eclipse.swt.widgets.ToolItem;				// REPRESENTS A SELECTABLE USER INTERFACE 
														// OBJECT THAT REPRESENTS A BUTTON 
														// IN A TOOL BAR. 
import org.eclipse.swt.events.SelectionAdapter;			// THIS ADAPTER CLASS PROVIDES DEFAULT 
														// IMPLEMENTATIONS FOR THE METHODS DESCRIBED
														// BY THE SELECTIONLISTENER INTERFACE. 
import org.eclipse.swt.events.SelectionEvent;			// SELECTION EVENTS ARE SENT AS A RESULT OF 
														// WIDGETS BEING SELECTED. 
import org.eclipse.swt.graphics.Image;					// REPRESENTS IMAGES (SO THEY MAY BE
														// DISPLAYED IN A WINDOW).
import org.eclipse.swt.layout.GridLayout;				// LAYS OUT THE CONTROL CHILDREN OF A 
														// COMPOSITE IN A GRID FORMAT 
														// (ALIGNS WINDOW ELEMENTS IN A 
														// GRID FORMAT).
import org.eclipse.swt.layout.GridData;					// GRIDDATA IS THE LAYOUT DATA OBJECT
														// ASSOCIATED WITH GRIDLAYOUT.
import org.eclipse.swt.browser.Browser;					// INSTANCES OF THIS CLASS ARE SENT AS A 
														// RESULT OF CONTROLS BEING MOVED OR RESIZED.
import org.eclipse.swt.custom.StackLayout;

public class BusProgramMenu extends AbstractBusProgramWindow 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	private ArrayList<Customer> customers; 				// LIST OF CUSTOMERS
	protected Shell shell;								// SHELL WHICH REPRESENTS THIS WINDOW
	
	/***********************/
	/* CONSTRUCTOR SECTION */
	/***********************/ 
	public BusProgramMenu() 
	{
		customers = new ArrayList<Customer>(); 	
		new BusFinances(); 
		new BusCalculation(); 
	}

	/************************************************************************************************/
	/*											 METHOD SECTION 									*/
	/************************************************************************************************/
	
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

	protected void createContents() 
	/*************************************************/
	/* PRECONDITION:  WINDOW NEEDS ELEMENTS 		 */
	/* POSTCONDITION: CREATES CONTENTS OF THE WINDOW */
	/*************************************************/
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
		FileInfo fileWindow = new FileInfo(customers); 
		ShowDirectoryDialog fileDialogWindow = new ShowDirectoryDialog(1); 
	
		/*********/
		/* SHELL */
		/*********/
		shell = new Shell();
		shell.setSize(700, 540);
		shell.setText(Messages.getString("title.text")); 								//$NON-NLS-1$
		GridLayout gl_shell = new GridLayout(4, true);
		shell.setLayout(gl_shell);
				
		/***********/
		/* BUTTONS */
		/***********/
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		
		ToolItem tltmLanguage = new ToolItem(toolBar, SWT.DROP_DOWN);
		
		tltmLanguage.setText(Messages.getString("BusProgramMenu.Language.text")); 		//$NON-NLS-1$
		
		Menu languageMenu = new Menu(toolBar);
		toolBar.setMenu(languageMenu);
		
		MenuItem mntmEnus = new MenuItem(languageMenu, SWT.NONE);
		mntmEnus.setText(Messages.getString("BusProgramMenu.Language.en_US.text")); 	//$NON-NLS-1$
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
		mntmEses.setText(Messages.getString("BusProgramMenu.Language.es_ES.text")); 	//$NON-NLS-1$
		
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
		mntmPortuguesePortugal.setText(Messages.getString
				("BusProgramMenu.Language.pt_PT.text"));								//$NON-NLS-1$
		
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
		btnModifyCustomer.setText(Messages.getString
				("BusProgramMenu.ModifyCustomer.text")); 								//$NON-NLS-1$
		btnModifyCustomer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				/************************************************************/
				/* ONLY OPEN MODIFY WINDOW IF THERE ARE CUSTOMERS TO MODIFY */ 
				/************************************************************/
				if (customers.size() > 0)
				{
					openSubWindow(modifyCustomerWindow, shell);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "There are no customers to modify. "
							+ "Open \"Add Customer\" to add customers.");
				}
			}
		});
		
		Button btnBusesNeededByDate = new Button(shell, SWT.NONE);
		GridData gd_btnBusesNeededByDate = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		
		gd_btnBusesNeededByDate.widthHint = 122;
		btnBusesNeededByDate.setLayoutData(gd_btnBusesNeededByDate);
		btnBusesNeededByDate.setText(Messages.getString
				("BusProgramMenu.BusesByDate.text")); 									//$NON-NLS-1$
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
		btnListCustomersByName.setText(Messages.getString
				("BusProgramMenu.CustomersByName.text")); 								//$NON-NLS-1$
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
		btnListCustomersBySize.setText(Messages.getString
				("BusProgramMenu.CustomersByGroupSize.text")); 							//$NON-NLS-1$
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
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(profitByDateWindow, shell); 
			}
		});
		btnProfitByDate.setText(Messages.getString
				("BusProgramMenu.ProfitByDate.text")); 									//$NON-NLS-1$
		
		Button btnProfitTotal = new Button(shell, SWT.NONE);
		GridData gd_btnProfitTotal = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_btnProfitTotal.widthHint = 148;
		btnProfitTotal.setLayoutData(gd_btnProfitTotal);
		btnProfitTotal.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(totalProfitWindow, shell);
			}
		});
		btnProfitTotal.setText(Messages.getString
				("BusProgramMenu.TotalProfit.text"));
		new Label(shell, SWT.NONE);
		
		Button btnQuit = new Button(shell, SWT.NONE);
		GridData gd_btnQuit = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_btnQuit.heightHint = 38;
		gd_btnQuit.widthHint = 335;
		btnQuit.setLayoutData(gd_btnQuit);
		btnQuit.setText(Messages.getString("btnQuit.text")); 							//$NON-NLS-1$
		new Label(shell, SWT.NONE);
		
		/***************/
		/* IMAGE/VIDEO */
		/***************/
		Composite mediaComposite = new Composite(shell, SWT.NONE);
		mediaComposite.setLayout(new GridLayout(1, false));
		
		StackLayout layout = new StackLayout();
		mediaComposite.setLayout(layout); 
		
	    // video composite
	    Composite videoComposite = new Composite(mediaComposite, SWT.NONE);
	    videoComposite.setLayout(new GridLayout());
	    Browser videoBrowser = new Browser(videoComposite, SWT.NONE);
	    videoBrowser.setUrl("https://www.youtube.com/embed/VRnk5gx2t1w"
				+ "?autoplay=1&amp;html5=1%20frameborder=%220%22%20allowfullscreen"); 
	    
	    // image composite
	    Composite imageComposite = new Composite(mediaComposite, SWT.NONE);
	    imageComposite.setLayout(new GridLayout());
		Image skiImage = new Image(Display.getDefault(), "ski-lift.jpg");	
		Label lblMainImage = new Label(imageComposite, SWT.CENTER);
		lblMainImage.setImage(skiImage);
		
		GridData gd_lblMainImage = new GridData(SWT.CENTER, SWT.CENTER, true, true, 4, 1);
		gd_lblMainImage.widthHint = 1280;
		gd_lblMainImage.heightHint = 960;
		videoBrowser.setLayoutData(gd_lblMainImage);
		lblMainImage.setLayoutData(gd_lblMainImage);
		
		GridData gd_mediaComposite = new GridData(SWT.CENTER, SWT.CENTER, true, true, 4, 1);
		gd_lblMainImage.widthHint = 1280;
		gd_lblMainImage.heightHint = 960;
		mediaComposite.setLayoutData(gd_mediaComposite);
		
		/**************************************************************************/
		/* IF STATEMENT TO DISPLAY IMAGE OR VIDEO COMPOSITE DEPENDING ON SETTINGS */
		/**************************************************************************/
		if (Settings.skiVideoEnabled == true) 
		{
			layout.topControl = videoComposite; 
			mediaComposite.layout();
		}
		else 
		{
			layout.topControl = imageComposite; 
			mediaComposite.layout();
		}
		
		gd_btnAddCustomer.widthHint = 122;
		btnAddCustomer.setLayoutData(gd_btnAddCustomer);
		btnAddCustomer.setText(Messages.getString
				("BusProgramMenu.AddNewCustomer.text")); 								//$NON-NLS-1$
		btnAddCustomer.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(addCustomerWindow, shell);
			}
		});
		gd_btnRemoveCustomer.widthHint = 122;
		btnRemoveCustomer.setLayoutData(gd_btnRemoveCustomer);
		btnRemoveCustomer.setText(Messages.getString
				("BusProgramMenu.RemoveCustomer.text"));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText(Messages.getString("mntmFile.text")); 							//$NON-NLS-1$
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmNew = new MenuItem(menu_1, SWT.NONE);
		mntmNew.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				
			}
		});
		mntmNew.setText("New...");
		
		MenuItem mntmLoad = new MenuItem(menu_1, SWT.NONE);
		mntmLoad.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				/********************/
				/* VARIABLE SECTION */
				/********************/
				int confirmLoad; 				// 0: 	YES
												// 1: 	NO
												// 2:	CANCEL
				if (customers.size() > 0) 
				{
					confirmLoad = JOptionPane.showConfirmDialog
							(null, "Warning: loading a file will reset program data and delete "
									+ "any unsaved data. Continue?"); 
				}
				else 
				{
					confirmLoad = 0; 
				}
				
				if (confirmLoad == 0) 
				{
					openSubWindow(fileDialogWindow, shell); 
					if (fileDialogWindow.getDir() != null)
					{
						fileWindow.setFileNameText(fileDialogWindow.getName()); 
						fileWindow.setFilePath(fileDialogWindow.getDir()); 
						openSubWindow(fileWindow, shell); 
					}
					else 
					{
						// no file selected
					}
				}
				else 
				{
					return; 
				}
			}
		});
		
		mntmLoad.setText("Load...");
		
		MenuItem mntmSave = new MenuItem(menu_1, SWT.NONE);
		mntmSave.setText("Save");
		
		MenuItem mntmSaveAs = new MenuItem(menu_1, SWT.NONE);
		mntmSaveAs.setText("Save as...");
		
		MenuItem mntmSettings = new MenuItem(menu, SWT.CASCADE);
		mntmSettings.setText("Settings");
		
		Menu menu_2 = new Menu(mntmSettings);
		mntmSettings.setMenu(menu_2);
		
		MenuItem mntmSkiVideo = new MenuItem(menu_2, SWT.CHECK);
		mntmSkiVideo.setText("Ski Video");
		mntmSkiVideo.setSelection(Settings.skiVideoEnabled);
		
		MenuItem mntmHelp = new MenuItem(menu, SWT.NONE);
		mntmHelp.setText(Messages.getString("mntmHelp.text")); 							//$NON-NLS-1$
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		shell.setTabList(new Control[]{btnAddCustomer, btnRemoveCustomer, btnModifyCustomer, 
				btnBusesNeededByDate, btnListCustomersByName, btnListCustomersBySize, 
				btnProfitByDate, btnProfitTotal, btnQuit, toolBar});
		
		/**************************/
		/* EVENT LISTENER SECTION */
		/**************************/
		mntmSkiVideo.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				Settings.skiVideoEnabled = mntmSkiVideo.getSelection();  
				
				/**************************************************************************/
				/* IF STATEMENT TO DISPLAY IMAGE OR VIDEO COMPOSITE DEPENDING ON SETTINGS */
				/**************************************************************************/
				if (Settings.skiVideoEnabled == true) 
				{
					layout.topControl = videoComposite; 
					videoBrowser.refresh();
					mediaComposite.layout();
				}
				else 
				{
					layout.topControl = imageComposite; 
					mediaComposite.layout();
				}
			}
		});
		
		btnRemoveCustomer.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				openSubWindow(removeCustomerWindow, shell);	
			}
		});
		
		btnQuit.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				shell.dispose(); 		// QUIT 
			}
		});
	}
	
	/*************************************************************************/
	/* PRECONDITION:  WINDOW NEEDS TO BE CLOSED								 */
	/* POSTCONDITION: CALLS CLOSE FUNCTION OF SUPERCLASS TO CLOSE THE WINDOW */
	/*************************************************************************/
	public void close()  
	{
		super.close(shell);
	}
}
