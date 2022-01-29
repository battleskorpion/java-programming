package trayprogmulti;

import java.net.InetAddress;
import java.util.*;

import abstractProgram.abstractProgram.AbstractProgramWindow;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

public class TrayProgMulti extends AbstractProgramWindow 
{
	/********************/
	/* VARIABLE SECTION */
	/********************/
	protected Shell shell;
	@SuppressWarnings("unused")
	private ServerProgram program; 
	private List clientList; 							// list of server clients
	private TrayProgServerDaemon serverDaemon;			// PROGRAM SERVER DAEMON
	private static TrayProgMulti window; 				// INSTANCE OF THIS WINDOW (CLASS)  
	public ArrayList<Button> buttons; 
	
//	public static void main(String[] args) 
//	{
//		/********************/
//		/* VARIABLE SECTION */
//		/********************/
//		window = new TrayProgMulti();
//		
//		/*****************************/
//		/* METHOD TO OPEN GUI WINDOW */
//		/*****************************/
//		// TODO: also label try/catch? 
//		try 
//		{
//			window.open();
//		} 
//		
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//	}
	
	public TrayProgMulti(ServerProgram program)
	{
		this.program = program; 
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
	protected void createContents() 
	{
		shell = new Shell();
		shell.setSize(700, 500);
		shell.setText("program"); 
		window = this; 
		shell.setLayout(new GridLayout(2, false));
		
		Button btnStartServer = new Button(shell, SWT.NONE);
		GridData gd_btnStartServer = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_btnStartServer.widthHint = 334;
		gd_btnStartServer.heightHint = 50;
		btnStartServer.setLayoutData(gd_btnStartServer);
		btnStartServer.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				System.out.println("Main program starting server daemon"); 
				serverDaemon = new TrayProgServerDaemon(window); 
			}
		});
		btnStartServer.setText("Start server");
		
		Button btnEndServer = new Button(shell, SWT.NONE);
		GridData gd_btnEndServer = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_btnEndServer.widthHint = 334;
		gd_btnEndServer.heightHint = 67;
		btnEndServer.setLayoutData(gd_btnEndServer);
		
		btnEndServer.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				System.out.println("process ending..."); 
				//serverDaemon.terminate(); 
				//try {
					serverDaemon.interrupt();
					//serverDaemon.join();
				//} 
				//catch (InterruptedException e1) {
				//	e1.prerintStackTrace();
				//}
				System.out.println("process ended."); 
				System.exit(0);
			}
		});
		btnEndServer.setText("End server");
		
		clientList = new List(shell, SWT.BORDER);
		GridData gd_list = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_list.heightHint = 160;
		clientList.setLayoutData(gd_list);
		
		/**
		 * drag & drop (move clients) 
		 * drag source 
		 */
		DragSource clientDragSource = new DragSource(clientList, DND.DROP_MOVE | DND.DROP_COPY);
		clientDragSource.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		clientDragSource.addDragListener(new DragSourceListener() {
			public void dragSetData(DragSourceEvent event) {
				DragSource ds = (DragSource) event.widget;
				List list = (List) ds.getControl();
		        String[] selection = list.getSelection();
		        
		        StringBuffer buffer = new StringBuffer();
		        for (int i = 0, n = selection.length; i < n; i++) {
		        	buffer.append(selection[i]);
		        }

		        event.data = buffer.toString();
			}

			@Override
			public void dragStart(DragSourceEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void dragFinished(DragSourceEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/**
		 * drag & drop (move clients) 	
		 * drop target
		 */
		DropTarget clientDropTarget = new DropTarget(clientList, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
		clientDropTarget.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		clientDropTarget.addDropListener(new DropTargetAdapter() {
			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					event.detail = (event.operations & DND.DROP_COPY) != 0 ? DND.DROP_COPY : DND.DROP_NONE;
			    }

			    // Allow dropping text only
			    for (int i = 0, n = event.dataTypes.length; i < n; i++) {
			    	if (TextTransfer.getInstance().isSupportedType(event.dataTypes[i])) {
			    		event.currentDataType = event.dataTypes[i];
			    	}
			    }
			}

			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
			}
			
			public void drop(DropTargetEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					// Get the dropped data
					DropTarget target = (DropTarget) event.widget;
					List list = (List) target.getControl();
					String data = (String) event.data;
					
			    
					// Create a new item in the table to hold the dropped data
			       // String item = new TableItem(table, SWT.NONE);
					list.add(data, event.y / (list.getItemHeight())); 
			        list.redraw();
				}
			}
		});
		
		Button btnExecuteDefaultOperation = new Button(shell, SWT.NONE);
		btnExecuteDefaultOperation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		btnExecuteDefaultOperation.setText("Execute Default Operation");
		
		Button btnExecuteOpenCommand = new Button(shell, SWT.NONE);
		GridData gd_btnExecuteOpenCommand = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnExecuteOpenCommand.widthHint = 157;
		btnExecuteOpenCommand.setLayoutData(gd_btnExecuteOpenCommand);
		btnExecuteOpenCommand.setText("Execute Open Command");
		
		Button btnExecuteCloseCommand = new Button(shell, SWT.NONE);
		btnExecuteCloseCommand.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnExecuteCloseCommand.setText("Execute Close Command");
		
		buttons = new ArrayList<Button>();
		buttons.add(btnExecuteOpenCommand);
		buttons.add(btnExecuteCloseCommand);
		buttons.add(btnExecuteDefaultOperation);
		
		btnExecuteDefaultOperation.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				executeCommand("default"); 
			}
		});
		
		btnExecuteOpenCommand.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				executeCommand("open"); 
			}
		});
		
		btnExecuteCloseCommand.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				executeCommand("close"); 
			}
		});
		
		Menu menu_0 = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu_0);
		
		MenuItem mntmSettings = new MenuItem(menu_0, SWT.CASCADE);
		mntmSettings.setText("Settings");
		
		Menu menu_1 = new Menu(mntmSettings);
		mntmSettings.setMenu(menu_1);
		
		MenuItem mntmDebug = new MenuItem(menu_1, SWT.CHECK);
		mntmDebug.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO: important
			}
		});
		mntmDebug.setText("Debug");
	}

	public void addClient(InetAddress inetAddress) 
	{
		clientList.add(inetAddress.toString());
	}
	
	public static void enableButtons(ArrayList<Button> buttons, boolean ans)
	{
		if (ans)
		{
			for (Button button: buttons) 
			{
				button.setEnabled(true);
			}
		}
		else
		{
			for (Button button: buttons)
			{
				button.setEnabled(false);
			}
		}
	}
	
	/**
	 * Execution of the given command
	 * @param command
	 */
	private void executeCommand(String command)
	{
		if (serverDaemon == null) {
			return; 
		}
		
		System.out.println(command + "command sending.");
		
		enableButtons(buttons, false);
		
		serverDaemon.setDiskCommand(command);
		serverDaemon.executeDiskCommand();
		
		try 
		{
			new java.util.Timer().schedule(new java.util.TimerTask() 
			{
				@Override
			    public void run() 
				{
					shell.getDisplay().syncExec(new Runnable() 
					{
						@Override
						public void run() 
						{
							enableButtons(buttons, true);
						}
					});
			    }
			}, 
			6000);
		}
		catch(Exception exc)
		{
			exc.printStackTrace(); 
		}
		
		System.out.println(command + "command sent.");
	}
	
}
